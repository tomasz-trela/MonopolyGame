package board;

import player.Player;

public class City extends ToBuy {
    private int size;
    private float touristAttraction;
    public City (String name, int priceEuro,int priceDollars, Player owner, int size, float touristAttraction){
        super(name, priceEuro, priceDollars, owner);
        this.size = size;
        this.touristAttraction = touristAttraction;
    }
    public int getSize(){
        return size;
    }
    public float getTouristAttraction(){
        return touristAttraction;
    }
    public void setSize(int size){
        this.size = size;
    }
    public void setTouristAttraction(float touristAttraction){
        this.touristAttraction = touristAttraction;
    }
}
