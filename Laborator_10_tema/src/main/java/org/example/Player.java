package org.example;

import java.util.Timer;
import java.util.TimerTask;


public class Player {
    private String name;
    private char symbol;
    private boolean timeExpired;

    public Player() {}

    public Player(String name, char symbol) {
        this.name = name;
        this.symbol = symbol;
        this.timeExpired = false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public char getSymbol() {
        return symbol;
    }

    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }

    public void setTimeExpired() {
        this.timeExpired = false;
    }

    public void modifyTimeExpired(boolean timeExpired) {
        this.timeExpired = timeExpired;
    }

    public boolean getTimeExpired() {
        return timeExpired;
    }

    public void startTimer(Player player) {
        TimerTask task = new TimerTask() {
            public void run() {
                if (!player.getTimeExpired()) {
                    System.out.println("Player " + player.getName() + " timed out!");
                    player.modifyTimeExpired(true);
                }
            }
        };

        Timer timer = new Timer();
        timer.schedule(task, 60 * 1000);
    }

}
