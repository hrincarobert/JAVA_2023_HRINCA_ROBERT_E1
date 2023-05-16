package org.example;

public class Management {
    private Game game;

    public Management() {}

    public Management(Game game) {
        this.game = game;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public void setTurn(Player player) {
        if (game == null) {
            throw new IllegalStateException("Game not initialized.");
        }

        if (player == null) {
            throw new IllegalArgumentException("Player cannot be null.");
        }

        if (player != game.getPlayer1() && player != game.getPlayer2()) {
            throw new IllegalArgumentException("Invalid player.");
        }

        game.setTurn(player);
    }

}
