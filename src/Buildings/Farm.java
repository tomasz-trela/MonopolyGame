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
    public void upgrade() {
        super.upgrade();
        this.setRevenueAnually(this.getRevenueAnually() * 3);
    }
    public int getRevenueAnually() {
        return revenueAnually;
    }
    public void setRevenueAnually(int revenueAnually) {
        this.revenueAnually = revenueAnually;
    }
    public String toString() {
        return this.getRevenuePerVisit() + "," + this.getRevenueAnually() + "," + this.getLevel() + "," + this.getPriceOfUpgrade();
    }
}
