package org.example;

/**
 * GameLogic class handles the rules and progression of the Pebble Game.
 * This includes turn tracking and determining when the game ends.
 */
public class GameLogic {
    private final GameBoard gameBoard;
    private final int boardSize;
    private int turnCount;
    private int turnLimit;

    /**
     * Constructor for the GameLogic class.
     * Initializes the game logic with the board size and the game board.
     *
     * @param gameBoard The game board object.
     * @param boardSize The size of the game board.
     */
    public GameLogic(GameBoard gameBoard, int boardSize) {
        this.gameBoard = gameBoard;
        this.boardSize = boardSize;
        this.turnCount = 0;
        setTurnLimit();  // Set turn limit based on the board size
    }

    /**
     * Sets the turn limit based on the board size.
     */
    private void setTurnLimit() {
        if (boardSize == 3) {
            turnLimit = 30;
        } else if (boardSize == 4) {
            turnLimit = 40;
        } else {
            turnLimit = 60;
        }
    }

    /**
     * Increments the turn counter after each turn.
     */
    public void incrementTurn() {
        turnCount++;
        //System.out.println(turnCount);
    }

    /**
     * Returns the current turn count.
     *
     * @return The number of turns that have been taken.
     */
    public int getTurnCount() {
        return turnCount;
    }

    /**
     * Checks whether the game has ended.
     * The game ends if the maximum turn limit is reached or if one player's pebbles are removed.
     *
     * @return True if the game has ended, otherwise false.
     */
    public boolean isGameEnd() {
        return turnCount >= turnLimit || gameBoard.getWhiteCount() == 0 || gameBoard.getBlackCount() == 0;
    }

    /**
     * Sets turn count back to 0, resetting the turn count.
     */
    public void resetTurnCount() {
        turnCount = 0;  // Reset turn count to 0
    }

    /**
     * Determines the winner of the game.
     *
     * @return The color of the winner ("White" or "Black"), or "Draw" if the game is tied.
     */
    public String getWinner() {
        if (gameBoard.getWhiteCount() > gameBoard.getBlackCount()) {
            return "White";
        } else if (gameBoard.getBlackCount() > gameBoard.getWhiteCount()) {
            return "Black";
        } else {
            return "Draw";
        }
    }
}
