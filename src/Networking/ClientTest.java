package Networking;

import java.time.LocalDateTime;

public class ClientTest {

    public static void main(String[] args) {

        LocalDateTime testDate = LocalDateTime.of(
                2026, 1, 15, 14, 30
        );

        MatchResult result = new MatchResult(
                "Gabriel",
                "Knight",
                "Assassin",
                "Gabriel",
                8,
                testDate
        );

        Client client = new Client();

        client.sendMatchResult(result);
    }
}