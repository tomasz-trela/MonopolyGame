package Building;

import board.Board;
import board.Field;
import board.ToBuy;

public abstract class Building {
    private Field location;
    private int price;
    private int revenuePerVisit;
    public Building(int price, int revenuePerVisit, Field location) {
        this.price = price;
        this.revenuePerVisit = revenuePerVisit;
    }
    public Building() {
        this.price = 200;
        this.revenuePerVisit = 100;
        this.location = Board.getCurrentPlayer().getLocation();
    }
    public int getPrice() {
        return price;
    }
    public int getRevenuePerVisit() {
        return revenuePerVisit;
    }
    public void setPrice(int price) {
        this.price = price;
    }
    public void setRevenuePerVisit(int revenuePerVisit) {
        this.revenuePerVisit = revenuePerVisit;
    }
    public Field getLocation() {
        return location;
    }
    public void setLocation(Field location) {
        this.location = location;
    }
}
