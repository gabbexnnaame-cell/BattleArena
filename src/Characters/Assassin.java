package Characters;

import Action.*;
import BattleArena.Character;

public class Assassin extends Character {

    public Assassin(String name) {
        super(name, 90);

        addAction(new AttackAction(25));
        addAction(new HealAction(5));
        addAction(new BlockAction(25));
    }
}