package board;

import player.Player;

public class Village extends ToBuy {
    private float ryeness;
    public Village (String name, int index, int price, int revenue, Player owner, float ryeness){
        super(name, index, price, revenue, owner);
        this.ryeness = ryeness;
    }

    //gettery i settery
    public float getRyeness(){
        return ryeness;
    }
    public void setRyeness(float ryeness){
        this.ryeness = ryeness;
    }
}
