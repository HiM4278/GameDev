package game.main;

import evaluator.node.Plan;
import evaluator.parser.PlanParser;
import exeption.SyntaxErrorException;

import java.util.HashMap;

public class ConstructionPlan {
    private Player owner;
    private String strPlan;
    private Plan plan;
    private HashMap<String, Integer> identifiers;

    public ConstructionPlan(Player owner){
        this.owner = owner;
    }

    public void updatePlan(String src){
        strPlan = src;
    }

    public void run(){

    }

    private void parsePlan(){
//        try {
//            plan = planParser.parse(strPlan);
//        } catch (SyntaxErrorException e){
//            System.out.println(e);
//        }
    }


}
