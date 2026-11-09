package BattleArena;

import Action.CombatAction;
import Characters.Character;

import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

public class Game {

    private final Character player;
    private final Character computer;
    private final Random random;

    public Game(Character player, Character computer) {
        this(player, computer, new Random());
    }

    Game(Character player, Character computer, Random random) {
        this.player = Objects.requireNonNull(player);
        this.computer = Objects.requireNonNull(computer);
        this.random = Objects.requireNonNull(random);
    }

    public void startGame() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Battle Arena!");
        System.out.println(player.getName() + " vs " + computer.getName());

        while (player.isAlive() && computer.isAlive()) {

            printStatus();

            CombatAction action = choosePlayerAction(scanner);

            if (action == null) {
                System.out.println("Game ended.");
                return;
            }

            playerTurn(action);

            if (computer.isAlive()) {
                computerTurn();
            }
        }

        printResult();
    }

    public void playerTurn(CombatAction playerAction) {
        Objects.requireNonNull(playerAction);

        if (!player.isAlive() || !computer.isAlive()) {
            return;
        }

        playerAction.execute(player, computer);
    }

    public void computerTurn() {

        if (!player.isAlive() || !computer.isAlive()) {
            return;
        }

        List<CombatAction> actions = computer.getActions();

        if (actions == null || actions.isEmpty()) {
            System.out.println("Computer has no available actions.");
            return;
        }

        int randomIndex = random.nextInt(actions.size());

        CombatAction action = actions.get(randomIndex);

        if (action != null) {
            action.execute(computer, player);
        }
    }

    private CombatAction choosePlayerAction(Scanner scanner) {

        List<CombatAction> actions = player.getActions();

        if (actions == null || actions.isEmpty()) {
            System.out.println("No available actions.");
            return null;
        }

        System.out.println("\nChoose your action:");

        for (int i = 0; i < actions.size(); i++) {

            CombatAction action = actions.get(i);

            String actionName = action == null
                    ? "Unavailable"
                    : action.getType().toString();

            System.out.println((i + 1) + ". " + actionName);
        }

        while (true) {

            System.out.print("Enter a number (1-" + actions.size() + "): ");

            if (!scanner.hasNextLine()) {
                return null;
            }

            String input = scanner.nextLine().trim();

            try {

                int choice = Integer.parseInt(input);

                if (choice >= 1 && choice <= actions.size()) {

                    CombatAction selectedAction = actions.get(choice - 1);

                    if (selectedAction != null) {
                        return selectedAction;
                    }
                }

            } catch (NumberFormatException ignored) {

            }

            System.out.println("Invalid choice. Please try again.");
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

        System.out.println("\n--- Game Over ---");

        if (!player.isAlive() && !computer.isAlive()) {

            System.out.println("The game ended in a draw!");

        } else if (player.isAlive()) {

            System.out.println(player.getName() + " wins!");

        } else {

            System.out.println(computer.getName() + " wins!");
        }
    }
}

