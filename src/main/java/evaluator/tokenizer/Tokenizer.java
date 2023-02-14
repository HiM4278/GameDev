package evaluator.tokenizer;

import exeption.SyntaxErrorException;

public interface Tokenizer {
    boolean hasNextToken();
    String peek() throws SyntaxErrorException;
    boolean peek(String s) throws SyntaxErrorException;
    String consume() throws SyntaxErrorException;
    void consume(String s) throws SyntaxErrorException;
}
