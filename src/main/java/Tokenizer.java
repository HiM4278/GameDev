public interface Tokenizer {
    boolean hasNextToken();
    String peek();
    boolean peek(String s);
    String consume();
    void consume(String s) throws SyntaxErrorException;
}
