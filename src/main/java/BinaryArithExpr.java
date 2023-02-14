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
        return 0;
    }

    @Override
    public void prettyPrint(StringBuilder s) {

    }
}
