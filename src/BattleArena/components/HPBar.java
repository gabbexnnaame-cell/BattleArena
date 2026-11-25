
package BattleArena.components;

import javax.swing.*;
import java.awt.*;

public class HPBar extends JPanel {

    private final JProgressBar healthBar;
    private final JLabel healthText;

    public HPBar() {
        setLayout(new BorderLayout(5, 5));
        setOpaque(false);

        healthBar = new JProgressBar(0, 100);
        healthBar.setValue(100);
        healthBar.setForeground(new Color(70, 180, 90));
        healthBar.setBackground(Color.DARK_GRAY);

        healthText = new JLabel("100 / 100 HP");
        healthText.setForeground(Color.WHITE);
        healthText.setHorizontalAlignment(SwingConstants.CENTER);

        add(healthBar, BorderLayout.CENTER);
        add(healthText, BorderLayout.SOUTH);
    }

    public void updateHealth(int currentHP, int maxHP) {
        healthBar.setMaximum(maxHP);
        healthBar.setValue(currentHP);

        healthText.setText(currentHP + " / " + maxHP + " HP");

        if (currentHP <= 30) {
            healthBar.setForeground(Color.RED);
        } else if (currentHP <= 60) {
            healthBar.setForeground(Color.ORANGE);
        } else {
            healthBar.setForeground(new Color(70, 180, 90));
        }
    }
}
