package ChancesAndModifications;

import board.Board;
import player.Player;

public class Chances {

    private String text;
    private int moneyChange;
    private int moneyGivenPerPlayer;
    private int positionChange;
    private int finalPosition;
    private int taxPerHouse;
    private int taxPerHotel;
    private int taxPerGround;
    private int taxPerPlayer;
    public Chances(String text, int moneyChange, int moneyGivenPerPlayer, int positionChange, int finalPosition, int taxPerHouse, int taxPerHotel, int taxPerGround, int taxPerPlayer){
        this.text = text;
        this.moneyChange = moneyChange;
        this.moneyGivenPerPlayer = moneyGivenPerPlayer;
        this.positionChange = positionChange;
        this.finalPosition = finalPosition;
        this.taxPerHouse = taxPerHouse;
        this.taxPerHotel = taxPerHotel;
        this.taxPerGround = taxPerGround;
        this.taxPerPlayer = taxPerPlayer;
    }
    public void doChance(){
        System.out.println(text);
    }


}
