
package Action;

import Characters.Character;
import java.util.Random;

public class AttackAction implements CombatAction {

    private String name;
    private int damage;
    private int maxUses;
    private int remainingUses;
    private int critChance;
    private int lifesteal;

    private Random random = new Random();

    public AttackAction(String name, int damage, int maxUses, int critChance, int lifesteal) {

        this.name = name;
        this.damage = damage;
        this.maxUses = maxUses;
        this.remainingUses = maxUses;
        this.critChance = critChance;
        this.lifesteal = lifesteal;
    }

    public String getName() {
        return name;
    }

    public int getDamage() {
        return damage;
    }

    public int getMaxUses() {
        return maxUses;
    }

    public int getRemainingUses() {
        return remainingUses;
    }

    public int getCritChance() {
        return critChance;
    }

    public int getLifesteal() {
        return lifesteal;
    }

    @Override
    public void execute(Character actor, Character target) {

        if (remainingUses <= 0) {
            System.out.println(name + " has no uses remaining!");
            return;
        }

        remainingUses--;

        int finalDamage = damage;

        // Apply blocking first
        if (target.isBlocking()) {

            int blockedDamage =
                    finalDamage * target.getBlockPercentage() / 100;

            finalDamage -= blockedDamage;

            target.setBlocking(false);

            System.out.println(
                    target.getName() + " blocked " +
                            blockedDamage + " damage!"
            );
        }

        // Check for critical hit after blocking
        if (random.nextInt(100) < critChance) {
            finalDamage *= 2;
            System.out.println("CRITICAL HIT!");
        }

        // Deal damage
        int previousHp = target.getHp();

        target.takeDamage(finalDamage);

        int actualDamageDealt = previousHp - target.getHp();

        System.out.println(
                actor.getName() + " used " + name + " on " +
                        target.getName() + " for " + actualDamageDealt + " damage!"
        );

        // Apply lifesteal based on actual damage dealt
        if (lifesteal > 0 && actualDamageDealt > 0) {

            int healingAmount = actualDamageDealt * lifesteal / 100;

            actor.heal(healingAmount);

            System.out.println(
                    actor.getName() + " recovered " + healingAmount + " HP!"
            );
        }
    }

    @Override
    public ActionType getType() {
        return ActionType.ATTACK;
    }

    @Override
    public boolean isAvailable() {
        return remainingUses > 0;
    }
}
