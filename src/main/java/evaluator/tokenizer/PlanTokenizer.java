package evaluator.tokenizer;

import exeption.SyntaxErrorException;

public class PlanTokenizer implements Tokenizer{
    private String src, next;
    private int pos;

    public PlanTokenizer(){
    }

    public void updateSource(String src) throws SyntaxErrorException {
        this.src = src;
        this.pos = 0;
        computeNext();
    }

    @Override
    public boolean hasNextToken() {
        return next != null;
    }

    @Override
    public String peek() throws SyntaxErrorException {
        if (!hasNextToken())
            throw new SyntaxErrorException("no more token");
        return next;
    }

    @Override
    public boolean peek(String s) throws SyntaxErrorException {
        if (!hasNextToken()) return false;
        return peek().equals(s);
    }

    @Override
    public String consume() throws SyntaxErrorException {
        if (!hasNextToken())
            throw new SyntaxErrorException("no more tokens");
        String result = next;
        computeNext();
        return result;
    }

    @Override
    public void consume(String s) throws SyntaxErrorException {
        if (peek(s)) {
            consume();
        } else {
            throw new SyntaxErrorException(s + " expected");
        }
    }

    private boolean isOperator(char c) {

        return c == '+' || c == '-' || c == '*' || c == '/' || c == '%' || c == '^'|| c == '(' || c == ')'|| c == '=';
    }

    private void computeNext() throws SyntaxErrorException {
        StringBuilder s = new StringBuilder();

        while (pos < src.length() && Character.isWhitespace(src.charAt(pos)))
            pos++; // ignore whitespace

        if (pos == src.length())
            { next = null; return; } // no more tokens

        if (src.charAt(pos) == '#') { // start of the comment
            while (src.charAt(pos) != '\n') {
                pos++;
                if (pos == src.length()) {
                    next = null;
                    return;
                } // no more tokens
            }
            pos++;
            while (pos < src.length() && Character.isWhitespace(src.charAt(pos)))
                pos++;
        }

        char c = src.charAt(pos); // assign c to be the same character at src index position

        if (Character.isDigit(c)) {   // start with number
            s.append(c);
            for (pos++; pos < src.length() && Character.isDigit(src.charAt(pos)); pos++) {
                s.append(src.charAt(pos));
            }
        }
        else if (Character.isLetter(c)) { // start of character
            s.append(c);
            for (pos++; pos < src.length() && ( Character.isLetter(src.charAt(pos)) || Character.isDigit(src.charAt((pos))) ); pos++) {
                s.append(src.charAt(pos));
            }
        }
        else if (isOperator(c)) { // start of operator
            s.append(c);
            for (pos++; pos < src.length() && isOperator(src.charAt(pos)); pos++) {
                s.append(src.charAt(pos));
            }
        }
        else {
            throw new SyntaxErrorException("lexical error");
        }
        next = s.toString();

    }

}
