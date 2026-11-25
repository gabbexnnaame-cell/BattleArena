package Networking;

public class MatchResultTest {

    public static void main(String[] args) {

        MatchResult result = new MatchResult(
                "Gabriel",
                "Knight",
                "Assassin",
                "Gabriel",
                8
        );

        System.out.println("Player: " + result.getPlayerName());
        System.out.println("Player character: " + result.getPlayerCharacter());
        System.out.println("Computer character: " + result.getComputerCharacter());
        System.out.println("Winner: " + result.getWinner());
        System.out.println("Rounds: " + result.getRounds());
        System.out.println("Date: " + result.getDateTime());
    }
}