package strategy;
import Buildings.Building;
import Buildings.Farm;
import Buildings.House;
import board.*;
import monopolyGame.GamePanel;
import player.Player;


import javax.swing.*;

public class BuyBuildingStrategy implements ActionStrategy {
    private static int temp1 = 0;
    private static int temp2 = 0;
    @Override
    public void action(Board board) {
        if (Board.getFieldsArray()[board.getCurrentPlayer().getFieldIndex()] instanceof ToBuy) {
            GamePanel.showBuildingPanel((ToBuy) Board.getFieldsArray()[board.getCurrentPlayer().getFieldIndex()]);
        }
    }
//    public void action(Board board) {
//        ToBuy field = (ToBuy) Board.getFieldsArray()[board.getCurrentPlayer().getFieldIndex()];
//        Player currentPlayer = board.getPlayers()[board.getRound()];
//        if (currentPlayer.getBalance()[0] > ((ToBuy) board.getCurrentPlayer().getLocation()).getCostOfBuilding()[0] && currentPlayer.getBalance()[1] > ((ToBuy) board.getCurrentPlayer().getLocation()).getCostOfBuilding()[1]){
//            if (field instanceof City){
//                field.addBuilding(new House());
//            }
//            if (field instanceof Village){
//                field.addBuilding(new Farm());
//            }
//            currentPlayer.decreaseBalance(((ToBuy) board.getCurrentPlayer().getLocation()).getCostOfBuilding());
//        }else{
//            JOptionPane.showMessageDialog(null, "You don't have enough money in your account");
//        }
//    }
}
