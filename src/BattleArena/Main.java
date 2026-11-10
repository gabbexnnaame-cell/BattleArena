import BattleArena.Game;
import Characters.Knight;
import Characters.Mage;

public class Main {

  public static void main(String[] args) {

    Knight player = new Knight("Player");
    Mage computer = new Mage("Computer");

    Game game = new Game(player, computer);

    game.startGame();
  }
}