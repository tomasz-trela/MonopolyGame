package strategy;
import board.Board;
import board.ToBuy;
import player.Player;

public class BuyFieldStrategy implements ActionStrategy {
    @Override
    public void action(Board board) {

        Player currentPlayer = board.getPlayers()[board.getRound()];
        int currentIndex = currentPlayer.getFieldIndex();
        ToBuy field = (ToBuy) Board.getFieldsArray()[currentIndex];

        if (currentPlayer.getBalance()[0]>field.getPrice()[0] && currentPlayer.getBalance()[1]>field.getPrice()[1]){
            field.setOwner(currentPlayer);
            currentPlayer.decreaseBalance(field.getPrice());
            currentPlayer.getOwnedFields().add(field);
        }

    }
}
