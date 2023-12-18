package Buildings;

import board.Field;

public class House extends Building{
    private float luxury;
    public House(int price, int revenuePerVisit, Field location, float luxury) {
        super(price, revenuePerVisit);
        this.luxury = luxury;
    }
    public House() {
        super();
        this.luxury = 1;
    }
    public void upgrade() {
        this.setRevenuePerVisit(this.getRevenuePerVisit() * 10);
    }
    public float getLuxury() {
        return luxury;
    }
    public void setLuxury(float luxury) {
        this.luxury = luxury;
    }

}
