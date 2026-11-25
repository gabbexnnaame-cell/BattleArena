
package Characters;

import Action.BlockAction;
import Action.CombatAction;
import Action.HealAction;

import java.util.ArrayList;
import java.util.List;

public abstract class Character {

    // Character information
    private final String name;
    private int hp;
    private final int maxHp;
    private final CharacterClass characterClass;

    // Combat state
    private boolean blocking;
    private int blockPercentage;

    // Available actions
    private final List<CombatAction> actions;


    // Constructor
    public Character(String name, int maxHp, int healAmount,
                     int blockPercentage, CharacterClass characterClass) {

        this.name = name;
        this.maxHp = maxHp;
        this.hp = maxHp;
        this.blockPercentage = blockPercentage;
        this.characterClass = characterClass;

        this.actions = new ArrayList<>();

        addAction(new HealAction(healAmount));
        addAction(new BlockAction(blockPercentage));
    }


    // Getters
    public String getName() {
        return name;
    }

    public int getHp() {
        return hp;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public CharacterClass getCharacterClass() {
        return characterClass;
    }

    public List<CombatAction> getActions() {
        return List.copyOf(actions);
    }


    // Action management
    public void addAction(CombatAction action) {
        actions.add(action);
    }


    // Health management
    public void takeDamage(int damage) {

        hp -= damage;

        if (hp < 0) {
            hp = 0;
        }
    }

    public void heal(int amount) {

        hp += amount;

        if (hp > maxHp) {
            hp = maxHp;
        }
    }

    public boolean isAlive() {
        return hp > 0;
    }


    // Blocking management
    public boolean isBlocking() {
        return blocking;
    }

    public void setBlocking(boolean blocking) {
        this.blocking = blocking;
    }

    public int getBlockPercentage() {
        return blockPercentage;
    }

    public void setBlockPercentage(int blockPercentage) {

        if (blockPercentage < 0 || blockPercentage > 100) {
            throw new IllegalArgumentException(
                    "Block percentage must be between 0 and 100"
            );
        }

        this.blockPercentage = blockPercentage;
    }
}
