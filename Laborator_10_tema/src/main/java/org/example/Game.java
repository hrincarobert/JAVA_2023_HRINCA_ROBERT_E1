package org.example;

import java.util.Scanner;

public class Game {
    private Board board;
    private Player player1;
    private Player player2;
    private Player turn;

    public Game() {
        board = new Board(8);
    }

    public Game(Player player1) {
        this.board = new Board(8);
        this.player1 = player1;
    }

    public boolean esteTerminat() {
        // Verifică dacă tabla este completă
        for (int i = 0; i < board.getDimension(); i++) {
            for (int j = 0; j < board.getDimension(); j++) {
                if (board.getElement(i, j) == '-') {
                    return false;
                }
            }
        }

        // Verifică dacă există un câștigător
        char castigator = board.checkWinner();
        if (castigator != '-') {
            System.out.println("Jocul s-a terminat! " + castigator + " a câștigat!");
            return true;
        }

        // Dacă nu există câștigător și tabla nu este completă, jocul nu este terminat
        return false;
    }

    public void joaca() {
        System.out.println("Începe jocul!");

        Player jucatorCurent = player1;
        while (!esteTerminat()) {
            System.out.println("Este rândul lui " + jucatorCurent.getName() + " (" + jucatorCurent.getSymbol() + ")");
            boolean mutareValida = false;
            while (!mutareValida) {
                System.out.println("Introdu poziția dorită (de exemplu: 0 1):");
                Scanner scanner = new Scanner(System.in);
                int x = scanner.nextInt();
                int y = scanner.nextInt();
                mutareValida = board.placePiece(x, y, jucatorCurent.getSymbol());
                if (!mutareValida) {
                    System.out.println("Poziție invalidă! Încearcă din nou.");
                }
            }
            if (jucatorCurent == player1) {
                jucatorCurent = player2;
            } else {
                jucatorCurent = player1;
            }
        }
    }

    public void getBoard(int row, int col, Player player) {

        boolean mutareValida = false;
        mutareValida = board.placePiece(row, col, player.getSymbol());
        if (mutareValida) {
            // Verifică dacă există un câștigător
            char castigator = board.checkWinner();
            if (castigator != '-') {
                System.out.println("Game over. " + player.getName() + " wins!");
            } else if (esteTerminat()) {
                System.out.println("Game over. It's a tie!");
            } else {
                if (turn == player1) {
                    turn = player2;
                } else {
                    turn = player1;
                }
                System.out.println("It's " + turn.getName() + "'s turn.");
            }
        }
        else
            System.out.println("Invalid move.");
    }


    public Player getPlayer1() {
        return player1;
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board table) {
        this.board = table;
    }

    public void setTurn(Player turn) {
        this.turn = turn;
    }

    public Player getTurn() {
        return turn;
    }
}
