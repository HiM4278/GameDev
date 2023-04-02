package com.example.game.object;

import com.example.extra.Direction;

public interface Unit extends GameObject{
    boolean move(Direction d);
}
