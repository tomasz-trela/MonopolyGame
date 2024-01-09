package strategy;

import board.Board;
import board.Chance;

public class ChanceStrategy implements ActionStrategy{
    @Override
    public void action(Board board) {
        int currentIndex = (board.getCurrentPlayer()).getFieldIndex();
        Chance field = (Chance) Board.getFieldsArray()[currentIndex];
        field.doChance(board);
    }
}
