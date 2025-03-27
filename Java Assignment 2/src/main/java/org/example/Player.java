package org.example;

/**
 * Player class represents the two players in the Pebble Game.
 * Tracks the current player's color and allows switching between players.
 */
public class Player {
    private String currentPlayer;

    /**
     * Constructor for the Player class.
     * Initializes the first player as "White".
     */
    public Player() {
        this.currentPlayer = "White";
    }

    /**
     * Returns the current player's color.
     *
     * @return The current player's color ("White" or "Black").
     */
    public String getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * Switches the current player to the other player.
     */
    public void switchPlayer() {
        if ("White".equals(currentPlayer)) {
            currentPlayer = "Black";
        } else {
            currentPlayer = "White";
        }
    }

    /**
     * Sets the current player to the specified color.
     *
     * @param color the color of the player, either "White" or "Black"
     */
    public void setCurrentPlayer(String color) {
        if ("White".equals(color) || "Black".equals(color)) {
            this.currentPlayer = color;
        }
    }
}
