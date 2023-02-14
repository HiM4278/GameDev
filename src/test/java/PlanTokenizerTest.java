import evaluator.tokenizer.PlanTokenizer;
import exeption.SyntaxErrorException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PlanTokenizerTest {
    @Test
    public void ComputeNext_CommentTest() throws SyntaxErrorException {
        PlanTokenizer p = new PlanTokenizer("123 -x # comment \n i (deposit)");

        assertEquals("123", p.consume());
        assertEquals("-", p.consume());
        assertEquals("x", p.consume());
        assertEquals("i", p.consume());
        assertEquals("(", p.consume());
        assertEquals("deposit", p.consume());
        assertEquals(")", p.consume());
    }

    @Test
    public void namingVarTest() throws SyntaxErrorException {
        PlanTokenizer p = new PlanTokenizer("var1 = 2 \n 1var = 3");

        assertEquals("var1", p.consume());
        p.consume();
        p.consume();
        assertEquals("1var", p.consume());
        //assertThrows(SyntaxErrorException.class, );
    }

}
