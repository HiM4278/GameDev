package evaluator.node.statement;

import evaluator.node.expression.Expression;
import exeption.EvalException;

import java.util.HashMap;

public class WhileStatement implements Statement{
    private final Expression condition;
    private final Statement statement;

    public WhileStatement(Expression condition, Statement statement) {
        this.condition = condition;
        this.statement = statement;
    }

    @Override
    public void prettyPrint(StringBuilder s, int depth) {
        // new line
        Statement.super.prettyPrint(s,depth);
        s.append("while (");
        condition.prettyPrint(s, depth);
        s.append(") ");
        statement.prettyPrint(s, depth+1);
    }

    @Override
    public boolean execute(HashMap<String, Integer> identifiers) throws EvalException {
        boolean isEnd = false;
        while(condition.eval(identifiers) > 0 && !isEnd){
            isEnd = statement.execute(identifiers);
        }
        return isEnd;
    }
}
