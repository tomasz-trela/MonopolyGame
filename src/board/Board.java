package board;

import player.Player;

public class Board {
    private static Field[] fields = new Field[36];
    private Player[] players = new Player[4];
    private int moveCounter; //licznik ruchów
    private int round; //ilość okrążeń gracza z największą ich iloscią

    public Board(){
        moveCounter = 0;
        round = 0;
        players[0] = new Player();
        players[1] = new Player();
        players[2] = new Player();
        players[3] = new Player();

        fields[0] = new Start();
        fields[1] = new Village("Wioska 1", 200, 5, null, 0.5f);
        fields[2] = new Village("Wioska 2", 300, 10, null, 1.5f);
        for(int i=3; i< fields.length; i++)
        {
            fields[i] = new City("Pole %s".formatted(i), 1, 1000, 100, null, 12, 0.5f);
        }
    }

    public static Field[] getFieldsArray(){
        return fields;
    }
    public int getMoveCounter(){
        return moveCounter;
    }
    public void setMoveCounter(int moveCounter){
        this.moveCounter = moveCounter;
    }
    public void incrementMoveCounter(){
        moveCounter++;
    }
    public int getRound(){
        return round;
    }
    public void setRound(int round){
        this.round=round;
    }
    public Player[] getPlayers() {
        return players;
    }
}
