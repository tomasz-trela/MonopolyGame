package ChancesAndModifications;

import player.Player;

public class Chances {

    private String text;
    private int moneyGiven;
    private int moneyGivenPerPlayer;
    private int positionChange;
    private int finalPosition;
    private int tax;
    private int taxPerHouse;
    private int taxPerHotel;
    private int taxPerGround;
    private int taxPerPlayer;
    public Chances(String text, int moneyGiven, int moneyGivenPerPlayer, int positionChange, int finalPosition, int tax, int taxPerHouse, int taxPerHotel, int taxPerGround, int taxPerPlayer){
        this.text = text;
        this.moneyGiven = moneyGiven;
        this.moneyGivenPerPlayer = moneyGivenPerPlayer;
        this.positionChange = positionChange;
        this.finalPosition = finalPosition;
        this.tax = tax;
        this.taxPerHouse = taxPerHouse;
        this.taxPerHotel = taxPerHotel;
        this.taxPerGround = taxPerGround;
        this.taxPerPlayer = taxPerPlayer;
    }
    public void doChance(){
        System.out.println(text);
        //Board.getCurrentPlayer().setMoney(Board.getCurrentPlayer().getMoney + moneyGiven);
        //Board.getCurrentPlayer().setMoney(Board.getCurrentPlayer().getMoney + moneyGivenPerPlayer * Board.getNumberOfPlayers());
        //Board.getCurrentPlayer().setPosition(Board.getCurrentPlayer().getPosition + positionChange);
        //Board.getCurrentPlayer().setPosition(finalPosition);
        //Board.getCurrentPlayer().setMoney(Board.getCurrentPlayer().getMoney - tax);
        //Board.getCurrentPlayer().setMoney(Board.getCurrentPlayer().getMoney - taxPerHouse * Board.getCurrentPlayer().getNumberOfHouses());
        //Board.getCurrentPlayer().setMoney(Board.getCurrentPlayer().getMoney - taxPerHotel * Board.getCurrentPlayer().getNumberOfHotels());
        //Board.getCurrentPlayer().setMoney(Board.getCurrentPlayer().getMoney - taxPerGround * Board.getCurrentPlayer().getNumberOfGrounds());
        //Board.getCurrentPlayer().setMoney(Board.getCurrentPlayer().getMoney - taxPerPlayer * Board.getNumberOfPlayers());
    }


}
