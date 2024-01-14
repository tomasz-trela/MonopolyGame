package board;
import Buildings.Building;
import Buildings.House;
import player.Player;

import java.util.ArrayList;

public abstract class ToBuy extends Field {
    private Player owner;
    private int[] price = new int[2];
    private int stayFee;
    private ArrayList<Building> Buildings;

    public ToBuy(String name, int priceEuro, int priceDollars, int feature, Player owner) {
        super(name);
        this.price[0] = priceEuro;
        this.price[1] = priceDollars;
        this.stayFee = CalculateStayFee(feature);
        this.Buildings = new ArrayList<>();
        this.owner = owner;
    }
    public ToBuy() {
        super();
        this.price[0] = 1000;
        this.price[1] = 0;
        this.Buildings = new ArrayList<>();
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

    public void setBuildings(ArrayList<Building> buildings) {
        Buildings = buildings;
    }
    public void addBuilding(Building building) {
        this.Buildings.add(building);
    }
    public void addBuilding() {
        this.Buildings.add(new House());
    }

    public ArrayList<Building> getBuildings() {
        return Buildings;
    }
    public String toString() {
        String price;
        if (this.getPrice()[0] == 0) {
            price = ",Price in dollars: " + this.getPrice()[1];
        }
        else {
            price = ",Price in euro: " + this.getPrice()[0];
        }
        try {
            return "Type: null,Name: " + this.getName() + ",Owner: " + this.getOwner().getName() + price;
        } catch (NullPointerException e) {
            return "Type: null,Name: " + this.getName() + ",Owner: " + "null" + price;
        }
    }
    public int [] getCostOfBuilding() {
        if (this.getPrice()[0] == 0) {
            return new int[] {0, 500};
        }
        else if (this.getPrice()[1] == 0) {
            return new int[] {500, 0};
        }
        else
            return new int[] {500, 500};
    }
    public int NumberOfBuildings(){
        return Buildings.size();
    }
    public int CalculateStayFee(int feature){
        return 4000*feature;
    }
    public int getStayFee() {
        return stayFee;
    }
    public void setStayFee(int stayFee) {
        this.stayFee = stayFee;
    }
}
