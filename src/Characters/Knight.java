package Characters;

import Action.*;
import BattleArena.Character;

public class Knight extends Character {

    public Knight (String name) {
        super(name, 120);

        addAction(new AttackAction(15));
        addAction(new HealAction(10));
        addAction(new BlockAction(50));
    }
}