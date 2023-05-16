package org.example.clientApplication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class GameClient {
    private final Socket socket;
    private final BufferedReader in;
    private final PrintWriter out;

    public GameClient(String host, int port) throws IOException {
        socket = new Socket(host, port);

        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);
    }

    public void run() throws IOException {
        String response = in.readLine();
        System.out.println(response);   // response = "Connected to the server!"

        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            String response1 = in.readLine();   // response = "Enter a command:"
            System.out.println("enter: " + response1);

            String command = console.readLine();    // client write the command
            out.println(command);   // command send to the server

            String response2;

            switch (command) {
                case "exit" : {
                    response2 = in.readLine();     // the response = "Client stopped."
                    System.out.println(response2);

                    in.close();
                    out.close();
                    socket.close();
                    break;
                }
                case "create" : {
                    response2 = in.readLine();     // the response = "Introduce your name"
                    System.out.println("create:" + response2);

                    if(response2.equals("Invalid command syntax.") || response2.equals("Game is already created! Waiting for player 2 to join!") ||
                    response2.equals("Game already started."))
                        break;
                    else {
                        command = console.readLine();  // client write the name field
                        out.println(command);       // name field send to the server

                        response2 = in.readLine();     // the response = "Introduce your symbol:"
                        System.out.println(response2);

                        command = console.readLine();  // client write the name symbol
                        out.println(command);       // symbol field send to the server

                        response2 = in.readLine();     // the response
                        System.out.println(response2);
                        break;
                    }
                }
                case "play": {
                    response2 = in.readLine();     // the response
                    System.out.println("play:" + response2);

                    if(response2.equals("No game created.") || response2.equals("Waiting for player 2 to join.\"") ||
                        response2.equals("It is not your turn."))
                            break;
                    else { // valid command "play"
                        response2 = in.readLine();     // the response
                        System.out.println(response2);

                        break;
                    }
                }
                case "join": {
                    response2 = in.readLine();     // the response = "Introduce your name"
                    System.out.println("join_1: " + response2);

                    if(response2.equals("The game already started!") || response2.equals("No game is created!"))
                        break;
                    else {
                        command = console.readLine();  // client write the name field
                        out.println(command);       // name field send to the server

                        response2 = in.readLine();     // the response = "Introduce your symbol:"
                        System.out.println("join_2: " + response2);

                        command = console.readLine();  // client write the name symbol
                        out.println(command);       // symbol field send to the server

                        response2 = in.readLine();     // the response
                        System.out.println("join_3: " + response2);

                        response2 = in.readLine();     // the response = "Game started!"
                        System.out.println("join_4: " + response2);
                        break;
                    }
                }
                default:
                    break;
            }
        }
    }

    public static void main(String[] args) {
        String host = "127.0.0.1";
        int port = 5001;

        if (args.length == 2) {
            host = args[0];
            port = Integer.parseInt(args[1]);
        }

        try {
            GameClient client = new GameClient(host, port);
            client.run();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
