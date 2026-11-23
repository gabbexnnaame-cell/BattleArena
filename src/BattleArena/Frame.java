package BattleArena;

import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {

    private static final int WIDTH = 1000;
    private static final int HEIGHT = 700;

    public Frame() {
        this.setTitle("Battle Mania!");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);

        // Set the content pane size
        this.getContentPane().setPreferredSize(
                new Dimension(WIDTH, HEIGHT)
        );

        // Use absolute positioning
        this.getContentPane().setLayout(null);

        // Show starting screen first
        startingScreen();

        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    // Creates the header
    private JPanel createHeader() {

        JPanel header = new JPanel(new BorderLayout());
        header.setBounds(0, 0, WIDTH, 70);
        header.setBackground(new Color(40, 40, 60));

        JLabel title = new JLabel("BATTLE MANIA!");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Arial", Font.BOLD, 25));

        title.setBorder(
                BorderFactory.createEmptyBorder(0, 20, 0, 0)
        );

        header.add(title, BorderLayout.WEST);

        return header;
    }

    private JPanel createFooter() {

        JPanel footer = new JPanel(new BorderLayout());
        footer.setBounds(0, 500, WIDTH, 700);
        footer.setBackground(new Color(40, 40, 60));

        return footer;
    }

    // Starting screen
    public void startingScreen() {

        // Remove previous components
        this.getContentPane().removeAll();

        this.getContentPane().setBackground(
                new Color(230, 230, 240)
        );

        // Header
        JPanel header = createHeader();
        this.add(header);

        // Main title
        JLabel label = new JLabel("Welcome to Battle Mania!");
        label.setFont(new Font("The Landstone Regular", Font.BOLD, 40));
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setBounds(200, 120, 600, 60);

        this.add(label);

        // Start button
        JButton startButton = new JButton("START!");
        startButton.setFont(new Font("The Landstone Regular", Font.BOLD, 40));

        startButton.setBounds(400, 250, 200, 55);

        startButton.addActionListener(e -> GameScreen());

        this.add(startButton);

        // Exit button
        JButton closeButton = new JButton("EXIT");
        closeButton.setFont(new Font("The Landstone Regular", Font.BOLD, 40));
        closeButton.setBounds(400, 320, 200, 55);

        closeButton.addActionListener(e -> dispose());

        this.add(closeButton);

        // Refresh screen
        this.getContentPane().revalidate();
        this.getContentPane().repaint();
    }

    // Game screen
    public void GameScreen() {

        // Remove starting screen components
        this.getContentPane().removeAll();

        this.getContentPane().setBackground(
                new Color(230, 230, 240)
        );

        // Header
        JPanel header = createHeader();
        this.add(header);

        // Entity
        Entity entity = new Entity();


        this.add(entity);

        // Attack button
        JButton attackButton = new JButton("Attack!");
        attackButton.setFont(new Font("The Landstone Regular", Font.BOLD, 20));

        attackButton.setBounds(50, 510, 120, 50);

        attackButton.addActionListener(e -> entity.attack());

        this.add(attackButton);

        // Shield button
        JButton shieldButton = new JButton("Shield!");
        shieldButton.setFont(new Font("The Landstone Regular", Font.BOLD, 20));

        shieldButton.setBounds(120, 570, 120, 50);

        shieldButton.addActionListener(e -> entity.shield());

        this.add(shieldButton);

        // Exit button
        JButton exitButton = new JButton("Exit!");
        exitButton.setFont(new Font("The Landstone Regular", Font.BOLD, 20));

        exitButton.setBounds(170, 630, 120, 50);

        exitButton.addActionListener(e -> dispose());

        this.add(exitButton);

        //Footer
        JPanel footer = createFooter();
        this.add(footer);

        // Refresh screen
        this.getContentPane().revalidate();
        this.getContentPane().repaint();
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            new Frame();
        });
    }
}