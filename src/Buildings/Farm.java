package Buildings;

public class Farm extends Building{
    private int revenueAnually;
    public Farm(int price, int revenuePerVisit, int revenueAnually) {
        super(price, revenuePerVisit);
        this.revenueAnually = revenueAnually;
    }
    public Farm() {
        super();
        this.revenueAnually = 1000;
    }
    public void upgrade() {
        super.upgrade();
        this.setRevenueAnually(this.getRevenueAnually() * 5);
    }
    public int getRevenueAnually() {
        return revenueAnually;
    }
    public void setRevenueAnually(int revenueAnually) {
        this.revenueAnually = revenueAnually;
    }
}
