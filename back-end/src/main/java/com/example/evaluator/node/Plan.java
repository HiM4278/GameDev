package com.example.evaluator.node;

import com.example.evaluator.node.statement.Statement;
import com.example.exeption.EvalException;
import com.example.exeption.SyntaxErrorException;
import com.example.game.main.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Plan implements Node {
    private final ArrayList<Statement> statements;

    public Plan(ArrayList<Statement> statements) throws SyntaxErrorException {
        if(statements.isEmpty()){
            throw new SyntaxErrorException("The construction plan must contain at least one statement");
        }else {
            this.statements = statements;
        }
    }

    @Override
    public void prettyPrint(StringBuilder s, int depth) {
        for(Statement statement: statements) {
            statement.prettyPrint(s, depth);
            s.append("\n");
        }
    }

    public void execute(HashMap<String, Long> identifiers, Player player) throws EvalException {
        for(Statement statement: statements) {
            if(statement.execute(identifiers, player)){
                break;
            }
        }
    }


}
