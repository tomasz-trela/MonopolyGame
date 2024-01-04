package observer;

import board.Board;
import monopolyGame.GameFrame;
import player.Player;

import java.util.Arrays;

/* LOGIKA AFFLUENCE_OBSERVER:
 * 
 * Klasa Affluence Obserwer implementuje interfejs obserwatora i sprawdza, czy waluta została wydana, by zwiększyć jej kurs
 * 
 * Liczymy sumę pieniędzy wszystkich graczy po to, 
 * by zwiększać kurs tylko jeśli gracze wydali pieniądze do banku, 
 * a nie dali innym graczom
 */


public class AffluenceObserver implements Observer{
    private int euroSum = 0;
    private int dollarSum = 0;
    private int[] currentAmountOfMoney;

    public AffluenceObserver(){
        currentAmountOfMoney = new int[2];

    }

    // Instancja Boarda w GamePanelu w GameFrame'ie
    // // Euro i Dollar rates
    // double dollarRate = Board.getExchange().getDolarRate();
    // double euroRate = Board.getExchange().getEuroRate();

    // // Wyrażenie Dollara przez Euro i Euro przez Dollara
    // double euroByDollar = euroRate / dollarRate;
    // double dollarByEuro = dollarRate / euroRate;

    // Metoda sumująca wszystkie pieniądze graczy
    public int[] sumMoneyInTheGame() {
        euroSum = 0;
        dollarSum = 0;

        for (Player player : (Board.GetPlayersArray())) {
            euroSum = euroSum + player.getBalance()[0];
            dollarSum = dollarSum + player.getBalance()[1];
        }

        return new int[] {euroSum, dollarSum};
    }

    // Metoda aktualizująca liczbę wszystkich pieniędzy w grze, gdy gracz wyda kase do banku
    public void updateCurrentMoneyAmount() {
        this.currentAmountOfMoney = sumMoneyInTheGame();
    }

    // Metoda sprawdzająca czy gracze wydali euro do banku
    public int moneyWasSpent() {
        if (sumMoneyInTheGame()[0] < currentAmountOfMoney[0]) {
            return 0; // Wydano Euro
        }
        else if (sumMoneyInTheGame()[1] < currentAmountOfMoney[1]) {
            return 1; // Wydano Dollary
        }
        return -1; // Nie wydano pieniędzy
    }

    // Zwiększamy kursy walut tylko, jeśli dokonano nimi płatności
    @Override
    public void updateDollar(Board board) {
        if (moneyWasSpent() == 1) {
            board.setDollarRate(board.getDollarRate() + Observer.rateModifier);
            updateCurrentMoneyAmount();
            System.out.println(board.getDollarRate());
        }
    }

    @Override
    public void updateEuro(Board board) {
        if (moneyWasSpent() == 0) {
            board.setEuroRate(board.getEuroRate() + Observer.rateModifier);
            updateCurrentMoneyAmount();
            System.out.println(board.getEuroRate());
        }
    }
}