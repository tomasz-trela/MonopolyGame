package board;

import java.awt.*;

public class Board {
    private Field[] fields = new Field[36];
    private int move_counter; //licznik ruchów
    private int round; //ilość okrążeń gracza z największą ich iloscią

    public Board(){
        move_counter = 0;
        round = 0;
        for(int i=0; i< fields.length; i++)
        {
            fields[i] = new Field("Pole %s".formatted(i));
        }
    }
    public Field[] getFieldsArray(){
        return fields;
    }
}
