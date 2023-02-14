package evaluator.node;

import evaluator.node.Directional;
import exeption.EvalException;

import java.util.HashMap;

public class Info implements Expression{
    InformationType type;
    Directional.Direction direction;
    public Info (InformationType type, Directional.Direction direction){
        this.type = type;
        this.direction = direction;
    }
    @Override
    public int eval(HashMap<String, Integer> identifier) throws EvalException {
        return 0;
    }

    @Override
    public void prettyPrint(StringBuilder s) {
        s.append(type);
    }

    public enum InformationType {OPPONENT,NEARBY}
}
