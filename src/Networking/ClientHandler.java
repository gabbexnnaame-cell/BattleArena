package Networking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class ClientHandler implements Runnable {

    private final Socket clientSocket;
    private final MatchHistory matchHistory;

    public ClientHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
        this.matchHistory = new MatchHistory();
    }

    @Override
    public void run() {

        try (
                BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                Socket socket = clientSocket) {

            String receivedData = reader.readLine();

            if (receivedData != null) {

                String[] data = receivedData.split(",", -1);

                if (data.length != 6) {
                    System.out.println("Invalid match data received!");
                    return;
                }

                MatchResult result = new MatchResult(
                        data[0],
                        data[1],
                        data[2],
                        data[3],
                        Integer.parseInt(data[4]),
                        LocalDateTime.parse(data[5])
                );

                matchHistory.saveMatch(result);

                System.out.println("Match result received and saved!");
            }

        } catch (IOException | NumberFormatException | DateTimeParseException e) {
            System.out.println("Error handling client: " + e.getMessage());
        }
    }
}
