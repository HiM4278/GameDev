package evaluator.node.expression;

import evaluator.node.Node;
import exeption.EvalException;
import java.util.HashMap;

public interface Expression extends Node {
    int eval(HashMap<String,Integer> identifier) throws EvalException;
}
