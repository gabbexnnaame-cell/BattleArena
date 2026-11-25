
package Tests.GameIntegration;

import Action.CombatAction;
import BattleArena.Game;
import Characters.Character;
import Characters.Knight;
import Characters.Mage;
import Networking.MatchHistory;
import Networking.MatchResult;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GameNetworkingTest {

    @Test
    void shouldSaveMatchResultWhenGameEnds()
            throws IOException, InterruptedException {

        // Arrange
        String playerName =
                "NetworkingTestPlayer-" + System.currentTimeMillis();

        Character player = new Knight(playerName);
        Character computer = new Mage("Computer");

        Game game = new Game(
                player,
                computer,
                playerName
        );

        MatchHistory matchHistory = new MatchHistory();

        // Leave the computer with only 1 HP
        computer.takeDamage(computer.getHp() - 1);

        // Act
        CombatAction attack = game.getAvailableActions().get(0);

        List<String> messages =
                game.performPlayerAction(attack);

        // Assert
        assertTrue(game.isGameOver());
        assertEquals(playerName, game.getWinner());
        assertFalse(messages.isEmpty());

        // Wait for the server to save the match
        List<MatchResult> matches = List.of();

        for (int i = 0; i < 20; i++) {

            matches = matchHistory.getMatchHistory();

            if (matches.stream().anyMatch(match ->
                    match.getPlayerName().equals(playerName))) {
                break;
            }

            Thread.sleep(100);
        }

        assertTrue(
                matches.stream().anyMatch(match ->
                        match.getPlayerName().equals(playerName)),
                "The completed match should be saved in match history"
        );
    }
}
