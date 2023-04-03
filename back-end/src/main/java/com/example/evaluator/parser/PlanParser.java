package com.example.evaluator.parser;

import com.example.evaluator.node.Plan;
import com.example.evaluator.node.expression.Expression;
import com.example.evaluator.node.expression.Identifier;
import com.example.evaluator.node.expression.Info;
import com.example.evaluator.node.expression.IntLit;
import com.example.evaluator.node.statement.*;
import com.example.evaluator.node.statement.command.*;
import com.example.evaluator.tokenizer.Tokenizer;
import com.example.exeption.SyntaxErrorException;
import com.example.exeption.UnknownTokenException;
import com.example.extra.Direction;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.NoSuchElementException;

public class PlanParser implements Parser {

    private final Tokenizer tokenizer;

    public PlanParser(Tokenizer tokenizer) {
        this.tokenizer = tokenizer;
    }

    @Override
    public Plan parse() throws SyntaxErrorException {
        Plan plan = parsePlan();
        if(tokenizer.hasNextToken()) throw new SyntaxErrorException("leftover token");
        System.out.println("-----------------");
        return plan;
    }

    public Expression parseExprForTest() throws SyntaxErrorException {
        Expression expr = parseExpression();
        if(tokenizer.hasNextToken()) throw new SyntaxErrorException("leftover token");
        return expr;
    }

    private Plan parsePlan() throws SyntaxErrorException {
        ArrayList<Statement> statements = new ArrayList<>();
        while(tokenizer.hasNextToken()){
            statements.add(parseStatement());
        }
        return new Plan(statements);
    }

    private Statement parseStatement() throws SyntaxErrorException{
        if (tokenizer.peek("if")){
            return parseIfStatement();
        } else if (tokenizer.peek("while")){
            return parseWhileStatement();
        } else if (tokenizer.peek("{")){
            return parseBlockStatement();
        } else if (tokenizer.peek("done")){
            tokenizer.consume();
            return new DoneCommand();
        } else if(tokenizer.peek("relocate")){
            tokenizer.consume();
            return new RelocateCommand();
        } else if(tokenizer.peek("move")){
            tokenizer.consume();
            return new MoveCommand(parseDirection());
        } else if(tokenizer.peek("collect")) {
            tokenizer.consume();
            return new RegionCommand(RegionCommand.RegionCommandType.COLLECT, parseExpression());
        } else if(tokenizer.peek("invest")) {
            tokenizer.consume();
            return new RegionCommand(RegionCommand.RegionCommandType.INVEST, parseExpression());
        } else if(tokenizer.peek("shoot")) {
            tokenizer.consume();
            return new AttackCommand(parseDirection(), parseExpression());
        } else {
            if(isReservedWords(tokenizer.peek())) {
                throw new SyntaxErrorException("\"" + tokenizer.peek() + "\" is reserved word.", tokenizer.getCurrentLine());
            }
            String identifier = tokenizer.consume();
            tokenizer.consume("=");
            Expression value = parseExpression();
            return new AssignStatement(identifier, value);
        }
    }

    private Statement parseIfStatement() throws SyntaxErrorException {
        tokenizer.consume("if");
        tokenizer.consume("(");
        Expression condition = parseExpression();
        tokenizer.consume(")");
        tokenizer.consume("then");
        Statement s1 = parseStatement();
        tokenizer.consume("else");
        Statement s2 = parseStatement();
        return new IfStatement(condition, s1, s2);
    }

    private Statement parseWhileStatement() throws SyntaxErrorException {
        tokenizer.consume("while");
        tokenizer.consume("(");
        Expression condition = parseExpression();
        tokenizer.consume(")");
        Statement statement = parseStatement();
        return new WhileStatement(condition, statement);
    }

    private Statement parseBlockStatement() throws SyntaxErrorException {
        BlockStatement block = new BlockStatement();
        tokenizer.consume("{");
        while(!tokenizer.peek("}")){
            block.append(parseStatement());
        }
        tokenizer.consume("}");
        return block;
    }

    private Direction parseDirection() throws SyntaxErrorException {
        if(tokenizer.peek("up")){
            tokenizer.consume();
            return Direction.UP;
        } else if(tokenizer.peek("down")) {
            tokenizer.consume();
            return Direction.DOWN;
        } else if(tokenizer.peek("upleft")) {
            tokenizer.consume();
            return Direction.UPLEFT;
        } else if(tokenizer.peek("upright")) {
            tokenizer.consume();
            return Direction.UPRIGHT;
        } else if(tokenizer.peek("downleft")) {
            tokenizer.consume();
            return Direction.DOWNLEFT;
        } else if (tokenizer.peek("downright")){
            tokenizer.consume();
            return Direction.DOWNRIGHT;
        } else {
            throw new SyntaxErrorException("the direction is missing", tokenizer.getCurrentLine());
        }
    }

    private Expression parseExpression() throws SyntaxErrorException {
        Expression expr = parseTerm();
        try {
            while(tokenizer.peek("+") || tokenizer.peek("-")) {
                String operator = tokenizer.consume();
                switch (operator) {
                    case "+" -> expr = new com.example.evaluator.node.expression.BinaryArithExpr(expr, "+", parseTerm());
                    case "-" -> expr = new com.example.evaluator.node.expression.BinaryArithExpr(expr, "-", parseTerm());
                }
            }
        } catch (NoSuchElementException | UnknownTokenException e){
            throw new com.example.exeption.SyntaxErrorException(e.getMessage());
        }
        return expr;
    }

    private Expression parseTerm() throws SyntaxErrorException {
        Expression expr = parseFactor();
        try{
            while(tokenizer.peek("*") || tokenizer.peek("/") || tokenizer.peek("%")){
                String operator = tokenizer.consume();
                switch (operator) {
                    case "*" -> expr = new com.example.evaluator.node.expression.BinaryArithExpr(expr, "*", parseFactor());
                    case "/" -> expr = new com.example.evaluator.node.expression.BinaryArithExpr(expr, "/", parseFactor());
                    case "%" -> expr = new com.example.evaluator.node.expression.BinaryArithExpr(expr, "%", parseFactor());
                }
            }
        } catch (NoSuchElementException | UnknownTokenException e){
            throw new com.example.exeption.SyntaxErrorException(e.getMessage());
        }
        return expr;
    }

    private Expression parseFactor() throws SyntaxErrorException {
        Expression expr = parsePower();
        if(tokenizer.peek("^")){
            tokenizer.consume();
            expr = new com.example.evaluator.node.expression.BinaryArithExpr(expr, "^", parseFactor());
        }
        return expr;
    }

    private Expression parsePower() throws SyntaxErrorException {
        try {
            if (isNumber(tokenizer.peek())) {
                return new IntLit(Integer.parseInt(tokenizer.consume()));
            } else if(tokenizer.peek("nearby")) {
                tokenizer.consume();
                return new Info(Info.InformationType.NEARBY, parseDirection());
            } else if(tokenizer.peek("opponent")) {
                tokenizer.consume();
                return new Info(Info.InformationType.OPPONENT, null);
            }else if(isVariable(tokenizer.peek())) {
                if(isReservedWords(tokenizer.peek())){
                    throw new SyntaxErrorException("\""+ tokenizer.peek() + "\" is reserved word.", tokenizer.getCurrentLine());
                }
                return new Identifier(tokenizer.consume());
            } else {
                tokenizer.consume("(");
                Expression expr = parseExpression();
                tokenizer.consume(")");
                return expr;
            }
        }catch (NoSuchElementException | UnknownTokenException e) {
            throw new SyntaxErrorException(e.getMessage());
        }
    }

    private boolean isNumber(String s){
        return s.matches("\\d+");
    }

    private boolean isVariable(String s){
        return s.matches("[A-Za-z]+\\d*");
    }

    private boolean isReservedWords(String s){
        String[] reserve = {"collect", "done", "down", "downleft", "downright", "else", "if", "invest", "move", "nearby", "opponent", "relocate", "shoot", "then", "up", "upleft", "upright", "while"};
        for(String word: reserve) {
            if(s.equals(word)) return true;
        }
        return false;
    }
}
