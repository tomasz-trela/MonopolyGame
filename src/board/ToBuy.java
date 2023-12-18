package board;
import Buildings.Building;
import player.Player;

import java.util.ArrayList;

public abstract class ToBuy extends Field {
    private Player owner;
    private int price;

    //private Country country;
    private ArrayList<Building> Buildings;
    public ToBuy(String name, int price, Player owner) {
        super(name);
        this.price = price;
        this.Buildings = null;
        this.owner = owner;
    }
    public ToBuy() {
        super();
        this.price = 1000;
        this.Buildings = null;
    }
    public int getPrice() {
        return price;
    }
    public Player getOwner() {
        return owner;
    }
    public void setPrice(int price) {
        this.price = price;
    }
    public void setOwner(Player owner) {
        this.owner = owner;
    }
}
