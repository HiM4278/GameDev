package com.example.evaluator.node.expression;

import com.example.exeption.EvalException;

import java.util.Map;

public class IntLit implements Expression {
    private final int value;
    public IntLit(int value){
        this.value = value;
    }
    @Override
    public long eval(Map<String, Long> identifier) throws EvalException {
        return value;
    }

    @Override
    public void prettyPrint(StringBuilder s, int depth) {
        s.append(value);
    }
}
