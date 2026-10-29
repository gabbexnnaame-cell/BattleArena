package Action;

import BattleArena.Character;

public class AttackAction implements CombatAction {

    private int damage;

    public AttackAction(int damage) {
        this.damage = damage;
    }

    @Override
    public void execute(Character actor, Character target) {
        int finalDamage = damage;

        if (target.isBlocking()) {
            int blockedDamage = damage * target.getBlockPercentage() / 100;
            finalDamage = damage - blockedDamage;

            target.setBlocking(false);

            System.out.println(
                    target.getName() + " blocked " + blockedDamage +
                            " damage and only took " + finalDamage + " damage!"
            );
        }

        target.takeDamage(finalDamage);

        System.out.println(
                actor.getName() + " attacked " + target.getName() + " for " + finalDamage + " damage!"
        );
    }

    @Override
    public ActionType getType() {
        return ActionType.ATTACK;
    }
}
