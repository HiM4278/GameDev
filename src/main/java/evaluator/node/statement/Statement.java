package evaluator.node.statement;

import evaluator.node.Node;
import exeption.EvalException;

import java.util.HashMap;

public interface Statement extends Node {
    boolean execute(HashMap<String, Integer> identifiers) throws EvalException;
}
