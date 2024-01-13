package board;

import Buildings.Building;
import Buildings.Farm;
import Buildings.House;
import player.Player;

import java.util.Arrays;

public class Village extends ToBuy {
    private int ryeness;
    public Village (String name, int priceEuro,int priceDollars, Player owner, int ryeness){
        super(name, priceEuro, priceDollars, owner);
        this.ryeness = ryeness;
    }
    public void update() {
        if (getOwner() != null) {
        }
    }

    //gettery i settery
    public int getRyeness(){
        return ryeness;
    }
    public void setRyeness(int ryeness){
        this.ryeness = ryeness;
    }
    public String toString() {
        try {
            return "Type: Village,Name: " + this.getName() + ",Owner: " + this.getOwner().getName() + ",Price in euro: " + this.getPrice()[0] + ",Price in dollars: " + this.getPrice()[1] + ",Ryeness: " + this.getRyeness();
        } catch (NullPointerException e) {
            return "Type: Village,Name: " + this.getName() + ",Owner: " + "null" + ",Price in euro: " + this.getPrice()[0] + ",Price in dollars: " + this.getPrice()[1] + ",Ryeness: " + this.getRyeness();
        }

    }
    public void addBuilding() {
        this.addBuilding(new Farm(this.ryeness));
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

}
