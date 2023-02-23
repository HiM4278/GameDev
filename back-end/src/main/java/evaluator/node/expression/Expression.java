package evaluator.node.expression;

import evaluator.node.Node;
import exeption.EvalException;

import java.util.Map;

public interface Expression extends Node {
    int eval(Map<String, Integer> identifier) throws EvalException;
}
