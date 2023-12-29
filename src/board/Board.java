package board;

import ChancesAndModifications.Chances;
import player.Player;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Board {
    private static Field[] fields ;
    private static Player[] players;
    private int moveCounter; //licznik ruchów
    private int round; //oblicza index gracza którego aktualnie jest kolej ruchu
    private Player currentPlayer;
    private static final Chances[] listOfChances = {new Chances("Chance1", 1000, 0, 0, 0, 0, 0, 0, 0)};

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
            players[i]=new Player("Player %s".formatted(i+1));
            players[i].setLocation(new Start());
        }
    }

    public Player[] GetPlayersArray(){
        return players;
    }

    public static void generateBoard(int boardNumber){
        fields[0] = new Start();
        fields[9] = new Exchage("Kantor1", 1, 1);
        fields[18] = new Exchage("Kantor2", 1, 1);
        //fields[27] = new Exchage("Kantor3", 1, 1);;
        if(boardNumber==1) {
            fields[1] = new Village("Leirose", 20000, 0, null, 0.5f);
            fields[2] = new Chance("Szansa1", listOfChances);
            fields[3] = new City("Lizbone", 30000, 0, null, 10, 10);
            fields[4] = new CarDealership();
            fields[5] = new City("Sevilla", 30000, 0, null, 10, 10);
            fields[6] = new Village("Los Cabezudos", 20000, 0, null, 0.5f);
            fields[7] = new Chance("Szansa2", listOfChances);
            fields[8] = new City("Madryt", 40000, 0, null, 10, 10);
            //fields[9] jest wspolne dla każdej mapy
            fields[10] = new City("Marsylie", 40000, 0, null, 10, 10);
            fields[11] = new CarDealership();
            fields[12] = new City("Lyon", 40000, 0, null, 10, 10);
            fields[13] = new Village("Donzy", 20000, 0, null, 0.5f);
            fields[14] = new Chance("Szansa3", listOfChances);
            fields[15] = new City("Antwerp", 50000, 0, null, 10, 10);
            fields[16] = new Chance("Szansa4", listOfChances);
            fields[17] = new City("Brussels", 50000, 0, null, 10, 10);
            //fields[18] jest wspolne dla każdej mapy
            //fields[27] jest wspolne dla każdej mapy

            for (int i = 19; i < fields.length; i++) {
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
        }
    }
    public void calculateRound(){
        round = moveCounter%players.length;
    }
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
