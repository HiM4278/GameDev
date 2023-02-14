package evaluator.tokenizer;

import exeption.SyntaxErrorException;

public interface Tokenizer {
    boolean hasNextToken();
    String peek();
    boolean peek(String s);
    String consume();
    void consume(String s) throws SyntaxErrorException;
}
