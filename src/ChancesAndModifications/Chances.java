package ChancesAndModifications;

import board.Board;
import player.Player;

public class Chances {

    private String text;
    private int [] moneyChange;
    private int [] moneyGivenPerPlayer;
    private int positionChange;
    private int finalPosition;
    private int taxPerHouse;
    public Chances(String text, int [] moneyChange, int [] moneyGivenPerPlayer, int positionChange, int finalPosition, int taxPerHouse){
        this.text = text;
        this.moneyChange = moneyChange;
        this.moneyGivenPerPlayer = moneyGivenPerPlayer;
        this.positionChange = positionChange;
        this.finalPosition = finalPosition;
        this.taxPerHouse = taxPerHouse;
    }
    public void execute() {
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
    public int getTaxPerHouse() {
        return taxPerHouse;
    }
    public void setTaxPerHouse(int taxPerHouse) {
        this.taxPerHouse = taxPerHouse;
    }

}

