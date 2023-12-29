package strategy;

import board.Board;
import board.Field;
import player.Player;

public class ChangeStrategy implements ActionStrategy{
    @Override
    public void action(Board board) {
        System.out.println("wymiana waluty");
    }
}
