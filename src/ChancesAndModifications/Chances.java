package ChancesAndModifications;

import board.Board;
import player.Player;

public class Chances {

    private String text;
    private int [] moneyChange = new int[2];
    private int [] moneyGivenPerPlayer = new int[2];
    private int positionChange;
    private int finalPosition;
    //private int taxPerHouse;
    //private int taxPerHotel;
    //private int taxPerGround;
    //private int taxPerPlayer;
    public Chances(String text, int moneyChange, int moneyGivenPerPlayer, int positionChange, int finalPosition){
        this.text = text;
        this.moneyChange[0] = moneyChange; //w euro
        this.moneyGivenPerPlayer[1] = moneyGivenPerPlayer; //w dolarach
        this.positionChange = positionChange;
        this.finalPosition = finalPosition;
    }

    public int getPositionChange() {
        return positionChange;
    }

    public int getFinalPosition() {
        return finalPosition;
    }

    public String getText() {
        return text;
    }

    public void setMoneyGivenPerPlayer(int[] moneyGivenPerPlayer) {
        this.moneyGivenPerPlayer = moneyGivenPerPlayer;
    }

    public int[] getMoneyChange() {
        return moneyChange;
    }

    public int[] getMoneyGivenPerPlayer() {
        return moneyGivenPerPlayer;
    }

    public void setPositionChange(int positionChange) {
        this.positionChange = positionChange;
    }

    public void setMoneyChange(int[] moneyChange) {
        this.moneyChange = moneyChange;
    }

    public void setFinalPosition(int finalPosition) {
        this.finalPosition = finalPosition;
    }

    public void setText(String text) {
        this.text = text;
    }

}

