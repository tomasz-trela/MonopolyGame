package strategy;

import board.Board;
import board.Exchange;
import board.Field;
import player.Player;
import monopolyGame.GamePanel;

import javax.swing.*;

public class ChangeStrategy implements ActionStrategy{
    @Override
    public void action(Board board) {
        Player currentPlayer = board.getPlayers()[board.getRound()];
        currentPlayer.setCanExchange(true);
    }
}
