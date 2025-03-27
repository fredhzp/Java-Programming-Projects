package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;
import javax.swing.border.LineBorder;

/**
 * Represents the main game window and game logic for the Pebble Game.
 * It handles the graphical user interface, player interaction, and game flow.
 */
public class PebbleGame extends JFrame implements ActionListener {
    private final GameBoard gameBoard;
    private final GameLogic gameLogic;
    private final Player player;
    private JButton[][] buttons;

    /**
     * Constructs a PebbleGame with the specified board size.
     * Initializes the game board, game logic, and sets up the GUI components.
     *
     * @param boardSize the size of the game board
     */
    public PebbleGame(int boardSize) {
        this.gameBoard = new GameBoard(boardSize);
        this.gameLogic = new GameLogic(gameBoard, boardSize);
        this.player = new Player();

        setTitle("Pebble Game");
        setSize(600, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(boardSize, boardSize));

        buttons = new JButton[boardSize][boardSize];
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                buttons[i][j] = new JButton();
                buttons[i][j].setFont(new Font("Arial", Font.BOLD, 20));
                buttons[i][j].addActionListener(this);
                add(buttons[i][j]);
                updateButton(i, j);
            }
        }
    }

    /**
     * Updates the button at the specified row and column to reflect the state of the game board.
     *
     * @param row the row of the button
     * @param col the column of the button
     */
    private void updateButton(int row, int col) {
        if (row >= 0 && row < gameBoard.getBoard().length && col >= 0 && col < gameBoard.getBoard()[0].length) {
            JButton button = buttons[row][col];
            button.setBorder(new LineBorder(Color.GRAY));

            String color = gameBoard.getBoard()[row][col];
            if (color == null) {
                button.setText("");
                button.setBackground(Color.LIGHT_GRAY);
            } else if ("White".equals(color)) {
                button.setText("W");
                button.setBackground(Color.WHITE);
                button.setForeground(Color.BLACK);
            } else if ("Black".equals(color)) {
                button.setText("B");
                button.setBackground(Color.BLACK);
                button.setForeground(Color.WHITE);
            }
            button.setOpaque(true);
        }
    }

    /**
     * Checks if the pebble at the specified row and column belongs to the current player.
     *
     * @param row the row of the pebble
     * @param col the column of the pebble
     * @return true if the pebble belongs to the current player, false otherwise
     */
    boolean isValidMove(int row, int col) {
        return gameBoard.getBoard()[row][col] != null && gameBoard.getBoard()[row][col].equals(player.getCurrentPlayer());
    }

    /**
     * Processes the move of a pebble by recursively moving it in the specified direction.
     * If the new position is out of bounds, the pebble is removed.
     * If the position is occupied, it recursively tries to move further.
     *
     * @param row    the current row of the pebble
     * @param col    the current column of the pebble
     * @param rowDir the row direction to move (e.g., -1 for up, 1 for down)
     * @param colDir the column direction to move (e.g., -1 for left, 1 for right)
     */
    void processMove(int row, int col, int rowDir, int colDir) {
        int newRow = row + rowDir;
        int newCol = col + colDir;

        // Check if the new position is out of bounds
        if (newRow < 0 || newRow >= gameBoard.getBoard().length || newCol < 0 || newCol >= gameBoard.getBoard()[0].length) {
            // Handle the case where the move is out of bounds (remove the pebble)
            gameBoard.decrementCount(gameBoard.getBoard()[row][col]);
            gameBoard.getBoard()[row][col] = null;
        } else if (gameBoard.getBoard()[newRow][newCol] == null) {
            // If the new spot is empty, move the pebble
            gameBoard.movePebble(row, col, newRow, newCol);
        } else {
            // If the spot is occupied, recursively process further
            processMove(newRow, newCol, rowDir, colDir);
            if (newRow >= 0 && newRow < gameBoard.getBoard().length && newCol >= 0 && newCol < gameBoard.getBoard()[0].length
                    && gameBoard.getBoard()[newRow][newCol] == null) {
                gameBoard.movePebble(row, col, newRow, newCol);
            }
        }
        // Update the UI for the current and new positions
        if (row >= 0 && row < gameBoard.getBoard().length && col >= 0 && col < gameBoard.getBoard()[0].length) {
            updateButton(row, col);
        }
        if (newRow >= 0 && newRow < gameBoard.getBoard().length && newCol >= 0 && newCol < gameBoard.getBoard()[0].length) {
            updateButton(newRow, newCol);
        }
    }

    /**
     * Resets the game by re-initializing the board, resetting the turn count,
     * switching the player, and updating the UI to reflect the initial state of the game.
     */
    public void resetGame() {
        gameBoard.initializeBoard();
        gameLogic.resetTurnCount();

        for (int i = 0; i < gameBoard.getBoard().length; i++) {
            for (int j = 0; j < gameBoard.getBoard()[i].length; j++) {
                updateButton(i, j);
            }
        }
    }

    /**
     * Handles the actions performed by the player. This method is triggered when a player clicks on a pebble.
     * It checks if the move is valid, allows the player to choose a direction, processes the move, and checks if the game ends.
     *
     * @param e the ActionEvent triggered by the player clicking a button
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < gameBoard.getBoard().length; i++) {
            for (int j = 0; j < gameBoard.getBoard()[i].length; j++) {
                if (e.getSource() == buttons[i][j]) {
                    if (isValidMove(i, j)) {
                        int choice = JOptionPane.showOptionDialog(
                                this, "Move pebble:", "Choose Direction",
                                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                                null, new String[]{"Up", "Down", "Left", "Right"}, "Up");

                        int rowDir = 0, colDir = 0;
                        if (choice == 0) rowDir = -1;
                        else if (choice == 1) rowDir = 1;
                        else if (choice == 2) colDir = -1;
                        else if (choice == 3) colDir = 1;

                        processMove(i, j, rowDir, colDir);
                        gameLogic.incrementTurn();

                        if (gameLogic.isGameEnd()) {
                            String winner = gameLogic.getWinner();
                            if (!Objects.equals(winner, "Draw")) {
                                JOptionPane.showMessageDialog(this, winner + " wins!");
                            } else {
                                JOptionPane.showMessageDialog(this, "It's a draw!");
                            }
                            resetGame();
                        } else {
                            player.switchPlayer();
                        }
                    } else {
                        String currentPlayer = player.getCurrentPlayer();
                        JOptionPane.showMessageDialog(this, currentPlayer + ", you must select your own pebble.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    break;
                }
            }
        }
    }

    /**
     * Returns the game board used in this game (For Testing).
     *
     * @return the GameBoard instance
     */
    public GameBoard getGameBoard() {
        return gameBoard;
    }
}
