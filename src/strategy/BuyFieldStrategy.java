package strategy;
import board.Board;
import board.ToBuy;
import player.Player;

import javax.swing.*;

public class BuyFieldStrategy implements ActionStrategy {
    @Override
    public void action(Board board) {

        Player currentPlayer = board.getCurrentPlayer();
        int currentIndex = currentPlayer.getFieldIndex();
        ToBuy field = (ToBuy) Board.getFieldsArray()[currentIndex];

        if (currentPlayer.getBalance()[0]>field.getPrice()[0] && currentPlayer.getBalance()[1]>field.getPrice()[1]){
            field.setOwner(currentPlayer);
            currentPlayer.decreaseBalance(field.getPrice());
            currentPlayer.getOwnedFields().add(field);
        }else{
            JOptionPane.showMessageDialog(null, "You don't have enough money in your account");
        }

    }
}
