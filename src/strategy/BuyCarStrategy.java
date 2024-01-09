package strategy;

import ChancesAndModifications.Car;
import board.Board;
import board.CarDealership;
import board.ToBuy;
import player.Player;

import java.util.Random;

import static player.Dice.Roll;

public class BuyCarStrategy implements ActionStrategy {
    @Override
    public void action(Board board) {
        Player currentPlayer = board.getPlayers()[board.getRound()];

        int currentIndex = currentPlayer.getFieldIndex();
        CarDealership field = (CarDealership) Board.getFieldsArray()[currentIndex];

        Random random = new Random();
        currentPlayer.decreaseBalance(field.getCar(random.nextInt(2)).getPrice());

        currentPlayer.setHaveCar(true);
    }
}
