package Action;

import BattleArena.Character;

public class HealAction implements CombatAction {

    private int healAmount;

    public HealAction(int healAmount) {
        this.healAmount = healAmount;
    }

    @Override
    public void execute(Character actor, Character target) {
        actor.heal(healAmount);

        System.out.println(
                actor.getName() + " heals for " + healAmount + " HP!"
        );
    }

    @Override
    public ActionType getType() {
        return ActionType.HEAL;
    }
}
