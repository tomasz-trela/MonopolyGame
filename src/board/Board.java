package board;

import player.Player;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Board {
    private static Field[] fields ;
    private static Player[] players;
    private int moveCounter; //licznik ruchów
    private int round; //oblicza index gracza którego aktualnie jest kolej ruchu
    private Player currentPlayer;

    public Board(){
        moveCounter = 0;
        round = 0;
        fields = new Field[36];
        /*fields[0] = new Start();
        fields[1] = new Village("Wioska 1", 20000, 0, null,0.5f);
        fields[2] = new Village("Wioska 2", 30000, 0, null, 0.5f);
        for(int i=3; i< fields.length; i++)
        {
            fields[i] = new City("Pole %s".formatted(i), 1, 0, null, 12, 0.5f);
        }*/
    }
    public static void generatePlayers(int playersCount){
        players = new Player[playersCount];
        for(int i=0; i<players.length; i++){
            players[i]=new Player();
            players[i].setLocation(new Start());
        }
    }
    public static void generateBoard(int boardNumber){
        fields[0] = new Start();
        if(boardNumber==1) {
            fields[1] = new Village("Wioska 1", 20000, 0, null, 0.5f);
            fields[2] = new Village("Wioska 2", 30000, 0, null, 0.5f);
            for (int i = 3; i < fields.length; i++) {
                fields[i] = new City("Pole %s".formatted(i), 1, 0, null, 12, 0.5f);
            }
        }
        if(boardNumber==2) {
            fields[1] = new Village("Wioska 1", 20000, 0, null, 0.5f);
            fields[2] = new Village("Wioska 2", 30000, 0, null, 0.5f);
            for (int i = 3; i < fields.length; i++) {
                fields[i] = new City("Pole %s".formatted(i), 1, 0, null, 12, 0.5f);
            }
        }
        if(boardNumber==3) {
            fields[1] = new Village("Wioska 1", 20000, 0, null, 0.5f);
            fields[2] = new Village("Wioska 2", 30000, 0, null, 0.5f);
            for (int i = 3; i < fields.length; i++) {
                fields[i] = new City("Pole %s".formatted(i), 1, 0, null, 12, 0.5f);
            }
        }
        if(boardNumber==4) {
            fields[1] = new Village("Wioska 1", 20000, 0, null, 0.5f);
            fields[2] = new Village("Wioska 2", 30000, 0, null, 0.5f);
            for (int i = 3; i < fields.length; i++) {
                fields[i] = new City("Pole %s".formatted(i), 1, 0, null, 12, 0.5f);
            }
        }
    }
    public void ChangePlayerLocation(int roll){
        if (players !=null){
            int temp = 0;
            if (fields != null){
                for (int i = 0; i < fields.length; i++) {
                    if (fields[i] != null){
                        if(fields[i] == currentPlayer.getLocation()){
                            temp = i;
                        }
                    }
                }
                currentPlayer.setLocation(fields[(temp + roll)%36]);
                currentPlayer.changeStrategy();
            }
        }
    }
    public void SetCurrentPlayerOnGamePanel(int round){
        setCurrentPlayer(players[round]);
    }
    private void calculate_round(){round=moveCounter%players.length;}
    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }
    public Player getCurrentPlayer() {
        return currentPlayer;
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
        calculate_round();
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
