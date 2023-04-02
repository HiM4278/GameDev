package com.example.evaluator.node.statement.command;

import com.example.evaluator.node.statement.Statement;
import com.example.exeption.EvalException;

import java.util.HashMap;

public class RelocateCommand implements Statement {
    @Override
    public void prettyPrint(StringBuilder s, int depth) {
        s.append("relocate");
    }

    @Override
    public boolean execute(HashMap<String, Integer> identifiers) throws EvalException {
        return true;
    }
}
