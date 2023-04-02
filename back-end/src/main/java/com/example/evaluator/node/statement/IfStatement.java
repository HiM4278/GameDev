package com.example.evaluator.node.statement;

import com.example.evaluator.node.expression.Expression;
import com.example.exeption.EvalException;

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
//        if(s.substring(s.length()-5,s.length()).equals("then ")) {
//            s.append("\n");
//            Statement.super.prettyPrint(s,depth);
//        }else if(!s.substring(s.length()-5,s.length()).equals("else ")){
//            Statement.super.prettyPrint(s,depth);
//        }
        s.append("\n");
        Statement.super.prettyPrint(s,depth);
        s.append("if (");
        condition.prettyPrint(s, depth);
        s.append(") then ");
        statementIfTrue.prettyPrint(s, depth+1);
        // new line
        s.append("\n");
        Statement.super.prettyPrint(s,depth);
        s.append("else ");
        statementIfFalse.prettyPrint(s, depth+1);
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
