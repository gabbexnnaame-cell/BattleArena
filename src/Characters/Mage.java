package Characters;

import Action.*;
import BattleArena.Character;

public class Mage extends Character {

    public Mage(String name) {
        super(name, 85);

        addAction(new AttackAction(18));
        addAction(new HealAction(20));
        addAction(new BlockAction(15));
    }
}
