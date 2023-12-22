package player;

import board.*;
import strategy.ActionStrategy;
import strategy.BuyBuildingStrategy;
import strategy.BuyFieldStrategy;
import strategy.ChanceStrategy;

import java.io.File;
import java.util.ArrayList;

public class Player {
    private int fieldIndex;
    private int lap;
    private int[] balance = new int[2];
    private boolean haveCar;
    private ArrayList<Field> OwnedFields;
    private Field location;
    protected ActionStrategy actionStrategy;

    public Player() {
        balance[0] = 500000; //euro
        balance[1] = 500000; //dolary
        this.haveCar = false;
        this.OwnedFields = null;
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

    public int[] getBalance() {
        return balance;
    }

    public void setBalance(int[] newbalance) {
        for (int i = 0; i < newbalance.length; i++) balance[i] = newbalance[i];
    }

    public void increaseBalance(int[] cost) {
        for (int i = 0; i < cost.length; i++) balance[i] += cost[i];
    }

    public void decreaseBalance(int[] cost) {
        for (int i = 0; i < cost.length; i++) balance[i] -= cost[i];
    }

    public ArrayList<Field> getOwnedFields() {
        return OwnedFields;
    }

    public void setOwnedFields(ArrayList<Field> ownedFields) {
        OwnedFields = ownedFields;
    }

    public void setHaveCar(boolean haveCar) {
        this.haveCar = haveCar;
    }

    public void setLap(int lap) {
        this.lap = lap;
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

    public void movePlayer(int roll) {
        int currentIndex = fieldIndex;
        int newIndex = (currentIndex + roll) % 36;
        setFieldIndex(newIndex);
        setLap(getLap() + 1);
    }

    public void playerAction(Board board) {
        actionStrategy.action(board);
    }

    public void setActionStrategy(ActionStrategy actionStrategy) {
        this.actionStrategy = actionStrategy;
    }

    public void changeStrategy(){
        if(location instanceof ToBuy){
            if(((ToBuy) location).getOwner() == null){
                setActionStrategy(new BuyFieldStrategy());
            }
            else {
                if (OwnedFields.contains(location)){
                    setActionStrategy(new BuyBuildingStrategy());
                }

            }

        }
        if(location instanceof Chance){
            setActionStrategy(new ChanceStrategy());
        }
    }
}