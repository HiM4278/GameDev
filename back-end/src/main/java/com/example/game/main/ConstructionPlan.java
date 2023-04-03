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
            StringBuilder s = new StringBuilder();
            updateIdentifiers();
            myPlan.prettyPrint(s, 0);
            System.out.println(s);
            return "";
        }catch (SyntaxErrorException e){
            return e.getMessage();
        }
    }

    public String run() {
        try{
            myPlan.execute(identifiers, owner);
            return "";
        }catch (EvalException e){
            return e.getMessage();
        }
    }

    public void updateIdentifiers(){
        identifiers.put("rows",Game.configuration.getM());
        identifiers.put("cols",Game.configuration.getN());
        identifiers.put("currow", (long) owner.getCrew().getPosition().getX());
        identifiers.put("curcol", (long) owner.getCrew().getPosition().getY());
        identifiers.put("budget", owner.getCrew().getBudget());
        if(owner.getCrew().getPosition().getLand() != null) {
            double deposit = owner.getCrew().getPosition().getLand().getDeposit();
            identifiers.put("deposit", (long) deposit);
            identifiers.put("int", (long) (Game.configuration.getInterest_pct()*Math.log10(deposit)*(-Math.log(1-owner.getTurn()))/owner.getTurn()));
        }else {
            identifiers.put("deposit", 1L);
            identifiers.put("int", 0L);
        }
        identifiers.put("maxdeposit", Game.configuration.getMax_dep());
        identifiers.put("random", (long) Math.floor(Math.random()*999));
    }

}
