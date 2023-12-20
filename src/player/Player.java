package player;

import board.Board;
import board.Field;
import board.Start;

import java.io.File;
import java.util.ArrayList;

public class Player {
    private int fieldIndex;
    private int lap;
    private int[] balance = new int[2];
    private boolean haveCar;
    private ArrayList<Integer> OwnedFields = new ArrayList<Integer>();
    private Field location;

    public Player(){
        balance[0] = 500000; //euro
        balance[1] = 500000; //dolary
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
    public int[] getBalance(){
        return balance;
    }
    public void setBalance(int[] newbalance){
        for(int i=0; i< newbalance.length; i++) balance[i]=newbalance[i];
    }
    public void increaseBalance(int[] cost){
        for(int i=0; i< cost.length; i++) balance[i]+=cost[i];
    }
    public void decreaseBalance(int[] cost){
        for(int i=0; i< cost.length; i++) balance[i]-=cost[i];
    }
    public ArrayList<Integer> getOwnedFields() {
        return OwnedFields;
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

    public void movePlayer(int roll) { // metoda zmienia obecne pole gracza, na pole o indeksie o liczbe oczek wieksze
        int currentIndex = fieldIndex;
            int newIndex = (currentIndex + roll) % 36;
            setFieldIndex(newIndex);
            setLap(getLap() + 1);
    }
}