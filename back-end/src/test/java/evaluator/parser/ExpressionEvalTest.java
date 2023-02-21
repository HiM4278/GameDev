package evaluator.parser;

import evaluator.node.expression.Expression;
import evaluator.tokenizer.PlanTokenizer;
import exeption.EvalException;
import exeption.SyntaxErrorException;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class ExpressionEvalTest {

    @Nested
    public class ArithmeticTest {
        @Test
        public void numberTest () throws SyntaxErrorException, EvalException {
            PlanTokenizer tkz = new PlanTokenizer();
            tkz.updateSource("069");
            ExprParser psr = new ExprParser(tkz);
            Expression expr = psr.parse();
            assertEquals(69, expr.eval(null));
        }
        @Test
        public void AdditionTest () throws SyntaxErrorException, EvalException {
            PlanTokenizer tkz = new PlanTokenizer();
            tkz.updateSource("33+36");
            ExprParser psr = new ExprParser(tkz);
            Expression expr = psr.parse();
            assertEquals(69, expr.eval(null));
        }
        @Test
        public void SubtractionTest () throws SyntaxErrorException, EvalException {
            PlanTokenizer tkz = new PlanTokenizer();
            tkz.updateSource("100 - 31");
            ExprParser psr = new ExprParser(tkz);
            Expression expr = psr.parse();
            assertEquals(69, expr.eval(null));
        }
        @Test
        public void MultiplicationTest () throws SyntaxErrorException, EvalException {
            PlanTokenizer tkz = new PlanTokenizer();
            tkz.updateSource("23*3");
            ExprParser psr = new ExprParser(tkz);
            Expression expr = psr.parse();
            assertEquals(69, expr.eval(null));
        }
        @Test
        public void DivisionTest () throws SyntaxErrorException, EvalException {
            PlanTokenizer tkz = new PlanTokenizer();
            tkz.updateSource("138/2");
            ExprParser psr = new ExprParser(tkz);
            Expression expr = psr.parse();
            assertEquals(69, expr.eval(null));
        }
        @Test
        public void ModuloTest () throws SyntaxErrorException, EvalException {
            PlanTokenizer tkz = new PlanTokenizer();
            tkz.updateSource("100 % 31");
            ExprParser psr = new ExprParser(tkz);
            Expression expr = psr.parse();
            assertEquals(69, expr.eval(null));
        }
        @Test
        public void powerTest () throws SyntaxErrorException, EvalException {
            PlanTokenizer tkz = new PlanTokenizer();
            tkz.updateSource("13^2");
            ExprParser psr = new ExprParser(tkz);
            Expression expr = psr.parse();
            assertEquals(169, expr.eval(null));
        }
        @Test
        public void PEMDASTest () throws SyntaxErrorException, EvalException {
            PlanTokenizer tkz = new PlanTokenizer();
            tkz.updateSource("(a+b-c) *x /y %z + xyz");
            ExprParser psr = new ExprParser(tkz);
            Expression expr = psr.parse();
            assertEquals(69,expr.eval(Map.of("a",10,"b",5,"c",1,"x",5,"y",1,"z",100, "xyz", -1)));
        }
        @Test
        public void associativityTest() throws SyntaxErrorException, EvalException{
            PlanTokenizer tkz = new PlanTokenizer();
            tkz.updateSource("333 * 222 / 111");
            ExprParser psr = new ExprParser(tkz);
            Expression expr = psr.parse();

            PlanTokenizer tkz2 = new PlanTokenizer();
            tkz2.updateSource("222 / 111 * 333");
            ExprParser psr2 = new ExprParser(tkz2);
            Expression expr2 = psr2.parse();

            assertEquals(666, expr.eval(null));
            assertEquals(666, expr2.eval(null));
        }
    }

    @Nested
    public class ArithmeticFailTest {
        @Disabled
        @Test
        public void ParenthesesFailTest () throws SyntaxErrorException {
            PlanTokenizer tkz = new PlanTokenizer();
            tkz.updateSource("((333)))");
            ExprParser psr = new ExprParser(tkz);
//            Expression expr = psr.parse();

//            PlanParser psr = new PlanParser(tkz);

            assertThrows(SyntaxErrorException.class, psr::parse);
        }
    }

}
