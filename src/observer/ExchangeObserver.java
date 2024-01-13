package observer;

import board.Board;

public class ExchangeObserver implements Observer{

    // Metody zwiększające kurs waluty
    @Override
    public void updateDollar(Board board) {
        board.setDollarRate(board.getDollarRate() + Observer.rateModifier);
    }

    @Override
    public void updateEuro(Board board) {
        board.setEuroRate(board.getEuroRate() + Observer.rateModifier);
    }
}