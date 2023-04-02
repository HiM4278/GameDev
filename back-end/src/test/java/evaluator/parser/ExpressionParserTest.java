package com.example.evaluator.parser;

import com.example.evaluator.node.Plan;
import com.example.evaluator.tokenizer.PlanTokenizer;
import com.example.evaluator.tokenizer.Tokenizer;
import com.example.exeption.SyntaxErrorException;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ExpressionParserTest {
    @Nested
    public class commandStatement_test {
        @Test
        public void move_test () throws SyntaxErrorException {
            PlanTokenizer tkz = new PlanTokenizer();
            tkz.updateSource("move up");
            PlanParser psr = new PlanParser(tkz);
            Plan plan = psr.parse();
            StringBuilder s = new StringBuilder();
            plan.prettyPrint(s, 0);
            assertEquals("move up\n", s.toString());
        }
        @Test
        public void shoot_test () throws SyntaxErrorException {
            Tokenizer tokenizer = new PlanTokenizer();
            tokenizer.updateSource("shoot up cost");
            PlanParser psr = new PlanParser(tokenizer);
            Plan plan = psr.parse();
            StringBuilder s = new StringBuilder();
            plan.prettyPrint(s, 0);
            assertEquals("shoot up cost\n",s.toString());
        }
        @Test
        public void collect_test () throws SyntaxErrorException {
            PlanTokenizer tkz = new PlanTokenizer();
            tkz.updateSource("collect (deposit / 4)");
            PlanParser psr = new PlanParser(tkz);
            Plan plan = psr.parse();
            StringBuilder s = new StringBuilder();
            plan.prettyPrint(s, 0);
            assertEquals("collect (deposit / 4)\n", s.toString());
        }
        @Test
        public void invest_test () throws SyntaxErrorException {
            PlanTokenizer tkz = new PlanTokenizer();
            tkz.updateSource("invest 50");
            PlanParser psr = new PlanParser(tkz);
            Plan plan = psr.parse();
            StringBuilder s = new StringBuilder();
            plan.prettyPrint(s, 0);
            assertEquals("invest 50\n", s.toString());
        }
        @Test
        public void relocate_test () throws SyntaxErrorException {
            PlanTokenizer tkz = new PlanTokenizer();
            tkz.updateSource("relocate");
            PlanParser psr = new PlanParser(tkz);
            Plan plan = psr.parse();
            StringBuilder s = new StringBuilder();
            plan.prettyPrint(s, 0);
            assertEquals("relocate\n", s.toString());
        }
        @Test
        public void done_test () throws SyntaxErrorException {
            PlanTokenizer tkz = new PlanTokenizer();
            tkz.updateSource("done");
            PlanParser psr = new PlanParser(tkz);
            Plan plan = psr.parse();
            StringBuilder s = new StringBuilder();
            plan.prettyPrint(s, 0);
            assertEquals("done\n", s.toString());
        }
    }

    @Nested
    public class commandFailTest {
        @Test
        public void shootFailTest () throws SyntaxErrorException {
            PlanTokenizer tkz = new PlanTokenizer();
            tkz.updateSource("move");
            PlanParser psr = new PlanParser(tkz);
            assertThrows(SyntaxErrorException.class, psr::parse);
        }
        @Test
        public void moveFailTest () throws SyntaxErrorException {
            PlanTokenizer tkz = new PlanTokenizer();
            tkz.updateSource("shoot");
            PlanParser psr = new PlanParser(tkz);
            assertThrows(SyntaxErrorException.class, psr::parse);
        }
        @Test
        public void collectFailTest () throws SyntaxErrorException {
            PlanTokenizer tkz = new PlanTokenizer();
            tkz.updateSource("collect");
            PlanParser psr = new PlanParser(tkz);
            assertThrows(SyntaxErrorException.class, psr::parse);
        }
        @Test
        public void investFailTest () throws SyntaxErrorException {
            PlanTokenizer tkz = new PlanTokenizer();
            tkz.updateSource("invest");
            PlanParser psr = new PlanParser(tkz);
            assertThrows(SyntaxErrorException.class, psr::parse);
        }
    }

    @Test
    public void voidBlockStatement_test () throws SyntaxErrorException {
        PlanTokenizer tkz = new PlanTokenizer();
        tkz.updateSource("{}");
        PlanParser psr = new PlanParser(tkz);
        Plan plan = psr.parse();
        StringBuilder s = new StringBuilder();
        plan.prettyPrint(s, 0);
        assertEquals("{}\n", s.toString());
    }

    @Test
    public void loopBlockStatement_test () throws SyntaxErrorException {
        PlanTokenizer tkz = new PlanTokenizer();
        tkz.updateSource("while(deposit) {if (budget - 100) then collect (deposit / 4) else {} }");
        PlanParser psr = new PlanParser(tkz);
        Plan plan = psr.parse();
        StringBuilder s =new StringBuilder();
        plan.prettyPrint(s, 0);
        assertEquals("while (deposit) {\n\n\tif ((budget - 100)) then collect (deposit / 4)\n\telse {}\n}\n",s.toString());
    }

    @Test
    public void IfStatement_test () throws SyntaxErrorException {
        PlanTokenizer tkz = new PlanTokenizer();
        tkz.updateSource("if (deposit - 100) then collect (deposit / 4)\n else {}");
        PlanParser psr = new PlanParser(tkz);
        Plan plan = psr.parse();
        StringBuilder s = new StringBuilder();
        plan.prettyPrint(s, 0);
        assertEquals("\nif ((deposit - 100)) then collect (deposit / 4)\nelse {}\n", s.toString());
    }

    @Test
    public void NestedIfStatement_test () throws SyntaxErrorException {
        PlanTokenizer tkz = new PlanTokenizer();
        tkz.updateSource("if (budget - 100) then if (budget - 1) then invest 1 else {} else {}");
        PlanParser psr = new PlanParser(tkz);
        Plan plan = psr.parse();
        StringBuilder s = new StringBuilder();
        plan.prettyPrint(s, 0);
        assertEquals("\nif ((budget - 100)) then \n\tif ((budget - 1)) then invest 1\n\telse {}\nelse {}\n", s.toString());
    }

    @Nested
    public class FailStatementTest {
        @Test
        public void emptyStatement_Test () throws SyntaxErrorException {
            PlanTokenizer tkz = new PlanTokenizer();
            tkz.updateSource("");
            PlanParser psr = new PlanParser(tkz);
            assertThrows(SyntaxErrorException.class, psr::parse);
        }

        @Test
        public void ReservedWords_test () throws SyntaxErrorException {
            String[] ReservedWords = {"collect", "done", "down", "downleft", "downright", "else", "if", "invest", "move", "nearby", "opponent", "relocate", "shoot", "then", "up", "upleft", "upright", "while"};
            for (String reservedWord : ReservedWords) {
                System.out.printf("if (%s) then move up else {}\n", reservedWord);
                PlanTokenizer tkz = new PlanTokenizer();
                tkz.updateSource(String.format("if (1) then move up else {%s=1}", reservedWord));
                PlanParser psr2 = new PlanParser(tkz);
                assertThrows(SyntaxErrorException.class, psr2::parse);
            }
        }
    }

}