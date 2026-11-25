
package BattleArena;

import Action.ActionType;
import Action.CombatAction;
import Characters.Character;
import Networking.Client;
import Networking.MatchResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game {

    // Game characters
    private final Character player;
    private final Character computer;

    // Game information
    private final String playerName;
    private int rounds;

    // Game functionality
    private final Random random;
    private final Client client;

    // Turn management
    private boolean awaitingSupportAction = false;
    private boolean matchResultSent = false;


    // Constructor
    public Game(Character player, Character computer, String playerName) {
        this.player = player;
        this.computer = computer;
        this.playerName = playerName;

        this.random = new Random();
        this.rounds = 0;
        this.client = new Client();
    }


    // ==========================================
    // PLAYER ACTIONS
    // ==========================================

    public List<String> performPlayerAction(CombatAction action) {

        List<String> messages = new ArrayList<>();

        if (isGameOver()) {
            return messages;
        }

        if (!getAvailableActions().contains(action)) {
            messages.add("That action is not available right now.");
            return messages;
        }

        // Execute the player's selected action
        messages.addAll(action.execute(player, computer));

        checkGameOver();

        if (isGameOver()) {
            return messages;
        }

        // First action: attack
        if (!awaitingSupportAction) {
            awaitingSupportAction = true;
        }

        // Second action: heal or block
        else {
            awaitingSupportAction = false;

            // Computer automatically takes its turn
            messages.addAll(performComputerTurn());
        }

        return messages;
    }


    // ==========================================
    // COMPUTER ACTIONS
    // ==========================================

    private List<String> performComputerTurn() {

        List<String> messages = new ArrayList<>();

        if (isGameOver()) {
            return messages;
        }

        rounds++;

        // Computer attack
        messages.addAll(computerTurn(1));
        checkGameOver();

        if (isGameOver()) {
            return messages;
        }

        // Computer heal or block
        messages.addAll(computerTurn(2));
        checkGameOver();

        return messages;
    }


    private List<String> computerTurn(int move) {

        List<CombatAction> actions = getAllowedActions(computer, move);

        if (actions.isEmpty()) {
            return List.of();
        }

        CombatAction action = actions.get(random.nextInt(actions.size()));

        return action.execute(computer, player);
    }


    // ==========================================
    // AVAILABLE ACTIONS
    // ==========================================

    public List<CombatAction> getAvailableActions() {

        if (awaitingSupportAction) {
            return getAllowedActions(player, 2);
        }

        return getAllowedActions(player, 1);
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

            } else if (move == 2 &&
                    (type == ActionType.HEAL || type == ActionType.BLOCK)) {
                allowedActions.add(action);
            }
        }

        return allowedActions;
    }


    // ==========================================
    // GAME STATUS
    // ==========================================

    public Character getPlayer() {
        return player;
    }

    public String getPlayerName() {
        return playerName;
    }


    public Character getComputer() {
        return computer;
    }

    public boolean isAwaitingSupportAction() {
        return awaitingSupportAction;
    }


    public boolean isGameOver() {
        return !player.isAlive() || !computer.isAlive();
    }


    public String getWinner() {

        if (!isGameOver()) {
            return null;
        }

        if (!player.isAlive() && !computer.isAlive()) {
            return "Draw";
        }

        if (player.isAlive()) {
            return playerName;
        }

        return computer.getName();
    }

    public int getRounds() {
        return rounds;
    }


    // ==========================================
    // GAME OVER AND MATCH HISTORY
    // ==========================================

    private void checkGameOver() {

        if (isGameOver() && !matchResultSent) {

            matchResultSent = true;

            sendMatchResult();
        }
    }

    private void sendMatchResult() {

        MatchResult result = new MatchResult(
                playerName,
                player.getCharacterClass().name(),
                computer.getCharacterClass().name(),
                getWinner(),
                rounds
        );

        client.sendMatchResult(result);
    }


    // ==========================================
    // CONSOLE VERSION - KEPT FOR REFERENCE
    // ==========================================

    /*
    import java.util.Scanner;

    public void startGame() {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Battle Arena!");
        System.out.println(player.getName() + " vs " + computer.getName());

        while (player.isAlive() && computer.isAlive()) {

            rounds++;

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
        sendMatchResult();
    }


    public void playerTurn(CombatAction action) {
        action.execute(player, computer);
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
                throw new IllegalStateException(
                        "Input ended before the game finished."
                );
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

        if (getWinner().equals("Draw")) {
            System.out.println("Draw!");
        } else {
            System.out.println(getWinner() + " wins!");
        }
    }

    */
}
