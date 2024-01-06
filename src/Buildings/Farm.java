package Buildings;

public class Farm extends Building{
    private int revenueAnually;
    public Farm(int price, int revenuePerVisit, int level, int revenueAnually) {
        super(price, revenuePerVisit, level);
        this.revenueAnually = revenueAnually;
    }
    public Farm() {
        super(1000, 100, 1);
        this.revenueAnually = 200;
    }
    public Farm(int ryeness) {
        super(1000, 100, 1);
        this.revenueAnually = 200 * ryeness;
    }
    public void upgrade() {
        if (this.getLevel() < 5) {
            super.upgrade();
            this.setRevenueAnually(this.getRevenueAnually() * 3);
        }
    }
    public int getRevenueAnually() {
        return revenueAnually;
    }
    public void setRevenueAnually(int revenueAnually) {
        this.revenueAnually = revenueAnually;
    }
    public String toString() {
        if (this.getLevel() < 5) {
            return this.getRevenuePerVisit() + "," + this.getRevenueAnually() + "," + this.getLevel() + "," + this.getPriceOfUpgrade();
        }
        else {
            return this.getRevenuePerVisit() + "," + this.getRevenueAnually() + "," + this.getLevel() + ",null";
        }
    }
}
