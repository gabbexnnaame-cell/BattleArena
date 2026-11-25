
package Action;

import Characters.Character;
import java.util.List;

public interface CombatAction {

    List<String> execute(Character actor, Character target);

    ActionType getType();

    boolean isAvailable();

    String getDescription();
}
