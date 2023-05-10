package org.example;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

public class GameServer {
    private int port;
    private boolean isRunning;

    public GameServer(int port) {
        this.port = port;
        this.isRunning = false;
    }

    public void start() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            isRunning = true;
            System.out.println("serverul a pornit pe: " + port);

            while (isRunning) {
                Socket clientSocket = serverSocket.accept();
                new ClientThread(clientSocket).start();
            }
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    public void stop() {
        isRunning = false;
    }

    public static void main(String[] args) {
        GameServer server = new GameServer(6969);
        server.start();
    }
}

