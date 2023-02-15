package evaluator.parser;

import evaluator.node.Node;
import exeption.SyntaxErrorException;

public interface Parser {
    Node parse(String src) throws SyntaxErrorException;
}
