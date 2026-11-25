
package BattleArena.components;

import javax.swing.*;
import java.awt.*;

public class GameButton extends JButton {

    public GameButton(String text) {
        super(text);

        setFont(new Font("Serif", Font.BOLD, 22));
        setForeground(Color.WHITE);
        setBackground(new Color(40, 40, 60));

        setFocusPainted(false);
        setBorderPainted(false);
        setOpaque(true);
        setCursor(new Cursor(Cursor.HAND_CURSOR));
    }
}
