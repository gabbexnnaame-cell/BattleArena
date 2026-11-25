
package Action;

import Characters.Character;

import java.util.ArrayList;
import java.util.List;

public class HealAction implements CombatAction {

    private final int healAmount;


    // Constructor
    public HealAction(int healAmount) {
        this.healAmount = healAmount;
    }


    // Executes healing and returns combat messages
    @Override
    public List<String> execute(Character actor, Character target) {

        List<String> messages = new ArrayList<>();

        int previousHp = actor.getHp();

        actor.heal(healAmount);

        int actualHealing = actor.getHp() - previousHp;

        messages.add(
                actor.getName() + " heals for " + actualHealing + " HP!"
        );

        return messages;
    }


    // Action information
    @Override
    public ActionType getType() {
        return ActionType.HEAL;
    }

    @Override
    public boolean isAvailable() {
        return true;
    }

    @Override
    public String getDescription() {
        return "Heal | Restores up to " + healAmount + " HP";
    }
}
