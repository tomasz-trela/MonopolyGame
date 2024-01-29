package test;
import board.Board;
import org.junit.Before;
import org.junit.Test;
import player.Player;

import static org.junit.Assert.assertEquals;
public class MovePlayerTest {
    private Board board;
    private Player currentPlayer;
    @Before
    public void setUp(){
        board = new Board();
        board.generatePlayers(2);
        currentPlayer = board.getPlayers()[0];
    }
    @Test
    public void MovePlayerTest(){
        int roll = 12;
        board.setCurrentPlayer(currentPlayer);

        int result = 12;
        board.changePlayerLocation(roll);
        assertEquals(result, currentPlayer.getFieldIndex());

        int result1 = 24;
        board.changePlayerLocation(roll);
        assertEquals(result1, currentPlayer.getFieldIndex());

        int result2 = 0;
        board.changePlayerLocation(roll);
        assertEquals(result2, currentPlayer.getFieldIndex());
    }
}
