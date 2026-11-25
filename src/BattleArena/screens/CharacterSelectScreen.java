
package BattleArena.screens;

import BattleArena.Frame;
import BattleArena.components.*;

import javax.swing.*;
import java.awt.*;

public class CharacterSelectScreen extends JPanel {

    private final Frame frame;

    public CharacterSelectScreen(Frame frame) {
        this.frame = frame;

        setLayout(null);
        setBackground(new Color(230, 230, 240));

        Header header = new Header();
        add(header);

        JLabel title = new JLabel("Choose Your Character");
        title.setFont(new Font("Serif", Font.BOLD, 45));
        title.setForeground(new Color(40, 40, 60));
        title.setBounds(400, 150, 900, 80);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        add(title);

        GameButton wizardButton = new GameButton("Wizard");
        wizardButton.setBounds(250, 400, 300, 100);
        wizardButton.addActionListener(e ->
                frame.startGame("Wizard")
        );
        add(wizardButton);

        GameButton assassinButton = new GameButton("Assassin");
        assassinButton.setBounds(700, 400, 300, 100);
        assassinButton.addActionListener(e ->
                frame.startGame("Assassin")
        );
        add(assassinButton);

        GameButton knightButton = new GameButton("Knight");
        knightButton.setBounds(1150, 400, 300, 100);
        knightButton.addActionListener(e ->
                frame.startGame("Knight")
        );
        add(knightButton);

        GameButton backButton = new GameButton("Back");
        backButton.setBounds(650, 700, 400, 70);
        backButton.addActionListener(e ->
                frame.showStartingScreen()
        );
        add(backButton);

        Footer footer = new Footer();
        add(footer);
    }
}
