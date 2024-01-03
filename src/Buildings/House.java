package Buildings;

import board.Field;

public class House extends Building{
    public House(int priceOfUpgrade, int revenuePerVisit, int level) {
        super(priceOfUpgrade, revenuePerVisit, level);
    }
    public House() {
        super();
    }
    public void upgrade() {
        super.upgrade();
        this.setRevenuePerVisit(this.getRevenuePerVisit() * 2);
    }
}
