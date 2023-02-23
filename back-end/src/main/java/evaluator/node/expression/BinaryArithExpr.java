package evaluator.node.expression;

import exeption.EvalException;

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
    public int eval(Map<String, Integer> identifier) throws EvalException {
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
    public void prettyPrint(StringBuilder s, int depth) {
        s.append("(");
        left.prettyPrint(s, depth);
        s.append(" ").append(op).append(" ");
        right.prettyPrint(s, depth);
        s.append(")");
    }
}
