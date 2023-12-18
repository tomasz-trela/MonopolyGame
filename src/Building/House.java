package Building;

import board.Field;

public class House extends Building{
    private float luxury;
    public House(int price, int revenuePerVisit, Field location, float luxury) {
        super(price, revenuePerVisit, location);
        this.luxury = luxury;
    }
    public House() {
        super();
        this.luxury = 1;
    }
    public float getLuxury() {
        return luxury;
    }
    public void setLuxury(float luxury) {
        this.luxury = luxury;
    }

}
