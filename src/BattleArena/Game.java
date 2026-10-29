package BattleArena;
import Action.*;

import java.util.Random;
import java.util.List;

public class Game {

    private Character player;
    private Character computer;

    public Game(Character player, Character computer) {
        this.player = player;
        this.computer = computer;
    }

    public void startGame() {
        while (player.isAlive() && computer.isAlive()) {

        }
    }

    public void playerTurn(CombatAction playerAction){
        playerAction.execute(player, computer);
    }

    public void computerTurn(){
        List<CombatAction> actions = computer.getActions();

        Random random = new Random();
        int randomIndex = random.nextInt(actions.size());

        CombatAction combatAction = actions.get(randomIndex);
        combatAction.execute(computer, player);
    }
}
