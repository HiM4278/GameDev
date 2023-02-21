import evaluator.node.Plan;
import evaluator.parser.PlanParser;
import exeption.SyntaxErrorException;
import game.main.Territory;

public class Main {
    public static void main(String[] args) throws SyntaxErrorException {
        PlanParser psr = new PlanParser();
        Plan plan = psr.parse("");
    }
}