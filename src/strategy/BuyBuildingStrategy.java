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
        Player currentPlayer = board.getPlayers()[board.getRound()];
        if (currentPlayer.getBalance()[0] > ((ToBuy) board.getCurrentPlayer().getLocation()).getCostOfBuilding()[0] && currentPlayer.getBalance()[1] > ((ToBuy) board.getCurrentPlayer().getLocation()).getCostOfBuilding()[1]){
            if (field instanceof City){
                field.addBuilding(new House());
            }
            if (field instanceof Village){
                field.addBuilding(new Farm());
            }
            currentPlayer.decreaseBalance(((ToBuy) board.getCurrentPlayer().getLocation()).getCostOfBuilding());
        }
    }
}
