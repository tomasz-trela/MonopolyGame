package player;

import board.Board;
import board.Field;
import board.Start;

import java.util.ArrayList;

public class Player {
    private int lap;
    private double balanceEuro;
    private double balanceDolar;
    private boolean haveCar;
    private ArrayList<Integer> OwnedFields = new ArrayList<Integer>();
    private Field field;

    public Player(){
        this.balanceEuro = 50000;
        this.balanceDolar = 50000;
        this.haveCar = false;
        this.OwnedFields.clear();
        this.lap = 0;
        this.field = new Start();
    }

    //gettery settery


    public int getLap() {
        return lap;
    }

    public double getBalanceDolar() {
        return balanceDolar;
    }

    public double getBalanceEuro() {
        return balanceEuro;
    }

    public ArrayList<Integer> getOwnedFields() {
        return OwnedFields;
    }

    public void setBalanceDolar(double balanceDolar) {
        this.balanceDolar = balanceDolar;
    }

    public void setBalanceEuro(double balanceEuro) {
        this.balanceEuro = balanceEuro;
    }

    public void setHaveCar(boolean haveCar) {
        this.haveCar = haveCar;
    }

    public void setLap(int lap) {
        this.lap = lap;
    }

    public void setOwnedFields(ArrayList<Integer> ownedFields) {
        OwnedFields = ownedFields;
    }

    public boolean isHaveCar() {
        return haveCar;
    }

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }

    //nie lepiej zamiast całej klasy kostka tutaj to zrobic ? (jak coś to to zmieć)
    public int rzutKostka(){
        return (int) ((Math.random()*6) + 1); 
    }
    public void movePlayer(Board board){ // metoda zmienia obecne pole gracza, na pole o indeksie o liczbe oczek wieksze
        int roll = Cube.Roll() + Cube.Roll();
        Field currentField = field;
        int currentIndex = currentField.getIndex();
        currentIndex += roll;
        Field[] currentBoard = board.getFieldsArray();
        if (currentBoard != null){
            for (int i = 0; i < currentBoard.length; i++) {
                if (currentBoard[i] != null){
                    if (currentBoard[i].getIndex() == currentIndex + roll){
                        setField(currentBoard[i]);
                    }
                }
            }
        }

    }
}