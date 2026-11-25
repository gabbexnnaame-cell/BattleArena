
package Networking;

import java.time.LocalDateTime;

public class MatchResult {

    private final String playerName;
    private final String playerCharacter;
    private final String computerCharacter;
    private final String winner;
    private final int rounds;
    private final LocalDateTime dateTime;

    // Constructor for creating a new match
    public MatchResult(String playerName, String playerCharacter,
                       String computerCharacter, String winner, int rounds) {

        this(playerName, playerCharacter, computerCharacter, winner,
                rounds, LocalDateTime.now());
    }

    // Constructor for loading a match with its original date and time
    public MatchResult(String playerName, String playerCharacter,
                       String computerCharacter, String winner,
                       int rounds, LocalDateTime dateTime) {

        this.playerName = playerName;
        this.playerCharacter = playerCharacter;
        this.computerCharacter = computerCharacter;
        this.winner = winner;
        this.rounds = rounds;
        this.dateTime = dateTime;
    }

    public String getPlayerName() {
        return playerName;
    }

    public String getPlayerCharacter() {
        return playerCharacter;
    }

    public String getComputerCharacter() {
        return computerCharacter;
    }

    public String getWinner() {
        return winner;
    }

    public int getRounds() {
        return rounds;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }
}
