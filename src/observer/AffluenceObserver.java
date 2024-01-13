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
    private int[] currentAmountOfMoney;
    private double modifier;
    private int euroSum;
    private int dollarSum;
    public AffluenceObserver(Board board){
        currentAmountOfMoney = new int[2];
        for(int i=0;i<board.getPlayers().length; i++){
            for(int k=0; k<currentAmountOfMoney.length; k++){
                currentAmountOfMoney[k]+=board.getPlayers()[i].getBalance()[k];
            }
        }
        euroSum = currentAmountOfMoney[0];
        dollarSum = currentAmountOfMoney[1];
    }

    // Metoda sumująca wszystkie pieniądze graczy
    public int[] sumMoneyInTheGame() {
        euroSum = 0;
        dollarSum = 0;

        for (Player player : (Board.GetPlayersArray())) {
            // terminateProgram(player.getBalance()[0], player.getBalance()[1]);
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
            modifier = ((double) saveCurrentAmountOfMoney[0]/(double)currentSum[0]-1)*Observer.rateModifier+1;
            return 0; // Wydano Euro
        }
        else if (currentSum[1] < saveCurrentAmountOfMoney[1]) {
            System.out.println("Wydano Dollary");
            modifier =((double)saveCurrentAmountOfMoney[1]/(double)currentSum[1]-1)*Observer.rateModifier+1;
            return 1; // Wydano Dollary
        }
        else {
            System.out.println("Nie wydano Dollarow ani Euro");
            return -1; // Nie wydano pieniędzy
        }
    }

    // Zwiększamy kursy walut tylko, jeśli dokonano nimi płatności
    @Override
    public void updateDollar(Board board) {
        if (moneyWasSpent() == 1) {
            board.setDollarRate(board.getDollarRate()/modifier);
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
            board.setEuroRate(board.getEuroRate()/modifier);
            board.setDollarRate(1/ board.getEuroRate());
            updateCurrentMoneyAmount();
            System.out.print("Kurs Dollara: ");
            System.out.println(board.getDollarRate());
            System.out.print("Kurs Euro: ");
            System.out.println(board.getEuroRate());
        }
    }

    // // Zakończ rozgrywkę
    // public void terminateProgram(int euroBalance, int dollarBalance) {
    //     if (euroBalance == 0 || dollarBalance == 0) {
    //         System.out.println("A player has a balance of 0. Terminating program.");
    //         System.exit(0);
    //     }
    // }

    // // TODO metoda wyświetlająca statystyki
    // public void showGameStats() {

    // }
    // +1
}