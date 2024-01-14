package player;

import ChancesAndModifications.Car;
import board.*;
import monopolyGame.GameFrame;
import monopolyGame.GamePanel;
import observer.Subject;
import strategy.*;
import board.Board;
import javax.swing.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Random;

import static java.util.function.Predicate.not;
import static player.Dice.Roll;

public class Player {
    private int fieldIndex;
    private int lap;
    private int[] balance = new int[2];
    private boolean haveCar;
    private boolean canExchange;
    private boolean canMoveAfterChance;
    private ArrayList<ToBuy> OwnedFields;
    private Field location;
    protected ActionStrategy actionStrategy;
    private String name;

    private int[] cashPerLap;

    public Player() {
        balance[0] = 250000; //euro
        balance[1] = 250000; //dolary
        this.haveCar = false;
        this.canExchange = false;
        this.canMoveAfterChance = false;
        this.OwnedFields = new ArrayList<>();
        this.lap = 0;
        this.location = null;
        this.actionStrategy = null;
        this.name = "Player";
        this.cashPerLap = new int[2];
        cashPerLap[0]=20000;
        cashPerLap[1]=20000;
    }
    public Player(String name) {
        balance[0] = 170000; //euro
        balance[1] = 170000; //dolary
        this.haveCar = false;
        this.canExchange = false;
        this.canMoveAfterChance = false;
        this.OwnedFields = new ArrayList<>();
        this.lap = 0;
        this.location = null;
        this.actionStrategy = null;
        this.name = name;
        this.cashPerLap = new int[2];
        cashPerLap[0]=20000;
        cashPerLap[1]=20000;
    }

    public void setLocation(Field location) {
        this.location = location;
    }

    public Field getLocation() {
        return location;
    }

    public int getLap() {
        return lap;
    }

    public int[] getBalance() {
        return balance;
    }

    public void setBalance(int[] newbalance) {
        for (int i = 0; i < newbalance.length; i++) balance[i] = newbalance[i];
    }

    public void increaseBalance(int[] cost) {
        for (int i = 0; i < cost.length; i++) balance[i] += cost[i];
    }

    public int[] getCashPerLap() {
        return cashPerLap;
    }

    public void setCashPerLap(int[] cashPerLap) {
        this.cashPerLap = cashPerLap;
    }

    public void decreaseBalance(int[] cost) {
        for (int i = 0; i < cost.length; i++) {

            if ((balance[i] - cost[i]) < 0) {
                forceExchange(cost);
            }
            
            balance[i] -= cost[i];
        }
    }

    // Zmuszamy gracza do wymiany waluty kiedy nie ma czym zapłacić
    public void forceExchange(int[] cost) {
        Board boardInstance = GameFrame.getInstance().GetGamePanel().getBoard();
        int[] amount = {0, 0};
        if (cost[0] == 0) {
            while (amount[1] < cost[1]) {
                amount[1] = amount[1] + boardInstance.ExchangeEURtoUSD(balance[0], 100, boardInstance)[1]; // Panowie ale używanie boarda w boardzie jest do wyjebania
                balance[1] = balance[1] - amount[1]; // Na razie zakładamy, że gracza stać i nie ma końca gry
            }
        }     
        else if (cost[1] == 0) {
            while (amount[0] < cost[0]) {
                amount[0] = amount[0] + boardInstance.ExchangeUSDtoEUR(balance[1], 100, boardInstance)[0]; // Panowie ale używanie boarda w boardzie jest do wyjebania
                balance[0] = balance[0] - amount[0]; // Na razie zakładamy, że gracza stać i nie ma końca gry
            }
        }
    }
    public long totalNetWorth(Board now){
        long suma = balance[0];
        suma+= (long) Math.round(balance[1]*now.getEuroRate());
        for(int i=0; i<OwnedFields.size(); i++){
            if(OwnedFields.get(i).getPrice()[0]!=0){
                suma+= (long) OwnedFields.get(i).getPrice()[0];
                suma+= (long) OwnedFields.get(i).getCostOfBuilding()[0]*OwnedFields.get(i).NumberOfBuildings();
            } else {
                suma+= (long) Math.round(OwnedFields.get(i).getPrice()[1]*now.getEuroRate());
                suma+= (long) Math.round(OwnedFields.get(i).getCostOfBuilding()[0]*OwnedFields.get(i).NumberOfBuildings()*now.getEuroRate());
            }
        }
        return suma;
    }

    public ArrayList<ToBuy> getOwnedFields() {
        return OwnedFields;
    }

    public void setOwnedFields(ArrayList<ToBuy> ownedFields) {
        OwnedFields = ownedFields;
    }

    public void setHaveCar(boolean haveCar) {
        this.haveCar = haveCar;
    }

    public void setLap(int lap) {
        this.lap = lap;
    }


    public boolean isHaveCar() {
        return haveCar;
    }

    public int getFieldIndex() {
        return fieldIndex;
    }

    public void setFieldIndex(int fieldIndex) {
        this.fieldIndex = fieldIndex;
    }

    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }

    public boolean isCanExchange() {
        return canExchange;
    }

    public void setCanExchange(boolean canExchange) {
        this.canExchange = canExchange;
    }

    public boolean isCanMoveAfterChance() {
        return canMoveAfterChance;
    }
    public void setCanMoveAfterChance(boolean canMoveAfterChance) {
        this.canMoveAfterChance = canMoveAfterChance;
    }

    public void playerAction(Board board) {
        if (actionStrategy != null) {
            actionStrategy.action(board);
        }
    }

    public void setActionStrategy(ActionStrategy actionStrategy) {
        this.actionStrategy = actionStrategy;
    }

    public void changeStrategy(){
        if(location instanceof ToBuy){
            if(((ToBuy) location).getOwner() == null){
                setActionStrategy(new BuyFieldStrategy());
            }
            else {
                setActionStrategy(null);
                if (OwnedFields.contains(location)){
                    setActionStrategy(new BuyBuildingStrategy());
                }

            }

        }
        if(location instanceof Chance){
            setActionStrategy(new ChanceStrategy());
        }
        if(location instanceof Exchange){
            setActionStrategy(new ChangeStrategy());
        }
        if(location instanceof CarDealership){
            setActionStrategy(new BuyCarStrategy());
        }
        if(location instanceof Start){
            setActionStrategy(null);
        }

    }
    public void useCar(Board board, int sum){
        board.changePlayerLocation(sum);
        changeStrategy();
        setHaveCar(false);
    }
    public void exchangeMoney(Board board, int enteredValue, int typeOfTransaction) throws OurOwnExeption{ //int typeOfTransaction (1- euro to usd), (2 - usd to euro)
        Player player = board.getCurrentPlayer();
        player.setCanExchange(false);

        if (typeOfTransaction == 1) {
            if (enteredValue > player.balance[0]) throw new OurOwnExeption();
            player.increaseBalance(board.ExchangeEURtoUSD(player.balance[0], enteredValue, board));

        }else if(typeOfTransaction == 2){
            if (enteredValue > player.balance[1]) throw new OurOwnExeption();
            player.increaseBalance(board.ExchangeUSDtoEUR(player.balance[1], enteredValue, board));

        }
    }
    public void chargeForCar(Board board){
        if(haveCar) {
            int cost = Car.getCostOfMaintenance();
            if (board.getCurrentPlayer().getLocation() instanceof ToBuy) {
                if (((ToBuy) location).getPrice()[0] > ((ToBuy) location).getPrice()[1]) {
                    balance[0] -= cost;
                } else {
                    balance[1] -= cost;
                }
            } else {
                Random random = new Random();
                balance[random.nextInt(2)] -= cost;
            }
        }
    }
    public void payFeeForBuiliding(Board board) {
        if (!(((ToBuy) board.getCurrentPlayer().getLocation()).getOwner() == null)) {
            if (!getOwnedFields().contains(getLocation())) {
                int stayFee = ((ToBuy) location).getStayFee();
                if (((ToBuy) location).getPrice()[0] > ((ToBuy) location).getPrice()[1]) {
                    balance[0] -= stayFee;
                } else {
                    balance[1] -= stayFee;
                }
                for (int i = 0; i < ((ToBuy) location).getBuildings().size(); i++) {
                    int revenuePerVisit = ((ToBuy) location).getBuildings().get(i).getRevenuePerVisit();
                    if (((ToBuy) location).getPrice()[0] > ((ToBuy) location).getPrice()[1]) {
                        balance[0] -= revenuePerVisit;
                    } else {
                        balance[1] -= revenuePerVisit;
                    }
                }
            }
        }
    }

}