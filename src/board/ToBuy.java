package board;
import Buildings.Building;
import player.Player;

import java.util.ArrayList;

public abstract class ToBuy extends Field {
    private Player owner;
    private int[] price = new int[2];

    //private Country country;
    private ArrayList<Building> Buildings;
    public ToBuy(String name, int priceEuro, int priceDollars, Player owner) {
        super(name);
        this.price[0] = priceEuro;
        this.price[1] = priceDollars;
        this.Buildings = null;
        this.owner = owner;
    }
    public ToBuy() {
        super();
        this.price[0] = 1000;
        this.price[1] = 0;
        this.Buildings = null;
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
}
