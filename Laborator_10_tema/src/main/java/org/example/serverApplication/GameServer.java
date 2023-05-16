package org.example.serverApplication;

import org.example.Management;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class GameServer {
    private Socket socket = null;
    private ServerSocket server = null;
    private final int port;
    private boolean isRunning;
    private Management management;
    private final List<ClientThread> clientThreads = new ArrayList<>();


    public GameServer(int port) {
        Management management = new Management();
        this.management = management;
        this.port = port;
        this.isRunning = true;
    }

    public void start() {

        try (ServerSocket server = new ServerSocket(port)) {
            this.server = server;
            System.out.println("Server started on port " + port);

            while (isRunning) {
                Socket clientSocket = server.accept();
                ClientThread clientThread = new ClientThread(clientSocket, this);
                this.clientThreads.add(clientThread);
                clientThread.start();
            }
        } catch (IOException e) {
            System.out.println("Error starting server: " + e.getMessage());
        }
    }

    public void stopServer() throws IOException {
        isRunning = false;
        System.out.println("Inchidere server (1)");
        server.close();
        System.exit(1);
    }

    public void broadcast(String message) {
        for (ClientThread client : clientThreads) {
            PrintWriter out = client.getOut();
            out.println(message);
        }
    }


    public static void main(String[] args) {
        GameServer gameServer = new GameServer(5001);
        gameServer.start();
    }

    public Management getManagement() {
        return management;
    }
}