package evaluator.node;

import exeption.EvalException;

import java.util.HashMap;

public class BinaryArithExpr implements Expression{
    Expression left,right;
    String op;
    public BinaryArithExpr(Expression left, String op, Expression right){
        this.left = left;
        this.op = op;
        this.right = right;
    }
    @Override
    public int eval(HashMap<String, Integer> identifier) throws EvalException {
        int lv = left.eval(identifier);
        int rv = right.eval(identifier);
        if (op.equals("+")) return lv + rv;
        if (op.equals("-")) return lv - rv;
        if (op.equals("*")) return lv * rv;
        if (op.equals("/")) return lv / rv;
        if (op.equals("%")) return lv % rv;
        if (op.equals("^")) return (int) Math.pow(lv,rv);
        throw new EvalException("unknown operator: " + op);
    }

    @Override
    public void prettyPrint(StringBuilder s) {
        s.append("(");
        left.prettyPrint(s);
        s.append(op);
        right.prettyPrint(s);
        s.append(")");
    }
}
