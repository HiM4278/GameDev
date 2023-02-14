package evaluator.node;

import exeption.EvalException;

import java.util.HashMap;

public class Identifier implements Expression{
    String name;
    public Identifier(String name){
        this.name = name;
    }
    @Override
    public int eval(HashMap<String, Integer> identifier) throws EvalException {
        if(identifier.containsKey(name)) return identifier.get(name);
        throw new EvalException("undefined variable: " + name);
    }

    @Override
    public void prettyPrint(StringBuilder s) {
        s.append(name);
    }
}
