package board;

import player.Player;

import java.util.Arrays;

public class Village extends ToBuy {
    private float ryeness;
    public Village (String name, int priceEuro,int priceDollars, Player owner, float ryeness){
        super(name, priceEuro, priceDollars, owner);
        this.ryeness = ryeness;
    }
    public void update() {
        if (getOwner() != null) {
        }
    }

    //gettery i settery
    public float getRyeness(){
        return ryeness;
    }
    public void setRyeness(float ryeness){
        this.ryeness = ryeness;
    }
    public String toString() {
        return "Type: Village,Name: " + this.getName() + ",Owner: " + this.getOwner() + ",Price in euro: " + this.getPrice()[0] + ",Price in dollars: " + this.getPrice()[1] + ",Ryeness: " + this.getRyeness();
    }
}
