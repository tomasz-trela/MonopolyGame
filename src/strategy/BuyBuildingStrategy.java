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
        int currentIndex = (board.getCurrentPlayer()).getFieldIndex();
        ToBuy field = (ToBuy) Board.getFieldsArray()[currentIndex];
        Building[] buildings = field.getBuildings();
        if (field instanceof City){
            if (temp1 < 3){
                if (buildings[temp1] == null){
                    buildings[temp1] = new House();
                    temp1++;
                }
            }
        }
        if (field instanceof Village){
            if (temp2 < 3) {
                if (buildings[temp2] == null){
                    buildings[temp2] = new Farm();
                    temp2++;
                }
            }
        }

    }
}
