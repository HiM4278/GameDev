package com.example.evaluator.node.expression;

import com.example.extra.Direction;
import com.example.exeption.EvalException;
import com.example.game.main.Player;
import com.example.game.main.Region;

import java.util.Map;

public class Info implements Expression {
    InformationType type;
    Direction direction;
    public Info (InformationType type, Direction direction){
        this.type = type;
        this.direction = direction;
    }
    @Override
    public long eval(Map<String, Long> identifier, Player player) throws EvalException {
        if(type == InformationType.OPPONENT){
            Direction[] directions = {Direction.UP, Direction.UPRIGHT, Direction.DOWNRIGHT, Direction.DOWN, Direction.DOWNLEFT, Direction.UPLEFT};
            int min = -1;
            int d = 0;
            for(int i = 0; i < 6; i++) {
                Region current = player.getCrew().getPosition();
                int count = 1;
                while(current.getNeighbor(directions[i]) != null){
                    if(current.getNeighbor(directions[i]).getLand() != null && current.getNeighbor(directions[i]).getLand().getOwner() != player) {
                        if(min < 0){
                            min = count;
                        }else {
                            if(count < min){
                                min = count;
                            }
                        }
                        d = i + 1;
                        break;
                    }else {
                        current = current.getNeighbor(directions[i]);
                        count++;
                    }
                }
            }
            if(min < 0) {
                return 0;
            }else {
                return min * 10L + d;
            }
        }else {
            Region current = player.getCrew().getPosition();
            int count = 1;
            while(current.getNeighbor(direction) != null){
                if(current.getNeighbor(direction).getLand() != null && current.getNeighbor(direction).getLand().getOwner() != player) {
                    return 100L *count+ Integer.toString((int)current.getNeighbor(direction).getLand().getDeposit()).length();
                }else {
                    current = current.getNeighbor(direction);
                    count++;
                }
            }
            return 0;
        }
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
