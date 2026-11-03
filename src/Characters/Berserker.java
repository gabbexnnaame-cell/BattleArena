package Characters;

import Action.*;
import BattleArena.Character;

public class Berserker extends Character {

    public Berserker(String name) {
        super(name, 110);

        addAction(new AttackAction(30));
        addAction(new HealAction(5));
        addAction(new BlockAction(20));
    }
}
