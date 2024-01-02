package observer;

import board.Exchange;

public class AffluenceObserver implements Observer{
    //O tyle będzie wzrastać wartość waluty przy wydaniu jej 
    private final double rateModifier = 0.05;

    Exchange exchange = new Exchange();
    Subject subjects = new Subject();
    
    // Metody zwiększające wartość wydanej waluty
    @Override
    public void updateDollar(double dollarRate) {
        dollarRate = dollarRate + rateModifier;
        subjects.notify();
    }

    @Override
    public void updateEuro(double euroRate) {
        euroRate = euroRate + rateModifier;
        subjects.notify();
    }

    // gettery
    public double getAffluenceEuro() {
        return exchange.getEuroRate() / exchange.getDolarRate();
    }

    public double getAffluenceDollar() {
        return exchange.getDolarRate() / exchange.getEuroRate();
    }
}
