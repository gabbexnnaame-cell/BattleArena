
package Tests.GameIntegration;

import Action.ActionType;
import Action.AttackAction;
import Action.CombatAction;
import BattleArena.Game;
import Characters.Character;
import Characters.CharacterClass;
import Characters.Knight;
import Characters.Mage;
import User.User;
import User.UserService;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GameIntegrationTest {

    private final UserService userService = new UserService();

    // Helper method to create a fresh game for each test
    private Game createGame() {

        User user = userService.createUser(
                "Gabriel",
                CharacterClass.KNIGHT
        );

        Character computer = new Mage("Computer");

        return new Game(
                user.getCharacter(),
                computer,
                user.getUsername()
        );
    }


    // TEST 1: Create a game with the correct characters
    @Test
    void shouldCreateGameWithPlayerAndComputer() {

        // Arrange
        User user = userService.createUser(
                "Gabriel",
                CharacterClass.KNIGHT
        );

        Character computer = new Mage("Computer");

        // Act
        Game game = new Game(
                user.getCharacter(),
                computer,
                user.getUsername()
        );

        // Assert
        assertNotNull(game);
        assertEquals("Gabriel", game.getPlayerName());
        assertEquals("Gabriel", game.getPlayer().getName());
        assertEquals("Computer", game.getComputer().getName());
        assertEquals(100, game.getPlayer().getHp());
        assertEquals(0, game.getRounds());
    }


    // TEST 2: Only attacks should be available at the beginning
    @Test
    void shouldGivePlayerOnlyAttackActionsAtStart() {

        // Arrange
        Game game = createGame();

        // Act
        List<CombatAction> actions = game.getAvailableActions();

        // Assert
        assertEquals(3, actions.size());

        for (CombatAction action : actions) {
            assertEquals(ActionType.ATTACK, action.getType());
        }

        assertFalse(game.isAwaitingSupportAction());
    }


    // TEST 3: After attacking, only Heal and Block should be available
    @Test
    void shouldGivePlayerOnlySupportActionsAfterAttacking() {

        // Arrange
        Game game = createGame();

        CombatAction attack = game.getAvailableActions().get(0);

        // Act
        List<String> messages = game.performPlayerAction(attack);

        List<CombatAction> actions = game.getAvailableActions();

        // Assert
        assertFalse(messages.isEmpty());
        assertTrue(game.isAwaitingSupportAction());

        assertEquals(2, actions.size());

        for (CombatAction action : actions) {
            assertTrue(
                    action.getType() == ActionType.HEAL ||
                            action.getType() == ActionType.BLOCK
            );
        }
    }


    // TEST 4: The computer should take its turn after the player's support action
    @Test
    void shouldAutomaticallyPerformComputerTurnAfterSupportAction() {

        // Arrange
        Game game = createGame();

        CombatAction attack = game.getAvailableActions().get(0);

        // Player attacks
        game.performPlayerAction(attack);

        CombatAction support = game.getAvailableActions().get(0);

        // Act
        List<String> messages = game.performPlayerAction(support);

        // Assert
        assertFalse(messages.isEmpty());
        assertFalse(game.isAwaitingSupportAction());
        assertEquals(1, game.getRounds());
    }


    // TEST 5: Invalid actions should be rejected
    @Test
    void shouldRejectInvalidPlayerAction() {

        // Arrange
        Game game = createGame();

        CombatAction invalidAction =
                new AttackAction("Invalid Attack", 50, 1, 0, 0);

        // Act
        List<String> messages =
                game.performPlayerAction(invalidAction);

        // Assert
        assertFalse(messages.isEmpty());

        assertEquals(
                "That action is not available right now.",
                messages.get(0)
        );

        assertFalse(game.isAwaitingSupportAction());
        assertEquals(0, game.getRounds());
    }


    // TEST 6: Game should detect when the player wins
    @Test
    void shouldDetectPlayerAsWinner() {

        // Arrange
        Game game = createGame();

        // Defeat the computer
        game.getComputer().takeDamage(
                game.getComputer().getHp()
        );

        // Assert
        assertTrue(game.isGameOver());
        assertEquals("Gabriel", game.getWinner());
    }


    // TEST 7: Game should detect when the computer wins
    @Test
    void shouldDetectComputerAsWinner() {

        // Arrange
        Game game = createGame();

        // Defeat the player
        game.getPlayer().takeDamage(
                game.getPlayer().getHp()
        );

        // Assert
        assertTrue(game.isGameOver());
        assertEquals("Computer", game.getWinner());
    }


    // TEST 8: Game should detect a draw
    @Test
    void shouldDetectDrawWhenBothCharactersAreDefeated() {

        // Arrange
        Game game = createGame();

        // Defeat both characters
        game.getPlayer().takeDamage(
                game.getPlayer().getHp()
        );

        game.getComputer().takeDamage(
                game.getComputer().getHp()
        );

        // Assert
        assertTrue(game.isGameOver());
        assertEquals("Draw", game.getWinner());
    }


    // TEST 9: Winner should be null while the game is ongoing
    @Test
    void shouldReturnNullWhenGameHasNoWinnerYet() {

        // Arrange
        Game game = createGame();

        // Act
        String winner = game.getWinner();

        // Assert
        assertFalse(game.isGameOver());
        assertNull(winner);
    }
}
