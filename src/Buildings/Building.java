package Buildings;

import board.Board;
import board.Field;

public abstract class Building {
    private Field location;
    private int price;
    private int revenuePerVisit;
    public Building(int price, int revenuePerVisit) {
        this.price = price;
        this.revenuePerVisit = revenuePerVisit;
    }
    public Building() {
        this.price = 200;
        this.revenuePerVisit = 100;
    }
    public void upgrade() {
        this.setRevenuePerVisit(2 * this.revenuePerVisit);
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
}
