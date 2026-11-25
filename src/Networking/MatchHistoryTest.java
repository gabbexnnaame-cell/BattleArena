
package Networking;

import java.io.IOException;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;

public class MatchHistoryTest {

    public static void main(String[] args) throws IOException {

        MatchHistory history = new MatchHistory();

        MatchResult result = new MatchResult(
                "Gabriel",
                "Knight",
                "Assassin",
                "Gabriel",
                8,
                LocalDateTime.now()
        );

        history.saveMatch(result);

        List<MatchResult> matches = history.getMatchHistory();

        for (MatchResult match : matches) {
            System.out.println(match);
        }
    }

    @Test
    void getMatchHistoryShouldReturnSavedMatches() throws IOException {
        MatchHistory matchHistory = new MatchHistory();

        List<MatchResult> matches = matchHistory.getMatchHistory();

        for (MatchResult match : matches) {
            System.out.println(
                    match.getPlayerName() + " - " +
                            match.getWinner() + " - " +
                            match.getDateTime()
            );
        }

        assertFalse(matches.isEmpty());
    }

    @Test
    void getMatchHistoryShouldPreserveOriginalDateTime() throws IOException {

        MatchHistory matchHistory = new MatchHistory();

        List<MatchResult> matches = matchHistory.getMatchHistory();

        assertFalse(matches.isEmpty());

        MatchResult firstMatch = matches.get(0);

        System.out.println("Player: " + firstMatch.getPlayerName());
        System.out.println("Winner: " + firstMatch.getWinner());
        System.out.println("Date: " + firstMatch.getDateTime());

        assertNotNull(firstMatch.getDateTime());
    }

    @Test
    void saveAndLoadMatchShouldPreserveAllInformation() throws IOException {

        MatchHistory history = new MatchHistory();

        MatchResult original = new MatchResult(
                "Gabriel",
                "Knight",
                "Assassin",
                "Gabriel",
                8
        );

    }

    @Test
    void saveAndLoadMatchShouldPreserveAllInformationTwo() throws IOException {

        MatchHistory history = new MatchHistory();

        MatchResult original = new MatchResult(
                "Gabriel",
                "Knight",
                "Assassin",
                "Gabriel",
                8
        );

        history.saveMatch(original);
    }
}
