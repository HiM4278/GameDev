package com.example.evaluator.node.statement;

import com.example.evaluator.node.expression.Expression;
import com.example.exeption.EvalException;
import com.example.game.main.Player;

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
    public boolean execute(HashMap<String, Long> identifiers, Player player) throws EvalException {
        player.getPlan().updateIdentifiers();
        if(!isConstVar(identifier)){
            if(!identifiers.containsKey(identifier)){
                identifiers.put(identifier, 0L);
            }
            identifiers.put(identifier, value.eval(identifiers, player));
        }
        return false;
    }

    private boolean isConstVar(String s){
        String[] constVar = {"rows", "cols", "currow", "curcol", "budget", "deposit", "int", "maxdeposit"};
        for(String word: constVar) {
            if(s.equals(word)) return true;
        }
        return false;
    }
}
