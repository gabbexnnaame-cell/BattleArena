package Characters;

import Action.*;

public class Berserker extends Character {

    public Berserker(String name) {
        super(name, 110, 20, "Brutal Smash");

        addAction(new AttackAction(30));
        addAction(new HealAction(5));
        addAction(new BlockAction(20));
    }
}
