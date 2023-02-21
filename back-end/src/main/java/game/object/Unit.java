package game.object;

import extra.Direction;

public interface Unit extends GameObject{
    boolean move(Direction d);
}
