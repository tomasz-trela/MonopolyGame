package board;

import Buildings.Building;
import Buildings.House;
import player.Player;

public class City extends ToBuy {
    private int size;
    private int touristAttraction;
    public City (String name, int priceEuro,int priceDollars, Player owner, int size, int touristAttraction){
        super(name, priceEuro, priceDollars, touristAttraction, owner);
        this.size = size;
        this.touristAttraction = touristAttraction;
    }
    public int getSize(){
        return size;
    }
    public int getTouristAttraction(){
        return touristAttraction;
    }
    public void setSize(int size){
        this.size = size;
    }
    public void setTouristAttraction(int touristAttraction){
        this.touristAttraction = touristAttraction;
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
            return "Type: City,Name: " + this.getName() + ",Owner: " + this.getOwner().getName() + price + ",Size: " + this.getSize() + ",Tourist attraction: " + this.getTouristAttraction();
        } catch (NullPointerException e) {
            return "Type: City,Name: " + this.getName() + ",Owner: " + "null" + price + ",Size: " + this.getSize() + ",Tourist attraction: " + this.getTouristAttraction();
        }
    }
    public void addBuilding() {
        this.addBuilding(new House(this.getSize(), this.getTouristAttraction()));
    }
    public int [] getCostOfBuilding() {
        if (this.getPrice()[0] == 0) {
            return new int[] {0, 500 * this.getSize()};
        }
        else if (this.getPrice()[1] == 0) {
            return new int[] {500 * this.getSize(), 0};
        }
        else
            return new int[] {500 * this.getSize(), 500 * this.getSize()};
    }
}
