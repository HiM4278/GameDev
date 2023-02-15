package evaluator.parser;

import evaluator.node.expression.Expression;
import exeption.EvalException;
import exeption.SyntaxErrorException;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class ExpressionParserTest {

    @Test
    void main() throws SyntaxErrorException, EvalException {
        PlanParser exprParser = new PlanParser();
        Expression expression = exprParser.parse("1 + 3 - 4");
        assertEquals(0,expression.eval(new HashMap<>()));

        expression = exprParser.parse("2 * 2 ^ 2-1");
        assertEquals(4,expression.eval(new HashMap<>()));

        expression = exprParser.parse("2 * 2 ^ 3 ^ 2");
        assertEquals(1024,expression.eval(new HashMap<>()));
    }
}