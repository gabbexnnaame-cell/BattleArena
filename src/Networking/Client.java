package Networking;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {

    private final String host = "localhost";
    private final int port = 5000;


    public void sendMatchResult(MatchResult result) {

        try (
                Socket socket = new Socket(host, port);
                PrintWriter writer = new PrintWriter(socket.getOutputStream(), true)
        ) {

            String matchData =
                    result.getPlayerName() + "," +
                            result.getPlayerCharacter() + "," +
                            result.getComputerCharacter() + "," +
                            result.getWinner() + "," +
                            result.getRounds() + "," +
                            result.getDateTime();

            writer.println(matchData);

            System.out.println("Match result sent to server!");

        } catch (IOException e) {
            System.out.println("Could not connect to server: " + e.getMessage());
        }
    }

}
