package observer;

import java.util.ArrayList;

public class Subject {
    private ArrayList<Observer> observers;

    ExchangeObserver exchangeObserver = new ExchangeObserver();
    AffluenceObserver affluenceObserver = new AffluenceObserver();

    public Subject() {
        observers = new ArrayList<>();
        registerObserver(exchangeObserver);
        registerObserver(affluenceObserver);
    }
    
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    public void notifyObserversEuro() {
        for (Observer observer : observers) {
            observer.updateEuro();
        }
    }

    public void notifyObserversDollar() {
        for (Observer observer : observers) {
            observer.updateDollar();
        }
    }
}