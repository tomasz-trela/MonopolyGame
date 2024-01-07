package observer;

import board.Board;
import player.Player;


/* LOGIKA AFFLUENCE_OBSERVER:
 * 
 * Klasa Affluence Obserwer implementuje interfejs obserwatora i sprawdza, czy waluta została wydana, by zwiększyć jej kurs
 * 
 * Liczymy sumę pieniędzy wszystkich graczy po to, 
 * by zwiększać kurs tylko jeśli gracze wydali pieniądze do banku, 
 * a nie dali innym graczom
 */


public class AffluenceObserver implements Observer{
    private int euroSum = 2000000; // TODO Tu trzeba zaktualizować przy optymalizacji liczb w grze
    private int dollarSum = 2000000; // TODO Tu trzeba zaktualizować przy optymalizacji liczb w grze
    private int[] currentAmountOfMoney;

    public AffluenceObserver(){
        currentAmountOfMoney = new int[2];
        currentAmountOfMoney[0] = euroSum;
        currentAmountOfMoney[1] = dollarSum;
    }

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
        int[] saveCurrentAmountOfMoney = currentAmountOfMoney;
        int[] currentSum = sumMoneyInTheGame();
        if (currentSum[0] < saveCurrentAmountOfMoney[0]) {
            System.out.println("Wydano Euro");
            return 0; // Wydano Euro
        }
        else if (currentSum[1] < saveCurrentAmountOfMoney[1]) {
            System.out.println("Wydano Dollary");
            return 1; // Wydano Dollary
        }
        System.out.println("Nie wydano Dollarow ani Euro");
        return -1; // Nie wydano pieniędzy
    }

    // Zwiększamy kursy walut tylko, jeśli dokonano nimi płatności
    @Override
    public void updateDollar(Board board) {
        if (moneyWasSpent() == 1) {
            board.setDollarRate(board.getDollarRate() + Observer.rateModifier);
            board.setEuroRate(1/ board.getDollarRate());
            updateCurrentMoneyAmount();
            System.out.print("Kurs Dollara: ");
            System.out.println(board.getDollarRate());
            System.out.print("Kurs Euro: ");
            System.out.println(board.getEuroRate());
        }
    }

    @Override
    public void updateEuro(Board board) {
        if (moneyWasSpent() == 0) {
            board.setEuroRate(board.getEuroRate() + Observer.rateModifier);
            board.setDollarRate(1/ board.getEuroRate());
            updateCurrentMoneyAmount();
            System.out.print("Kurs Dollara: ");
            System.out.println(board.getDollarRate());
            System.out.print("Kurs Euro: ");
            System.out.println(board.getEuroRate());
        }
    }
}