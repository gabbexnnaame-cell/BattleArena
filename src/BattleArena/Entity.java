package BattleArena;

import javax.swing.*;
import java.awt.*;

public class Entity extends JLabel {

    ImageIcon idleImage = new ImageIcon(getClass().getResource("pixelKnight1.png"));
    ImageIcon attackImage = new ImageIcon(getClass().getResource("pixelKnight2.png"));
    ImageIcon shieldImage = new ImageIcon(getClass().getResource("pixelKnight3.png"));


    public Entity() {
        Image scaledImage = idleImage.getImage().getScaledInstance(
                300, 300,
                Image.SCALE_SMOOTH
        );
        setIcon(idleImage);
        setIcon(new ImageIcon(scaledImage));

        setBounds(0, 70, 1000, 550);

    }




    public void attack() {

        Image scaledImage = attackImage.getImage().getScaledInstance(
                300, 300,
                Image.SCALE_SMOOTH
        );

        setBounds(200, 100, 400, 400);

        setIcon(new ImageIcon(scaledImage));

        Timer timer = new Timer(500, e -> {
            setIcon(new ImageIcon(idleImage.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH)));
            setBounds(0, 70, 1000, 550);
        });



        timer.setRepeats(false);
        timer.start();
    }



    public void shield() {

        Image scaledImage = shieldImage.getImage().getScaledInstance(
                300, 300,
                Image.SCALE_SMOOTH
        );

        setIcon(new ImageIcon(scaledImage));

        Timer timer = new Timer(2000, e -> {
            setIcon(new ImageIcon(idleImage.getImage().getScaledInstance(
                    300, 300,
                    Image.SCALE_SMOOTH
            )));
        });

        timer.setRepeats(false);
        timer.start();
    }
}
