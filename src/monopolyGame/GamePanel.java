package monopolyGame;

import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
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
     private static JPanel[] descArray;
    private static JPanel[] centerArray;
    private static JPanel[] priceArray;

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
    JPanel strategyPanel = new JPanel();
    JLabel strategyLabel = new JLabel();
    JButton yesButton = new JButton();
    JButton noButton = new JButton();
    JButton okButton = new JButton();
    JButton carButton = new JButton();
    JLabel balancePlayer0Label;
    JLabel balancePlayer1Label;
    JLabel balancePlayer2Label;
    JLabel balancePlayer3Label;

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

        this.add(leftPanel);
        this.add(rightPanel);
        this.setFocusable(true);
        createRollButton();
        createDiceLabels();
        createStrategyPanel();
        createCarButton();
        leftPanel.add(rollButton);
        leftPanel.add(fieldInformation);
        rollButton.setVisible(true);

      // createBoard(36);

        leftPanel.add(diceLabel1);
        leftPanel.add(diceLabel2);
        leftPanel.add(strategyPanel);
        leftPanel.add(carButton);
        leftPanel.add(pawn1.getPawn());
        leftPanel.add(pawn2.getPawn());
        leftPanel.add(pawn3.getPawn());
        leftPanel.add(pawn0.getPawn());
        this.setVisible(true);
    }
    public void setPawnsStart(){
        pawn0.placePawnOn(0);
        pawn1.placePawnOn(0);
        pawn2.placePawnOn(0);
        pawn3.placePawnOn(0);
    }

   public void createBoard(int fieldnumber){
        fieldArray=new JPanel[fieldnumber];


        descArray= new JPanel[fieldnumber];
        centerArray=new JPanel[fieldnumber];
        priceArray= new JPanel[fieldnumber];

        FIELD_WIDTH=800/((fieldnumber/4)+3);
        FIELD_HEIGHT=2*FIELD_WIDTH;
        pawn0.SizeSet(FIELD_WIDTH/3, FIELD_WIDTH/3);
        pawn1.SizeSet(FIELD_WIDTH/3, FIELD_WIDTH/3);
        pawn2.SizeSet(FIELD_WIDTH/3, FIELD_WIDTH/3);
        pawn3.SizeSet(FIELD_WIDTH/3, FIELD_WIDTH/3);


        for(int i=0;i<fieldArray.length; i++){
            fieldArray[i]=new JPanel();
            descArray[i]=new JPanel();
            centerArray[i]=new JPanel();
            priceArray[i]=new JPanel();
        }

        int x,y;
        x=100;
        y=100+FIELD_HEIGHT+FIELD_WIDTH*((fieldnumber/4)-1);


        fieldArray[0].setBounds(x,y, FIELD_HEIGHT, FIELD_HEIGHT);
        fieldArray[0].setLayout(new FlowLayout(0,0,0));
        centerArray[0]= new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g.create();
                Image backgroundImage= new ImageIcon("src/images/start.png").getImage();
                backgroundImage.getScaledInstance(FIELD_HEIGHT,FIELD_HEIGHT,Image.SCALE_DEFAULT);
                g2d.drawImage(backgroundImage, 0, 0, FIELD_HEIGHT, FIELD_HEIGHT, null);
                g2d.dispose();
            }

        };
        centerArray[0].setPreferredSize(new Dimension(FIELD_HEIGHT, FIELD_HEIGHT));
        fieldArray[0].setBackground(FIELD_COLOR1);
        fieldArray[0].add(centerArray[0]);



        y-=FIELD_WIDTH;
        for(int i=1; i<(fieldnumber/4);i++){
            fieldArray[i].setLayout(new BoxLayout(fieldArray[i],BoxLayout.X_AXIS));
            drawVertical1(i);
            fieldArray[i].setBounds(x,y, FIELD_HEIGHT, FIELD_WIDTH);
            y=y-FIELD_WIDTH;

        }

        y-=FIELD_WIDTH;
        fieldArray[(fieldnumber/4)].setBounds(x, y, FIELD_HEIGHT, FIELD_HEIGHT);
        fieldArray[(fieldnumber/4)].setBackground(FIELD_COLOR2);

        x+=FIELD_HEIGHT;
        for(int i=(fieldnumber/4)+1; i<2*(fieldnumber/4);i++){
            fieldArray[i].setLayout(new BoxLayout(fieldArray[i],BoxLayout.Y_AXIS));
            fieldArray[i].setBounds(x,y, FIELD_WIDTH, FIELD_HEIGHT);
            drawHorizontal(i);
            fieldArray[i].setBackground(FIELD_COLOR1);
            x=x+FIELD_WIDTH;
        }

        fieldArray[2*(fieldnumber/4)].setBounds(x, y, FIELD_HEIGHT, FIELD_HEIGHT);
        fieldArray[2*(fieldnumber/4)].setBackground(FIELD_COLOR2);


        y+=FIELD_HEIGHT;

        for(int i=2*(fieldnumber/4)+1; i<3*(fieldnumber/4);i++){
            fieldArray[i].setBounds(x,y, FIELD_HEIGHT, FIELD_WIDTH);
            fieldArray[i].setLayout(new BoxLayout(fieldArray[i],BoxLayout.X_AXIS));
            drawVertical2(i);
            fieldArray[i].setBackground(FIELD_COLOR1);
            y=y+FIELD_WIDTH;
        }

        fieldArray[3*(fieldnumber/4)].setBounds(x, y, FIELD_HEIGHT, FIELD_HEIGHT);
        fieldArray[3*(fieldnumber/4)].setBackground(FIELD_COLOR2);


        x-=FIELD_WIDTH;

        for(int i=3*(fieldnumber/4)+1; i<fieldnumber;i++){
            fieldArray[i].setLayout(new BoxLayout(fieldArray[i],BoxLayout.Y_AXIS));
            fieldArray[i].setBounds(x,y, FIELD_WIDTH, FIELD_HEIGHT);
            drawHorizontal(i);
            fieldArray[i].setBackground(FIELD_COLOR1);
            x=x-FIELD_WIDTH;

        }

        rollButton.setBounds(450, 470, ROLL_BUTTON_WIDTH,ROLL_BUTTON_HEIGHT);
        diceLabel1.setBounds(450, 540, 50, 50);
        diceLabel2.setBounds(500, 540, 50, 50);
        strategyPanel.setBounds(385, 430, 230,80);
        for(int i=fieldnumber/4; i<fieldnumber; i+=fieldnumber/4)
        {
            fieldArray[i].setLayout(new FlowLayout(0,0,0));
            centerArray[i].setBorder(BorderFactory.createLineBorder(Color.black,2));
            drawExchange(i);
        }
        for(JPanel m: fieldArray){
            leftPanel.add(m);
        }
        for (int i = 0; i < fieldArray.length; i++) {
            fieldArray[i].addMouseListener(new ShowInfoPanelMouseListener());
            priceArray[i].setBorder(BorderFactory.createLineBorder(Color.black,2));
            priceArray[i].setBackground(FIELD_COLOR1);
            descArray[i].setBorder(BorderFactory.createLineBorder(Color.black,2));
            descArray[i].setBackground(Color.cyan);
            centerArray[i].setBorder(BorderFactory.createLineBorder(Color.black,2));
            centerArray[i].setBackground(FIELD_COLOR1);
        }
       this.setPawnsStart();
    }
    public void drawHorizontal(int i)
    {
            final int tmp=i;

            if(Board.getFieldsArray()[i] instanceof ToBuy)
            {
                descArray[i]= new JPanel() {
                    @Override
                    protected void paintComponent(Graphics g) {
                        super.paintComponent(g);
                        Graphics2D g2d = (Graphics2D) g.create();
                        Font font = new Font("Arial", Font.BOLD, 12);
                        g2d.setFont(font);
                        String nap=Board.getFieldsArray()[tmp].getName();
                        FontMetrics fontMetrics = g.getFontMetrics(font);
                        int textWidth = fontMetrics.stringWidth(nap);
                        int x = (getWidth() - textWidth) / 2;
                        int y = (getHeight() - fontMetrics.getHeight()) / 2 + fontMetrics.getAscent();
                        g2d.drawString(nap, x,y);
                        g2d.dispose();
                    }
                };
                descArray[i].setMaximumSize(new Dimension(FIELD_WIDTH,25));
                fieldArray[i].add(descArray[i]);
            }
            centerArray[i]= new JPanel()
            {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    Graphics2D g2d = (Graphics2D) g.create();
                    if(Board.getFieldsArray()[tmp] instanceof City)
                    {
                        Image backgroundImage = new ImageIcon("src/images/city.png").getImage();
                        AffineTransform at= scaleIMG(backgroundImage, getWidth(), getHeight(), 0);
                        g2d.drawImage(backgroundImage, at, null);
                    }
                    if(Board.getFieldsArray()[tmp] instanceof Village)
                    {
                        Image backgroundImage = new ImageIcon("src/images/village.png").getImage();
                        AffineTransform at= scaleIMG(backgroundImage, getWidth(), getHeight(), 0);
                        g2d.drawImage(backgroundImage, at, null);
                    }
                    if(Board.getFieldsArray()[tmp] instanceof Chance)
                    {
                        Image backgroundImage = new ImageIcon("src/images/chance.png").getImage();
                        AffineTransform at= scaleIMG(backgroundImage, getWidth(), getHeight(), 0);
                        g2d.drawImage(backgroundImage, at, null);
                    }
                    if(Board.getFieldsArray()[tmp] instanceof CarDealership)
                    {
                        Image backgroundImage = new ImageIcon("src/images/car.jpeg").getImage();
                        AffineTransform at= scaleIMG(backgroundImage, getWidth(), getHeight(), 0);
                        g2d.drawImage(backgroundImage, at, null);
                    }
                }
            };
            fieldArray[i].add(centerArray[i]);
            if(Board.getFieldsArray()[i] instanceof ToBuy)
            {
                priceArray[tmp]= new JPanel() {

                    @Override
                    protected void paintComponent(Graphics g) {
                        super.paintComponent(g);
                        Graphics2D g2d = (Graphics2D) g.create();
                        Font font = new Font("Arial", Font.BOLD, 12);
                        g2d.setFont(font);
                        String nap=getActualPrice(tmp);
                        FontMetrics fontMetrics = g.getFontMetrics(font);
                        int textWidth = fontMetrics.stringWidth(nap);
                        int x = (getWidth() - textWidth) / 2;
                        int y = (getHeight() - fontMetrics.getHeight()) / 2 + fontMetrics.getAscent()+1;
                        g2d.drawString(nap, x,y);
                        g2d.dispose();
                    }
                };
                priceArray[i].setMaximumSize(new Dimension(FIELD_WIDTH, 15));
                fieldArray[i].add(priceArray[i]);
            }
    }
    public void drawVertical1(int i)
    {
        final int tmp=i;


        if(Board.getFieldsArray()[i] instanceof ToBuy)
        {
            priceArray[tmp]= new JPanel() {

                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    Graphics2D g2d = (Graphics2D) g.create();
                    g2d.rotate(Math.PI / 2);
                    Font font = new Font("Arial", Font.BOLD, 12);
                    g2d.setFont(font);
                    String nap=getActualPrice(tmp);
                    FontMetrics fontMetrics = g.getFontMetrics(font);
                    int textWidth = fontMetrics.stringWidth(nap);
                    int x = (getHeight() - textWidth) / 2;
                    int y = (getWidth() - fontMetrics.getHeight()) / 2 -2;
                    g2d.drawString(nap, x,y);
                    g2d.dispose();
                }
            };
            priceArray[i].setMaximumSize(new Dimension(15, FIELD_WIDTH));
            fieldArray[i].add(priceArray[i]);
        }


        centerArray[i]= new JPanel()
        {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g.create();
                if(Board.getFieldsArray()[tmp] instanceof City)
                {
                    Image backgroundImage = new ImageIcon("src/images/city.png").getImage();
                    AffineTransform at= scaleIMG(backgroundImage, getWidth(), getHeight(), Math.PI/2);
                    g2d.drawImage(backgroundImage, at, null);
                }
                if(Board.getFieldsArray()[tmp] instanceof Village)
                {
                    Image backgroundImage = new ImageIcon("src/images/village.png").getImage();
                    AffineTransform at= scaleIMG(backgroundImage, getWidth(), getHeight(), Math.PI/2);
                    g2d.drawImage(backgroundImage, at, null);
                }
                if(Board.getFieldsArray()[tmp] instanceof Chance)
                {
                    Image backgroundImage = new ImageIcon("src/images/chance.png").getImage();
                    AffineTransform at= scaleIMG(backgroundImage, getWidth(), getHeight(), Math.PI/2);
                    g2d.drawImage(backgroundImage, at, null);
                }
                if(Board.getFieldsArray()[tmp] instanceof CarDealership)
                {
                    Image backgroundImage = new ImageIcon("src/images/car.jpeg").getImage();
                    AffineTransform at= scaleIMG(backgroundImage, getWidth(), getHeight(), Math.PI/2);
                    g2d.drawImage(backgroundImage, at, null);
                }
            }
        };
        fieldArray[i].add(centerArray[i]);
        if(Board.getFieldsArray()[i] instanceof ToBuy)
        {
            descArray[i]= new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    Graphics2D g2d = (Graphics2D) g.create();
                    g2d.rotate(Math.PI / 2);
                    Font font = new Font("Arial", Font.BOLD, 12);
                    g2d.setFont(font);
                    String nap=Board.getFieldsArray()[tmp].getName();
                    FontMetrics fontMetrics = g.getFontMetrics(font);
                    int textWidth = fontMetrics.stringWidth(nap);
                    int x = (getHeight() - textWidth) / 2;
                    int y = (getWidth() - fontMetrics.getHeight()) / 2 -2;
                    g2d.drawString(nap, x,y);
                    g2d.dispose();
                }

            };
            descArray[i].setMaximumSize(new Dimension(25, FIELD_WIDTH));
            fieldArray[i].add(descArray[i]);
        }
    }
    public void drawVertical2(int i)
    {
        final int tmp=i;
        if(Board.getFieldsArray()[i] instanceof ToBuy)
        {
            descArray[i]= new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    Graphics2D g2d = (Graphics2D) g.create();
                    Font font = new Font("Arial", Font.BOLD, 12);
                    g2d.setFont(font);
                    g2d.rotate(-Math.PI/2);
                    String nap=Board.getFieldsArray()[tmp].getName();
                    FontMetrics fontMetrics = g2d.getFontMetrics(font);
                    int textHeight = fontMetrics.stringWidth(nap);
                    int textDescent = fontMetrics.getDescent();
                    int x=(-getHeight()-textHeight)/2;
                    int y=(getWidth() + fontMetrics.getAscent()) / 2 - textDescent+2;
                    g2d.drawString(nap, x, y);
                    g2d.dispose();
                }
            };
            descArray[i].setMaximumSize(new Dimension(25,FIELD_WIDTH));
            fieldArray[i].add(descArray[i]);
        }
        centerArray[i]= new JPanel()
        {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g.create();
                if(Board.getFieldsArray()[tmp] instanceof City)
                {
                    Image backgroundImage = new ImageIcon("src/images/city.png").getImage();
                    AffineTransform at= scaleIMG(backgroundImage, getWidth(), getHeight(), -Math.PI/2);
                    g2d.drawImage(backgroundImage, at, null);
                }
                if(Board.getFieldsArray()[tmp] instanceof Village)
                {
                    Image backgroundImage = new ImageIcon("src/images/village.png").getImage();
                    AffineTransform at= scaleIMG(backgroundImage, getWidth(), getHeight(), -Math.PI/2);
                    g2d.drawImage(backgroundImage, at, null);
                }
                if(Board.getFieldsArray()[tmp] instanceof Chance)
                {
                    Image backgroundImage = new ImageIcon("src/images/chance.png").getImage();
                    AffineTransform at= scaleIMG(backgroundImage, getWidth(), getHeight(), -Math.PI/2);
                    g2d.drawImage(backgroundImage, at, null);
                }
                if(Board.getFieldsArray()[tmp] instanceof CarDealership)
                {
                    Image backgroundImage = new ImageIcon("src/images/car.jpeg").getImage();
                    AffineTransform at= scaleIMG(backgroundImage, getWidth(), getHeight(), -Math.PI/2);
                    g2d.drawImage(backgroundImage, at, null);
                }
            }
        };
        fieldArray[i].add(centerArray[i]);
        if(Board.getFieldsArray()[i] instanceof ToBuy)
        {
            priceArray[tmp]= new JPanel() {

                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    Graphics2D g2d = (Graphics2D) g.create();
                    Font font = new Font("Arial", Font.BOLD, 12);
                    g2d.setFont(font);
                    g2d.rotate(-Math.PI/2);
                    String nap=getActualPrice(tmp);
                    FontMetrics fontMetrics = g2d.getFontMetrics(font);
                    int textHeight = fontMetrics.stringWidth(nap);
                    int textDescent = fontMetrics.getDescent();
                    int x=(-getHeight()-textHeight)/2;
                    int y=(getWidth() + fontMetrics.getAscent()) / 2 - textDescent+2;
                    g2d.drawString(nap, x, y);
                    g2d.dispose();
                }
            };
            priceArray[i].setMaximumSize(new Dimension(15, FIELD_WIDTH));
            fieldArray[i].add(priceArray[i]);
        }
    }
    public AffineTransform scaleIMG(Image backgroundImage, int panelWidth, int panelHeight, double rotationAngleInRadians)
    {
        double scaleFactorWidth = (double) panelWidth / backgroundImage.getWidth(null);
        double scaleFactorHeight = (double) panelHeight / backgroundImage.getHeight(null);
        AffineTransform at = AffineTransform.getScaleInstance(scaleFactorWidth, scaleFactorHeight);
        at.rotate(rotationAngleInRadians, backgroundImage.getWidth(null) / 2.0, backgroundImage.getHeight(null) / 2.0);
        return at;
    }
    public String getActualPrice(int i)
    {
        ToBuy buyt= (ToBuy) Board.getFieldsArray()[i];
        String nap;
        if(buyt.getPrice()[0]>buyt.getPrice()[1])
        {
            nap=Integer.toString(buyt.getPrice()[0]);
            nap+=" €";
        }
        else
        {
            nap=Integer.toString(buyt.getPrice()[1]);
            nap+=" $";
        }
        return nap;
    }
    class ShowInfoPanelMouseListener implements MouseListener{
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
    }
    public void drawExchange(int i) {
        centerArray[i] = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g.create();
                Image backgroundImage = new ImageIcon("src/images/Exchange.png").getImage();
                backgroundImage.getScaledInstance(FIELD_HEIGHT, FIELD_HEIGHT, Image.SCALE_DEFAULT);
                g2d.drawImage(backgroundImage, 0, 0, FIELD_HEIGHT, FIELD_HEIGHT, null);
                g2d.dispose();
            }
        };
        centerArray[i].setPreferredSize(new Dimension(FIELD_HEIGHT, FIELD_HEIGHT));
        fieldArray[i].setBackground(FIELD_COLOR1);
        fieldArray[i].add(centerArray[i]);
    }

    public static JPanel[] getFieldArray() {
        return centerArray;
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

        yesButton = new JButton("yes");
        noButton = new JButton("no");
        okButton = new JButton("ok");
        okButton.setVisible(false);

        yesButton.setBackground(Color.WHITE);
        yesButton.setFont(new Font("Serif", Font.BOLD, 14));
        noButton.setBackground(Color.WHITE);
        noButton.setFont(new Font("Serif", Font.BOLD, 14));
        okButton.setBackground(Color.WHITE);
        okButton.setFont(new Font("Serif", Font.BOLD, 14));

        yesButton.addActionListener(new StrategyPanelButtonYesReaction());
        noButton.addActionListener(new StrategyPanelButtonNoReaction());
        okButton.addActionListener(new StrategyPanelButtonOkReaction());

        panel2.add(yesButton);
        panel2.add(noButton);
        panel2.add(okButton);

        strategyPanel.setVisible(false);
    }
    public void updateStrategyLabel(){
        yesButton.setVisible(true);
        noButton.setVisible(true);
        okButton.setVisible(false);
        if(board.getCurrentPlayer().getLocation() instanceof ToBuy){
            if(((ToBuy) board.getCurrentPlayer().getLocation()).getOwner() == null){
                strategyLabel.setText("Would you like to buy this field?");
            }
            else {
                if (board.getCurrentPlayer().getOwnedFields().contains((board.getCurrentPlayer().getLocation()))){
                    strategyLabel.setText("Would you like to build something here?");
                }
                else {
                    yesButton.setVisible(false);
                    noButton.setVisible(false);
                    okButton.setVisible(true);
                    strategyLabel.setText("A fee has been collected");
                }

            }
        }
        if(board.getCurrentPlayer().getLocation() instanceof CarDealership){
            strategyLabel.setText("Would you like to buy a car?");
        }
        if(board.getCurrentPlayer().getLocation() instanceof Chance){
            strategyLabel.setText("Would you like to take a card?");
        }
        if(board.getCurrentPlayer().getLocation() instanceof Exchange){
            strategyLabel.setText("Would you like to exchange currency?");
        }

        strategyPanel.setVisible(true);

        if(board.getCurrentPlayer().getLocation() instanceof Start){
            strategyPanel.setVisible(false);
        }

    }

    public void createBalanceLabels(){
        JPanel rightTopPanel = new JPanel();
        Font f = new Font(Font.SANS_SERIF, Font.BOLD, 23);
        rightTopPanel.setLayout(new BoxLayout(rightTopPanel, BoxLayout.PAGE_AXIS));
        
        rightPanel.add(rightTopPanel);
        
        for(int i=0;i<board.GetPlayersArray().length;i++) {
            String PlayerNumber = "Player " + (i + 1);
            String EuroBalance = "Euro: " + board.GetPlayersArray()[i].getBalance()[0];
            String DolarBalance = "Dolar: " + board.GetPlayersArray()[i].getBalance()[1];

            String PlayerInfo = "<html>" + PlayerNumber + "<br>" + EuroBalance + "<br>" + DolarBalance;


            switch (i) {
                case 0:
                    balancePlayer0Label = new JLabel(PlayerInfo);
                    createPlayerLabel(balancePlayer0Label, f, i, rightTopPanel);
                    break;
                case 1:
                    balancePlayer1Label = new JLabel(PlayerInfo);
                    createPlayerLabel(balancePlayer1Label, f, i, rightTopPanel);
                    break;
                case 2:
                    balancePlayer2Label = new JLabel(PlayerInfo);
                    createPlayerLabel(balancePlayer2Label, f, i, rightTopPanel);
                    break;
                case 3:
                    balancePlayer3Label = new JLabel(PlayerInfo);
                    createPlayerLabel(balancePlayer3Label, f, i, rightTopPanel);
                    break;
            }
        }

        revalidate();
    }

    private void createPlayerLabel(JLabel AllInOne, Font f, int i, JPanel rightTopPanel){
        AllInOne.setFont(f);
        AllInOne.setBackground(Color.decode("#bfbfbf"));
        AllInOne.setOpaque(true);

        switch (i) {
            case 0:{
                Border border = BorderFactory.createLineBorder(Color.decode("#e34242"), 10, true) ;
                AllInOne.setBorder(border);
                break;
            }
            case 1:{
                Border border = BorderFactory.createLineBorder(Color.decode("#2aa9e8"), 10, true) ;
                AllInOne.setBorder(border);
                break;
            }
            case 2:{
                Border border = BorderFactory.createLineBorder(Color.decode("#f0f026"), 10, true) ;
                AllInOne.setBorder(border);
                break;
            }
            case 3:{
                Border border = BorderFactory.createLineBorder(Color.decode("#3cd646"), 10, true) ;
                AllInOne.setBorder(border);
                break;
            }
        }

        rightTopPanel.add(AllInOne);
    }

    public void updateBalanceLabels(){
        revalidate();
    }
    public void CreateExchangeLabels(){
        JPanel RightBottomPanel = new JPanel();
        RightBottomPanel.setBorder(BorderFactory.createLineBorder(Color.decode("#000000"), 10, true));
        RightBottomPanel.setLayout(new BoxLayout(RightBottomPanel, BoxLayout.PAGE_AXIS));
        Font f = new Font(Font.SANS_SERIF, Font.BOLD, 23);
        Exchange e = (Exchange) board.getFieldsArray()[9];
        
        
        //obecny kurs walut
        double tmp = Math.round(e.getDolarRate()/e.getEuroRate() * 100) / 100;
        String EuroTODolar = "1 Euro is worth " + Double.toString((tmp)) + " Dolars";
        tmp = Math.round(e.getEuroRate()/e.getDolarRate() * 100) / 100;
        String DolarTOEuro = "1 Dolar is worth " + Double.toString((tmp)) + " Euros";

        JLabel EurotoDolar = new JLabel(EuroTODolar);
        JLabel DolartoEuro = new JLabel(DolarTOEuro);
        EurotoDolar.setAlignmentX(Component.CENTER_ALIGNMENT);
        DolartoEuro.setAlignmentX(Component.CENTER_ALIGNMENT);
        EurotoDolar.setFont(f);
        DolartoEuro.setFont(f);

        
        //opcje wyboru wymiany
        String[] choices = {"Euro to Dolar", "Dolar to Euro"};
        JComboBox<String> options = new JComboBox<String>(choices);
        options.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        options.setMaximumSize(options.getPreferredSize());
        options.setAlignmentX(Component.CENTER_ALIGNMENT);       
        
        JTextField moneyInput = new JTextField("Enter the amount");
        Dimension a = new Dimension(200, 30);
        moneyInput.setMaximumSize(a);
        moneyInput.setAlignmentX(Component.CENTER_ALIGNMENT);

        String money = "You will recieve " ;
        //jakoś trzeba dodać że w zależności od wcześniej wybranych opcji 
        //wyświetla przeliczoną kwotę i walutę

        JLabel moneyOutput = new JLabel(money);
        moneyOutput.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel space = new JLabel("<html> <br> <br> <br> </html>");
        
        JButton Bexchange = new JButton("Exchange");
        Bexchange.setAlignmentX(Component.CENTER_ALIGNMENT);
        Dimension b = new Dimension(200,70);
        Bexchange.setMaximumSize(b);
        Bexchange.setBackground(Color.WHITE);
        Bexchange.setFont(new Font("Serif", Font.BOLD, 28));
        Bexchange.setMargin(new Insets(20, 20, 20,20));

        RightBottomPanel.add(EurotoDolar);
        RightBottomPanel.add(DolartoEuro);
        RightBottomPanel.add(options);
        RightBottomPanel.add(moneyInput);
        RightBottomPanel.add(moneyOutput);
        RightBottomPanel.add(space);
        RightBottomPanel.add(Bexchange);

        rightPanel.add(RightBottomPanel);
        
    }

    class StrategyPanelButtonYesReaction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            board.getCurrentPlayer().playerAction(board);

            strategyPanel.setVisible(false);
            carButton.setVisible(false);
            rollButton.setVisible(true);
        }
    }
    class StrategyPanelButtonNoReaction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            strategyPanel.setVisible(false);
            carButton.setVisible(false);
            rollButton.setVisible(true);
        }
    }
    class StrategyPanelButtonOkReaction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            strategyPanel.setVisible(false);
            carButton.setVisible(false);
            rollButton.setVisible(true);
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
            board.calculateRound();
            int round = board.getRound();
            //System.out.println("Gracz: " + (round+1));//test
            board.setCurrentPlayer(board.getPlayers()[round]);

            int dice1 = Roll();
            int dice2 = Roll();
            int sum =dice1 +dice2;
            //System.out.println("Suma: " + sum);//test
            updateDiceImages(dice1, dice2);

            if(board.getCurrentPlayer().isHaveCar()) {
                carButton.setVisible(true);
            }

            board.getPlayers()[round].movePlayer(sum);
            board.ChangePlayerLocation(sum);
            //System.out.println("New location: " + board.getCurrentPlayer().getLocation().getName());//test
            board.getCurrentPlayer().changeStrategy();

            if(round==0) pawn0.placePawnOn(board.getPlayers()[round].getFieldIndex());
            if(round==1) pawn1.placePawnOn(board.getPlayers()[round].getFieldIndex());
            if(round==2) pawn2.placePawnOn(board.getPlayers()[round].getFieldIndex());
            if(round==3) pawn3.placePawnOn(board.getPlayers()[round].getFieldIndex());

            updateStrategyLabel();
            if(board.getCurrentPlayer().getLocation() instanceof Start){
                rollButton.setVisible(true);
            }else{
                rollButton.setVisible(false);
            }
            board.incrementMoveCounter();
            updateBalanceLabels();
            //System.out.println();//test
        }
    }
    public void createCarButton(){
        carButton = new JButton("use car");
        carButton.setBackground(Color.WHITE);
        carButton.setFont(new Font("Serif", Font.BOLD, 14));
        carButton.addActionListener(new CarButtonReaction());
        carButton.setVisible(false);

    }
    class CarButtonReaction implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            int round = board.getRound();
            int dice1 = Roll();
            int dice2 = Roll();
            int sum =dice1 +dice2;
            updateDiceImages(dice1, dice2);
            board.getCurrentPlayer().useCar(board, round, sum);
            if(round==0) pawn0.placePawnOn(board.getPlayers()[round].getFieldIndex());
            if(round==1) pawn1.placePawnOn(board.getPlayers()[round].getFieldIndex());
            if(round==2) pawn2.placePawnOn(board.getPlayers()[round].getFieldIndex());
            if(round==3) pawn3.placePawnOn(board.getPlayers()[round].getFieldIndex());
            updateStrategyLabel();

            carButton.setVisible(false);
        }
    }
}


