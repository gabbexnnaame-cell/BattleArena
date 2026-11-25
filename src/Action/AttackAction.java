
package Action;

import Characters.Character;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AttackAction implements CombatAction {

    private final String name;
    private final int damage;
    private final int maxUses;
    private int remainingUses;
    private final int critChance;
    private final int lifesteal;

    private final Random random = new Random();


    // Constructor
    public AttackAction(String name, int damage, int maxUses,
                        int critChance, int lifesteal) {

        this.name = name;
        this.damage = damage;
        this.maxUses = maxUses;
        this.remainingUses = maxUses;
        this.critChance = critChance;
        this.lifesteal = lifesteal;
    }


    // Getters
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


    // Executes the attack and returns combat messages
    @Override
    public List<String> execute(Character actor, Character target) {

        List<String> messages = new ArrayList<>();

        if (remainingUses <= 0) {
            messages.add(name + " has no uses remaining!");
            return messages;
        }

        remainingUses--;

        int finalDamage = damage;

        messages.add(actor.getName() + " used " + name + "!");

        // Apply blocking first
        if (target.isBlocking()) {

            int blockedDamage =
                    finalDamage * target.getBlockPercentage() / 100;

            finalDamage -= blockedDamage;

            target.setBlocking(false);

            messages.add(
                    target.getName() + " blocked " + blockedDamage + " damage!"
            );
        }

        // Check for critical hit after blocking
        if (random.nextInt(100) < critChance) {

            finalDamage *= 2;

            messages.add("CRITICAL HIT!");
        }

        // Deal damage
        int previousHp = target.getHp();

        target.takeDamage(finalDamage);

        int actualDamageDealt = previousHp - target.getHp();

        messages.add(
                target.getName() + " took " + actualDamageDealt + " damage!"
        );

        // Apply lifesteal based on actual damage dealt
        if (lifesteal > 0 && actualDamageDealt > 0) {

            int healingAmount = actualDamageDealt * lifesteal / 100;

            actor.heal(healingAmount);

            messages.add(
                    actor.getName() + " recovered " + healingAmount + " HP!"
            );
        }

        return messages;
    }


    // Action information
    @Override
    public ActionType getType() {
        return ActionType.ATTACK;
    }

    @Override
    public boolean isAvailable() {
        return remainingUses > 0;
    }

    @Override
    public String getDescription() {
        return name + " | Damage: " + damage +
                " | Crit: " + critChance + "%" +
                " | Uses: " + remainingUses + "/" + maxUses;
    }
}
