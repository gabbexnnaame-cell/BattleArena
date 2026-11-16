package BattleArena;

import Action.ActionType;
import Action.CombatAction;
import Characters.Character;

import java.util.ArrayList;
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

            for (int move = 1; move <= 2; move++) {

                System.out.println("\nYour move " + move + " of 2");

                CombatAction action = choosePlayerAction(scanner, move);

                if (action != null) {
                    playerTurn(action);
                }

                if (!player.isAlive() || !computer.isAlive()) {
                    break;
                }
            }

            if (!player.isAlive() || !computer.isAlive()) {
                break;
            }

            for (int move = 1; move <= 2; move++) {

                System.out.println("\nComputer move " + move + " of 2");

                computerTurn(move);

                if (!player.isAlive() || !computer.isAlive()) {
                    break;
                }
            }
        }

        printResult();
    }

    public void playerTurn(CombatAction action) {
        action.execute(player, computer);
    }

    public void computerTurn() {
        computerTurn(1);
    }

    public void computerTurn(int move) {

        List<CombatAction> actions = getAllowedActions(computer, move);

        if (actions.isEmpty()) {
            System.out.println("Computer has no available action. Move skipped.");
            return;
        }

        CombatAction action = actions.get(random.nextInt(actions.size()));

        System.out.println("Computer chooses: " + action.getType());

        action.execute(computer, player);
    }

    private CombatAction choosePlayerAction(Scanner scanner, int move) {

        List<CombatAction> actions = getAllowedActions(player, move);

        if (actions.isEmpty()) {
            System.out.println("No available action. Move skipped.");
            return null;
        }

        if (move == 1) {
            System.out.println("Choose an attack:");
        } else {
            System.out.println("Choose heal or block:");
        }

        for (int i = 0; i < actions.size(); i++) {
            System.out.println(
                    (i + 1) + ". " + actions.get(i).getType()
                            + " - " + actions.get(i).getDescription()
            );
        }

        while (true) {

            System.out.print("Enter your choice: ");

            if (!scanner.hasNextLine()) {
                throw new IllegalStateException("Input ended before the game finished.");
            }

            String input = scanner.nextLine();

            try {
                int choice = Integer.parseInt(input.trim());

                if (choice >= 1 && choice <= actions.size()) {
                    return actions.get(choice - 1);
                }

                System.out.println("Invalid choice. Try again.");

            } catch (NumberFormatException e) {
                System.out.println("Please enter a number.");
            }
        }
    }

    private List<CombatAction> getAllowedActions(Character character, int move) {

        List<CombatAction> allowedActions = new ArrayList<>();
        List<CombatAction> actions = character.getActions();

        if (actions == null) {
            return allowedActions;
        }

        for (CombatAction action : actions) {

            if (action == null || !action.isAvailable()) {
                continue;
            }

            ActionType type = action.getType();

            if (move == 1 && type == ActionType.ATTACK) {
                allowedActions.add(action);
            } else if (move == 2
                    && (type == ActionType.HEAL || type == ActionType.BLOCK)) {
                allowedActions.add(action);
            }
        }

        return allowedActions;
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