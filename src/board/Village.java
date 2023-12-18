package board;

import player.Player;

public class Village extends ToBuy {
    private float ryeness;
    public Village (String name, int price, Player owner, float ryeness){
        super(name, price, owner);
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
}
