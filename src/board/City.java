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
    public String toString() {
        return "Type: City\nName: " + this.getName() + "\nOwner: " + this.getOwner() + "\nPrice in euro: " + this.getPrice()[0] + "\nPrice in dollars: " + this.getPrice()[1] + "\nSize: " + this.getSize() + "\nTourist attraction: " + this.getTouristAttraction();
    }
}
