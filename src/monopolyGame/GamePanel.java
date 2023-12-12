package monopolyGame;

import javax.swing.*;
import java.awt.*;

import board.Board;

public class GamePanel extends JPanel {
    static final int SCREEN_WIDTH = 900;
    static final int SCREEN_HEIGHT = 750;
    Board board = new Board();
    GamePanel(){
        this.setPreferredSize(new Dimension(SCREEN_WIDTH,SCREEN_HEIGHT));
        this.setBackground(Color.WHITE);
        this.setFocusable(true);
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        draw(g);
    }
    public void draw(Graphics g){
        g.setColor(board.getBoardColor());//kolor Planszy
        g.fillRect((this.getWidth()-board.getBoardWidth())/2,(this.getHeight()-board.getBoardHeigtht())/2, board.getBoardWidth(), board.getBoardHeigtht());//rysujemy prostokąt na środku to jest plansza

        g.setColor(Color.BLACK);//kolor Marginesów
        g.fillRect((this.getWidth()-board.getBoardWidth())/2,(this.getHeight()-board.getBoardHeigtht())/2, board.getBoardWidth(), board.getFieldHeight()+4);
        g.fillRect((this.getWidth()-board.getBoardWidth())/2,(this.getHeight()-board.getBoardHeigtht())/2+(board.getBoardHeigtht()-(board.getFieldHeight()+4)), board.getBoardWidth(), board.getFieldHeight()+4);
        g.fillRect((this.getWidth()-board.getBoardWidth())/2,(this.getHeight()-board.getBoardHeigtht())/2, board.getFieldHeight()+4, board.getBoardHeigtht());
        g.fillRect((this.getWidth()-board.getBoardWidth())/2+(board.getBoardWidth()-(board.getFieldHeight()+4)),(this.getHeight()-board.getBoardHeigtht())/2, board.getFieldHeight()+4, board.getBoardHeigtht());

        int tmp=0;
        for(int i=0;i<board.getFieldsArray().length; i++){

            if(i==0){
                g.setColor(board.getFieldColor2());//Kolor Narożników
                g.fillRect((this.getWidth()-board.getBoardWidth())/2+2, (this.getHeight()-board.getBoardHeigtht())/2+(board.getBoardHeigtht()-(board.getFieldHeight()+2)), board.getFieldHeight(), board.getFieldHeight());
                tmp +=board.getFieldWidth()+2;
            }
            else if (i<9) {
                g.setColor(board.getFieldColor1());//Kolor Pól do kupienia
                g.fillRect((this.getWidth()-board.getBoardWidth())/2+2, (this.getHeight()-board.getBoardHeigtht())/2+(board.getBoardHeigtht()-(board.getFieldHeight()+2)) -tmp, board.getFieldHeight(), board.getFieldWidth());
                tmp +=board.getFieldWidth()+2;
            }
            else if (i==9) {
                tmp=0;
                g.setColor(board.getFieldColor2());//Kolor Narożników
                g.fillRect((this.getWidth()-board.getBoardWidth())/2+2, (this.getHeight()-board.getBoardHeigtht())/2+2, board.getFieldHeight(), board.getFieldHeight());
                tmp +=board.getFieldHeight()+2;
            }
            else if(i<18) {
                g.setColor(board.getFieldColor1());//Kolor Pól do kupienia
                g.fillRect((this.getWidth() - board.getBoardWidth()) / 2 + 2 + tmp, (this.getHeight() - board.getBoardHeigtht()) / 2 + 2, board.getFieldWidth(), board.getFieldHeight());
                tmp += board.getFieldWidth() + 2;
            }
            else if (i==18) {
                tmp=0;
                g.setColor(board.getFieldColor2());//Kolor Narożników
                g.fillRect((this.getWidth()-board.getBoardWidth())/2+(board.getBoardWidth()-(board.getFieldHeight()+2)), (this.getHeight()-board.getBoardHeigtht())/2+2, board.getFieldHeight(), board.getFieldHeight());
                tmp +=board.getFieldHeight()+2;
            }
            else if (i<27) {
                g.setColor(board.getFieldColor1());//Kolor Pól do kupienia
                g.fillRect((this.getWidth()-board.getBoardWidth())/2+(board.getBoardWidth()-(board.getFieldHeight()+2)), (this.getHeight()-board.getBoardHeigtht())/2+2 +tmp, board.getFieldHeight(), board.getFieldWidth());
                tmp +=board.getFieldWidth()+2;
            }
            else if (i==27) {
                tmp=0;
                g.setColor(board.getFieldColor2());//Kolor Narożników
                g.fillRect((this.getWidth()-board.getBoardWidth())/2+(board.getBoardWidth()-(board.getFieldHeight()+2)), (this.getHeight()-board.getBoardHeigtht())/2+(board.getBoardHeigtht()-(board.getFieldHeight()+2)), board.getFieldHeight(), board.getFieldHeight());
                tmp +=board.getFieldHeight()+2;
            }
            else  {
                g.setColor(board.getFieldColor1());//Kolor Pól do kupienia
                g.fillRect((this.getWidth()-board.getBoardWidth())/2+(board.getBoardWidth()-(board.getFieldWidth()+2)) - tmp, (this.getHeight()-board.getBoardHeigtht())/2+(board.getBoardHeigtht()-(board.getFieldHeight()+2)), board.getFieldWidth(), board.getFieldHeight());
                tmp += board.getFieldWidth() + 2;
            }

        }
    }

}
