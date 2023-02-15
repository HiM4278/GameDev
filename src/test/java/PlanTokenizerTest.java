import evaluator.tokenizer.PlanTokenizer;
import exeption.SyntaxErrorException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PlanTokenizerTest {
    @Test
    public void ComputeNext_CommentTest() throws SyntaxErrorException {
        PlanTokenizer tokenizer = new PlanTokenizer();
        tokenizer.updateSource("123 -x # comment \n i (deposit)");
        assertEquals("123", tokenizer.consume());
        assertEquals("-", tokenizer.consume());
        assertEquals("x", tokenizer.consume());
        assertEquals("i", tokenizer.consume());
        assertEquals("(", tokenizer.consume());
        assertEquals("deposit", tokenizer.consume());
        assertEquals(")", tokenizer.consume());
    }

    @Test
    public void namingVarTest() throws SyntaxErrorException {
        PlanTokenizer tokenizer = new PlanTokenizer();
        tokenizer.updateSource("var1 = 2 \n 1var = 3");
        assertEquals("var1", tokenizer.consume());
        tokenizer.consume();
        tokenizer.consume();
        assertEquals("1", tokenizer.consume());
    }

}
