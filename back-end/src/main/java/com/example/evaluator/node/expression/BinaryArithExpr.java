package com.example.evaluator.node.expression;

import com.example.exeption.EvalException;
import com.example.game.main.Player;

import java.util.Map;

public class BinaryArithExpr implements Expression{
    Expression left,right;
    String op;
    public BinaryArithExpr(Expression left, String op, Expression right){
        this.left = left;
        this.op = op;
        this.right = right;
    }
    @Override
    public long eval(Map<String, Long> identifier, Player player) throws EvalException {
        long lv = left.eval(identifier,player );
        long rv = right.eval(identifier, player);
        if (op.equals("+")) return lv + rv;
        if (op.equals("-")) return lv - rv;
        if (op.equals("*")) return lv * rv;
        if (op.equals("/")) return lv / rv;
        if (op.equals("%")) return lv % rv;
        if (op.equals("^")) return (int) Math.pow(lv,rv);
        throw new EvalException("unknown operator: " + op);
    }

    @Override
    public void prettyPrint(StringBuilder s, int depth) {
        s.append("(");
        left.prettyPrint(s, depth);
        s.append(" ").append(op).append(" ");
        right.prettyPrint(s, depth);
        s.append(")");
    }
}
