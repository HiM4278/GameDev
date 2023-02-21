package evaluator.node.expression;

import extra.Direction;
import exeption.EvalException;

import java.util.HashMap;

public class Info implements Expression {
    InformationType type;
    Direction direction;
    public Info (InformationType type, Direction direction){
        this.type = type;
        this.direction = direction;
    }
    @Override
    public int eval(HashMap<String, Integer> identifier) throws EvalException {
        return 0;
    }

    @Override
    public void prettyPrint(StringBuilder s, int depth) {
        s.append(type.toString().toLowerCase());
        if(type == InformationType.NEARBY) {
            s.append(" ");
            s.append(direction.toString().toLowerCase());
        }
    }

    public enum InformationType {OPPONENT,NEARBY}
}
