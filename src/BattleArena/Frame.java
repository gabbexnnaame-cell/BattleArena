
package BattleArena;

import BattleArena.entities.ComputerEntity;
import BattleArena.entities.Entity;
import BattleArena.screens.*;

import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {

    private static final int WIDTH = 1700;
    private static final int HEIGHT = 1000;

    private final CardLayout cardLayout;
    private final JPanel mainPanel;

    private GameScreen gameScreen;

    public Frame() {
        setTitle("Battle Mania");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        mainPanel.add(new StartingScreen(this), "START");
        mainPanel.add(new CharacterSelectScreen(this), "SELECT");

        add(mainPanel);

        showStartingScreen();
        setVisible(true);
    }

    public void showStartingScreen() {
        cardLayout.show(mainPanel, "START");
    }

    public void showCharacterSelectScreen() {
        cardLayout.show(mainPanel, "SELECT");
    }

    public void startGame(String character) {
        if (gameScreen != null) {
            mainPanel.remove(gameScreen);
        }

        Entity entity = new Entity(character);
        ComputerEntity computerEntity = new ComputerEntity(character);
        gameScreen = new GameScreen(this, entity, computerEntity);

        mainPanel.add(gameScreen, "GAME");
        cardLayout.show(mainPanel, "GAME");

        mainPanel.revalidate();
        mainPanel.repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Frame::new);
    }
}
