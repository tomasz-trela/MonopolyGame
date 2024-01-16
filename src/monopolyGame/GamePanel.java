package monopolyGame;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;

import board.*;
import static player.Dice.Roll;

import observer.AffluenceObserver;
import observer.Subject;
import player.*;
import player.OurOwnException;

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
    private static int resultOfExchange = 0;
    private static int exchangeType = 0;

    static {
        SCREEN_WIDTH=1500;
        SCREEN_HEIGHT = 1000;
        BOARD_WIDTH = 800;
        BOARD_HEIGHT = 800;
        BOARD_COLOR = new Color(200, 224,196);
        FIELD_COLOR1 = new Color(251, 242, 221);
        FIELD_COLOR2 = new Color(97, 211,171);
        FIELD_WIDTH = BOARD_WIDTH/12-2;
        FIELD_HEIGHT = FIELD_WIDTH*2+1;
        ROLL_BUTTON_HEIGHT=60;
        ROLL_BUTTON_WIDTH = 100;
    }
    JPanel ownedFieldsPanel = new JPanel();
    Board board = new Board();
    JButton rollButton;
    JLabel diceLabel1;
    JLabel diceLabel2;
    JLabel fieldInformation = new JLabel();
    JPanel InfoPanel = new JPanel();
    static JPanel BuildingPanel = new JPanel();
    static JPanel TopBuildingPanel = new JPanel();
    JPanel leftPanel=new JPanel();
    JPanel rightPanel= new JPanel();
    static JPanel strategyPanel = new JPanel();
    static JLabel strategyLabel = new JLabel();
    static JButton yesButton = new JButton();
    static JButton noButton = new JButton();
    static JButton okButton = new JButton();
    JButton carButton = new JButton();
    JButton exchangeButton = new JButton();
    JLabel balancePlayer0Label;
    JLabel balancePlayer1Label;
    JLabel balancePlayer2Label;
    JLabel balancePlayer3Label;
    JLabel timerLabel;
    JLabel EurotoDolar;
    JLabel DolartoEuro;
    JComboBox<String> optionsOfExchange;
    JTextField moneyInput;
    JLabel moneyOutput;
    JButton CalculateExchange;
    Timer timer;
    private int seconds;
    JPanel RightBottomPanel;

    protected Subject subject;
    Pawn pawn0= Board.getPawns()[0];
    Pawn pawn1= Board.getPawns()[1];
    Pawn pawn2 = Board.getPawns()[2];
    Pawn pawn3= Board.getPawns()[3];
    private static int boardsides=4;
    private static int heighttowidth=2;
    private static int gap=100;
    Font font = new Font("Arial", Font.BOLD, 12);
    private ImageIcon start= new ImageIcon("src/images/start.png");
    private ImageIcon villpicture= new ImageIcon("src/images/village.png");
    private ImageIcon citypicture= new ImageIcon("src/images/city.png");
    private ImageIcon exchangepicture= new ImageIcon("src/images/Exchange.png");
    private ImageIcon chancepicture= new ImageIcon("src/images/chance2.png");
    private ImageIcon carpicture= new ImageIcon("src/images/car.png");
    GamePanel(){

        this.setPreferredSize(new Dimension(SCREEN_WIDTH,SCREEN_HEIGHT));
        this.setBackground(Color.WHITE);
        this.setLayout(null);

        TopBuildingPanel.setBounds(SCREEN_WIDTH/4-125, SCREEN_HEIGHT/4, SCREEN_WIDTH/3, SCREEN_HEIGHT/25);
        BuildingPanel.setBounds(TopBuildingPanel.getX(), TopBuildingPanel.getY() + TopBuildingPanel.getHeight(), TopBuildingPanel.getWidth(), SCREEN_HEIGHT/4 - TopBuildingPanel.getHeight() - 50);
        BuildingPanel.setBorder(BorderFactory.createLineBorder(Color.black, 5));
        BuildingPanel.setLayout(new FlowLayout());

        TopBuildingPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        TopBuildingPanel.setVisible(false);
        TopBuildingPanel.setBackground(new Color(0, 113, 253, 255));

        ownedFieldsPanel.setBorder(new LineBorder(new Color(250, 100, 100)));
        ownedFieldsPanel.setBackground(new Color(100, 100, 250));

        leftPanel.setBounds(0,0,1000, 1000);
        rightPanel.setBounds(1000,0,500, 1000);

        leftPanel.setLayout(null);
        InfoPanel.setBackground(new Color(0,250,250));
        leftPanel.add(InfoPanel);

        seconds = 1800;
        timerLabel = new JLabel("30:00",SwingConstants.CENTER);
        timerLabel.setFont(new Font("Arial", Font.PLAIN, 40));
        timerLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        timerLabel.setBounds(440,25,120,50);
        timerLabel.setOpaque(true);
        timerLabel.setBackground(Color.WHITE);
        timer = new Timer(1000, new TimerActionListener());
        leftPanel.add(timerLabel);

        rightPanel.setLayout(new GridLayout(2,1));

        leftPanel.setVisible(true);
        rightPanel.setVisible(true);

        ownedFieldsPanel.setVisible(false);

        this.add(TopBuildingPanel);
        this.add(BuildingPanel);

        this.add(ownedFieldsPanel);

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
        BuildingPanel.setVisible(false);

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
    public void startTimer(int time){
        seconds =time;
        timer.start();
        updateTimer();
    }
    public void updateTimer(){
        seconds-=1;
        String minutes =String.valueOf(seconds/60);
        String secondsLeft = String.valueOf(seconds%60);
        if((seconds%60)<10)timerLabel.setText(minutes + ":0" + secondsLeft);
        else timerLabel.setText(minutes + ":" + secondsLeft);
        if(seconds==0){
            timer.stop();
            rollButton.setVisible(false);
            showScoreboard();
        }
    }

    public static class Para {
        long pierwsza;
        long druga;

        public Para(long pierwsza, long druga) {
            this.pierwsza = pierwsza;
            this.druga = druga;
        }
    }
    public void showScoreboard(){
        JFrame score = new JFrame();
        score.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        score.setSize(300,300);
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        score.getContentPane().add(panel);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        JLabel napis = new JLabel();
        //napis.setHorizontalAlignment(SwingConstants.CENTER);
        napis.setText("SCOREBOARD");
        napis.setFont(new Font("Arial", Font.BOLD, 28));
        napis.setHorizontalAlignment(JLabel.CENTER);
        napis.setBorder(BorderFactory.createEmptyBorder(5, 90, 5, 10));
        panel.add(napis);
        String pom;
        long[] tab = board.Ranking();
        Para[] wyniki = new Para[tab.length];
        for(int i=1; i<=tab.length; i++){
            wyniki[i-1] = new Para(tab[i-1], i);
        }
        Arrays.sort(wyniki, new Comparator<Para>() {
            @Override
            public int compare(Para para1, Para para2) {
                if (para1.pierwsza == para2.pierwsza) {
                    return Integer.compare((int) para1.druga, (int) para2.druga);
                }
                return Integer.compare((int) para1.pierwsza, (int) para2.pierwsza);
            }
        });
        int akt=wyniki.length-1;
        long pop=0;
        int teraz;
        for(int i=wyniki.length-1; i>=0; i--){
            teraz=wyniki.length-(i);
            if(pop==wyniki[i].pierwsza) teraz=akt;
            pom=teraz+". Player"+wyniki[i].druga+" has assets worth: "+wyniki[i].pierwsza+" euro ";
            JLabel pomm = new JLabel(pom);
            pomm.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
            if(i==0){
                pomm.setBorder(BorderFactory.createEmptyBorder(5, 10, 15, 10));
            }
            pomm.setFont(new Font("Arial", Font.BOLD, 14));
            panel.add(pomm);
            akt=teraz;
            pop=wyniki[i].pierwsza;
        }
        JButton przycisk = new JButton("Finish game");
        przycisk.addActionListener(new ZakonczListener());
        przycisk.setFocusable(false);
        panel.add(przycisk);
        przycisk.setBorder(BorderFactory.createEmptyBorder(10, score.getWidth()/2, 10, score.getWidth()/2));
        panel.setBackground(new Color(232, 220, 202));
        score.setResizable(false);
        Dimension rozdzielczosc = Toolkit.getDefaultToolkit().getScreenSize();
        score.setAlwaysOnTop(true);
        int x = (rozdzielczosc.width - score.getWidth()) / 2;
        int y = (rozdzielczosc.height - score.getHeight()) / 2;
        score.setLocation(x, y);
        score.pack();
        score.setVisible(true);
    }
    public class ZakonczListener implements ActionListener{
        public void actionPerformed(ActionEvent z){
            System.exit(0);
        }
    }

    public void createSubject(){
        subject = new Subject();
        subject.registerObserver(new AffluenceObserver(board));
    }
    public void setPawnsStart(int numberOfPawns){
        pawn0.placePawnOn(0);
        pawn1.placePawnOn(0);
        pawn2.placePawnOn(0);
        pawn3.placePawnOn(0);

        if(numberOfPawns == 2){
            pawn2.hidePawn();
            pawn3.hidePawn();
        }
        if(numberOfPawns == 3) pawn3.hidePawn();
    }

    public void createBoard(int fieldnumber)
    {
        fieldArray=new JPanel[fieldnumber];
        descArray= new JPanel[fieldnumber];
        centerArray=new JPanel[fieldnumber];
        priceArray= new JPanel[fieldnumber];
        FIELD_WIDTH=BOARD_WIDTH/((fieldnumber/boardsides)+heighttowidth+1);
        FIELD_HEIGHT=heighttowidth*FIELD_WIDTH;
        int x,y;
        x=gap;
        y=gap+FIELD_HEIGHT+FIELD_WIDTH*((fieldnumber/boardsides)-1);
        for(int i=0; i<fieldnumber; i++)
        {
            fieldArray[i]=new JPanel();
            centerArray[i]=new JPanel();
            descArray[i]= new JPanel();
            priceArray[i]=new JPanel();
        }
        for(int i=0; i<fieldnumber; i++)
        {
            if(i==0)
            {
                cornerpiece(i,x,y);
                y-=FIELD_WIDTH;
                drawStart(i);
            }
            if(i>0 && i<fieldnumber/boardsides)
            {
                verticalpiece(i,x,y);
                drawVertical1(i);
                y-=FIELD_WIDTH;
            }
            if(i==fieldnumber/boardsides)
            {
                y-=FIELD_WIDTH;
                cornerpiece(i,x,y);
                drawExchange(i);
                x+=FIELD_HEIGHT;
            }
            if(i>fieldnumber/boardsides && i<2*fieldnumber/boardsides)
            {
                horizontalpiece(i,x,y);
                drawHorizontal(i);
                x+=FIELD_WIDTH;
            }
            if(i==2*fieldnumber/boardsides)
            {
                cornerpiece(i,x,y);
                drawExchange(i);
                y+=FIELD_HEIGHT;
            }
            if(i>2*fieldnumber/boardsides && i<3*fieldnumber/boardsides)
            {
                verticalpiece(i,x,y);
                drawVertical2(i);
                y+=FIELD_WIDTH;
            }
            if(i==3*fieldnumber/boardsides)
            {
                cornerpiece(i,x,y);
                drawExchange(i);
                x-=FIELD_WIDTH;
            }
            if(i>3*fieldnumber/boardsides && i<fieldnumber)
            {
                horizontalpiece(i,x,y);
                drawHorizontal(i);
                x-=FIELD_WIDTH;
            }
        }
        for(JPanel m: fieldArray){
            leftPanel.add(m);
        }
        for(int i=0; i<fieldnumber; i++)
        {
            fieldArray[i].setBorder(BorderFactory.createLineBorder(Color.black,1));
            descArray[i].setBackground(FIELD_COLOR1);
            centerArray[i].setBackground(FIELD_COLOR1);
            priceArray[i].setBackground(FIELD_COLOR1);
            fieldArray[i].setDoubleBuffered(true);
            descArray[i].setBorder(BorderFactory.createLineBorder(Color.black,1));
            centerArray[i].setBorder(BorderFactory.createLineBorder(Color.black,1));
            priceArray[i].setBorder(BorderFactory.createLineBorder(Color.black,1));
        }
        rollButton.setBounds(450, 470, ROLL_BUTTON_WIDTH,ROLL_BUTTON_HEIGHT);
        diceLabel1.setBounds(450, 540, 50, 50);
        diceLabel2.setBounds(500, 540, 50, 50);
        strategyPanel.setBounds(385, 430, 230,80);
        carButton.setBounds(460,600,80,30);
        int size= (int)Math.floor(FIELD_WIDTH/2.3);
        pawn0.SizeSet(size, size);
        pawn1.SizeSet(size, size);
        pawn2.SizeSet(size, size);
        pawn3.SizeSet(size, size);
        for(int i=0; i<fieldnumber; i++)
        {
            fieldArray[i].addMouseListener(new ShowInfoPanelMouseListener());
        }
    }
    public void cornerpiece(int i, int x, int y)
    {
        fieldArray[i].setBounds(x,y,FIELD_HEIGHT, FIELD_HEIGHT);
    }
    public void verticalpiece(int i, int x, int y)
    {
        fieldArray[i].setBounds(x,y, FIELD_HEIGHT, FIELD_WIDTH);
    }
    public void horizontalpiece(int i, int x, int y)
    {
        fieldArray[i].setBounds(x,y,FIELD_WIDTH, FIELD_HEIGHT);
    }
    public void drawStart(int i)
    {
        fieldArray[i].setLayout(new FlowLayout(0,0,0));
        centerArray[i]= new JPanel() {
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
        centerArray[i].setPreferredSize(new Dimension(FIELD_HEIGHT, FIELD_HEIGHT));
        fieldArray[i].setBackground(FIELD_COLOR1);
        fieldArray[i].add(centerArray[0]);
    }
    public void drawHorizontal(int i)
    {
        fieldArray[i].setLayout(new BoxLayout(fieldArray[i],BoxLayout.Y_AXIS));
        if(Board.getFieldsArray()[i] instanceof ToBuy)
        {
            drawDesc(0, i);
            fieldArray[i].add(descArray[i]);
        }
        drawPictcent(0,i);
        fieldArray[i].add(centerArray[i]);
        centerArray[i].setPreferredSize(new Dimension(getHeight(), getWidth()));
        if(Board.getFieldsArray()[i] instanceof ToBuy)
        {
            drawPrice(0,i);
            fieldArray[i].add(priceArray[i]);
        }
    }
    public void drawVertical1(int i)
    {
        fieldArray[i].setLayout(new BoxLayout(fieldArray[i],BoxLayout.X_AXIS));
        FontMetrics fontMetrics = fieldArray[i].getFontMetrics(font);
        if(Board.getFieldsArray()[i] instanceof ToBuy)
        {
            drawPrice(Math.PI/2, i);
            // priceArray[i].setSize(fontMetrics.getHeight()+1, FIELD_WIDTH);
            fieldArray[i].add(priceArray[i]);
        }
        drawPictcent(Math.PI / 2, i);
        fieldArray[i].add(centerArray[i]);
        centerArray[i].setPreferredSize(new Dimension(getWidth(), getHeight()));
        if(Board.getFieldsArray()[i] instanceof ToBuy)
        {
            drawDesc(Math.PI/2, i);
            //    descArray[i].setSize(fontMetrics.getHeight()+1, FIELD_WIDTH);
            fieldArray[i].add(descArray[i]);
        }
    }
    public void drawVertical2(int i)
    {
        FontMetrics fontMetrics = fieldArray[i].getFontMetrics(font);
        fieldArray[i].setLayout(new BoxLayout(fieldArray[i],BoxLayout.X_AXIS));
        if(Board.getFieldsArray()[i] instanceof ToBuy)
        {
            drawDesc(-Math.PI/2, i);
            descArray[i].setSize(fontMetrics.getHeight()+1, FIELD_WIDTH);
            fieldArray[i].add(descArray[i]);
        }
        drawPictcent(-Math.PI/2, i);
        fieldArray[i].add(centerArray[i]);
        centerArray[i].setPreferredSize(new Dimension(getWidth(), getHeight()));
        if(Board.getFieldsArray()[i] instanceof ToBuy)
        {
            drawPrice(-Math.PI/2, i);
            priceArray[i].setSize(fontMetrics.getHeight()+1, FIELD_WIDTH);
            fieldArray[i].add(priceArray[i]);
        }
    }
    private void drawDesc(double rotation, int ind)
    {
        descArray[ind]= new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.rotate(rotation);
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setFont(font);
                String nap=Board.getFieldsArray()[ind].getName();
                FontMetrics fontMetrics= g2d.getFontMetrics();
                int textWidth = fontMetrics.stringWidth(nap);
                int x=0;
                int y=0;
                if(rotation==0)
                {
                    x = (getWidth() - textWidth) / 2;
                    y = (getHeight() - fontMetrics.getHeight())/2 + fontMetrics.getAscent();
                }
                else
                {
                    if(rotation==Math.PI/2)
                    {
                        x = (getHeight() - textWidth) / 2;
                        y = (getWidth() - fontMetrics.getHeight()) / 2-1 ;
                    }
                    if (rotation==-Math.PI/2)
                    {
                        x = (-textWidth-getHeight())/2;
                        y =(getWidth() + fontMetrics.getHeight()) / 2-3;
                    }
                }
                g2d.drawString(nap, x,y);
                g2d.dispose();

            }

        };
        descArray[ind].setDoubleBuffered(true);
    }
    private void drawPrice(double rotation, int ind)
    {
        priceArray[ind]= new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.rotate(rotation);
                this.setDoubleBuffered(true);
                g2d.setFont(font);
                String nap=getActualPrice(ind);
                FontMetrics fontMetrics = g.getFontMetrics(font);
                int textWidth = fontMetrics.stringWidth(nap);
                int x=0;
                int y=0;
                if(rotation==0)
                {
                    x = (getWidth() - textWidth) / 2;
                    y = (getHeight() - fontMetrics.getHeight()) / 2 + fontMetrics.getAscent();
                }
                else
                {
                    if(rotation==Math.PI/2)
                    {
                        x = (getHeight() - textWidth) / 2;
                        y = (getWidth() - fontMetrics.getHeight()) / 2 ;
                    }
                    if (rotation==-Math.PI/2) {
                        x = (-textWidth - getHeight()) / 2;
                        y = (getWidth() + fontMetrics.getHeight()) / 2 -2;
                    }
                }
                g2d.drawString(nap, x,y);
                g2d.dispose();
            }
        };
    }
    private void drawPictcent(double rotation, int ind)
    {
        centerArray[ind]= new JPanel()
        {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                Image backgroundImage = null;
                if(Board.getFieldsArray()[ind] instanceof City)
                {
                    backgroundImage=citypicture.getImage();
                } else if (Board.getFieldsArray()[ind] instanceof Village)
                {
                    backgroundImage=villpicture.getImage();
                } else if(Board.getFieldsArray()[ind] instanceof Chance)
                {
                    backgroundImage=chancepicture.getImage();
                } else if(Board.getFieldsArray()[ind] instanceof CarDealership)
                {
                    backgroundImage=carpicture.getImage();
                }
                AffineTransform at= scaleIMG(backgroundImage, getWidth(), getHeight(), rotation);
                g2d.drawImage(backgroundImage, at, null);
            }
        };
        centerArray[ind].setDoubleBuffered(true);
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
            //tymczasowe dla testow
            //showBuildingPanel((ToBuy) Board.getFieldsArray()[getFieldIndex(this)]);
        }

        @Override
        public void mousePressed(MouseEvent e) {
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            if (!(TopBuildingPanel.isVisible())) {
                showFieldInformation(this);
            }
        }

        @Override
        public void mouseExited(MouseEvent e) {
            InfoPanel.setVisible(false);
            InfoPanel.removeAll();
        }
    }
    public int getFieldIndex(MouseListener mouseListener) {
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
        return TempInt;
    }
    public static void showBuildingPanel(ToBuy field) {
        TopBuildingPanel.removeAll();
//            JButton closeButton = new JButton("Close");
//            closeButton.addActionListener(new ActionListener() {
//                @Override
//                public void actionPerformed(ActionEvent e) {
//                    BuildingPanel.setVisible(false);
//                    TopBuildingPanel.setVisible(false);
//                    BuildingPanel.removeAll();
//
//                }
//            });
//            TopBuildingPanel.add(closeButton);
        TopBuildingPanel.add(new JLabel(("Cost of building: " + ((field).getCostOfBuilding()[0] + (field.getCostOfBuilding()[1])))));
        if (field instanceof City) {
            TopBuildingPanel.add(new JLabel("City: " + field.getName()), BorderLayout.WEST);
//                TopBuildingPanel.add(new JLabel(("Tourist attraction: " + ((City) field).getTouristAttraction())), BorderLayout.EAST);
//
        } else if (field instanceof Village) {
            TopBuildingPanel.add(new JLabel("Village: " + field.getName()), BorderLayout.WEST);
            TopBuildingPanel.add(new JLabel((("Ryeness: " + ((Village) field).getRyeness()))));
        }
        try {
            TopBuildingPanel.add(new JLabel("Owner: " + field.getOwner().getName()), BorderLayout.EAST);
            if (!((field.getBuildings().size() >= 4))) {
                JButton buildButton = new JButton("Build");
                buildButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (field.getOwner().getBalance()[0] < field.getCostOfBuilding()[0] || field.getOwner().getBalance()[1] < field.getCostOfBuilding()[1]) {
                            JOptionPane.showMessageDialog(null, "You don't have enough money");
                        } else {
                            field.getOwner().decreaseBalance(field.getCostOfBuilding());
                            field.addBuilding();
                            BuildingPanel.removeAll();
                            showBuildingPanel(field);
                            GameFrame.getInstance().GetGamePanel().updateBalanceLabels();
                        }
                    }
                });
                TopBuildingPanel.add(buildButton);
            }
        } catch (NullPointerException e) {
            TopBuildingPanel.add(new JLabel("Owner: null"), BorderLayout.EAST);
        }

        try {
            if (!(field.getBuildings().get(0) == null)) {

                BuildingPanel.setFont(new Font("Serif", Font.BOLD, 30));
                ArrayList<JButton> listOfUpgradeButtons = new ArrayList<>();

                if (field instanceof City) {

                    BuildingPanel.setLayout(new GridLayout(5, 4));

                    BuildingPanel.add(new JLabel("Revenue per visit"));
                    BuildingPanel.add(new JLabel("Level"));
                    BuildingPanel.add(new JLabel("Cost of upgrade"));
                    BuildingPanel.add(new JLabel("Upgrade"));


                } else if (field instanceof Village) {

                    BuildingPanel.setLayout(new GridLayout(5, 5));

                    BuildingPanel.add(new JLabel("Revenue per visit"));
                    BuildingPanel.add(new JLabel("Revenue per year"));
                    BuildingPanel.add(new JLabel("Level"));
                    BuildingPanel.add(new JLabel("Cost of upgrade"));
                    BuildingPanel.add(new JLabel("Upgrade"));


                }
                for (int i = 0; i < field.getBuildings().size(); i++) {

                    String [] tempString = field.getBuildings().get(i).toString().split(",");
                    BuildingPanel.add(new JLabel(tempString[0]));
                    BuildingPanel.add(new JLabel(tempString[1]));
                    BuildingPanel.add(new JLabel(tempString[2]));
                    if (field instanceof Village) {
                        BuildingPanel.add(new JLabel(tempString[3]));
                    }
                    if (field.getBuildings().get(i).getLevel() < 5) {

                        JButton upgradeButton = new JButton("Upgrade");
                        int temp = i;
                        upgradeButton.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                int currency = 0;
                                if (field.getPrice()[0] == 0) {
                                    currency = 1;
                                }
                                if (field.getOwner().getBalance()[currency] < field.getBuildings().get(temp).getPriceOfUpgrade()) {
                                    JOptionPane.showMessageDialog(null, "You don't have enough money");
                                } else {
                                    int [] price = new int[2];
                                    if (currency == 1) {
                                        price[1] = field.getBuildings().get(temp).getPriceOfUpgrade();
                                    } else {
                                        price[0] = field.getBuildings().get(temp).getPriceOfUpgrade();
                                    }
                                    field.getOwner().decreaseBalance(price);
                                    field.getBuildings().get(temp).upgrade();
                                    BuildingPanel.removeAll();
                                    showBuildingPanel(field);
                                    GameFrame.getInstance().GetGamePanel().updateBalanceLabels();
                                }
                            }
                        });
                        BuildingPanel.add(upgradeButton);
                    } else {
                        BuildingPanel.add(new JLabel("Max level"));
                    }


                }
                if (field instanceof City) {
                    for (int i = 0; i < (4 - (field.getBuildings().size()) * 4); i++) {
                        BuildingPanel.add(new JLabel(" "));
                    }
                } else if (field instanceof Village) {
                    for (int i = 0; i < (4 - (field.getBuildings().size()) * 5); i++) {
                        BuildingPanel.add(new JLabel(" "));
                    }
                }
                if (field instanceof City) {
                    for (int i = 0; i < (16 - (field.getBuildings().size()) * 4); i++) {
                        BuildingPanel.add(new JLabel("..."));
                    }
                } else {
                    for (int i = 0; i < (20 - (field.getBuildings().size()) * 5); i++) {
                        BuildingPanel.add(new JLabel("..."));
                    }
                }
            }
        } catch (IndexOutOfBoundsException e) {
            BuildingPanel.setLayout(new GridLayout());
            BuildingPanel.setFont(new Font("Serif", Font.BOLD, 50));
            BuildingPanel.add(new JLabel("No buildings"));

        } finally {
            BuildingPanel.setVisible(true);
            TopBuildingPanel.setVisible(true);
        }
    }
    public Board getBoard() {
        return board;
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
        fieldArray[i].setLayout(new FlowLayout(FlowLayout.CENTER,0,0));
        fieldArray[i].setBackground(FIELD_COLOR1);
        fieldArray[i].add(centerArray[i]);
    }

    public static JPanel[] getFieldArray() {
        return centerArray;
    }
    public static JPanel[] getDescArray(){return descArray;}


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
        int TempInt = getFieldIndex(mouseListener);
        ArrayList<JLabel> tempList = new ArrayList<>();
        String [] tempString = Board.getFieldsArray()[TempInt].toString().split(",");
        if (Objects.equals(tempString[0], "Car Dealership")) {
            tempList.add(new JLabel("Car Dealership"));
            tempList.get(0).setForeground(Color.BLACK);
            tempList.get(0).setFont(new Font("Serif", Font.BOLD, 20));
            tempList.get(0).setVisible(false);
        } else {
            for (int i = 0; i < tempString.length; i++) {
                tempList.add(new JLabel(tempString[i]));
                tempList.get(i).setForeground(Color.BLACK);
                tempList.get(i).setFont(new Font("Serif", Font.BOLD, 20));
                tempList.get(i).setVisible(false);
            }
        }
        int y = 200;
        InfoPanel.setBounds(fieldArray[TempInt].getX() + FIELD_WIDTH/2, fieldArray[TempInt].getY() - 2*FIELD_HEIGHT, 200, 10 + 28 * tempList.size());
        if (InfoPanel.getY() > 300) {
            InfoPanel.setBounds(fieldArray[TempInt].getX() + FIELD_WIDTH/2, fieldArray[TempInt].getY() - 2*FIELD_HEIGHT + (8 - tempList.size()) * 35, 200, 10 + 28 * tempList.size());
        } else {
            InfoPanel.setBounds(fieldArray[TempInt].getX() + FIELD_WIDTH/2, fieldArray[TempInt].getY() + FIELD_HEIGHT/2, 200, 10 + 28 * tempList.size());
        }
        for (int i = 0; i < tempList.size(); i++) {
            y += i * 15;
            tempList.get(i).setVisible(true);
            tempList.get(i).setBounds(SCREEN_WIDTH/2-500, y, 200, 100);
            tempList.get(i).setVisible(true);
            InfoPanel.add(tempList.get(i));
        }
        InfoPanel.setMinimumSize(new Dimension(1, 1));
        InfoPanel.setLayout(new BoxLayout(InfoPanel, BoxLayout.Y_AXIS));
        InfoPanel.setBorder(BorderFactory.createLineBorder(Color.black, 5));
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

    public  static JPanel getStrategyPanel() {
        return strategyPanel;
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
                    strategyLabel.setText("A stay-fee will be charged");
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
        if((board.getCurrentPlayer().getLocation() instanceof CarDealership) && (board.getCurrentPlayer().isHaveCar())){
            strategyPanel.setVisible(false);
        }

    }
    public void ballanceLabelsAddMouselisteners(JLabel label, Player player) {
        label.addMouseListener(new MouseListener() {

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
                createOwnedFieldsPanel(player);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                ownedFieldsPanel.setVisible(false);
                ownedFieldsPanel.removeAll();
            }
        });
    }
    public void createOwnedFieldsPanel(Player player){
        ownedFieldsPanel.setLayout(new GridLayout(player.getOwnedFields().size() + 1, 1));
        if (Objects.equals(player.getName(), "Player 1")) {
            ownedFieldsPanel.setBackground(Color.decode("#e34242"));
        } else if(Objects.equals(player.getName(), "Player 2")) {
            ownedFieldsPanel.setBackground(Color.decode("#2aa9e8"));
        } else if(Objects.equals(player.getName(), "Player 3")) {
            ownedFieldsPanel.setBackground(Color.decode("#f0f026"));
        } else if(Objects.equals(player.getName(), "Player 4")) {
            ownedFieldsPanel.setBackground(Color.decode("#3cd646"));
        }
        if (player.getOwnedFields().isEmpty()) {
            ownedFieldsPanel.add(new JLabel("No owned fields"));
        } else {
            ownedFieldsPanel.add(new JLabel("Owned fields: "));
            for (int i = 0; i < player.getOwnedFields().size(); i++) {
                ownedFieldsPanel.add(new JLabel((player.getOwnedFields().get(i)).getName()));
//                for (int j = 0; j < player.getOwnedFields().get(i).getBuildings().size(); j++) {
//                    if (player.getOwnedFields().get(i) instanceof City) {
//                        ownedFieldsPanel.add(new JLabel("House level " + player.getOwnedFields().get(i).getBuildings().get(j).getLevel()));
//                    } else {
//                        ownedFieldsPanel.add(new JLabel("Farm level " + player.getOwnedFields().get(i).getBuildings().get(j).getLevel()));
//                    }
//                }
            }
        }
        ownedFieldsPanel.setBounds(SCREEN_WIDTH/2, 0, 200, 100);
        ownedFieldsPanel.setVisible(true);
    }

    public void createBalanceLabels(){
        JPanel rightTopPanel = new JPanel();
        Font f = new Font(Font.SANS_SERIF, Font.BOLD, 23);
        rightTopPanel.setLayout(new BoxLayout(rightTopPanel, BoxLayout.PAGE_AXIS));

        rightPanel.add(rightTopPanel);

        for(int i = 0; i< Board.GetPlayersArray().length; i++) {
            String PlayerNumber = "Player " + (i + 1);
            String EuroBalance = "Euro: " + Board.GetPlayersArray()[i].getBalance()[0];
            String DolarBalance = "Dolar: " + Board.GetPlayersArray()[i].getBalance()[1];

            String PlayerInfo = "<html>" + PlayerNumber + "<br>" + EuroBalance + "<br>" + DolarBalance;


            switch (i) {
                case 0:
                    balancePlayer0Label = new JLabel(PlayerInfo);
                    ballanceLabelsAddMouselisteners(balancePlayer0Label, Board.GetPlayersArray()[i]);
                    createPlayerLabel(balancePlayer0Label, f, i, rightTopPanel);
                    break;
                case 1:
                    balancePlayer1Label = new JLabel(PlayerInfo);
                    ballanceLabelsAddMouselisteners(balancePlayer1Label, Board.GetPlayersArray()[i]);
                    createPlayerLabel(balancePlayer1Label, f, i, rightTopPanel);
                    break;
                case 2:
                    balancePlayer2Label = new JLabel(PlayerInfo);
                    ballanceLabelsAddMouselisteners(balancePlayer2Label, Board.GetPlayersArray()[i]);
                    createPlayerLabel(balancePlayer2Label, f, i, rightTopPanel);
                    break;
                case 3:
                    balancePlayer3Label = new JLabel(PlayerInfo);
                    ballanceLabelsAddMouselisteners(balancePlayer3Label, Board.GetPlayersArray()[i]);
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

        int round = board.getRound();

        for(int i=0;i<board.GetPlayersArray().length;i++) {
            String PlayerNumber = "Player " + (i + 1);
            String EuroBalance = "Euro: " + board.GetPlayersArray()[i].getBalance()[0];
            String DolarBalance = "Dolar: " + board.GetPlayersArray()[i].getBalance()[1];

            String PlayerInfo = "<html>" + PlayerNumber + "<br>" + EuroBalance + "<br>" + DolarBalance;


            switch (i) {
                case 0:
                    balancePlayer0Label.setText(PlayerInfo);
                    balancePlayer0Label.setBackground(Color.decode("#bfbfbf"));
                    break;
                case 1:
                    balancePlayer1Label.setText(PlayerInfo);
                    balancePlayer1Label.setBackground(Color.decode("#bfbfbf"));
                    break;
                case 2:
                    balancePlayer2Label.setText(PlayerInfo);
                    balancePlayer2Label.setBackground(Color.decode("#bfbfbf"));
                    break;
                case 3:
                    balancePlayer3Label.setText(PlayerInfo);
                    balancePlayer3Label.setBackground(Color.decode("#bfbfbf"));
                    break;
            }
        }

        switch(round){
            case 0:{
                balancePlayer0Label.setBackground(Color.decode("#F5BABA"));
                break;
            }
            case 1:{
                balancePlayer1Label.setBackground(Color.decode("#BAE3F5"));
                break;
            }
            case 2:{
                balancePlayer2Label.setBackground(Color.decode("#F1F5BA"));
                break;
            }
            case 3:{
                balancePlayer3Label.setBackground(Color.decode("#C3F5BA"));
                break;
            }
        }

        revalidate();
    }

    public void CreateExchangeLabels(){

        RightBottomPanel = new JPanel(){
            protected void paintComponent(Graphics g){
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g.create();
                Image backGroundExchange = new ImageIcon("src/images/exchangeRightBottom.png").getImage();

                g2d.drawImage(backGroundExchange, null, RightBottomPanel);
                g2d.dispose();
            }
        };



        RightBottomPanel.setLayout(new BoxLayout(RightBottomPanel, BoxLayout.PAGE_AXIS));
        Font f = new Font(Font.SANS_SERIF, Font.BOLD, 23);

        //obecny kurs walut
        double tmp = Math.round(board.getDollarRate()/board.getEuroRate() * 100.0) / 100.0;
        String EuroTODolar = "1 Euro is worth " + Double.toString((tmp)) + " Dolars";
        tmp = Math.round(board.getEuroRate()/board.getDollarRate() * 100.0) / 100.0;
        String DolarTOEuro = "1 Dolar is worth " + Double.toString((tmp)) + " Euros";


        EurotoDolar = new JLabel(EuroTODolar);
        DolartoEuro = new JLabel(DolarTOEuro);
        EurotoDolar.setAlignmentX(Component.CENTER_ALIGNMENT);
        DolartoEuro.setAlignmentX(Component.CENTER_ALIGNMENT);
        EurotoDolar.setFont(f);
        DolartoEuro.setFont(f);

        //opcje wyboru wymiany
        String[] choices = {"Euro to Dolar", "Dolar to Euro"};
        optionsOfExchange = new JComboBox<String>(choices);
        optionsOfExchange.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        optionsOfExchange.setMaximumSize(optionsOfExchange.getPreferredSize());
        optionsOfExchange.setAlignmentX(Component.CENTER_ALIGNMENT);
        optionsOfExchange.setBackground(Color.decode("#D1D1D1"));

        moneyInput = new JTextField("Enter the amount");
        Dimension a = new Dimension(200, 30);
        moneyInput.setMaximumSize(a);
        moneyInput.setAlignmentX(Component.CENTER_ALIGNMENT);
        moneyInput.setBackground(Color.decode("#D1D1D1"));
        moneyInput.setBorder(new MatteBorder(0, 0, 2, 0, Color.black));


        CalculateExchange = new JButton("Calculate");
        CalculateExchange.setMaximumSize(CalculateExchange.getPreferredSize());
        CalculateExchange.setAlignmentX(Component.CENTER_ALIGNMENT);
        CalculateExchange.addActionListener(new CalculateExchangeReaction());
        CalculateExchange.setBackground(Color.white);


        String money = "" ;

        moneyOutput = new JLabel(money);
        moneyOutput.setAlignmentX(Component.CENTER_ALIGNMENT);

        //jak jest tylo jedna to niedziała idk dlaczego
        //if it works it works tho
        JLabel space0 = new JLabel("<html> <br> <br> <br> <br> <br> <br> <br> </html>");
        JLabel space = new JLabel("<html> <br> <br> <br> <br> <br> <br> <br> </html>");

        exchangeButton = new JButton("Exchange");
        exchangeButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        Dimension b = new Dimension(200,70);
        exchangeButton.setMaximumSize(b);
        exchangeButton.setBackground(Color.WHITE);
        exchangeButton.setFont(new Font("Serif", Font.BOLD, 28));
        exchangeButton.setMargin(new Insets(20, 20, 20,20));
        exchangeButton.setVisible(false);
        exchangeButton.addActionListener(new ExchangeButtonReaction());

        RightBottomPanel.add(space0);
        RightBottomPanel.add(EurotoDolar);
        RightBottomPanel.add(DolartoEuro);
        RightBottomPanel.add(optionsOfExchange);
        RightBottomPanel.add(moneyInput);
        RightBottomPanel.add(CalculateExchange);
        RightBottomPanel.add(moneyOutput);
        RightBottomPanel.add(space);
        RightBottomPanel.add(exchangeButton);

        rightPanel.add(RightBottomPanel);

    }
    public int RollDices(){
        int dice1 = Roll();
        int dice2 = Roll();
        updateDiceImages(dice1, dice2);
        return dice1+dice2;
    }
    public void updateExchageRates(){
        double tmp = Math.round(board.getDollarRate()/board.getEuroRate() * 100.0) / 100.0;
        String EuroTODolar = "1 Euro is worth " + Double.toString((tmp)) + " Dolars";
        tmp = Math.round(board.getEuroRate()/board.getDollarRate() * 100.0) / 100.0;
        String DolarTOEuro = "1 Dolar is worth " + Double.toString((tmp)) + " Euros";

        EurotoDolar.setText(EuroTODolar);
        DolartoEuro.setText(DolarTOEuro);
    }

    class CalculateExchangeReaction implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent event){
            try {
                int money = (int) Double.parseDouble(moneyInput.getText());


                String selectedOption = (String) optionsOfExchange.getSelectedItem();
                double multiplier = 1;
                String currency = "";

                if (selectedOption == "Euro to Dolar") {
                    multiplier = Math.round(board.getDollarRate() / board.getEuroRate() * 100.0) / 100.;
                    currency = "$";
                    exchangeType = 1;

                } else if (selectedOption == "Dolar to Euro") {
                    multiplier = Math.round(board.getEuroRate() / board.getDollarRate() * 100.0) / 100.0;
                    currency = "€";
                    exchangeType = 2;
                }

                resultOfExchange = (int) (money * multiplier);

                moneyOutput.setText("You will recieve: " + resultOfExchange + " " + currency);
            }
            catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Please enter a valid decimal number.");
            }
        }
    }

    class ExchangeButtonReaction implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                board.getCurrentPlayer().exchangeMoney(board, resultOfExchange , exchangeType);
            }catch (OurOwnException exception){
                JOptionPane.showMessageDialog(null, "You don't have enough money in your account");
            }
            exchangeButton.setVisible(false);
            rollButton.setVisible(true);
            updateBalanceLabels();
        }
    }

    class StrategyPanelButtonYesReaction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            board.getCurrentPlayer().playerAction(board);
            strategyPanel.setVisible(false);
            carButton.setVisible(false);
            updateRollButtonVisability(true);

            updateBalanceLabels();
            subject.notifyObserversEuro(board);
            subject.notifyObserversDollar(board);
            updateExchageRates();
        }
    }
    class StrategyPanelButtonNoReaction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            strategyPanel.setVisible(false);
            carButton.setVisible(false);
            rollButton.setVisible(true);
            updateBalanceLabels();
        }
    }
    class StrategyPanelButtonOkReaction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            strategyPanel.setVisible(false);
            carButton.setVisible(false);
            rollButton.setVisible(true);
            board.getCurrentPlayer().payFeeForBuiliding(board);
            updateBalanceLabels();
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
            if (BuildingPanel.isVisible()) {
                BuildingPanel.setVisible(false);
                TopBuildingPanel.setVisible(false);
                BuildingPanel.removeAll();
            }
            board.calculateRound();
            int round = board.getRound();
            board.setCurrentPlayer(board.getPlayers()[round]);

            int sum =RollDices();

            if(board.getCurrentPlayer().isHaveCar()) {
                carButton.setVisible(true);
            }

            board.changePlayerLocation(sum);
            board.getCurrentPlayer().changeStrategy();
            board.getCurrentPlayer().chargeForCar(board);
            board.movePawn();

            updateStrategyLabel();
            updateRollButtonVisability(false);
            board.incrementMoveCounter();

            updateBalanceLabels();

        }
    }
    public void updateRollButtonVisability(boolean bool){ //true - pokaz, false - schowaj (chyba ze wyjatki)
        if(bool){
            if (board.getCurrentPlayer().isCanExchange()){
                rollButton.setVisible(false);
                exchangeButton.setVisible(true);
            }else if (board.getCurrentPlayer().isCanMoveAfterChance()) {
                rollButton.setVisible(false);
                updateStrategyLabel();
                board.getCurrentPlayer().setCanMoveAfterChance(false);
            }
            else {
                rollButton.setVisible(true);
            }
        }else {
            if ((board.getCurrentPlayer().getLocation() instanceof Start) || ((board.getCurrentPlayer().getLocation() instanceof CarDealership) && (board.getCurrentPlayer().isHaveCar()))) {
                rollButton.setVisible(true);
            } else {
                rollButton.setVisible(false);
            }
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
            int sum = RollDices();
            board.getCurrentPlayer().useCar(board, sum);
            board.movePawn();
            updateStrategyLabel();
            updateRollButtonVisability(false);
            carButton.setVisible(false);
        }

    }
    class TimerActionListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            updateTimer();
        }
    }

    public static void showChance(int chanceIndex){
        ImageIcon chanceIcon = new ImageIcon("src/images/chance.png");
        double scale=0.3;


        Image scaledChanceImage = chanceIcon.getImage().getScaledInstance((int) (chanceIcon.getIconWidth()*scale), (int) (chanceIcon.getIconHeight()*scale),Image.SCALE_SMOOTH);

        ImageIcon scaledChanceIcon = new ImageIcon(scaledChanceImage);



        JFrame chanceFrame = new JFrame();
        chanceFrame.setVisible(true);
        chanceFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);



        JPanel chance= new JPanel();
        chance.setLayout(new BoxLayout(chance, BoxLayout.Y_AXIS));
        chance.setBackground(Color.WHITE);
        chance.setVisible(true);


        JLabel txt=new JLabel("CHANCE");
        txt.setFont(new Font("Arial", Font.BOLD, 25));
        txt.setHorizontalAlignment(JLabel.CENTER);
        txt.setIcon(scaledChanceIcon);
        txt.setIconTextGap(0);
        //txt.setVerticalTextPosition(SwingConstants.BOTTOM);

        JPanel txtPanel = new JPanel();
        txtPanel.add(txt);
        txtPanel.setBackground(FIELD_COLOR1);





        JTextArea cardTxt= new JTextArea(Chance.getListOfChances()[chanceIndex].getText());


        cardTxt.setLineWrap(true);
        cardTxt.setWrapStyleWord(true);
        cardTxt.setEditable(false);
        cardTxt.setFocusable(false);
        cardTxt.setFont(new Font("Arial", Font.PLAIN, 18));


        cardTxt.setBackground(FIELD_COLOR1);
        JScrollPane scrollPane = new JScrollPane(cardTxt);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setPreferredSize(new Dimension(150,125));
        chanceFrame.setLayout(new FlowLayout(FlowLayout.CENTER,0,5));
        chance.add(txtPanel);
        chance.add(scrollPane);
        chanceFrame.add(chance);
        chanceFrame.setResizable(false);
        chanceFrame.setBounds(SCREEN_WIDTH/2,SCREEN_HEIGHT/2,200,250);
        chanceFrame.setIconImage(chanceIcon.getImage());
        chanceFrame.setAlwaysOnTop(true);
    }
}
