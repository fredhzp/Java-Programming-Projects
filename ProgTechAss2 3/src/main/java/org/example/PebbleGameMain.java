package org.example;

import javax.swing.*;

/**
 * The main entry point for the Pebble Game application.
 * It allows the user to select the board size and then launches the game window.
 */
public class PebbleGameMain {

    /**
     * The main method that starts the PebbleGame application.
     * It presents the user with an option to select the board size and then initializes the game.
     *
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {

            String[] sizes = {"3x3", "4x4", "6x6"};
            int choice = JOptionPane.showOptionDialog(
                    null, "Select board size:", "Pebble Game",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                    null, sizes, sizes[0]);


            int boardSize = switch (choice) {
                case 0 -> 3;
                case 1 -> 4;
                case 2 -> 6;
                default -> 3;
            };

            PebbleGame game = new PebbleGame(boardSize);
            game.setVisible(true);
        });
    }
}
