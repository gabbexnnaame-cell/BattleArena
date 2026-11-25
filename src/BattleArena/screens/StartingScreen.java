
package BattleArena.screens;

import BattleArena.Frame;
import BattleArena.components.*;

import javax.swing.*;
import java.awt.*;

public class StartingScreen extends JPanel {

    private final Frame frame;

    public StartingScreen(Frame frame) {
        this.frame = frame;

        setLayout(null);
        setBackground(new Color(230, 230, 240));

        Header header = new Header();
        add(header);

        JLabel title = new JLabel("Welcome to Battle Mania!");
        title.setFont(new Font("Serif", Font.BOLD, 55));
        title.setForeground(new Color(40, 40, 60));
        title.setBounds(400, 250, 900, 100);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        add(title);

        GameButton startButton = new GameButton("Start Game");
        startButton.setBounds(650, 450, 400, 80);
        startButton.addActionListener(e ->
                frame.showCharacterSelectScreen()
        );
        add(startButton);

        Footer footer = new Footer();
        add(footer);
    }
}
