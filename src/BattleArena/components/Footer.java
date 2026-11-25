
package BattleArena.components;

import javax.swing.*;
import java.awt.*;

public class Footer extends JPanel {

    public Footer() {
        setBounds(0, 920, 1700, 80);
        setBackground(new Color(40, 40, 60));
        setLayout(new BorderLayout());

        JLabel text = new JLabel("Battle Mania");
        text.setForeground(Color.WHITE);
        text.setFont(new Font("Serif", Font.PLAIN, 18));
        text.setHorizontalAlignment(SwingConstants.CENTER);

        add(text, BorderLayout.CENTER);
    }
}
