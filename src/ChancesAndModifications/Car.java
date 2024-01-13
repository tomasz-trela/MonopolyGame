package ChancesAndModifications;

import board.Board;
import board.Start;

import java.util.Random;

import static player.Dice.Roll;

public class Car {
    private String name;
    private int[] price = new int[2];
    private static int costOfMaintenance = 100;
    private boolean isAvailable;
    private final String [] nameList = {"Fiat", "Ford", "Audi", "BMW", "Mercedes", "Toyota", "Honda", "Mazda", "Nissan", "Volkswagen"};

    public Car(int euroPrice, int dolarPrice){
        Random generator = new Random();
        this.name = nameList[generator.nextInt(nameList.length)];
        this.price[0] = euroPrice;
        this.price[1] = dolarPrice;
        this.isAvailable = true;
    }

    public int[] getPrice() {
        return price;
    }

    public static int getCostOfMaintenance() {
        return costOfMaintenance;
    }

    public String getName() {
        return name;
    }

    public String[] getNameList() {
        return nameList;
    }

    public static void setCostOfMaintenance(int costOfMaintenance) {
        Car.costOfMaintenance = costOfMaintenance;
    }

    public void setPrice(int[] price) {
        this.price = price;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isAvailable() {
        return isAvailable;
    } //czy nie jest zepsute
}
