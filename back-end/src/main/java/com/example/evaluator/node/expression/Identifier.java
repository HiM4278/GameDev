package com.example.evaluator.node.expression;

import com.example.exeption.EvalException;

import java.util.Map;

public class Identifier implements Expression{
    String name;
    public Identifier(String name){
        this.name = name;
    }
    @Override
    public int eval(Map<String, Integer> identifier) throws EvalException {
        if(identifier.containsKey(name)) return identifier.get(name);
        throw new EvalException("undefined variable: " + name);
    }

    @Override
    public void prettyPrint(StringBuilder s, int depth) {
        s.append(name);
    }
}
