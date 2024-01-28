package test;

import board.Board;
import player.*;
import monopolyGame.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PawnTest {
    private GamePanel gamepanel;
    private Board board;
    private Pawn pawn;
    @Before
    public void setUp(){
        gamepanel=new GamePanel();
        gamepanel.createBoard(36);
        board =gamepanel.getBoard();
        pawn= board.getPawns()[0];
    }
    @Test
    public void placePawnOn()
    {
        pawn.setPosition(10);
        int result= 20;
        pawn.placePawnOn(20);
        assertEquals(result, pawn.getPosition());

        pawn.setPosition(30);
        int result1= 2;
        pawn.placePawnOn(2);
        assertEquals(result1, pawn.getPosition());

        pawn.setPosition(0);
        int result2= 10;
        pawn.placePawnOn(10);
        assertEquals(result2, pawn.getPosition());
    }
}
