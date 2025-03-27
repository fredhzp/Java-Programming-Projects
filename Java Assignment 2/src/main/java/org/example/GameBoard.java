package org.example;

import java.util.Random;

/**
 * Represents the game board for the Pebble Game.
 * Handles the initialization of the board, placement of pebbles, and movement of pebbles.
 */
public class GameBoard {
    private final int boardSize;
    private String[][] board;
    private int whiteCount;
    private int blackCount;

    /**
     * Constructs a GameBoard with the specified size.
     * Initializes the board and places pebbles for both players.
     *
     * @param boardSize the size of the game board
     */
    public GameBoard(int boardSize) {
        this.boardSize = boardSize;
        this.board = new String[boardSize][boardSize];
        initializeBoard();
    }

    /**
     * Gets the current game board.
     *
     * @return the game board
     */
    public String[][] getBoard() {
        return board;
    }

    /**
     * Gets the count of white pebbles on the board.
     *
     * @return the number of white pebbles
     */
    public int getWhiteCount() {
        return whiteCount;
    }

    /**
     * Gets the count of black pebbles on the board.
     *
     * @return the number of black pebbles
     */
    public int getBlackCount() {
        return blackCount;
    }

    /**
     * Decreases the count of the specified color's pebbles.
     *
     * @param color the color of the pebble to decrement ("White" or "Black")
     */
    public void decrementCount(String color) {
        if ("White".equals(color)) whiteCount--;
        else if ("Black".equals(color)) blackCount--;
    }

    /**
     * Initializes the game board by setting all tiles to null and placing pebbles for both players.
     * The board is populated with an equal number of white and black pebbles.
     */
    public void initializeBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = null;
            }
        }
        Random random = new Random();
        whiteCount = boardSize;
        blackCount = boardSize;

        placePebbles("White", whiteCount, random);
        placePebbles("Black", blackCount, random);
    }

    /**
     * Places the specified number of pebbles of a given color on the board randomly.
     *
     * @param color the color of the pebbles ("White" or "Black")
     * @param count the number of pebbles to place
     * @param random the Random object used to generate random positions
     */
    private void placePebbles(String color, int count, Random random) {
        int placed = 0;
        while (placed < count) {
            int row = random.nextInt(boardSize);
            int col = random.nextInt(boardSize);
            if (board[row][col] == null) {
                board[row][col] = color;
                placed++;
            }
        }
    }

    /**
     * Moves a pebble from the specified position to a new position on the board.
     *
     * @param row the row of the current pebble position
     * @param col the column of the current pebble position
     * @param newRow the row of the new position
     * @param newCol the column of the new position
     */
    public void movePebble(int row, int col, int newRow, int newCol) {
        board[newRow][newCol] = board[row][col];
        board[row][col] = null;
    }

    /**
     * Sets the pebble color at the specified position on the board.
     *
     * @param row   the row index of the board
     * @param col   the column index of the board
     * @param color the color of the pebble, either "White" or "Black"
     */
    public void setPebble(int row, int col, String color) {
        if (row >= 0 && row < board.length && col >= 0 && col < board[0].length) {
            String currentColor = board[row][col];
            board[row][col] = color;

            // Update count based on pebble color change
            if ("White".equals(color)) {
                if (!"White".equals(currentColor)) {
                    whiteCount++;
                }
                if ("Black".equals(currentColor)) {
                    blackCount--;
                }
            } else if ("Black".equals(color)) {
                if (!"Black".equals(currentColor)) {
                    blackCount++;
                }
                if ("White".equals(currentColor)) {
                    whiteCount--;
                }
            } else if (color == null) {
                // Handle null (empty) case for removing pebbles
                if ("White".equals(currentColor)) {
                    whiteCount--;
                } else if ("Black".equals(currentColor)) {
                    blackCount--;
                }
            }
        }
    }

}
