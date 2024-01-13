package board;
import ChancesAndModifications.Car;

import java.util.ArrayList;

public class CarDealership extends Field {
    private ArrayList<Car> availableCars;
    private String whichCurrency;
    private final int carPrice;
    public CarDealership(String whichCurrency){
        super("Car Dealership");
        this.whichCurrency = whichCurrency;
        this.availableCars = new ArrayList<Car>();
        this.carPrice = 3000;
        for (int i = 0; i < 2; i++){
            if (whichCurrency.equals("Euro")) {
                this.availableCars.add(new Car(carPrice,0));
            }else {
                this.availableCars.add(new Car(0,carPrice));
            }
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

    public String toString(){
        try{
            return "Car Dealership, " + "Car price: " + carPrice + " Cost of maintenance 100 dolars/euro";
        }
        catch (NullPointerException e){
            return "Car Dealership ";
        }
    }

    public String getStan(ArrayList<Car> availableCars){
        String stan = "";
        for (int i = 0; i < availableCars.size(); i++){
            stan += availableCars.get(i).getName() + ", ";
        }
        return stan;
    }

    public String getWhichCurrency() {
        return whichCurrency;
    }

    public void setWhichCurrency(String whichCurrency) {
        this.whichCurrency = whichCurrency;
    }
}

