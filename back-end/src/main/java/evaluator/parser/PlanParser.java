package evaluator.parser;

import evaluator.node.Plan;
import evaluator.node.expression.Expression;
import evaluator.node.statement.*;
import evaluator.node.statement.command.*;
import evaluator.tokenizer.PlanTokenizer;
import evaluator.tokenizer.Tokenizer;
import exeption.SyntaxErrorException;
import extra.Direction;

import java.util.LinkedList;

public class PlanParser implements Parser {

    private final Tokenizer tokenizer;
    private final ExprParser exprParser;

    public PlanParser(Tokenizer tokenizer) {
        this.tokenizer = tokenizer;
        exprParser = new ExprParser(tokenizer);
    }

    @Override
    public Plan parse() throws SyntaxErrorException {
        Plan plan = parsePlan();
        if(tokenizer.hasNextToken()) throw new SyntaxErrorException("leftover token");
        return plan;
    }

    private Plan parsePlan() throws SyntaxErrorException {
        LinkedList<Statement> statements = new LinkedList<>();
        while(tokenizer.hasNextToken()){
            statements.push(parseStatement());
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
            return new RegionCommand(RegionCommand.RegionCommandType.COLLECT, exprParser.parse());
        } else if(tokenizer.peek("invest")) {
            tokenizer.consume();
            return new RegionCommand(RegionCommand.RegionCommandType.INVEST, exprParser.parse());
        } else if(tokenizer.peek("shoot")) {
            tokenizer.consume();
            return new AttackCommand(parseDirection(), exprParser.parse());
        } else {
            String identifier = tokenizer.consume();
            tokenizer.consume("=");
            Expression value = exprParser.parse();
            return new AssignStatement(identifier, value);
        }
    }

    private Statement parseIfStatement() throws SyntaxErrorException {
        tokenizer.consume("if");
        tokenizer.consume("(");
        Expression condition = exprParser.parse();
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
        Expression condition = exprParser.parse();
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
}
