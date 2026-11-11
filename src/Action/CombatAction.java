package Action;

import Characters.Character;

public interface CombatAction {

    void execute(Character actor, Character target);

    ActionType getType();

    boolean isAvailable();

    String getDescription();
}
