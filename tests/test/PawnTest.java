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
        int reusult= 20;
        pawn.placePawnOn(20);
        assertEquals(reusult, pawn.getPosition());

        pawn.setPosition(30);
        int reusult2= 2;
        pawn.placePawnOn(2);
        assertEquals(reusult2, pawn.getPosition());

        pawn.setPosition(0);
        int reusult3= 10;
        pawn.placePawnOn(10);
        assertEquals(reusult3, pawn.getPosition());
    }
}
