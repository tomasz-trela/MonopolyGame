package board;

import java.awt.*;

public class Board {
    private final static int BOARD_WIDTH;
    private final static int BOARD_HEIGHT;
    private final static int FIELD_WIDTH;
    private final static int FIELD_HEIGHT;
    private final static Color BOARD_COLOR;
    private final static Color FIELD_COLOR1;
    private final static Color FIELD_COLOR2;
    static {
        BOARD_WIDTH = 528;
        BOARD_HEIGHT = 528;
        BOARD_COLOR = new Color(200, 224,196);
        FIELD_COLOR1 = new Color(201, 200,252);
        FIELD_COLOR2 = new Color(252, 221,201);
        FIELD_WIDTH = BOARD_WIDTH/12-2;
        FIELD_HEIGHT = FIELD_WIDTH*2+1;
    }

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

    public int getBoardWidth()
    {
        return BOARD_WIDTH;
    }
    public int getBoardHeigtht()
    {
        return BOARD_HEIGHT;
    }
    public Color getBoardColor()
    {
        return BOARD_COLOR;
    }
    public Field[] getFieldsArray(){
        return fields;
    }
    public static int getFieldHeight() {
        return FIELD_HEIGHT;
    }
    public static int getFieldWidth() {
        return FIELD_WIDTH;
    }

    public static Color getFieldColor1() {
        return FIELD_COLOR1;
    }

    public static Color getFieldColor2() {
        return FIELD_COLOR2;
    }
}
