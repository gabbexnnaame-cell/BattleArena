
package Characters;

import Action.*;

public class Berserker extends Character {

    public Berserker(String name) {
        super(name, 100, 15, 35);

        addAction(new AttackAction("Heavy Swing", 20, 8, 10, 0));
        addAction(new AttackAction("Blood Cleave", 15, 5, 10, 25));
        addAction(new AttackAction("Raging Smash", 40, 2, 20, 0));

    }
}
