package Characters;

import Action.CombatAction;

import java.util.ArrayList;
import java.util.List;

public abstract class Character {

    private String name;
    private int hp;
    private int maxHp;
    private boolean blocking;
    private int blockPercentage;
    private List<CombatAction> actions;
    private String typeOfStrike;
    private int critChance;

    public Character(String name, int maxHp, int critChance, String typeOfStrike) {
        this.name = name;
        this.maxHp = maxHp;
        this.hp = maxHp;
        this.actions = new ArrayList<>();
        this.critChance = critChance;
        this.typeOfStrike = typeOfStrike;
    }


    public String getTypeOfStrike() {
        return typeOfStrike;
    }

    public int getCritChance() {
        return critChance;
    }

    public String getName() {
        return name;
    }

    public int getHp() {
        return hp;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public List<CombatAction> getActions() {
        return actions;
    }

    public void addAction(CombatAction action) {
        actions.add(action);
    }

    public void takeDamage (int damage) {
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

    public boolean isAlive() {
        return hp > 0;
    }
}
