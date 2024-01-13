package board;

import Buildings.Farm;
import player.Player;

public class Village extends ToBuy {
    private int ryeness;
    public Village (String name, int priceEuro,int priceDollars, Player owner, int ryeness){
        super(name, priceEuro, priceDollars, ryeness, owner);
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
        String price;
        if (this.getPrice()[0] == 0) {
            price = ",Price dollars: " + this.getPrice()[1];
        }
        else {
            price = ",Price euro: " + this.getPrice()[0];
        }
        try {
            return "Type: Village,Name: " + this.getName() + ",Owner: " + this.getOwner().getName() + price + ",Ryeness: " + this.getRyeness();
        } catch (NullPointerException e) {
            return "Type: Village,Name: " + this.getName() + ",Owner: " + "null" + price + ",Ryeness: " + this.getRyeness();
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
