package evaluator.node;

import exeption.EvalException;
import java.util.HashMap;

public interface Expression extends Node {
    int eval(HashMap<String,Integer> identifier) throws EvalException;
}
