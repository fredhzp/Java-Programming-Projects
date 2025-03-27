package org.assignment;

import javax.swing.*;

/**
 * The Game class is the entry point for the Tron Light-Cycle Battle game.
 * It initializes the game window and displays the menu screen.
 */
public class Game {

    /**
     * The main method that launches the game by creating the JFrame,
     * setting up the game window, and displaying the MenuPanel.
     *
     * @param args Command line arguments (not used in this case).
     */
    public static void main(String[] args) {
        JFrame frame = new JFrame("Tron Light-Cycle Battle");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setResizable(false);

        MenuPanel menu = new MenuPanel(frame);
        frame.add(menu);

        frame.setVisible(true);
    }
}
