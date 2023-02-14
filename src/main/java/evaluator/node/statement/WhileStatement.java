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
    public void prettyPrint(StringBuilder s) {
    }

    @Override
    public boolean execute(HashMap<String, Integer> identifier) throws EvalException {
        boolean isEnd = false;
        while(condition.eval(identifier) > 0 && !isEnd){
            isEnd = statement.execute(identifier);
        }
        return isEnd;
    }
}
