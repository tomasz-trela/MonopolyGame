package monopolyGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import board.*;
import static player.Dice.Roll;
import player.*;

public class GamePanel extends JPanel{
    private static final int SCREEN_WIDTH;
    private static final int SCREEN_HEIGHT;
    private final static int BOARD_WIDTH;
    private final static int BOARD_HEIGHT;
    private static int FIELD_WIDTH;
    private static int FIELD_HEIGHT;
    private final static int ROLL_BUTTON_HEIGHT;
    private final static int ROLL_BUTTON_WIDTH;
    private final static Color BOARD_COLOR;
    private final static Color FIELD_COLOR1;
    private final static Color FIELD_COLOR2;
    private ArrayList<JLabel> list;
    private static JPanel[] fieldArray;

    static {
        SCREEN_WIDTH=1500;
        SCREEN_HEIGHT = 1000;
        BOARD_WIDTH = 528;
        BOARD_HEIGHT = 528;
        BOARD_COLOR = new Color(200, 224,196);
        FIELD_COLOR1 = new Color(31, 186,192);
        FIELD_COLOR2 = new Color(97, 211,171);
        FIELD_WIDTH = BOARD_WIDTH/12-2;
        FIELD_HEIGHT = FIELD_WIDTH*2+1;
        ROLL_BUTTON_HEIGHT=60;
        ROLL_BUTTON_WIDTH = 100;
    }
    Board board = new Board();
    JButton rollButton;
    JLabel diceLabel1;
    JLabel diceLabel2;
    JLabel fieldInformation = new JLabel();
    JPanel InfoPanel = new JPanel();
    JPanel leftPanel=new JPanel();
    JPanel rightPanel= new JPanel();
    JPanel rightTopPanel = new JPanel();
    JPanel rightBottomPanel = new JPanel();
    JPanel strategyPanel = new JPanel();
    JLabel strategyLabel = new JLabel();

    private boolean start=true;
    private int round;
    Pawn pawn0=new Pawn(0);
    Pawn pawn1=new Pawn(1);
    Pawn pawn2=new Pawn(2);
    Pawn pawn3=new Pawn(3);
    GamePanel(){

        this.setPreferredSize(new Dimension(SCREEN_WIDTH,SCREEN_HEIGHT));
        this.setBackground(Color.WHITE);
        this.setLayout(null);
        
        leftPanel.setBounds(0,0,1000, 1000);
        rightPanel.setBounds(1000,0,500, 1000);
        
        leftPanel.setLayout(null);
        //rightPanel.setLayout(null);
        InfoPanel.setBackground(new Color(0,250,250));
        leftPanel.add(InfoPanel);
        
        rightPanel.setLayout(new GridLayout(2,1));

        leftPanel.setVisible(true);
        rightPanel.setVisible(true);
        
        
        //¯\_(ツ)_/¯ Exception in thread "AWT-EventQueue-0" java.lang.NullPointerException: Cannot read the array length because the return value of "board.Board.GetPlayersArray()" is null
        /*rightTopPanel.setLayout(new BoxLayout(rightTopPanel,BoxLayout.PAGE_AXIS));

        for(int i=0;i<board.GetPlayersArray().length;i++){
            JLabel PlayerNumber = new JLabel("Player " + i);
            JLabel EuroBalance = new JLabel("Euro: " + board.GetPlayersArray()[i].getBalance()[0]);
            JLabel DolarBalance = new JLabel("Dolar " + board.GetPlayersArray()[i].getBalance()[1]);
            
            rightTopPanel.add(PlayerNumber);
            rightTopPanel.add(EuroBalance);
            rightTopPanel.add(DolarBalance);
        }*/
        
        JLabel exchange  = new JLabel("Exchange (also soon)");
        rightBottomPanel.add(exchange);


        rightPanel.add(rightTopPanel);
        rightPanel.add(rightBottomPanel);

        this.add(leftPanel);
        this.add(rightPanel);
        this.setFocusable(true);
        createRollButton();
        createDiceLabels();
        createStrategyPanel();
        leftPanel.add(rollButton);
        leftPanel.add(fieldInformation);
        rollButton.setVisible(true);

        createBoard(36);

        leftPanel.add(diceLabel1);
        leftPanel.add(diceLabel2);
        leftPanel.add(strategyPanel);
        leftPanel.add(pawn1.getPawn());
        leftPanel.add(pawn2.getPawn());
        leftPanel.add(pawn3.getPawn());
        leftPanel.add(pawn0.getPawn());
        this.setVisible(true);
        this.setPawnsStart();
    }
    public void setPawnsStart(){
        pawn0.placePawnOn(0);
        pawn1.placePawnOn(0);
        pawn2.placePawnOn(0);
        pawn3.placePawnOn(0);
    }

    public void createBoard(int fieldnumber){
        fieldArray=new JPanel[fieldnumber];

        FIELD_WIDTH=800/((fieldnumber/4)+3);
        FIELD_HEIGHT=2*FIELD_WIDTH;
        pawn0.SizeSet(FIELD_WIDTH/2, FIELD_WIDTH/2);
        pawn1.SizeSet(FIELD_WIDTH/2, FIELD_WIDTH/2);
        pawn2.SizeSet(FIELD_WIDTH/2, FIELD_WIDTH/2);
        pawn3.SizeSet(FIELD_WIDTH/2, FIELD_WIDTH/2);


        for(int i=0;i<fieldArray.length; i++){
            fieldArray[i]=new JPanel();
          //  fieldArray[i].setLayout(new FlowLayout(FlowLayout.CENTER,2,2));
        }

        int x,y;
        x=100;
        y=100+FIELD_HEIGHT+FIELD_WIDTH*((fieldnumber/4)-1);

        fieldArray[0].setBounds(x,y, FIELD_HEIGHT, FIELD_HEIGHT);
        fieldArray[0].setBackground(FIELD_COLOR2);


        y-=FIELD_WIDTH;
        for(int i=1; i<(fieldnumber/4);i++){
            fieldArray[i].setBounds(x,y, FIELD_HEIGHT, FIELD_WIDTH);;
            fieldArray[i].setBackground(FIELD_COLOR1);
            y=y-FIELD_WIDTH;
        }

        y-=FIELD_WIDTH;
        fieldArray[(fieldnumber/4)].setBounds(x, y, FIELD_HEIGHT, FIELD_HEIGHT);
        fieldArray[(fieldnumber/4)].setBackground(FIELD_COLOR2);

        x+=FIELD_HEIGHT;
        for(int i=(fieldnumber/4)+1; i<2*(fieldnumber/4);i++){
            fieldArray[i].setBounds(x,y, FIELD_WIDTH, FIELD_HEIGHT);
            fieldArray[i].setBackground(FIELD_COLOR1);
            x=x+FIELD_WIDTH;
        }

        fieldArray[2*(fieldnumber/4)].setBounds(x, y, FIELD_HEIGHT, FIELD_HEIGHT);
        fieldArray[2*(fieldnumber/4)].setBackground(FIELD_COLOR2);


        y+=FIELD_HEIGHT;

        for(int i=2*(fieldnumber/4)+1; i<3*(fieldnumber/4);i++){
            fieldArray[i].setBounds(x,y, FIELD_HEIGHT, FIELD_WIDTH);
            fieldArray[i].setBackground(FIELD_COLOR1);
            y=y+FIELD_WIDTH;
        }

        fieldArray[3*(fieldnumber/4)].setBounds(x, y, FIELD_HEIGHT, FIELD_HEIGHT);
        fieldArray[3*(fieldnumber/4)].setBackground(FIELD_COLOR2);


        x-=FIELD_WIDTH;

        for(int i=3*(fieldnumber/4)+1; i<fieldnumber;i++){
            fieldArray[i].setBounds(x,y, FIELD_WIDTH, FIELD_HEIGHT);
            fieldArray[i].setBackground(FIELD_COLOR1);
            x=x-FIELD_WIDTH;

        }

        for(int i=0; i<fieldArray.length; i++){
            /*JLabel index = new JLabel();
            index.setText("Id: " + i);
            fieldArray[i].add(index);*/
            fieldArray[i].setBorder(BorderFactory.createLineBorder(Color.black, 2));
        }

        rollButton.setBounds(450, 470, ROLL_BUTTON_WIDTH,ROLL_BUTTON_HEIGHT);
        diceLabel1.setBounds(450, 540, 50, 50);
        diceLabel2.setBounds(500, 540, 50, 50);
        strategyPanel.setBounds(400, 360, 200,80);
        for(JPanel m: fieldArray){
            leftPanel.add(m);
        }
        for (int i = 0; i < fieldArray.length; i++) {
            fieldArray[i].addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                }

                @Override
                public void mousePressed(MouseEvent e) {
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    showFieldInformation(this);
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    InfoPanel.setVisible(false);
                    InfoPanel.removeAll();
                }
            });
        }

    }

    public static javax.swing.JPanel[] getFieldArray() {
        return fieldArray;
    }


    public void createRollButton()
    {
        rollButton = new JButton("Roll");
        rollButton.setFocusable(false);
        rollButton.addActionListener(new RollButtonReaction());
        rollButton.setBounds((SCREEN_WIDTH - ROLL_BUTTON_WIDTH)/2, (SCREEN_HEIGHT - ROLL_BUTTON_HEIGHT)/2, ROLL_BUTTON_WIDTH, ROLL_BUTTON_HEIGHT);
        rollButton.setBackground(Color.WHITE);
        rollButton.setFont(new Font("Serif", Font.BOLD, 28));
    }
    public void showFieldInformation(MouseListener mouseListener) {
        JPanel Temp = null;
        int TempInt = 0;
        while (TempInt <= fieldArray.length & Temp == null) {
            assert fieldArray[TempInt] != null;
            if (fieldArray[TempInt].getMouseListeners()[0] == mouseListener) {
                Temp = fieldArray[TempInt];
            } else {
                TempInt++;
            }
        }
        ArrayList<JLabel> tempList = new ArrayList<>();
        String [] tempString = Board.getFieldsArray()[TempInt].toString().split(",");
        for (int i = 0; i < tempString.length; i++) {
            tempList.add(new JLabel(tempString[i]));
            tempList.get(i).setForeground(Color.BLACK);
            tempList.get(i).setFont(new Font("Serif", Font.BOLD, 20));
            tempList.get(i).setVisible(false);
        }
        int y = 200;

        if (InfoPanel.getY() > 300) {
            InfoPanel.setBounds(fieldArray[TempInt].getX() + FIELD_WIDTH/2, fieldArray[TempInt].getY() - 2*FIELD_HEIGHT, 200, tempList.size() * 35);
        } else {
            InfoPanel.setBounds(fieldArray[TempInt].getX() + FIELD_WIDTH/2, fieldArray[TempInt].getY() + FIELD_HEIGHT/2, 200, tempList.size() * 35);
        }
        for (int i = 0; i < tempList.size(); i++) {
            y += i * 15;
            tempList.get(i).setVisible(true);
            tempList.get(i).setBounds(SCREEN_WIDTH/2-500, y, 200, 100);
            tempList.get(i).setVisible(true);
            InfoPanel.add(tempList.get(i));
        }
        InfoPanel.setVisible(true);
    }
    public void createDiceLabels() {
        diceLabel1 = new JLabel();
        diceLabel2 = new JLabel();

        diceLabel1.setBounds(SCREEN_WIDTH/2-50, 420, 50, 50);
        diceLabel2.setBounds(SCREEN_WIDTH/2, 420, 50, 50);
    }
    public void createStrategyPanel(){
        strategyPanel = new JPanel();
        //strategyPanel.setBounds(SCREEN_WIDTH/2-25, 600,200,100);
        strategyPanel.setLayout(new BoxLayout(strategyPanel,BoxLayout.Y_AXIS));
        strategyPanel.setBackground(new Color(156,238,141));

        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        panel1.setOpaque(false);
        panel2.setOpaque(false);
        strategyPanel.add(panel1);
        strategyPanel.add(panel2);

        strategyLabel = new JLabel("blad");
        panel1.add(strategyLabel);

        JButton takButton = new JButton("tak");
        JButton nieButton = new JButton("nie");
        takButton.setBackground(Color.WHITE);
        takButton.setFont(new Font("Serif", Font.BOLD, 14));
        nieButton.setBackground(Color.WHITE);
        nieButton.setFont(new Font("Serif", Font.BOLD, 14));
        takButton.addActionListener(new StrategyPanelButtonTakReaction());
        nieButton.addActionListener(new StrategyPanelButtonNieReaction());
        panel2.add(takButton);
        panel2.add(nieButton);

        strategyPanel.setVisible(false);
    }
    public void updateStrategyPanel(){
        if(board.getCurrentPlayer().getLocation() instanceof ToBuy){
            if(((ToBuy) board.getCurrentPlayer().getLocation()).getOwner() == null){
                strategyLabel.setText("Czy chcesz kupić to pole?");
            }
            else {
                if (board.getCurrentPlayer().getOwnedFields().contains((board.getCurrentPlayer().getLocation()))){
                    strategyLabel.setText("Czy chcesz coś tutaj wybudować?");
                }

            }
        }
        if(board.getCurrentPlayer().getLocation() instanceof CarDealership){
            strategyLabel.setText("Czy chcesz kupić auto?");
        }
        if(board.getCurrentPlayer().getLocation() instanceof Chance){
            strategyLabel.setText("Czy chcesz wziąć kartę?");
        }
        strategyPanel.setVisible(true);
        if(board.getCurrentPlayer().getLocation() instanceof Start){
            strategyPanel.setVisible(false);
        }

    }

    class StrategyPanelButtonTakReaction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            //board.getCurrentPlayer().playerAction(board);
        }
    }
    class StrategyPanelButtonNieReaction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

    public void updateDiceImages(int value1, int value2) {
        ImageIcon imagePath1 = new ImageIcon(getClass().getResource("/images/dice" + value1 + ".png"));
        ImageIcon imagePath2 = new ImageIcon(getClass().getResource("/images/dice" + value2 + ".png"));


        Image scaledImage1 = imagePath1.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        Image scaledImage2 = imagePath2.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);

        ImageIcon icon1 = new ImageIcon(scaledImage1);
        ImageIcon icon2 = new ImageIcon(scaledImage2);

        diceLabel1.setIcon(icon1);
        diceLabel2.setIcon(icon2);
    }
    class RollButtonReaction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int dice1 = Roll();
            int dice2 = Roll();
            int sum =dice1 +dice2;
            updateDiceImages(dice1, dice2);
            round = board.getMoveCounter()%board.getPlayers().length;
            board.getPlayers()[round].movePlayer(sum);

            board.SetCurrentPlayerOnGamePanel(round);
            board.ChangePlayerLocation(sum);
            updateStrategyPanel();

            board.getCurrentPlayer().playerAction(board);//tymczasowo tutaj a nie w klasie przycisku "tak"

            if(round==0) pawn0.placePawnOn(board.getPlayers()[round].getFieldIndex());
            if(round==1) pawn1.placePawnOn(board.getPlayers()[round].getFieldIndex());
            if(round==2) pawn2.placePawnOn(board.getPlayers()[round].getFieldIndex());
            if(round==3) pawn3.placePawnOn(board.getPlayers()[round].getFieldIndex());

            board.incrementMoveCounter();//na końcu
        }
    }
}


