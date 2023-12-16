package board;
import ChancesAndModifications.Car;

import java.util.ArrayList;

public class CarDealership extends Field {
    private ArrayList<Car> availableCars;
    public CarDealership(String name, int index, ArrayList<Car> availableCars){
        super(name);
        this.availableCars = availableCars;
    }
    public CarDealership(){
        //super("Car Dealership");
        this.availableCars = new ArrayList<Car>();
        for (int i = 0; i < 2; i++){
            this.availableCars.add(new Car());
        }
    }
    public CarDealership(int amountOfCars){
        //super("Car Dealership");
        this.availableCars = new ArrayList<Car>();
        for (int i = 0; i < amountOfCars; i++){
            this.availableCars.add(new Car());
        }
    }
    public ArrayList<Car> getAvailableCars(){
        return availableCars;
    }
    public void setAvailableCars(ArrayList<Car> availableCars){
        this.availableCars = availableCars;
    }
    public void addCar(Car car){
        availableCars.add(car);
    }
    public void removeCar(Car car){
        availableCars.remove(car);
    }
    public void removeCar(int index){
        availableCars.remove(index);
    }
    public Car getCar(int index){
        return availableCars.get(index);
    }
    public void setCar(int index, Car car){
        availableCars.set(index, car);
    }

}

