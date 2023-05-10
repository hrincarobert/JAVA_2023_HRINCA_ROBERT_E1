package org.example;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class GameClient {
    private String serverAddress;
    private int serverPort;
    private BufferedReader in;
    private PrintWriter out;

    public GameClient(String serverAddress, int serverPort) {
        this.serverAddress = serverAddress;
        this.serverPort = serverPort;
    }

    public void start() {
        try (Socket socket = new Socket(serverAddress, serverPort)) {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);

            System.out.println("Conectat la server pe " + serverAddress + ":" + serverPort);

            BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
            while (true) {
                String command = keyboard.readLine();
                if (command.equalsIgnoreCase("exit")) {
                    out.println(command);
                    break;
                }
                out.println(command);
                String response = in.readLine();
                System.out.println("Server raspuns: " + response);
            }
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        GameClient client = new GameClient("localhost", 6969);
        client.start();
    }
}
