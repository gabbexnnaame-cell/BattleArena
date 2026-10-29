package Action;

import BattleArena.Character;

public interface CombatAction {

    void execute(Character actor, Character target);

    ActionType getType();
}
