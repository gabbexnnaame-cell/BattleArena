
package BattleArena.screens;

import BattleArena.Frame;
import BattleArena.entities.Entity;
import BattleArena.entities.ComputerEntity;
import BattleArena.components.*;

import javax.swing.*;
import java.awt.*;

public class GameScreen extends JPanel {

    private final Frame frame;
    private final Entity entity;
    private final ComputerEntity computerEntity;

    private final HPBar hpBar;
    private final HPBar cpHpBar;

    private JTextArea battleLog;

    public GameScreen(Frame frame, Entity entity, ComputerEntity computerEntity) {

        this.frame = frame;
        this.entity = entity;
        this.computerEntity = computerEntity;

        setLayout(null);

        // Background
        JLabel background = new JLabel();

        ImageIcon backgroundImage = new ImageIcon(
                getClass().getResource(
                        "/BattleArena/characterPictures/Moonlit Gothic Duel Arena.png"
                )
        );

        Image scaledBackground = backgroundImage.getImage()
                .getScaledInstance(1700, 1000, Image.SCALE_SMOOTH);

        background.setIcon(new ImageIcon(scaledBackground));
        background.setBounds(0, 0, 1700, 1000);
        add(background);

        // Header
        Header header = new Header();
        add(header);

        // Footer
        Footer footer = new Footer();
        add(footer);

        // Player character
        entity.setBounds(250, 300, 350, 500);
        add(entity);

        // Computer character
        computerEntity.setBounds(1000, 300, 350, 500);
        add(computerEntity);

        // Player health bar
        hpBar = new HPBar();
        hpBar.setBounds(250, 230, 350, 55);
        hpBar.updateHealth(entity.getCurrentHP(), entity.getMaxHP());
        add(hpBar);

        // Computer health bar
        cpHpBar = new HPBar();
        cpHpBar.setBounds(1000, 230, 350, 55);
        cpHpBar.updateHealth(
                computerEntity.getCurrentHP(),
                computerEntity.getMaxHP()
        );
        add(cpHpBar);

        // Attack button
        GameButton attackButton = new GameButton("ATTACK");
        attackButton.setBounds(100, 700, 250, 65);
        add(attackButton);

        attackButton.addActionListener(e -> {

            if (entity.isDead() || computerEntity.isDead()) {
                return;
            }

            entity.attack();

            int previousHP = computerEntity.getCurrentHP();
            computerEntity.takeDamage(20);

            int damage = previousHP - computerEntity.getCurrentHP();

            addLog("Player 1 attacked Computer!");
            addLog("Computer took " + damage + " damage.");

            updateHP();

            if (computerEntity.isDead()) {
                addLog("Computer was defeated!");
            }
        });

        // Shield button
        GameButton shieldButton = new GameButton("SHIELD");
        shieldButton.setBounds(100, 800, 250, 65);
        add(shieldButton);

        shieldButton.addActionListener(e -> {

            if (entity.isDead()) {
                return;
            }

            entity.shield();
            addLog("Player 1 used Shield!");
        });

        // Heal button
        GameButton healButton = new GameButton("HEAL");
        healButton.setBounds(450, 700, 250, 65);
        add(healButton);

        healButton.addActionListener(e -> {

            if (entity.isDead()) {
                return;
            }

            int previousHP = entity.getCurrentHP();

            entity.heal();

            int healed = entity.getCurrentHP() - previousHP;

            addLog("Player 1 used Heal!");
            addLog("Player 1 restored " + healed + " HP.");

            updateHP();
        });

        // Computer attack button
        GameButton damageButton = new GameButton("COMPUTER ATTACKS");
        damageButton.setBounds(850, 760, 250, 65);
        add(damageButton);

        damageButton.addActionListener(e -> {

            if (entity.isDead() || computerEntity.isDead()) {
                return;
            }

            computerEntity.attack();

            int previousHP = entity.getCurrentHP();
            entity.takeDamage(20);

            int damage = previousHP - entity.getCurrentHP();

            addLog("Computer attacked Player 1!");
            addLog("Player 1 took " + damage + " damage.");

            updateHP();

            if (entity.isDead()) {
                addLog("Player 1 was defeated!");
            }
        });

        // Exit button
        GameButton exitButton = new GameButton("EXIT");
        exitButton.setBounds(450, 800, 250, 65);
        add(exitButton);

        exitButton.addActionListener(e -> {
            frame.showStartingScreen();
        });

        // Battle log
        createBattleLog();

        // Keep background behind everything
        setComponentZOrder(background, getComponentCount() - 1);
    }

    // Creates a transparent, text-only battle log
    private void createBattleLog() {

        battleLog = new JTextArea();

        battleLog.setEditable(false);
        battleLog.setLineWrap(true);
        battleLog.setWrapStyleWord(true);

        battleLog.setOpaque(false);
        battleLog.setBackground(new Color(0, 0, 0, 0));
        battleLog.setForeground(Color.WHITE);

        battleLog.setFont(new Font("Impact", Font.PLAIN, 25));

        battleLog.setText("===== BATTLE LOG =====\n");
        battleLog.append("The battle has started!\n");

        JScrollPane logScrollPane = new JScrollPane(battleLog);

        logScrollPane.setBounds(1350, 650, 450, 250);
        logScrollPane.setOpaque(false);
        logScrollPane.setBackground(new Color(0, 0, 0, 0));
        logScrollPane.setBorder(null);
        logScrollPane.getViewport().setOpaque(false);

        add(logScrollPane);
    }

    // Adds a message to the battle log
    private void addLog(String message) {

        battleLog.append(message + "\n");

        battleLog.setCaretPosition(
                battleLog.getDocument().getLength()
        );
    }

    // Updates both health bars
    private void updateHP() {

        hpBar.updateHealth(
                entity.getCurrentHP(),
                entity.getMaxHP()
        );

        cpHpBar.updateHealth(
                computerEntity.getCurrentHP(),
                computerEntity.getMaxHP()
        );
    }
}
