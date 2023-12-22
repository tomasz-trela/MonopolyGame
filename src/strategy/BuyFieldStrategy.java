package strategy;
import board.Board;
import board.ToBuy;
import player.Player;

public class BuyFieldStrategy implements ActionStrategy {
    @Override
    public void action(Board board) {

        Player currentPlayer = board.getPlayers()[board.getRound()];
        int currentIndex = currentPlayer.getFieldIndex();
        ToBuy field = (ToBuy) board.getFieldsArray()[currentIndex];

        field.setOwner(currentPlayer);
        currentPlayer.decreaseBalance(field.getPrice());
        currentPlayer.getOwnedFields().add(field);
    }
}
