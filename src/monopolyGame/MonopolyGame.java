package monopolyGame;

import javax.swing.*;

public class MonopolyGame {
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            GameFrame gameFrame = GameFrame.getInstance();
            gameFrame.setVisible(true);

        });
    }
}
