package evaluator.node.statement;

import exeption.EvalException;

import java.util.HashMap;
import java.util.LinkedList;

public class BlockStatement implements Statement{
    private final LinkedList<Statement> statements;

    public BlockStatement() {
        statements = new LinkedList<>();
    }

    @Override
    public void prettyPrint(StringBuilder s) {

    }

    @Override
    public boolean execute(HashMap<String, Integer> identifiers) throws EvalException {
        boolean isEnd = false;
        for(Statement statement: statements) {
            isEnd = statement.execute(identifiers);
            if(isEnd) break;
        }
        return isEnd;
    }

    public void append(Statement statement){
        statements.add(statement);
    }
}
