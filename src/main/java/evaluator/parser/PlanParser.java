package evaluator.parser;

import evaluator.node.Plan;
import evaluator.node.expression.Expression;
import evaluator.node.expression.Identifier;
import evaluator.node.expression.IntLit;
import evaluator.node.statement.*;
import evaluator.node.statement.command.*;
import evaluator.tokenizer.PlanTokenizer;
import evaluator.tokenizer.Tokenizer;
import exeption.SyntaxErrorException;
import exeption.UnknownTokenException;
import extra.Direction;

import java.util.NoSuchElementException;

public class PlanParser implements Parser {

    private final Tokenizer tokenizer;

    public PlanParser() {
        tokenizer = new PlanTokenizer();
    }

    @Override
    public Plan parse(String src) throws SyntaxErrorException {
        tokenizer.updateSource(src);
        Plan plan = parsePlan();
        if(tokenizer.hasNextToken()) throw new SyntaxErrorException("leftover token");
        return plan;
    }

    private Plan parsePlan() throws SyntaxErrorException {
        Plan plan = new Plan();
        while(tokenizer.hasNextToken()){
            plan.append(parseStatement());
        }
        return plan;
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
            tokenizer.consume("downright");
            return Direction.DOWNRIGHT;
        } else {
            throw new SyntaxErrorException("Line " + tokenizer.getCurrentLine() + ": the direction is missing");
        }
    }

    private Expression parseExpression() throws SyntaxErrorException {
        Expression expr = parseTerm();
        try {
            while(tokenizer.peek("+") || tokenizer.peek("-")) {
                String operator = tokenizer.consume();
                switch (operator) {
                    case "+" -> expr = new evaluator.node.expression.BinaryArithExpr(expr, "+", parseTerm());
                    case "-" -> expr = new evaluator.node.expression.BinaryArithExpr(expr, "-", parseTerm());
                }
            }
        } catch (NoSuchElementException | UnknownTokenException e){
            throw new exeption.SyntaxErrorException(e.getMessage());
        }
        return expr;
    }

    private Expression parseTerm() throws SyntaxErrorException {
        Expression expr = parseFactor();
        try{
            while(tokenizer.peek("*") || tokenizer.peek("/") || tokenizer.peek("%")){
                String operator = tokenizer.consume();
                switch (operator) {
                    case "*" -> expr = new evaluator.node.expression.BinaryArithExpr(expr, "*", parseFactor());
                    case "/" -> expr = new evaluator.node.expression.BinaryArithExpr(expr, "/", parseFactor());
                    case "%" -> expr = new evaluator.node.expression.BinaryArithExpr(expr, "%", parseFactor());
                }
            }
        } catch (NoSuchElementException | UnknownTokenException e){
            throw new exeption.SyntaxErrorException(e.getMessage());
        }
        return expr;
    }

    private Expression parseFactor() throws SyntaxErrorException {
        Expression expr = parsePower();
        if(tokenizer.peek("^")){
            tokenizer.consume();
            expr = new evaluator.node.expression.BinaryArithExpr(expr, "^", parseFactor());
        }
        return expr;
    }

    private Expression parsePower() throws SyntaxErrorException {
        try {
            if (isNumber(tokenizer.peek())) {
                return new IntLit(Integer.parseInt(tokenizer.consume()));
            } else if(isVariable(tokenizer.peek())) {
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
}
