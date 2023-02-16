package evaluator.node.statement.command;

import evaluator.node.expression.Expression;
import evaluator.node.statement.Statement;
import exeption.EvalException;
import extra.Direction;

import java.util.HashMap;

public class AttackCommand implements Statement {
    private final Direction direction;
    private final Expression volume;

    public AttackCommand(Direction direction, Expression volume) {
        this.direction = direction;
        this.volume = volume;
    }

    @Override
    public boolean execute(HashMap<String, Integer> identifiers) throws EvalException {
        return false;
    }

    @Override
    public void prettyPrint(StringBuilder s, int depth) {
        s.append("shoot ");
        s.append(direction.toString().toLowerCase());
        s.append(" ");
        volume.prettyPrint(s, depth);
    }
}
