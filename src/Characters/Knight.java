package Characters;

import Action.*;

public class Knight extends Character {

    public Knight(String name) {
        super(name, 100, 20, 50);

        addAction(new AttackAction("Sword Strike", 15, 10, 10, 0));
        addAction(new AttackAction("Shield Bash", 20, 5, 15, 0));
        addAction(new AttackAction("Royal Execution", 35, 2, 20, 0));
    }
}