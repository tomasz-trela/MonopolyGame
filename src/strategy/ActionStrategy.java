package strategy;

import board.Board;
import board.Field;
import player.Player;

public interface ActionStrategy {
    public abstract void action(Board board);

}
