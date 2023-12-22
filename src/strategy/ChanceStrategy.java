package strategy;

import board.Board;
import board.Field;
import board.ToBuy;
import player.Player;
import board.Chance;

public class ChanceStrategy implements ActionStrategy{
    @Override
    public void action(Board board) {
        int currentIndex = (board.getCurrentPlayer()).getFieldIndex();
        Chance field = (Chance) board.getFieldsArray()[currentIndex];
        field.drawChance();
    }
}
