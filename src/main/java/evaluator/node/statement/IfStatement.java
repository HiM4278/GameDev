package evaluator.node.statement;

import evaluator.node.Expression;
import exeption.EvalException;

import java.util.HashMap;

public class IfStatement implements Statement{
    private final Expression condition;
    private final Statement statementIfTrue;
    private final Statement statementIfFalse;

    public IfStatement(Expression condition, Statement statementIfTrue, Statement statementIfFalse) {
        this.condition = condition;
        this.statementIfTrue = statementIfTrue;
        this.statementIfFalse = statementIfFalse;
    }

    @Override
    public void prettyPrint(StringBuilder s) {

    }

    @Override
    public boolean execute(HashMap<String, Integer> identifier) throws EvalException {
        if(condition.eval(identifier) > 0){
            return statementIfTrue.execute(identifier);
        }else {
            return statementIfFalse.execute(identifier);
        }
    }
}
