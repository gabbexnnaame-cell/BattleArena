
package BattleArena.components;

import javax.swing.*;
import java.awt.*;

public class Header extends JPanel {

    public Header() {
        setBounds(0, 0, 1700, 80);
        setBackground(new Color(40, 40, 60));
        setLayout(new BorderLayout());

        JLabel title = new JLabel("BATTLE MANIA");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Serif", Font.BOLD, 32));
        title.setHorizontalAlignment(SwingConstants.CENTER);

        add(title, BorderLayout.CENTER);
    }
}
