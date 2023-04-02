package com.example.game.main;

import com.example.evaluator.node.Plan;
import com.example.evaluator.parser.PlanParser;
import com.example.evaluator.tokenizer.PlanTokenizer;
import com.example.exeption.EvalException;
import com.example.exeption.SyntaxErrorException;

import java.util.HashMap;

public class ConstructionPlan {
    private Player owner;
    private String strPlan;
    private Plan myPlan;

    private HashMap<String, Long> identifiers = new HashMap<>();

    public ConstructionPlan(Player owner){
        this.owner = owner;
    }

    public void updatePlan(String src) throws SyntaxErrorException {
        strPlan = src;
        Game.tokenizer.updateSource(strPlan);
    }

    public String parse() {
        try {
            myPlan = Game.planParser.parse();
        }catch (SyntaxErrorException e){
            return e.getMessage();
        }
        return "";
    }

    public void run() {
        try{
            myPlan.execute(identifiers, owner);
        }catch (EvalException e){

        }
    }

    public void updateIdentifiers(){
        identifiers.put("rows",Game.configuration.getM());
        identifiers.put("cols",Game.configuration.getN());
        identifiers.put("currow", (long) owner.getCrew().getPosition().getX());
        identifiers.put("curcol", (long) owner.getCrew().getPosition().getY());
        identifiers.put("budget", owner.getCrew().getBudget());
        identifiers.put("deposit", owner.getCrew().getBudget());
    }

}
