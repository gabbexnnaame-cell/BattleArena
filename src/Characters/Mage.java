package Characters;

import Action.*;

public class Mage extends Character {

    public Mage(String name) {
        super(name, 85, 15, "Fireball");

        addAction(new AttackAction(18));
        addAction(new HealAction(20));
        addAction(new BlockAction(15));
    }
}
