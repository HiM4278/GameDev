package com.example.evaluator.node.statement.command;

import com.example.evaluator.node.expression.Expression;
import com.example.evaluator.node.statement.Statement;
import com.example.exeption.EvalException;
import com.example.extra.Direction;
import com.example.game.main.Player;

import java.util.HashMap;

public class AttackCommand implements Statement {
    private final Direction direction;
    private final Expression volume;

    public AttackCommand(Direction direction, Expression volume) {
        this.direction = direction;
        this.volume = volume;
    }

    @Override
    public boolean execute(HashMap<String, Long> identifiers, Player player) throws EvalException {
        player.getPlan().updateIdentifiers();
        player.getCrew().shoot(direction,volume.eval(identifiers, player));
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
