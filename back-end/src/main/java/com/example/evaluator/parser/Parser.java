package com.example.evaluator.parser;

import com.example.evaluator.node.Node;
import com.example.exeption.SyntaxErrorException;

public interface Parser {
    Node parse() throws SyntaxErrorException;
}
