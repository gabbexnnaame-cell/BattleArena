package BattleArena;

import Action.CombatAction;
import Characters.Character;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Game {

    private final Character player;
    private final Character computer;
    private final Random random;

    public Game(Character player, Character computer) {
        this.player = player;
        this.computer = computer;
        this.random = new Random();
    }
    
    public void startGame() {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Battle Arena!");
        System.out.println(player.getName() + " vs " + computer.getName());
        
        while (player.isAlive() && computer.isAlive()) {

            System.out.println("\n====================");
            System.out.println("       NEW ROUND");
            System.out.println("====================");

            printStatus();
            
            CombatAction action = choosePlayerAction(scanner);

            if (action == null) {
                System.out.println("No action available. Game ended.");
                break;
            }
            
            playerTurn(action);
            
            if (!computer.isAlive()) {
                break;
            }
            
            computerTurn();
            
            if (!player.isAlive()) {
                break;
            }
        }

        printResult();
    }
    
    public void playerTurn(CombatAction action) {
        action.execute(player, computer);
    }
    
    public void computerTurn() {

        List<CombatAction> actions = computer.getActions();

        if (actions == null || actions.isEmpty()) {
            System.out.println("Computer has no actions!");
            return;
        }

        int randomIndex = random.nextInt(actions.size());

        CombatAction action = actions.get(randomIndex);

        action.execute(computer, player);
    }
    
    private CombatAction choosePlayerAction(Scanner scanner) {

        List<CombatAction> actions = player.getActions();

        System.out.println("\nChoose your action:");

        for (int i = 0; i < actions.size(); i++) {
            System.out.println(
                    (i + 1) + ". " + actions.get(i).getType()
            );
        }

        while (true) {

            System.out.print("Enter your choice: ");

            if (!scanner.hasNextInt()) {
                System.out.println("Please enter a number.");
                scanner.nextLine();
                continue;
            }

            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice >= 1 && choice <= actions.size()) {
                return actions.get(choice - 1);
            }

            System.out.println("Invalid choice. Try again.");
        }
    }
    
    private void printStatus() {

        System.out.println("\n--- Current HP ---");

        System.out.println(
                player.getName() + ": "
                        + player.getHp() + "/" + player.getMaxHp()
        );

        System.out.println(
                computer.getName() + ": "
                        + computer.getHp() + "/" + computer.getMaxHp()
        );
    }
    
    private void printResult() {

        System.out.println("\n===== GAME OVER =====");

        if (!player.isAlive() && !computer.isAlive()) {
            System.out.println("Draw!");
        } else if (player.isAlive()) {
            System.out.println(player.getName() + " wins!");
        } else {
            System.out.println(computer.getName() + " wins!");
        }
    }
}
