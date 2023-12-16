package board;
import player.Player;
public abstract class ToBuy extends Field {
    private Player owner;
    private int price;
    private int revenue;
    //private Country country;
    //private Buildings [] buildings;
    public ToBuy(String name, int price, int revenue, Player owner) {
        super(name);
        this.price = price;
        this.revenue = revenue;
        this.owner = owner;
    }
    public ToBuy() {
        super();
        this.price = 0;
        this.revenue = 0;
    }
    public int getPrice() {
        return price;
    }
    public int getRevenue() {
        return revenue;
    }
    public Player getOwner() {
        return owner;
    }
    public void setPrice(int price) {
        this.price = price;
    }
    public void setRevenue(int revenue) {
        this.revenue = revenue;
    }
    public void setOwner(Player owner) {
        this.owner = owner;
    }
}
