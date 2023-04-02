package com.example.evaluator.node.statement.command;

import com.example.evaluator.node.statement.Statement;
import com.example.exeption.EvalException;
import com.example.extra.Direction;

import java.util.HashMap;

public class MoveCommand implements Statement {
    private final Direction direction;

    public MoveCommand(Direction direction) {
        this.direction = direction;
    }

    @Override
    public boolean execute(HashMap<String, Integer> identifiers) throws EvalException {
        return false;
    }

    @Override
    public void prettyPrint(StringBuilder s, int depth) {
        s.append("move ");
        s.append(direction.toString().toLowerCase());
    }
}
