package test;

import board.Board;

import org.junit.Assert;
import org.junit.Test;

public class EURtoUSDtest {


    @Test
    public void exchangeEURtoUSD() {
        Board board = new Board();
        board.setDollarRate(0.8);
        int [] result = new int[]{-100,80};
        Assert.assertArrayEquals(result, board.ExchangeEURtoUSD(1000, 100, board));

        board.setDollarRate(20);
        int [] result1 = new int[]{-200,4000};
        Assert.assertArrayEquals(result1, board.ExchangeEURtoUSD(1000, 200, board));

        board.setDollarRate(0.2);
        int [] result2 = new int[]{-150, 30};
        Assert.assertArrayEquals(result2, board.ExchangeEURtoUSD(1000, 150, board));

    }
}