package board;

import java.awt.*;

public class Board {
    private Field[] fields = new Field[36];
    private int move_counter; //licznik ruchów
    private int round; //ilość okrążeń gracza z największą ich iloscią

    public Board(){
        move_counter = 0;
        round = 0;
        fields[0] = new Start();
        fields[1] = new Village("Wioska 1", 1, 200, 5, null, 0.5f);
        fields[2] = new Village("Wioska 2", 2, 300, 10, null, 1.5f);
        for(int i=3; i< fields.length; i++)
        {
            fields[i] = new City("Pole %s".formatted(i), 1, 1000, 100, null, 12, 0.5f);
        }
    }
    public Field[] getFieldsArray(){
        return fields;
    }
    public int getMove_counter(){
        return move_counter;
    }
    public void setMove_counter(int move_counter){
        this.move_counter=move_counter;
    }
    public int getRound(){
        return round;
    }
    public void setRound(int round){
        this.round=round;
    }

}
