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
    public void prettyPrint(StringBuilder s, int depth) {
        s.append("{");
        if(!statements.isEmpty()){
            s.append("\n");
        }
        for(Statement statement: statements) {
            // new line
//            Statement.super.prettyPrint(s,depth);
            statement.prettyPrint(s, depth);
            s.append("\n");
        }
        // new line
        if(!statements.isEmpty()){
            Statement.super.prettyPrint(s,depth-1);
        }
        s.append("}");
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
