package evaluator.node.statement;

import exeption.EvalException;

import java.util.HashMap;

public class WhileStatement implements Statement{
    @Override
    public void prettyPrint(StringBuilder s) {
    }

    @Override
    public boolean execute(HashMap<String, Integer> identifier) throws EvalException {
        return false;
    }
}
