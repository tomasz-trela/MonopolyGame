package strategy;
import board.Board;
import board.Field;
import board.ToBuy;
import player.Player;

public class BuyFieldStrategy implements ActionStrategy {
    @Override
    public void action(Board board) {

        Player currentPlayer = board.getPlayers()[board.getRound()];
        int currentIndex = currentPlayer.getFieldIndex();
        ToBuy field = (ToBuy) board.getFieldsArray()[currentIndex];
        int euroCost = field.getPrice()[0];
        int dollarCost = field.getPrice()[1];

        field.setOwner(currentPlayer);
        currentPlayer.decreaseBalance(euroCost, dollarCost);
    }
}
