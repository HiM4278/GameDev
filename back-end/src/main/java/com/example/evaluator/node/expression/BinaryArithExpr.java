package com.example.evaluator.node.expression;

import com.example.exeption.EvalException;

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
    public long eval(Map<String, Long> identifier) throws EvalException {
        long lv = left.eval(identifier);
        long rv = right.eval(identifier);
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
