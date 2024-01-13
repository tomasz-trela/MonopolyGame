package monopolyGame;

import board.Board;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuPanel extends JPanel{
    JSlider playersSilder;
    JLabel playersLabel;
    JSlider boardsSilder;
    JLabel boardsLabel;
    JSlider timeSilder;
    JLabel timeLabel;
    JButton startButton;

    MenuPanel() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBackground(Color.WHITE);
        this.setFocusable(true);
        this.setVisible(true);
        this.add(Box.createVerticalStrut(210));
        addPlayersLabel();
        this.add(Box.createVerticalStrut(30));
        addPlayersSlider();
        this.add(Box.createVerticalStrut(70));
        addBoardsLabel();
        this.add(Box.createVerticalStrut(30));
        addBoardsSlider();
        this.add(Box.createVerticalStrut(70));
        addTimeLabel();
        this.add(Box.createVerticalStrut(30));
        addTimeSlider();
        this.add(Box.createVerticalStrut(0));
        addStartButton();
        this.add(Box.createVerticalGlue());
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        ImageIcon imageIcon = new ImageIcon("src/images/menuBackground.jpg");
        Image backgroundImage = imageIcon.getImage();

        // Draw the image without automatic scaling
        g.drawImage(backgroundImage, 0, 0, 1500, 1500, this);
        imageIcon = new ImageIcon("src/images/paper.png");
        Image paperImage = imageIcon.getImage();
        g.drawImage(paperImage, 540, 160, 420, 550, this);
    }

    public void addPlayersLabel() {
        playersLabel = new JLabel("Number of players");
        playersLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Wyśrodkowanie wzdłuż osi X
        playersLabel.setFont(new Font("Monotype Corsiva", Font.BOLD, 25));

        this.add(playersLabel);
    }

    private void addMenuSlider(JSlider slider){
        slider.setMaximumSize(new Dimension(330, 60));
        slider.setFont(new Font("Monotype Corsiva", Font.BOLD, 18));
        slider.setPaintTrack(true);
        slider.setAlignmentX(Component.CENTER_ALIGNMENT); // Wyśrodkowanie wzdłuż osi X
        slider.setPaintLabels(true);
        slider.setOpaque(false);

    }

    public void addPlayersSlider() {
        playersSilder = new JSlider(2, 4, 2);
        addMenuSlider(playersSilder);
        playersSilder.setMajorTickSpacing(1);

        this.add(playersSilder);
    }

    public void addBoardsLabel() {
        boardsLabel = new JLabel("Board selection");
        boardsLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Wyśrodkowanie wzdłuż osi X
        boardsLabel.setFont(new Font("Monotype Corsiva", Font.BOLD, 25));

        this.add(boardsLabel);
    }

    public void addBoardsSlider() {
        boardsSilder = new JSlider(1, 4, 1);
        addMenuSlider(boardsSilder);
        boardsSilder.setMajorTickSpacing(1);

        this.add(boardsSilder);
    }

    public void addTimeLabel() {
        timeLabel = new JLabel("Game Duration");
        timeLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Wyśrodkowanie wzdłuż osi X
        timeLabel.setFont(new Font("Monotype Corsiva", Font.BOLD, 25));

        this.add(timeLabel);
    }

    public void addTimeSlider() {
        timeSilder = new JSlider(5, 30, 5);
        addMenuSlider(timeSilder);
        timeSilder.setMajorTickSpacing(5);

        this.add(timeSilder);
    }

    public void addStartButton() {
        startButton = new JButton("Start");
        startButton.setMaximumSize(new Dimension(230, 200));
        startButton.setFont(new Font("Monotype Corsiva", Font.BOLD, 28));
        startButton.setAlignmentX(Component.CENTER_ALIGNMENT); // Wyśrodkowanie wzdłuż osi X
        startButton.setBackground(new Color(124, 213, 114));
        startButton.addActionListener(new StartButtonReaction());

        this.add(startButton);
    }

    class StartButtonReaction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Board.generateBoard(boardsSilder.getValue());
            Board.generatePlayers(playersSilder.getValue());
            GameFrame gameFrame = GameFrame.getInstance();
            gameFrame.GetGamePanel().startTimer((timeSilder.getValue())*60);
            gameFrame.showGamePanel();
            gameFrame.GetGamePanel().createBalanceLabels();
            gameFrame.GetGamePanel().CreateExchangeLabels();
            gameFrame.GetGamePanel().createBoard(36);
            gameFrame.GetGamePanel().setPawnsStart(playersSilder.getValue());
            gameFrame.GetGamePanel().createSubject();
        }
    }
}
