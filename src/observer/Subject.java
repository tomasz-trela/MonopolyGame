package observer;

import java.util.ArrayList;
import board.Board;
import board.Field;
import board.ToBuy;

public class Subject {
    private ArrayList<Field> observers = new ArrayList<>();
    AffluenceObserver affluenceObserver = new AffluenceObserver();

    public Subject() {
        for (int i = 0; i < Board.getFieldsArray().length; i++) {
            observers.add(Board.getFieldsArray()[i]);
        }
    }

    public void notifyObservers() {
        for (int i = 0; i < observers.size(); i++) {
            if (observers.get(i) instanceof ToBuy) {
                ToBuy purchasable = (ToBuy) observers.get(i);

                purchasable.setPrice(purchasable.getPrice()[0], (int) (purchasable.getPrice()[0] * affluenceObserver.getAffluenceDollar()));  // Zmieńmy ceny na double, nie będą kosmicznie rosnąć by int wystarczył
            }
        }
    }
}
