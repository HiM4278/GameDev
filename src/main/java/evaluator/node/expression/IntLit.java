package evaluator.node.expression;

import evaluator.node.expression.Expression;
import exeption.EvalException;

import java.util.HashMap;

public class IntLit implements Expression {
    int value;
    public IntLit(int value){
        this.value = value;
    }
    @Override
    public int eval(HashMap<String, Integer> identifier) throws EvalException {
        return value;
    }

    @Override
    public void prettyPrint(StringBuilder s) {
        s.append(value);
    }
}
