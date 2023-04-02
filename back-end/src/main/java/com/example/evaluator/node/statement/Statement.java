package com.example.evaluator.node.statement;

import com.example.evaluator.node.Node;
import com.example.exeption.EvalException;

import java.util.HashMap;

public interface Statement extends Node {
    boolean execute(HashMap<String, Integer> identifiers) throws EvalException;

    @Override
    default void prettyPrint(StringBuilder s, int depth) {
        s.append("\t".repeat(Math.max(0, depth)));
    }
}
