package monopolyGame;

import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {
    private GamePanel gamePanel;
    private MenuPanel menuPanel;
    private JPanel cardPanel;
    private CardLayout cardLayout;

    private static GameFrame instance;

    public GameFrame() {
        gamePanel = new GamePanel();
        menuPanel = new MenuPanel();

        // Use a JPanel with CardLayout to switch between MenuPanel and GamePanel
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        cardPanel.add(menuPanel, "MenuPanel");
        cardPanel.add(gamePanel, "GamePanel");

        this.setTitle("Monopoly");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.add(cardPanel);
        this.pack();
        this.setLocationRelativeTo(null);
        setIcon();
        showMenuPanel(); // Show the MenuPanel by default
    }

    private void setIcon() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/monopoly_icon.png")));
    }

    public static GameFrame getInstance() {
        if (instance == null) {
            instance = new GameFrame();
        }
        return instance;
    }

    public void showGamePanel() {
        cardLayout.show(cardPanel, "GamePanel");
    }

    public void showMenuPanel() {
        cardLayout.show(cardPanel, "MenuPanel");
    }
    /*public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GameFrame gameFrame = GameFrame.getInstance();
            gameFrame.setVisible(true);
        });
    }*/
}
