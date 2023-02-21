package evaluator.parser;

import evaluator.node.Plan;
import exeption.SyntaxErrorException;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ExpressionParserTest {
    @Nested
    public class commandStatement_test {
        @Test
        public void move_test () throws SyntaxErrorException {
            PlanParser psr = new PlanParser();
            Plan plan = psr.parse("move up");
            StringBuilder s = new StringBuilder();
            plan.prettyPrint(s, 0);
            assertEquals("move up\n", s.toString());
        }
        @Test
        public void shoot_test () throws SyntaxErrorException {
            PlanParser psr = new PlanParser();
            Plan plan = psr.parse("shoot up cost");
            StringBuilder s = new StringBuilder();
            plan.prettyPrint(s, 0);
            assertEquals("shoot up cost\n",s.toString());
        }
        @Test
        public void collect_test () throws SyntaxErrorException {
            PlanParser psr = new PlanParser();
            Plan plan = psr.parse("collect (deposit / 4)");
            StringBuilder s = new StringBuilder();
            plan.prettyPrint(s, 0);
            assertEquals("collect (deposit / 4)\n", s.toString());
        }
        @Test
        public void invest_test () throws SyntaxErrorException {
            PlanParser psr = new PlanParser();
            Plan plan = psr.parse("invest 50");
            StringBuilder s = new StringBuilder();
            plan.prettyPrint(s, 0);
            assertEquals("invest 50\n", s.toString());
        }
        @Test
        public void relocate_test () throws SyntaxErrorException {
            PlanParser psr = new PlanParser();
            Plan plan = psr.parse("relocate");
            StringBuilder s = new StringBuilder();
            plan.prettyPrint(s, 0);
            assertEquals("relocate\n", s.toString());
        }
        @Test
        public void done_test () throws SyntaxErrorException {
            PlanParser psr = new PlanParser();
            Plan plan = psr.parse("done");
            StringBuilder s = new StringBuilder();
            plan.prettyPrint(s, 0);
            assertEquals("done\n", s.toString());
        }
    }

    @Nested
    public class commandFailTest {
        @Test
        public void shootFailTest (){
            PlanParser psr = new PlanParser();
            assertThrows(SyntaxErrorException.class, () -> psr.parse("move"));
        }
        @Test
        public void moveFailTest () {
            PlanParser psr = new PlanParser();
            assertThrows(SyntaxErrorException.class, () -> psr.parse("shoot"));
        }
        @Test
        public void collectFailTest () {
            PlanParser psr = new PlanParser();
            assertThrows(SyntaxErrorException.class, () -> psr.parse("collect"));
        }
        @Test
        public void investFailTest () {
            PlanParser psr = new PlanParser();
            assertThrows(SyntaxErrorException.class, () -> psr.parse("invest"));
        }
    }

    @Test
    public void voidBlockStatement_test () throws SyntaxErrorException {
        PlanParser psr = new PlanParser();
        Plan plan = psr.parse("{}");
        StringBuilder s = new StringBuilder();
        plan.prettyPrint(s, 0);
        assertEquals("{}\n", s.toString());
    }

    @Test
    public void loopBlockStatement_test () throws SyntaxErrorException {
        PlanParser psr = new PlanParser();
        Plan plan = psr.parse("while(deposit) {if (budget - 100) then collect (deposit / 4) else {} }");
        StringBuilder s =new StringBuilder();
        plan.prettyPrint(s, 0);
        assertEquals("while (deposit) {\n\n\tif ((budget - 100)) then collect (deposit / 4)\n\telse {}\n}\n",s.toString());
    }

    @Test
    public void IfStatement_test () throws SyntaxErrorException {
        PlanParser psr = new PlanParser();
        Plan plan = psr.parse("if (deposit - 100) then collect (deposit / 4)\n else {}");
        StringBuilder s = new StringBuilder();
        plan.prettyPrint(s, 0);
        assertEquals("\nif ((deposit - 100)) then collect (deposit / 4)\nelse {}\n", s.toString());
    }

    @Test
    public void NestedIfStatement_test () throws SyntaxErrorException {
        PlanParser psr = new PlanParser();
        Plan plan = psr.parse("if (budget - 100) then if (budget - 1) then invest 1 else {} else {}");
        StringBuilder s = new StringBuilder();
        plan.prettyPrint(s, 0);
        assertEquals("\nif ((budget - 100)) then \n\tif ((budget - 1)) then invest 1\n\telse {}\nelse {}\n", s.toString());
    }
}