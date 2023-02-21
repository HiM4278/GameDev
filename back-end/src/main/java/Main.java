import evaluator.node.expression.Expression;
import evaluator.parser.ExprParser;
import evaluator.tokenizer.PlanTokenizer;
import exeption.EvalException;
import exeption.SyntaxErrorException;

import java.util.HashMap;

public class Main {
    public static void main(String[] args) throws SyntaxErrorException, EvalException {
        PlanTokenizer tokenizer = new PlanTokenizer();
        tokenizer.updateSource("2^6");
        ExprParser psr = new ExprParser(tokenizer);
        Expression plan = psr.parse();
        System.out.println(plan.eval(new HashMap<>()));
    }
}