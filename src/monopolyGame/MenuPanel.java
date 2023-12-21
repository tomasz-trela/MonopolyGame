package monopolyGame;

import board.Board;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class        MenuPanel extends JPanel implements ActionListener {
    JSlider playersSilder;
    JLabel playersLabel;
    JSlider boardsSilder;
    JLabel boardsLabel;
    JButton startButton;

    MenuPanel() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBackground(Color.WHITE);
        this.setFocusable(true);
        this.setVisible(true);
        this.add(Box.createVerticalStrut(80));
        addPlayersLabel();
        this.add(Box.createVerticalStrut(30));
        addPlayersSlider();
        this.add(Box.createVerticalStrut(70));
        addBoardsLabel();
        this.add(Box.createVerticalStrut(30));
        addBoardsSlider();
        this.add(Box.createVerticalStrut(200));
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
        g.drawImage(paperImage, 540, 30, 420, 400, this);

    }
    public void addPlayersLabel() {
        playersLabel = new JLabel("Number of players");
        playersLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Wyśrodkowanie wzdłuż osi X
        playersLabel.setFont(new Font("Monotype Corsiva", Font.BOLD, 25));

        this.add(playersLabel);
    }

    public void addPlayersSlider() {
        playersSilder = new JSlider(2, 4, 2);
        playersSilder.setMaximumSize(new Dimension(330, 60));
        playersSilder.setFont(new Font("Monotype Corsiva", Font.BOLD, 18));
        playersSilder.setPaintTrack(true);
        playersSilder.setMajorTickSpacing(1);
        playersSilder.setAlignmentX(Component.CENTER_ALIGNMENT); // Wyśrodkowanie wzdłuż osi X
        playersSilder.setPaintLabels(true);
        playersSilder.setBackground(new Color(255, 255, 255, 50));
        playersSilder.setOpaque(false);

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
        boardsSilder.setMaximumSize(new Dimension(330, 60));
        boardsSilder.setFont(new Font("Monotype Corsiva", Font.BOLD, 18));
        boardsSilder.setPaintTrack(true);
        boardsSilder.setMajorTickSpacing(1);
        boardsSilder.setAlignmentX(Component.CENTER_ALIGNMENT); // Wyśrodkowanie wzdłuż osi X
        boardsSilder.setPaintLabels(true);
        boardsSilder.setBackground(new Color(255, 255, 255, 50));
        boardsSilder.setOpaque(false);

        this.add(boardsSilder);
    }

    public void addStartButton() {
        startButton = new JButton("Start");
        startButton.setMaximumSize(new Dimension(230, 200));
        startButton.setFont(new Font("Monotype Corsiva", Font.BOLD, 28));
        startButton.setAlignmentX(Component.CENTER_ALIGNMENT); // Wyśrodkowanie wzdłuż osi X
        startButton.setBackground(new Color(124, 213, 114));
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
