package strategy;

import ChancesAndModifications.Car;
import board.Board;
import board.CarDealership;
import board.ToBuy;
import player.Player;

import static player.Dice.Roll;

public class BuyCarStrategy implements ActionStrategy {
    @Override
    public void action(Board board) {
        Player currentPlayer = board.getPlayers()[board.getRound()];
        currentPlayer.setHaveCar(true);
    }
}
