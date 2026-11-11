
package Characters;

import Action.*;

public class Mage extends Character {

    public Mage(String name) {
        super(name, 100, 25, 30);

        addAction(new AttackAction("Magic Bolt", 15, 10, 15, 0));
        addAction(new AttackAction("Arcane Drain", 12, 5, 15, 25));
        addAction(new AttackAction("Meteor Strike", 35, 2, 25, 0));
    }
}
