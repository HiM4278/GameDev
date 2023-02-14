package evaluator.node.statement;

import evaluator.node.expression.Expression;
import exeption.EvalException;

import java.util.HashMap;

public class AssignStatement implements Statement{
    String identifier;
    Expression value;

    @Override
    public void prettyPrint(StringBuilder s) {

    }

    @Override
    public boolean execute(HashMap<String, Integer> identifiers) throws EvalException {
        identifiers.put(identifier, value.eval(identifiers));
        return false;
    }
}
