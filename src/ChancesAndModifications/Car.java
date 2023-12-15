package ChancesAndModifications;

import java.util.Random;

public class Car {
    private String name;
    private int price;
    private int additionalSpeed;
    private int costOfMaintenance;
    private boolean isAvailable;
    private final String [] nameList = {"Fiat", "Ford", "Audi", "BMW", "Mercedes", "Toyota", "Honda", "Mazda", "Nissan", "Volkswagen"};
    public Car(String name, int price, int additionalSpeed, int costOfMaintenance, boolean isAvailable){
        this.name = name;
        this.price = price;
        this.additionalSpeed = additionalSpeed;
        this.costOfMaintenance = costOfMaintenance;
        this.isAvailable = isAvailable;
    }
    public Car(){
        Random generator = new Random();
        this.name = nameList[generator.nextInt(nameList.length)];
        this.price = generator.nextInt(1000, 3000);
        this.additionalSpeed = Math.round((float) this.price /1000);
        this.costOfMaintenance = 100;
        this.isAvailable = true;
    }
    public String getName(){
        return name;
    }
    public int getPrice(){
        return price;
    }
    public int getAdditionalSpeed(){
        return additionalSpeed;
    }
    public int getCostOfMaintenance(){
        return costOfMaintenance;
    }
    public boolean getIsAvailable(){
        return isAvailable;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setPrice(int price){
        this.price = price;
    }
    public void setAdditionalSpeed(int additionalSpeed){
        this.additionalSpeed = additionalSpeed;
    }
    public void setCostOfMaintenance(int costOfMaintenance){
        this.costOfMaintenance = costOfMaintenance;
    }
    public void setIsAvailable(boolean isAvailable){
        this.isAvailable = isAvailable;
    }
}
