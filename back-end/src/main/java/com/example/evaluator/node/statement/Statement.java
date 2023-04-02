package com.example.evaluator.node.statement;

import com.example.evaluator.node.Node;
import com.example.exeption.EvalException;
import com.example.game.main.Player;

import java.util.HashMap;

public interface Statement extends Node {
    boolean execute(HashMap<String, Long> identifiers, Player player) throws EvalException;

    @Override
    default void prettyPrint(StringBuilder s, int depth) {
        s.append("\t".repeat(Math.max(0, depth)));
    }
}
