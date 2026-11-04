package Action;

import Characters.Character;
import java.util.Random;

public class AttackAction implements CombatAction {

    private int damage;
    private Random random = new Random();

    public AttackAction(int damage) {
        this.damage = damage;
    }

    @Override
    public void execute(Character actor, Character target) {

        int finalDamage = damage;

        // Kontrollerar om attacken blir kritisk
        if (random.nextInt(100) < actor.getCritChance()) {
            finalDamage *= 2;
            System.out.println("CRITICAL HIT!");
        }


        if (target.isBlocking()) {

            int blockedDamage =
                    finalDamage * target.getBlockPercentage() / 100;

            finalDamage = finalDamage - blockedDamage;

            target.setBlocking(false);

            System.out.println(
                    target.getName() + " blocked " +
                            blockedDamage + " damage!"
            );
        }


        target.takeDamage(finalDamage);

        System.out.println(
                actor.getName() + " used " +
                        actor.getAttackName() + " on " +
                        target.getName() + " for " +
                        finalDamage + " damage!"
        );
    }

    @Override
    public ActionType getType() {
        return ActionType.ATTACK;
    }
}