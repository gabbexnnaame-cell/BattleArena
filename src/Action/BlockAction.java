package Action;

import BattleArena.Character;

public class BlockAction implements CombatAction {

    private int blockPercentage;

    public BlockAction(int blockPercentage) {
        this.blockPercentage = blockPercentage;
    }

    @Override
    public void execute(Character actor, Character target) {
        actor.setBlockPercentage(blockPercentage);
        actor.setBlocking(true);

        System.out.println(
                actor.getName() + " prepares to block "
                + blockPercentage + "% of incoming damage!"
        );
    }

    @Override
    public ActionType getType() {
        return ActionType.BLOCK;
    }
}
