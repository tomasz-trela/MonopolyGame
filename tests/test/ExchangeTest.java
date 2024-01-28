package test;

import board.Board;

import org.junit.Test;
import static org.junit.Assert.assertArrayEquals;

public class ExchangeTest {



    @Test
    public void exchangeEURtoUSD() {
        Board board = new Board();
        board.setDollarRate(0.8);
        int [] result = new int[]{-100,80};
        assertArrayEquals(result, board.ExchangeEURtoUSD(1000, 100, board));

        board.setDollarRate(20);
        int [] result1 = new int[]{-200,4000};
        assertArrayEquals(result1, board.ExchangeEURtoUSD(1000, 200, board));

        board.setDollarRate(0.2);
        int [] result2 = new int[]{-150, 30};
        assertArrayEquals(result2, board.ExchangeEURtoUSD(1000, 150, board));

    }

    @Test
    public void exchangeUSDtoEUR() {
        Board board = new Board();
        board.setEuroRate(0.8);
        int [] result = new int[]{80,-100};
        assertArrayEquals(result, board.ExchangeUSDtoEUR(1000, 100, board));

        board.setEuroRate(20);
        int [] result1 = new int[]{4000,-200};
        assertArrayEquals(result1, board.ExchangeUSDtoEUR(1000, 200, board));

        board.setEuroRate(0.2);
        int [] result2 = new int[]{30, -150};
        assertArrayEquals(result2, board.ExchangeUSDtoEUR(1000, 150, board));

    }
}