package com.example.evaluator.tokenizer;

import com.example.exeption.SyntaxErrorException;

public interface Tokenizer {
    void updateSource(String src) throws SyntaxErrorException;
    int getCurrentLine();
    boolean hasNextToken();
    String peek() throws SyntaxErrorException;
    boolean peek(String s) throws SyntaxErrorException;
    String consume() throws SyntaxErrorException;
    void consume(String s) throws SyntaxErrorException;
}
