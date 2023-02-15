package evaluator.node;

import evaluator.node.statement.Statement;

import java.util.LinkedList;

public class Plan implements Node {
    private final LinkedList<Statement> statements;

    public Plan() {
        this.statements = new LinkedList<>();
    }

    @Override
    public void prettyPrint(StringBuilder s, int depth) {
        for(Statement statement: statements) {
            statement.prettyPrint(s, depth+1);
            s.append("\n");
        }
    }

    public void append(Statement statement){
        statements.add(statement);
    }
}
