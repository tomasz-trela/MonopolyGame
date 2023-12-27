package strategy;

import board.Board;
import board.CarDealership;
import board.ToBuy;
import player.Player;

public class BuyCarStrategy implements ActionStrategy {
    @Override
    public void action(Board board) {
        Player currentPlayer = board.getPlayers()[board.getRound()];
        currentPlayer.setHaveCar(true);
    }
}
