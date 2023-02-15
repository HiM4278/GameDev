package evaluator.node.statement;

import evaluator.node.expression.Expression;
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
    public void prettyPrint(StringBuilder s, int depth) {
        // new line
        Statement.super.prettyPrint(s,depth);
        s.append("if (");
        condition.prettyPrint(s, depth);
        s.append(")");
        statementIfTrue.prettyPrint(s, depth);
        // new line
        Statement.super.prettyPrint(s,depth);
        s.append("else");
        statementIfFalse.prettyPrint(s, depth);
    }

    @Override
    public boolean execute(HashMap<String, Integer> identifiers) throws EvalException {
        if(condition.eval(identifiers) > 0){
            return statementIfTrue.execute(identifiers);
        }else {
            return statementIfFalse.execute(identifiers);
        }
    }
}
