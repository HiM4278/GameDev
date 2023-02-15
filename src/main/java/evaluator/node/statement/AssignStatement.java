package evaluator.node.statement;

import evaluator.node.expression.Expression;
import exeption.EvalException;

import java.util.HashMap;

public class AssignStatement implements Statement{
    private final String identifier;
    private final Expression value;

    public AssignStatement(String identifier, Expression value) {
        this.identifier = identifier;
        this.value = value;
    }

    @Override
    public void prettyPrint(StringBuilder s, int depth) {
        s.append(identifier);
        s.append(" = ");
        value.prettyPrint(s,depth);
    }

    @Override
    public boolean execute(HashMap<String, Integer> identifiers) throws EvalException {
        identifiers.put(identifier, value.eval(identifiers));
        return false;
    }
}
