package game.main;

import evaluator.node.Plan;
import evaluator.parser.PlanParser;
import evaluator.tokenizer.PlanTokenizer;
import exeption.SyntaxErrorException;

import java.util.HashMap;

public class ConstructionPlan {
    private Player owner;
    private String strPlan;
    private PlanParser plan;
    private PlanTokenizer tokenizer = new PlanTokenizer();
    private HashMap<String, Integer> identifiers = new HashMap<>();

    public ConstructionPlan(Player owner){
        this.owner = owner;
    }

    public void updatePlan(String src) throws SyntaxErrorException {
        strPlan = src;
        tokenizer.updateSource(strPlan);
    }

    public void run() throws SyntaxErrorException {
        parsePlan();
    }

    private void parsePlan() throws SyntaxErrorException {
        plan = new PlanParser(tokenizer);
        plan.parse();
    }


}
