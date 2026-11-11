
package Characters;

import Action.*;

public class Assassin extends Character {

    public Assassin(String name) {
        super(name, 100, 15, 25);

        addAction(new AttackAction("Quick Stab", 12, 10, 25, 0));
        addAction(new AttackAction("Shadow Strike", 22, 5, 35, 0));
        addAction(new AttackAction("Assassinate", 35, 2, 45, 0));
    }
}
