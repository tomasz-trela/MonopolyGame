package observer;

import board.Board;
import board.Exchange;

public class ExchangeObserver implements Observer{

    // Nowe zmienne dla Przejrzystości
    private Exchange kantor = Board.getExchange(); 

    // Metody zwiększające kurs waluty
    @Override
    public void updateDollar() {
        kantor.setDolarRate(Board.getExchange().getDolarRate() + Observer.rateModifier);
    }

    @Override
    public void updateEuro() {
        kantor.setEuroRate(Board.getExchange().getEuroRate() + Observer.rateModifier);
    }
}
