package BattleArena;
import Action.*;
import Characters.Character;

import java.util.Random;
import java.util.List;

public class Game {

    private Characters.Character player;
    private Characters.Character computer;

    public Game(Characters.Character player, Character computer) {
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
