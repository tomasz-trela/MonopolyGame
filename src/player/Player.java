package player;

import board.*;
import strategy.*;

import java.io.File;
import java.util.ArrayList;

import static player.Dice.Roll;

public class Player {
    private int fieldIndex;
    private int lap;
    private int[] balance = new int[2];
    private boolean haveCar;
    private ArrayList<Field> OwnedFields;
    private Field location;
    protected ActionStrategy actionStrategy;
    private String name;

    public Player() {
        balance[0] = 500000; //euro
        balance[1] = 500000; //dolary
        this.haveCar = false;
        this.OwnedFields = new ArrayList<>();
        this.lap = 0;
        this.location = null;
        this.actionStrategy = null;
        this.name = "Player";
    }
    public Player(String name) {
        balance[0] = 500000; //euro
        balance[1] = 500000; //dolary
        this.haveCar = false;
        this.OwnedFields = new ArrayList<>();
        this.lap = 0;
        this.location = null;
        this.actionStrategy = null;
        this.name = name;
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

    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }

    public void movePlayer(int roll) {
        int currentIndex = fieldIndex;
        int newIndex = (currentIndex + roll) % 36;
        setFieldIndex(newIndex);
        setLap(getLap() + 1);
    }

    public void playerAction(Board board) {
        if (actionStrategy != null) {
            actionStrategy.action(board);
        }
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
                setActionStrategy(null);
                if (OwnedFields.contains(location)){
                    setActionStrategy(new BuyBuildingStrategy());
                }

            }

        }
        if(location instanceof Chance){
            setActionStrategy(new ChanceStrategy());
        }
        if(location instanceof Exchange){
            setActionStrategy(new ChangeStrategy());
        }
        if(location instanceof CarDealership){
            setActionStrategy(new BuyCarStrategy());
        }
        if(location instanceof Start){
            setActionStrategy(null);
        }

    }
    public void useCar(Board board, int round, int sum){
        board.getPlayers()[round].movePlayer(sum);
        board.ChangePlayerLocation(sum);
        board.getCurrentPlayer().changeStrategy();
        board.getCurrentPlayer().setHaveCar(false);
    }


}