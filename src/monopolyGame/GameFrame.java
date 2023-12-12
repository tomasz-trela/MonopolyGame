package monopolyGame;

import javax.swing.*;

public class GameFrame extends JFrame {
    GameFrame(){
        this.add(new GamePanel());
        this.setTitle("Monopoly");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setLayout(null);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }

}
