package Networking;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private final int port = 5000;
    private final MatchHistory matchHistory;

    public Server() {
        this.matchHistory = new MatchHistory();
    }

    public static void main(String[] args) {
        Server server = new Server();
        server.startServer();
    }

    public void startServer() {

        try (ServerSocket serverSocket = new ServerSocket(port)) {

            System.out.println("Server started on port " + port);
            System.out.println("Waiting for clients...");

            while (true) {

                Socket clientSocket = serverSocket.accept();

                System.out.println("Client connected!");

                ClientHandler handler = new ClientHandler(clientSocket);

                Thread thread = new Thread(handler);

                thread.start();

            }

        } catch (IOException e) {
            System.out.println("Server error: " + e.getMessage());
        }
    }
}
