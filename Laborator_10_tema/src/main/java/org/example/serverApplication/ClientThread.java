package org.example.serverApplication;

import org.example.Game;
import org.example.Management;
import org.example.Player;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientThread extends Thread {
    private final Socket clientSocket;
    private final GameServer gameServer;
    private final PrintWriter out;
    private Management management;

    public ClientThread(Socket clientSocket, GameServer gameServer) throws IOException {
        this.clientSocket = clientSocket;
        this.gameServer = gameServer;
        this.out = new PrintWriter(clientSocket.getOutputStream(), true);
        this.management = gameServer.getManagement();
    }

    @Override
    public void run() {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {
            out.println("Connected to the server!");

            while (true) {
                out.println("Enter a command:");
                String request = in.readLine();
                if (request == null) {
                    break;
                }

                String[] parts = request.split(" ");

                String command1 = parts[0];
//                String command2 = null;
//                if (parts.length >= 2)
//                    command2 = parts[1];
//
//
//                if (parts.length == 1) {
////                    out.println("Server received the request [" + command1 + "]");
//                    System.out.println("Server received the request [" + command1 + "]");
//                } else {
////                    out.println("Server received the request [" + command1 + " " + command2 + "]");
//                    System.out.println("Server received the request [" + command1 + " " + command2 + "]");
//                }


                switch (command1) {
                    case "create": {
                        if (parts.length != 1) {
                            out.println("Invalid command syntax.");
                            break;
                        } else if (management.getGame() == null) {     // no game created
                            Player player1 = new Player();

                            out.println("Player1, Introduce your name:");
                            request = in.readLine();

                            player1.setName(request);

                            out.println("Introduce your symbol:");
                            char symbol = (char) in.read();

                            player1.setSymbol(symbol);
                            Game game = new Game(player1);
                            management.setGame(game);
                            out.println("Game created. You are player 1! Your name is [" + player1.getName() + "] and your symbol is " + player1.getSymbol() + ".");
                            System.out.println("Game created. Player 1: " + player1.getName() + ", symbol: " + symbol);

                        } else if (management.getGame() != null && management.getGame().getPlayer1() == null) {    // game already created, waiting for player 2
                            out.println("Game is already created! Waiting for player 2 to join!");
                            System.out.println("Game is already created! Waiting for player 2 to join!");
                            management.getGame().setTurn(management.getGame().getPlayer1());
                        } else if (management.getGame() != null && management.getGame().getPlayer2() != null){    // game already started
                            out.println("Game already started!");
                            System.out.println("Game already started.");
                        }
                        break;
                    }
                    case "join": {
                        if(management.getGame() != null && management.getGame().getPlayer2() == null) {
                            Player player2 = new Player();

                            out.println("Introduce your name:");
                            request = in.readLine();

                            player2.setName(request);

                            out.println("Introduce your symbol:");
                            char symbol = (char) in.read();

                            player2.setSymbol(symbol);
                            management.getGame().setPlayer2(player2);

                            out.println("You are player 2 and your symbol is " + symbol + ".");
                            System.out.println("Player 2: " + player2.getName() + ", symbol: " + symbol);

                            management.getGame().joaca();
                            out.println("Game started!");
                            System.out.println("Game started!");
                            management.getGame().setTurn(management.getGame().getPlayer1());
                            break;
                        } else if (management.getGame() == null) {
                            out.println("No game is created!");
                            System.out.println("No game is created!");
                            break;
                        } else if (management.getGame() != null && management.getGame().getPlayer2() != null) {
                            out.println("The game already started!");
                            System.out.println("The game already started!");
                            break;
                        }
                    }
                    case "play": {
                        if (parts.length != 1) {
                            out.println("Invalid command syntax.");
                            break;
                        } else if (management.getGame() == null) {     // no game created
                            out.println("No game created.");
                            System.out.println("No game created.");
                            break;
                        } else if (management.getGame().getPlayer2() == null) {    // game created, waiting for player 2
                            out.println("Waiting for player 2 to join.");
                            System.out.println("Waiting for player 2 to join.");
                            break;
                        } else if (management.getGame().getTurn().equals(management.getGame().getPlayer2())) {   // not your turn
                            out.println("It is not your turn.");
                            System.out.println("It is not your turn.");
                            break;
                        } else {    // valid move
                            int row = Integer.parseInt(parts[1]);
                            int col = Integer.parseInt(parts[2]);
                            Player player = management.getGame().getTurn();

                            if (management.getGame().getBoard().cellAvailable(row, col)) {
                                management.getGame().getBoard().placePiece(row, col, player.getSymbol());
                                out.println("Move accepted.");

                                // Verifică dacă există un câștigător
                                char castigator = management.getGame().getBoard().checkWinner();
                                if (castigator != '-') {
                                    out.println("Game over. " + player.getName() + " wins!");
                                    System.out.println("Game over. " + player.getName() + " wins!");
                                    management.setGame(null);
                                } else if (management.getGame().esteTerminat()) {
                                    out.println("Game over. It's a tie!");
                                    System.out.println("Game over. It's a tie!");
                                    management.setGame(null);
                                } else {
                                    if (management.getGame().getTurn().equals(management.getGame().getPlayer1())) {
                                        management.getGame().setTurn(management.getGame().getPlayer2());
                                    } else {
                                        management.getGame().setTurn(management.getGame().getPlayer1());
                                    }
                                }
                            } else {
                                out.println("Invalid move. Try again.");
                                System.out.println("Invalid move. Try again.");
                            }
                        }
                    }
                    case "stop": {
                        if (parts.length != 1) {
                            out.println("Invalid command syntax.");
                            break;
                        }
                        out.println("Client stopped.");
                        gameServer.stopServer();
                        break;
                    }
                    case "exit":
                        System.out.println("Client stopped.");
                    default:
                        out.println("Invalid command: [" + command1 + "]");
                        break;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public PrintWriter getOut() {
        return out;
    }
}