package board;

import ChancesAndModifications.Chances;
import monopolyGame.GameFrame;
import player.Player;

public class Board {
    private static Field[] fields ;
    private static Player[] players;
    private int moveCounter; //licznik ruchów
    private int round; //oblicza index gracza którego aktualnie jest kolej ruchu
    private Player currentPlayer;
    private static final Chances[] listOfChances = {new Chances("Chance1", 1000, 0, 0, 0)};
    private static double GameDolarRate;
    private static double GameEuroRate;

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

    public static Player[] GetPlayersArray(){
        return players;
    }
    static Exchange kantor  = new Exchange("Kantor", GameDolarRate, GameEuroRate);

    public static Exchange getExchange() {
        return kantor;
    }

    public static void generateBoard(int boardNumber){
        fields[0] = new Start();
        fields[9] = new Exchange("Kantor1", GameDolarRate, GameEuroRate); //kantory musza byc osobnymi obiektami aby dzialala strategia
        fields[18] = new Exchange("Kantor2", GameDolarRate, GameEuroRate);
        fields[27] = new Exchange("Kantor3", GameDolarRate, GameEuroRate);
        if(boardNumber==1) {
            fields[1] = new Village("Leirose", 20000, 0, null, 0.5f);
            fields[2] = new Chance("Szansa1", listOfChances);
            fields[3] = new City("Lizbone", 30000, 0, null, 10, 8);
            fields[4] = new CarDealership();
            fields[5] = new City("Sevilla", 30000, 0, null, 10, 7);
            fields[6] = new Village("Los Cabezudos", 20000, 0, null, 0.5f);
            fields[7] = new Chance("Szansa2", listOfChances);
            fields[8] = new City("Madryt", 40000, 0, null, 10, 9);
            //fields[9] jest wspolne dla każdej mapy
            fields[10] = new City("Marsylie", 40000, 0, null, 10, 4);
            fields[11] = new CarDealership();
            fields[12] = new City("Lyon", 40000, 0, null, 10, 2);
            fields[13] = new Village("Donzy", 20000, 0, null, 0.5f);
            fields[14] = new Chance("Szansa3", listOfChances);
            fields[15] = new City("Antwerp", 50000, 0, null, 10, 3);
            fields[16] = new Village("Farmville", 400, 1500, null, 0.3f);
            fields[17] = new City("Brussels", 50000, 0, null, 10, 7);
            //fields[18] jest wspolne dla każdej mapy
            fields[19] = new Village("Quebec", 0, 20000, null, 0.5f);
            fields[20] = new Village("Smalltown", 0, 2000, null, 0.5f);
            fields[21] = new CarDealership();
            fields[22] = new City("Seattle", 0, 45000, null, 10, 7);
            fields[23] = new Village("Ruralville", 0, 1000, null, 0.4f);
            fields[24] = new City("Dallas", 0, 38000, null, 10, 4);
            fields[25] = new City("Miami", 0, 32000, null, 10, 5);
            fields[26] = new Village("Countryside", 0, 3000, null, 0.6f);
            //fields[27] jest wspolne dla każdej mapy
            fields[28] = new City("Denver", 0, 42000, null, 10, 6);
            fields[29] = new Village("Hamlet", 0, 800, null, 0.2f);
            fields[30] = new City("Atlanta", 0,37000, null, 10, 5);
            fields[31] = new City("Minneapolis", 0, 35000, null, 10, 5);
            fields[32] = new Village("Villageton", 0, 2500, null, 0.5f);
            fields[33] = new CarDealership();
            fields[34] = new Chance("Szansa3", listOfChances);
            fields[35] = new Village("Hometown", 0, 1800, null, 0.4f);

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
    public void SetPlayerLocation(int finalPosition){
        if (fields != null){
            if (finalPosition < 36) {
                currentPlayer.setLocation(fields[(finalPosition)]);
            }
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
