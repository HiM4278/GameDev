package com.example.evaluator.node.expression;

import com.example.evaluator.node.Node;
import com.example.exeption.EvalException;
import com.example.game.main.Player;

import java.util.Map;

public interface Expression extends Node {
    long eval(Map<String, Long> identifier, Player player) throws EvalException;
}
