package player;

import board.Board;
import board.Field;
import board.Start;

import java.io.File;
import java.util.ArrayList;

public class Player {
    private int fieldIndex;
    private int lap;
    private double balanceEuro;
    private double balanceDolar;
    private boolean haveCar;
    private ArrayList<Integer> OwnedFields = new ArrayList<Integer>();
    private Field location;

    public Player(){
        this.balanceEuro = 50000;
        this.balanceDolar = 50000;
        this.haveCar = false;
        this.OwnedFields.clear();
        this.lap = 0;
        this.location = null;
    }
    public void setLocation(Field location) {
        this.location = location;
    }
    public Field getLocation() {
        return location;
    }

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

    public int getFieldIndex() {
        return fieldIndex;
    }

    public void setFieldIndex(int fieldIndex) {
        this.fieldIndex = fieldIndex;
    }

    //nie lepiej zamiast całej klasy kostka tutaj to zrobic ? (jak coś to to zmieć)
    public int rzutKostka() {
        return (int) ((Math.random() * 6) + 1);
    }

    public void movePlayer(int roll) { // metoda zmienia obecne pole gracza, na pole o indeksie o liczbe oczek wieksze
        int currentIndex = fieldIndex;
        if ((currentIndex + roll) < 36) {
            currentIndex += roll;
            setFieldIndex(currentIndex);
        } else {
            int newIndex = (currentIndex + roll) % 36;
            setFieldIndex(newIndex);
            setLap(getLap() + 1);
        }


    }
}