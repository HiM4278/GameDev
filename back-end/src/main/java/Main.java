import evaluator.node.expression.Expression;
import evaluator.parser.PlanParser;
import evaluator.tokenizer.PlanTokenizer;
import exeption.EvalException;
import exeption.SyntaxErrorException;

import java.util.HashMap;

public class Main {
    public static void main(String[] args) throws SyntaxErrorException, EvalException {
        PlanTokenizer tokenizer = new PlanTokenizer();
        tokenizer.updateSource("2^6");
        PlanParser psr = new PlanParser(tokenizer);
        Expression expr = psr.parseExprForTest();
        System.out.println(expr.eval(new HashMap<>()));


    }
}