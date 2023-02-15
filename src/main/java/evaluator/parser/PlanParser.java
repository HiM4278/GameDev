package evaluator.parser;

import evaluator.node.expression.Expression;
import evaluator.node.expression.Identifier;
import evaluator.node.expression.IntLit;
import evaluator.tokenizer.PlanTokenizer;
import evaluator.tokenizer.Tokenizer;
import exeption.SyntaxErrorException;
import exeption.UnknownTokenException;

import java.util.NoSuchElementException;

public class PlanParser implements Parser {

    private final Tokenizer tokenizer;

    public PlanParser() {
        tokenizer = new PlanTokenizer();
    }

    @Override
    public Expression parse(String src) throws SyntaxErrorException {
        tokenizer.updateSource(src);
        Expression expr = parseExpression();
        if(tokenizer.hasNextToken()) throw new SyntaxErrorException("leftover token");
        return expr;
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
        return null;
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
