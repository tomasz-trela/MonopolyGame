package strategy;
import Buildings.Building;
import Buildings.Farm;
import Buildings.House;
import board.*;
import player.Player;
public class BuyBuildingStrategy implements ActionStrategy {
    private static int temp1 = 0;
    private static int temp2 = 0;
    @Override
    public void action(Board board) {
        ToBuy field = (ToBuy) Board.getFieldsArray()[board.getCurrentPlayer().getFieldIndex()];
        if (field instanceof City){
            field.addBuilding(new House());
        }
        if (field instanceof Village){
            field.addBuilding(new Farm());
        }

    }
}
