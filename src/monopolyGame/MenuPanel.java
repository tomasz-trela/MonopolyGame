package monopolyGame;

import board.Board;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuPanel extends JPanel implements ActionListener {
    private static final int SCREEN_WIDTH;
    private static final int SCREEN_HEIGHT;

    static {
        SCREEN_WIDTH = 400;
        SCREEN_HEIGHT = 400;
    }

    JSlider playersSilder;
    JLabel playersLabel;
    JSlider boardsSilder;
    JLabel boardsLabel;
    JButton startButton;

    MenuPanel() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.WHITE);
        this.setFocusable(true);
        this.setVisible(true);
        this.add(Box.createVerticalStrut(20));
        addPlayersLabel();
        this.add(Box.createVerticalStrut(20));
        addPlayersSlider();
        this.add(Box.createVerticalStrut(40));
        addBoardsLabel();
        this.add(Box.createVerticalStrut(20));
        addBoardsSlider();
        this.add(Box.createVerticalStrut(90));
        addStartButton();
        this.add(Box.createVerticalGlue());
    }

    public void addPlayersLabel() {
        playersLabel = new JLabel("Number of players");
        playersLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Wyśrodkowanie wzdłuż osi X
        playersLabel.setFont(new Font("Monotype Corsiva", Font.BOLD, 18));

        this.add(playersLabel);
    }

    public void addPlayersSlider() {
        playersSilder = new JSlider(2, 4, 2);
        playersSilder.setMaximumSize(new Dimension(250, 40));
        playersSilder.setPaintTrack(true);
        playersSilder.setMajorTickSpacing(1);
        playersSilder.setAlignmentX(Component.CENTER_ALIGNMENT); // Wyśrodkowanie wzdłuż osi X
        playersSilder.setPaintLabels(true);
        playersSilder.setBackground(new Color(255, 255, 255, 100));
        playersSilder.setOpaque(false);

        this.add(playersSilder);
    }

    public void addBoardsLabel() {
        boardsLabel = new JLabel("Board selection");
        boardsLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Wyśrodkowanie wzdłuż osi X
        boardsLabel.setFont(new Font("Monotype Corsiva", Font.BOLD, 18));

        this.add(boardsLabel);
    }

    public void addBoardsSlider() {
        boardsSilder = new JSlider(1, 4, 1);
        boardsSilder.setMaximumSize(new Dimension(250, 40));
        boardsSilder.setPaintTrack(true);
        boardsSilder.setMajorTickSpacing(1);
        boardsSilder.setAlignmentX(Component.CENTER_ALIGNMENT); // Wyśrodkowanie wzdłuż osi X
        boardsSilder.setPaintLabels(true);
        boardsSilder.setBackground(new Color(255, 255, 255, 100));
        boardsSilder.setOpaque(false);

        this.add(boardsSilder);
    }

    public void addStartButton() {
        startButton = new JButton("Start");
        startButton.setMaximumSize(new Dimension(150, 110));
        startButton.setFont(new Font("Monotype Corsiva", Font.BOLD, 24));
        startButton.setAlignmentX(Component.CENTER_ALIGNMENT); // Wyśrodkowanie wzdłuż osi X
        startButton.setBackground(new Color(40, 210, 40));
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Board.generateBoard(boardsSilder.getValue());
                Board.generatePlayers(playersSilder.getValue());
                GameFrame gameFrame = GameFrame.getInstance();
                gameFrame.showGamePanel();
            }
        });

        this.add(startButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

}
