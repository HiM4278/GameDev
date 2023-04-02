package com.example.evaluator.node.statement.command;

import com.example.evaluator.node.statement.Statement;
import com.example.exeption.EvalException;
import com.example.game.main.Player;

import java.util.HashMap;

public class DoneCommand implements Statement {

    public DoneCommand(){}

    @Override
    public void prettyPrint(StringBuilder s, int depth) {
        s.append("done");
    }

    @Override
    public boolean execute(HashMap<String, Long> identifiers, Player player) throws EvalException {
        return true;
    }
}
