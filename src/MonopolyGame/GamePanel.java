package MonopolyGame;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    static final int SCREEN_WIDTH = 900;
    static final int SCREEN_HEIGHT = 750;
    GamePanel(){
        this.setPreferredSize(new Dimension(SCREEN_WIDTH,SCREEN_HEIGHT));
        this.setBackground(Color.black);
        this.setFocusable(true);
    }

}
