package evaluator.node.statement.command;

import evaluator.node.statement.Statement;
import exeption.EvalException;

import java.util.HashMap;

public class RelocateCommand implements Statement {
    @Override
    public void prettyPrint(StringBuilder s, int depth) {
        s.append("relocate");
    }

    @Override
    public boolean execute(HashMap<String, Integer> identifiers) throws EvalException {
        return true;
    }
}
