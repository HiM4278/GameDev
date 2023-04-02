package com.example.evaluator.node.statement;

import com.example.evaluator.node.expression.Expression;
import com.example.exeption.EvalException;

import java.util.HashMap;

public class AssignStatement implements Statement{
    private final String identifier;
    private final Expression value;

    public AssignStatement(String identifier, Expression value) {
        this.identifier = identifier;
        this.value = value;
    }

    @Override
    public void prettyPrint(StringBuilder s, int depth) {
        if(!s.isEmpty() && s.substring(s.length()-1,s.length()).equals("\n")) {
            Statement.super.prettyPrint(s, depth);
        }
        s.append(identifier);
        s.append(" = ");
        value.prettyPrint(s,depth);
    }

    @Override
    public boolean execute(HashMap<String, Integer> identifiers) throws EvalException {
        identifiers.put(identifier, value.eval(identifiers));
        return false;
    }
}
