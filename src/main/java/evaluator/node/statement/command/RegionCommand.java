package evaluator.node.statement.command;

import evaluator.node.expression.Expression;
import evaluator.node.statement.Statement;
import exeption.EvalException;

import java.util.HashMap;

public class RegionCommand implements Statement {
    private final Expression volume;
    private final RegionCommandType type;

    public RegionCommand(RegionCommandType type, Expression volume) {
        this.volume = volume;
        this.type = type;
    }

    @Override
    public boolean execute(HashMap<String, Integer> identifiers) throws EvalException {
        return false;
    }

    @Override
    public void prettyPrint(StringBuilder s, int depth) {
        s.append(type);
        s.append(" ");
        volume.prettyPrint(s, depth);
    }

    public enum RegionCommandType {COLLECT,INVEST}
}
