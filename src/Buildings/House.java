package Buildings;

import board.Field;

public class House extends Building{
    public House(int priceOfUpgrade, int revenuePerVisit, int level) {
        super(priceOfUpgrade, revenuePerVisit, level);
    }
    public House() {
        super();
    }
    public House(int touristAttraction, int size) {
        super(2000 * size, 100 * touristAttraction * size, 1);
    }
    public void upgrade() {
        if (this.getLevel() < 5) {
            super.upgrade();
            this.setRevenuePerVisit(this.getRevenuePerVisit() * 2);
        }
    }

}
