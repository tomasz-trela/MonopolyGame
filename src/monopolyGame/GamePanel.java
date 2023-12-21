package monopolyGame;

import java.awt.Font;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.URL;
import java.util.Scanner;

import board.*;
import static player.Dice.Roll;
import player.*;

public class GamePanel extends JPanel implements ActionListener {
    private static final int SCREEN_WIDTH;
    private static final int SCREEN_HEIGHT;
    private final static int BOARD_WIDTH;
    private final static int BOARD_HEIGHT;
    private final static int FIELD_WIDTH;
    private final static int FIELD_HEIGHT;
    private final static int ROLL_BUTTON_HEIGHT;
    private final static int ROLL_BUTTON_WIDTH;
    private final static Color BOARD_COLOR;
    private final static Color FIELD_COLOR1;
    private final static Color FIELD_COLOR2;

    static {
        SCREEN_WIDTH=1100;
        SCREEN_HEIGHT = 750;
        BOARD_WIDTH = 528;
        BOARD_HEIGHT = 528;
        BOARD_COLOR = new Color(200, 224,196);
        FIELD_COLOR1 = new Color(201, 200,252);
        FIELD_COLOR2 = new Color(252, 221,201);
        FIELD_WIDTH = BOARD_WIDTH/12-2;
        FIELD_HEIGHT = FIELD_WIDTH*2+1;
        ROLL_BUTTON_HEIGHT=60;
        ROLL_BUTTON_WIDTH = 100;
    }
    Board board = new Board();
    JButton rollButton;
    JLabel diceLabel1;
    JLabel diceLabel2;

    private static int poz[][]=new int[36][4];
    private boolean start=true;
    private int round;
    Pawn pawn0=new Pawn(0);
    Pawn pawn1=new Pawn(1);
    Pawn pawn2=new Pawn(2);
    Pawn pawn3=new Pawn(3);
    GamePanel(){
        this.setPreferredSize(new Dimension(SCREEN_WIDTH,SCREEN_HEIGHT));
        this.setBackground(Color.WHITE);
        this.setFocusable(true);
        createRollButton();
        createDiceLabels();
        this.setLayout(null);
        this.add(rollButton);
        rollButton.setVisible(true);
        this.add(diceLabel1);
        this.add(diceLabel2);
        this.add(pawn1.getPawn());
        this.add(pawn2.getPawn());
        this.add(pawn3.getPawn());
        this.add(pawn0.getPawn());
        this.setVisible(true);
    }

    public void setPawnsStart(){
        pawn0.placePawnOn(0);
        pawn1.placePawnOn(0);
        pawn2.placePawnOn(0);
        pawn3.placePawnOn(0);
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        draw(g);
    }
     public void draw(Graphics g) {
        ImageIcon imageIcon = new ImageIcon("src/images/menuBackground.jpg");
        Image backgroundImage = imageIcon.getImage();

        // Draw the image without automatic scaling
         g.drawImage(backgroundImage, 0, 0, 1100, 1100, this);


        g.setColor(BOARD_COLOR);//kolor Planszy
        g.fillRect((this.getWidth() - BOARD_WIDTH) / 2, (this.getHeight() - BOARD_HEIGHT) / 2, BOARD_WIDTH, BOARD_HEIGHT);//rysujemy prostokąt na środku to jest plansza

        g.setColor(Color.BLACK);//kolor Marginesów
        g.fillRect((this.getWidth() - BOARD_WIDTH) / 2, (this.getHeight() - BOARD_HEIGHT) / 2, BOARD_WIDTH, FIELD_HEIGHT + 4);
        g.fillRect((this.getWidth() - BOARD_WIDTH) / 2, (this.getHeight() - BOARD_HEIGHT) / 2 + (BOARD_HEIGHT - (FIELD_HEIGHT + 4)), BOARD_WIDTH, FIELD_HEIGHT + 4);
        g.fillRect((this.getWidth() - BOARD_WIDTH) / 2, (this.getHeight() - BOARD_HEIGHT) / 2, FIELD_HEIGHT + 4, BOARD_HEIGHT);
        g.fillRect((this.getWidth() - BOARD_WIDTH) / 2 + (BOARD_WIDTH - (FIELD_HEIGHT + 4)), (this.getHeight() - BOARD_HEIGHT) / 2, FIELD_HEIGHT + 4, BOARD_HEIGHT);
        //rysujemy czarne prostokąty na których powstaną kafelki dzięki czemu zostaną nam marginesy między kafelkami
        int tmp = 0;//zmienna pomocnicza do rysowania kafelków w pentli
        for (int i = 0; i < board.getFieldsArray().length; i++) {

            if (i == 0) {
                g.setColor(FIELD_COLOR2);//Kolor Narożników
                g.fillRect((this.getWidth() - BOARD_WIDTH) / 2 + 2, (this.getHeight() - BOARD_HEIGHT) / 2 + (BOARD_HEIGHT - (FIELD_HEIGHT + 2)), FIELD_HEIGHT, FIELD_HEIGHT);
                tmp += FIELD_WIDTH + 2;
                poz[i][0]=((this.getWidth() - BOARD_WIDTH) / 2 + 2);
                poz[i][1]=(this.getHeight() - BOARD_HEIGHT) / 2 + (BOARD_HEIGHT - (FIELD_HEIGHT + 2));
                poz[i][2]=poz[i][0]+FIELD_HEIGHT;
                poz[i][3]=poz[i][1]+FIELD_HEIGHT;
            }//rysujemy narożnik
            else if (i < 9) {
                 g.setColor(FIELD_COLOR1);//Kolor Pól do kupienia
                g.fillRect((this.getWidth() - BOARD_WIDTH) / 2 + 2, (this.getHeight() - BOARD_HEIGHT) / 2 + (BOARD_HEIGHT - (FIELD_HEIGHT + 2)) - tmp, FIELD_HEIGHT, FIELD_WIDTH);
                poz[i][0]=(this.getWidth() - BOARD_WIDTH) / 2 + 2;
                poz[i][1]=(this.getHeight() - BOARD_HEIGHT) / 2 + (BOARD_HEIGHT - (FIELD_HEIGHT + 2)) - tmp;
                poz[i][2]=poz[i][0]+FIELD_HEIGHT;
                poz[i][3]=poz[i][1]+FIELD_WIDTH;
                tmp += FIELD_WIDTH + 2;
            }//rysujemy kafelki pomiedzy narożnikami
            else if (i == 9) {
                tmp = 0;
                g.setColor(FIELD_COLOR2);//Kolor Narożników
                g.fillRect((this.getWidth() - BOARD_WIDTH) / 2 + 2, (this.getHeight() - BOARD_HEIGHT) / 2 + 2, FIELD_HEIGHT, FIELD_HEIGHT);
                poz[i][0]=(this.getWidth() - BOARD_WIDTH) / 2 + 2;
                poz[i][1]=(this.getHeight() - BOARD_HEIGHT) / 2 + 2;
                poz[i][2]=poz[i][0]+FIELD_HEIGHT;
                poz[i][3]=poz[i][1]+FIELD_HEIGHT;
                tmp += FIELD_HEIGHT + 2;
            }//rysujemy narożnik
            else if (i < 18) {
                g.setColor(FIELD_COLOR1);//Kolor Pól do kupienia
                g.fillRect((this.getWidth() - BOARD_WIDTH) / 2 + 2 + tmp, (this.getHeight() - BOARD_HEIGHT) / 2 + 2, FIELD_WIDTH, FIELD_HEIGHT);
                poz[i][0]=(this.getWidth() - BOARD_WIDTH) / 2 + 2 + tmp;
                poz[i][1]=(this.getHeight() - BOARD_HEIGHT) / 2 + 2;
                poz[i][2]=poz[i][0]+FIELD_WIDTH;
                poz[i][3]=poz[i][1]+FIELD_HEIGHT;
                tmp += FIELD_WIDTH + 2;
            }//rysujemy kafelki pomiedzy narożnikami
            else if (i == 18) {
                tmp = 0;
                g.setColor(FIELD_COLOR2);//Kolor Narożników
                g.fillRect((this.getWidth() - BOARD_WIDTH) / 2 + (BOARD_WIDTH - (FIELD_HEIGHT + 2)), (this.getHeight() - BOARD_HEIGHT) / 2 + 2, FIELD_HEIGHT, FIELD_HEIGHT);
                poz[i][0]=(this.getWidth() - BOARD_WIDTH) / 2 + (BOARD_WIDTH - (FIELD_HEIGHT + 2));
                poz[i][1]=(this.getHeight() - BOARD_HEIGHT) / 2 + 2;
                poz[i][2]=poz[i][0]+ FIELD_HEIGHT;
                poz[i][3]=poz[i][1]+FIELD_HEIGHT;
                tmp += FIELD_HEIGHT + 2;
            }//rysujemy narożnik
            else if (i < 27) {
                g.setColor(FIELD_COLOR1);//Kolor Pól do kupienia
                g.fillRect((this.getWidth() - BOARD_WIDTH) / 2 + (BOARD_WIDTH - (FIELD_HEIGHT + 2)), (this.getHeight() - BOARD_HEIGHT) / 2 + 2 + tmp, FIELD_HEIGHT, FIELD_WIDTH);
                poz[i][0]=(this.getWidth() - BOARD_WIDTH) / 2 + (BOARD_WIDTH - (FIELD_HEIGHT + 2));
                poz[i][1]=(this.getHeight() - BOARD_HEIGHT) / 2 + 2 + tmp;
                poz[i][2]=poz[i][0]+FIELD_HEIGHT;
                poz[i][3]=poz[i][1]+FIELD_WIDTH;
                tmp += FIELD_WIDTH + 2;
            }//rysujemy kafelki pomiedzy narożnikami
            else if (i == 27) {
                tmp = 0;
                g.setColor(FIELD_COLOR2);//Kolor Narożników
                g.fillRect((this.getWidth() - BOARD_WIDTH) / 2 + (BOARD_WIDTH - (FIELD_HEIGHT + 2)), (this.getHeight() - BOARD_HEIGHT) / 2 + (BOARD_HEIGHT - (FIELD_HEIGHT + 2)), FIELD_HEIGHT, FIELD_HEIGHT);
                poz[i][0]=(this.getWidth() - BOARD_WIDTH) / 2 + (BOARD_WIDTH - (FIELD_HEIGHT + 2));
                poz[i][1]=(this.getHeight() - BOARD_HEIGHT) / 2 + (BOARD_HEIGHT - (FIELD_HEIGHT + 2));
                poz[i][2]=poz[i][0]+FIELD_HEIGHT;
                poz[i][3]=poz[i][1]+FIELD_HEIGHT;
                tmp += FIELD_HEIGHT + 2;
            }//rysujemy narożnik
            else {
                g.setColor(FIELD_COLOR1);//Kolor Pól do kupienia
                g.fillRect((this.getWidth() - BOARD_WIDTH) / 2 + (BOARD_WIDTH - (FIELD_WIDTH + 2)) - tmp, (this.getHeight() - BOARD_HEIGHT) / 2 + (BOARD_HEIGHT - (FIELD_HEIGHT + 2)), FIELD_WIDTH, FIELD_HEIGHT);
                poz[i][0]=(this.getWidth() - BOARD_WIDTH) / 2 + (BOARD_WIDTH - (FIELD_WIDTH + 2)) - tmp;
                poz[i][1]=(this.getHeight() - BOARD_HEIGHT) / 2 + (BOARD_HEIGHT - (FIELD_HEIGHT + 2));
                poz[i][2]=poz[i][0]+FIELD_WIDTH;
                poz[i][3]=poz[i][1]+FIELD_HEIGHT;
                tmp += FIELD_WIDTH + 2;
            }//rysujemy kafelki pomiedzy narożnikami
            if(start){ setPawnsStart(); start=false;}//ustawia pionki na początku
        }
    }
    public void createRollButton()
    {
        rollButton = new JButton("Roll");
        rollButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int dice1 = Roll();
                int dice2 = Roll();
                int sum =dice1 +dice2;
                updateDiceImages(dice1, dice2);
                round = board.getMoveCounter()%board.getPlayers().length;
                board.getPlayers()[round].movePlayer(sum);
                if(round==0) pawn0.placePawnOn(board.getPlayers()[round].getFieldIndex());
                if(round==1) pawn1.placePawnOn(board.getPlayers()[round].getFieldIndex());
                if(round==2) pawn2.placePawnOn(board.getPlayers()[round].getFieldIndex());
                if(round==3) pawn3.placePawnOn(board.getPlayers()[round].getFieldIndex());


                board.incrementMoveCounter();//na końcu
            }
        });
        rollButton.setBounds((SCREEN_WIDTH - ROLL_BUTTON_WIDTH)/2, (SCREEN_HEIGHT - ROLL_BUTTON_HEIGHT)/2, ROLL_BUTTON_WIDTH, ROLL_BUTTON_HEIGHT);
        rollButton.setBackground(Color.WHITE);
        rollButton.setFont(new Font("Serif", Font.BOLD, 28));
    }
    public void createDiceLabels() {
        diceLabel1 = new JLabel();
        diceLabel2 = new JLabel();

        diceLabel1.setBounds(SCREEN_WIDTH/2-50, 420, 50, 50);
        diceLabel2.setBounds(SCREEN_WIDTH/2, 420, 50, 50);
    }
    public void updateDiceImages(int value1, int value2) {

        ImageIcon imagePath1 = new ImageIcon("src/images/dice" + value1 + ".png");
        ImageIcon imagePath2 = new ImageIcon("src/images/dice" + value2 + ".png");

        Image scaledImage1 = imagePath1.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        Image scaledImage2 = imagePath2.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);

        ImageIcon icon1 = new ImageIcon(scaledImage1);
        ImageIcon icon2 = new ImageIcon(scaledImage2);

        diceLabel1.setIcon(icon1);
        diceLabel2.setIcon(icon2);


    }




    @Override
    public void actionPerformed(ActionEvent e) {
        
    }
    public static int[][] getPoz()
    {
        return poz;
    }

}


