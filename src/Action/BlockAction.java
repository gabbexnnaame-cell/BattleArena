
package Action;

import Characters.Character;

import java.util.List;

public class BlockAction implements CombatAction {

    private final int blockPercentage;


    // Constructor
    public BlockAction(int blockPercentage) {
        this.blockPercentage = blockPercentage;
    }


    // Activates blocking and returns combat messages
    @Override
    public List<String> execute(Character actor, Character target) {

        actor.setBlockPercentage(blockPercentage);
        actor.setBlocking(true);

        return List.of(
                actor.getName() + " prepares to block "
                        + blockPercentage + "% of incoming damage!"
        );
    }


    // Action information
    @Override
    public ActionType getType() {
        return ActionType.BLOCK;
    }

    @Override
    public boolean isAvailable() {
        return true;
    }

    @Override
    public String getDescription() {
        return "Block | Reduces incoming damage by "
                + blockPercentage + "%";
    }
}
