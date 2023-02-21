package evaluator.node;

import evaluator.node.statement.Statement;
import exeption.SyntaxErrorException;
import java.util.List;

public class Plan implements Node {
    private final List<Statement> statements;

    public Plan(List<Statement> statements) throws SyntaxErrorException {
        if(statements.isEmpty()){
            throw new SyntaxErrorException("The construction plan must contain at least one statement");
        }else {
            this.statements = statements;
        }
    }

    @Override
    public void prettyPrint(StringBuilder s, int depth) {
        for(Statement statement: statements) {
            statement.prettyPrint(s, depth);
            s.append("\n");
        }
    }
}
