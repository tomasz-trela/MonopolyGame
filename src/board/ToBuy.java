package board;
import Buildings.Building;
import player.Player;

import java.util.ArrayList;

public abstract class ToBuy extends Field {
    private Player owner;
    private int[] price = new int[2];

    //private Country country;
    private Building[] Buildings;

    public ToBuy(String name, int priceEuro, int priceDollars, Player owner) {
        super(name);
        this.price[0] = priceEuro;
        this.price[1] = priceDollars;
        this.Buildings = new Building[3];
        this.owner = owner;
    }
    public ToBuy() {
        super();
        this.price[0] = 1000;
        this.price[1] = 0;
        this.Buildings = new Building[3];
    }
    public int[] getPrice() {
        return price;
    }
    public Player getOwner() {
        return owner;
    }
    public void setPrice(int priceEuro,int priceDollars) {
        this.price[0] = priceEuro;
        this.price[1] = priceDollars;
    }
    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public void setBuildings(Building[] buildings) {
        Buildings = buildings;
    }

    public Building[] getBuildings() {
        return Buildings;
    }
    public String toString() {
        try {
            return "Type: null,Name: " + this.getName() + ",Owner: " + this.getOwner().getName() + ",Price in euro: " + this.getPrice()[0] + ",Price in dollars: " + this.getPrice()[1];
        } catch (NullPointerException e) {
            return "Type: null,Name: " + this.getName() + ",Owner: " + "null" + ",Price in euro: " + this.getPrice()[0] + ",Price in dollars: " + this.getPrice()[1];
        }
    }
}
