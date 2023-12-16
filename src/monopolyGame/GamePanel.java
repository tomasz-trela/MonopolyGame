package monopolyGame;

import java.awt.Font;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import board.*;
import static player.Dice.Roll;

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
        SCREEN_WIDTH=900;
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
    JTextField dice1TextField;
    JTextField dice2TextField;
    private static int poz[][]=new int[36][4];
    private boolean test=false;
    Pawn pawn0=new Pawn(0);
    Pawn pawn1=new Pawn(1);
    Pawn pawn2=new Pawn(2);
    Pawn pawn3=new Pawn(3);
    GamePanel(){
        this.setPreferredSize(new Dimension(SCREEN_WIDTH,SCREEN_HEIGHT));
        this.setBackground(Color.WHITE);
        this.setFocusable(true);
        createRollButton();
        this.setLayout(null);
        this.add(rollButton);
        rollButton.setVisible(true);
        this.createDice();
        this.add(dice1TextField);
        this.add(dice2TextField);
        this.add(pawn1.getPawn());
        this.add(pawn2.getPawn());
        this.add(pawn3.getPawn());
        this.add(pawn4.getPawn());
        //trzeba dodać labele z stanem konta
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        draw(g);
    }
     public void draw(Graphics g) {
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
                if(test && i==4)    g.setColor(Color.RED);//test zmiany koloru
                else g.setColor(FIELD_COLOR1);//Kolor Pól do kupienia
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

                dice1TextField.setText(Integer.toString(dice1));
                dice2TextField.setText(Integer.toString(dice2));

                repaint();
            }
        });
        rollButton.setBounds((SCREEN_WIDTH - ROLL_BUTTON_WIDTH)/2, (SCREEN_HEIGHT - ROLL_BUTTON_HEIGHT)/2, ROLL_BUTTON_WIDTH, ROLL_BUTTON_HEIGHT);
        rollButton.setBackground(Color.WHITE);
        rollButton.setFont(new Font("Serif", Font.BOLD, 28));
    }
    public void createDice(){
        dice1TextField = new JTextField("");
        dice2TextField = new JTextField("");

        dice1TextField.setBounds((SCREEN_WIDTH - ROLL_BUTTON_WIDTH) / 2  + 10, (SCREEN_HEIGHT - ROLL_BUTTON_HEIGHT) / 2 + ROLL_BUTTON_HEIGHT + 10, 30, 30);
        dice2TextField.setBounds((SCREEN_WIDTH - ROLL_BUTTON_WIDTH) / 2 + 50, (SCREEN_HEIGHT - ROLL_BUTTON_HEIGHT) / 2 + ROLL_BUTTON_HEIGHT + 10, 30, 30);

        dice1TextField.setBackground(Color.WHITE);
        dice2TextField.setBackground(Color.WHITE);
        dice1TextField.setForeground(Color.BLACK);
        dice2TextField.setForeground(Color.BLACK);

        dice1TextField.setFont(new Font("Arial", Font.BOLD, 15));
        dice2TextField.setFont(new Font("Arial", Font.BOLD, 15));

        dice1TextField.setEditable(false);
        dice2TextField.setEditable(false);
        dice1TextField.setBorder(null);
        dice2TextField.setBorder(null);

        dice1TextField.setHorizontalAlignment(JTextField.CENTER);
        dice2TextField.setHorizontalAlignment(JTextField.CENTER);

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        
    }

}


