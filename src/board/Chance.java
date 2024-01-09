package board;
import ChancesAndModifications.Chances;
import player.Player;

import java.util.Random;

public class Chance extends Field{
    private final Chances [] listOfChances;
    public Chance(String name, Chances [] listOfChances){
        super(name);
        this.listOfChances = listOfChances;
    }
    public Chances [] getListOfChances(){
        return listOfChances;
    }
    public Chances getChance(int index){
        return listOfChances[index];
    }
    public void doChance(Board board){
        Random generator = new Random();
        int i = generator.nextInt(listOfChances.length);
        System.out.println(i);
        int [] moneyChange = listOfChances[i].getMoneyChange();
        int [] moneyGivenPerPlayer = listOfChances[i].getMoneyGivenPerPlayer();
        int positionChange = listOfChances[i].getPositionChange();
        int finalPosition = listOfChances[i].getFinalPosition();

        Player player = board.getCurrentPlayer();
        if (i==0) {
            player.decreaseBalance(moneyChange);
        }
        if (i==1){
            player.decreaseBalance(moneyGivenPerPlayer);
            board.getPlayers()[(board.getRound()+1)%board.getPlayers().length].increaseBalance(moneyGivenPerPlayer);
        }
        if (i==2){
            player.movePlayer(positionChange);
            board.ChangePlayerLocation(positionChange);
            player.changeStrategy();
            board.movePawn();
        }
        if (i==3){
            player.setFieldIndex(finalPosition);
            board.SetPlayerLocation(finalPosition);
            player.changeStrategy();
            board.movePawn();
        }
    }
    public String toString(){
        return "Chance";
    }
}
