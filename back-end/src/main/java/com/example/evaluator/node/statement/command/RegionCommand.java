package com.example.evaluator.node.statement.command;

import com.example.evaluator.node.expression.Expression;
import com.example.evaluator.node.statement.Statement;
import com.example.exeption.EvalException;
import com.example.game.main.Player;

import java.util.HashMap;

public class RegionCommand implements Statement {
    private final Expression volume;
    private final RegionCommandType type;

    public RegionCommand(RegionCommandType type, Expression volume) {
        this.volume = volume;
        this.type = type;
    }

    @Override
    public boolean execute(HashMap<String, Long> identifiers, Player player) throws EvalException {
        if(type == RegionCommandType.COLLECT){
            return !player.getCrew().collect(volume.eval(identifiers));
        }else if(type == RegionCommandType.INVEST){
            player.getCrew().invest(volume.eval(identifiers));
        }
        return false;
    }

    @Override
    public void prettyPrint(StringBuilder s, int depth) {
        s.append(type.toString().toLowerCase());
        s.append(" ");
        volume.prettyPrint(s, depth);
    }

    public enum RegionCommandType {COLLECT,INVEST}
}
