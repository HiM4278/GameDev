package evaluator.node.statement.command;

import evaluator.node.statement.Statement;
import exeption.EvalException;

import java.util.HashMap;

public class DoneCommand implements Statement {

    public DoneCommand(){}

    @Override
    public void prettyPrint(StringBuilder s, int depth) {
        s.append("done");
    }

    @Override
    public boolean execute(HashMap<String, Integer> identifiers) throws EvalException {
        return true;
    }
}
