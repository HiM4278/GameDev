package evaluator.parser;

import evaluator.node.Node;
import evaluator.node.expression.Expression;
import evaluator.node.expression.Identifier;
import evaluator.node.expression.Info;
import evaluator.node.expression.IntLit;
import evaluator.tokenizer.Tokenizer;
import exeption.SyntaxErrorException;
import exeption.UnknownTokenException;
import extra.Direction;

import java.util.NoSuchElementException;

public class ExprParser implements Parser{
    private final Tokenizer tokenizer;

    public ExprParser(Tokenizer tokenizer) {
        this.tokenizer = tokenizer;
    }

    @Override
    public Expression parse() throws SyntaxErrorException {
        return parseExpression();
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
