package com.example.evaluator.node.statement.command;

import com.example.evaluator.node.statement.Statement;
import com.example.exeption.EvalException;
import com.example.extra.Direction;
import com.example.game.main.Player;

import java.util.HashMap;

public class MoveCommand implements Statement {
    private final Direction direction;

    public MoveCommand(Direction direction) {
        this.direction = direction;
    }

    @Override
    public boolean execute(HashMap<String, Long> identifiers, Player player) throws EvalException {
        player.getPlan().updateIdentifiers();
        return !player.getCrew().move(direction);
    }

    @Override
    public void prettyPrint(StringBuilder s, int depth) {
        s.append("move ");
        s.append(direction.toString().toLowerCase());
    }
}
