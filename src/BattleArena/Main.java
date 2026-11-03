package BattleArena;
import Action.CombatAction;
import java.util.List;
import java.util.Scanner;
import Characters.*;

public class Main {

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    Knight player = new Knight("Player");
    Mage computer = new Mage("Computer");

    Game game = new Game(player,computer );
    CombatAction action = chooseAction(player, scanner);
    game.playerTurn(action);
    game.computerTurn();
  }

  private static CombatAction chooseAction(Character player, Scanner scanner) {
    List<CombatAction> actions = player.getActions();

    System.out.println("Choose your action:");

    for (int i = 0; i < actions.size(); i++) {
      System.out.println((i + 1) + ". " + actions.get(i).getType());
    }

    int choice = scanner.nextInt();
    return actions.get(choice -1);
  }


}
