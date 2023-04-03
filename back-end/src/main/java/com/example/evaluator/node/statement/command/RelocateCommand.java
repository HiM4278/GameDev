package com.example.evaluator.node.statement.command;

import com.example.evaluator.node.statement.Statement;
import com.example.exeption.EvalException;
import com.example.game.main.Player;

import java.util.HashMap;

public class RelocateCommand implements Statement {
    @Override
    public void prettyPrint(StringBuilder s, int depth) {
        s.append("relocate");
    }

    @Override
    public boolean execute(HashMap<String, Long> identifiers, Player player) throws EvalException {
        player.getPlan().updateIdentifiers();
        player.getCrew().relocate(player.getCityCenter().getPosition());
        return true;
    }
}
