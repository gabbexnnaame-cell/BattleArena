
package BattleArena.entities;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.net.URL;

public class ComputerEntity extends JLabel {

    private static final int IMAGE_WIDTH = 300;
    private static final int IMAGE_HEIGHT = 300;
    private static final int MAX_HP = 100;

    private String character;

    private ImageIcon idleImage;
    private ImageIcon attackImage;
    private ImageIcon blockImage;
    private ImageIcon healImage;

    private Timer animationTimer;

    private int currentHP = MAX_HP;
    private boolean shieldActive = false;

    public ComputerEntity() {
        this("Wizard");
    }

    public ComputerEntity(String character) {
        setBounds(0, 70, 350, 500);
        setHorizontalAlignment(SwingConstants.CENTER);
        setVerticalAlignment(SwingConstants.CENTER);

        chosenCharacter(character);
    }

    public void chosenCharacter(String character) {
        this.character = character;

        String prefix;

        switch (character.toLowerCase()) {
            case "wizard":
                prefix = "Wizard";
                break;

            case "assassin":
            case "assasin":
                prefix = "Assasin";
                break;

            case "knight":
                prefix = "Knight";
                break;

            default:
                throw new IllegalArgumentException(
                        "Unknown character: " + character
                );
        }

        idleImage = loadImage(prefix + " idle.png");
        attackImage = loadImage(prefix + " attack.png");
        blockImage = loadImage(prefix + " block.png");
        healImage = loadImage(prefix + " heal.png");

        showImage(idleImage);
    }

    private ImageIcon loadImage(String fileName) {
        String path = "/BattleArena/characterPictures/" + fileName;

        URL imageURL = getClass().getResource(path);

        if (imageURL == null) {
            throw new IllegalStateException(
                    "Could not find image: " + path
            );
        }

        return new ImageIcon(imageURL);
    }

    // Displays the image flipped horizontally
    private void showImage(ImageIcon image) {
        if (image == null) {
            return;
        }

        BufferedImage flippedImage = new BufferedImage(
                IMAGE_WIDTH,
                IMAGE_HEIGHT,
                BufferedImage.TYPE_INT_ARGB
        );

        Graphics2D g2d = flippedImage.createGraphics();

        Image originalImage = image.getImage();

        g2d.drawImage(
                originalImage,
                IMAGE_WIDTH, 0,
                -IMAGE_WIDTH, IMAGE_HEIGHT,
                null
        );

        g2d.dispose();

        setIcon(new ImageIcon(flippedImage));
    }

    public void attack() {
        playAnimation(attackImage, 500);
    }

    public void shield() {
        shieldActive = true;
        playAnimation(blockImage, 2000);
    }

    public void heal() {
        currentHP = Math.min(MAX_HP, currentHP + 20);
        playAnimation(healImage, 1000);
    }

    public void takeDamage(int damage) {
        if (damage <= 0 || isDead()) {
            return;
        }

        if (shieldActive) {
            damage = damage / 2;
            shieldActive = false;
        }

        currentHP = Math.max(0, currentHP - damage);
    }

    private void playAnimation(ImageIcon image, int duration) {
        if (animationTimer != null && animationTimer.isRunning()) {
            animationTimer.stop();
        }

        showImage(image);

        animationTimer = new Timer(duration, e -> {
            showImage(idleImage);
        });

        animationTimer.setRepeats(false);
        animationTimer.start();
    }

    public String getCharacter() {
        return character;
    }

    public int getCurrentHP() {
        return currentHP;
    }

    public int getMaxHP() {
        return MAX_HP;
    }

    public boolean isShieldActive() {
        return shieldActive;
    }

    public boolean isDead() {
        return currentHP <= 0;
    }
}
