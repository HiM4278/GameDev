package evaluator.node.expression;

import exeption.EvalException;

import java.util.Map;

public class IntLit implements Expression {
    private final int value;
    public IntLit(int value){
        this.value = value;
    }
    @Override
    public int eval(Map<String, Integer> identifier) throws EvalException {
        return value;
    }

    @Override
    public void prettyPrint(StringBuilder s, int depth) {
        s.append(value);
    }
}
