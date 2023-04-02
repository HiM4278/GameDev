package com.example.evaluator.node.statement;

import com.example.evaluator.node.expression.Expression;
import com.example.exeption.EvalException;
import com.example.game.main.Player;

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
    public boolean execute(HashMap<String, Long> identifiers, Player player) throws EvalException {
        boolean isEnd = false;
        while(condition.eval(identifiers) > 0 && !isEnd){
            isEnd = statement.execute(identifiers, player);
        }
        return isEnd;
    }
}
