package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit test class for the GameLogic class.
 * Tests the functionality of turn progression, game end conditions, and winner determination.
 */
class GameLogicTest {

    private GameBoard gameBoard;
    private GameLogic gameLogic;

    /**
     * Sets up a new GameBoard and GameLogic instance before each test.
     * Initializes a 3x3 board for testing.
     */
    @BeforeEach
    void setUp() {
        gameBoard = new GameBoard(3);  // Assuming a 3x3 board for testing
        gameLogic = new GameLogic(gameBoard, 3);
    }

    /**
     * Tests the incrementTurn method.
     * Verifies that the turn count is correctly incremented after each turn.
     */
    @Test
    void testIncrementTurn() {
        gameLogic.incrementTurn();
        gameLogic.incrementTurn();
        assertEquals(2, gameLogic.getTurnCount(), "Turn count should be incremented to 2");
    }

    /**
     * Tests the isGameEnd method by player having no pebbles.
     * Verifies that the game ends when one player has no pebbles left.
     */
    @Test
    void testIsGameEndByNoPebbles() {
        gameBoard.initializeBoard();
        while (gameBoard.getWhiteCount() > 0) {
            gameBoard.decrementCount("White");
        }
        assertTrue(gameLogic.isGameEnd(), "Game should end when one player's pebbles are all removed");
    }

    /**
     * Tests the getWinner method when white wins.
     * Verifies that the winner is correctly determined when white has more pebbles.
     */
    @Test
    void testGetWinnerWhiteWins() {
        gameBoard.initializeBoard();
        gameBoard.decrementCount("Black");
        assertEquals("White", gameLogic.getWinner(), "White should be the winner with more pebbles");
    }

    /**
     * Tests the getWinner method for a draw.
     * Verifies that the game results in a draw when both players have the same number of pebbles.
     */
    @Test
    void testGetWinnerDraw() {
        gameBoard.initializeBoard();
        assertEquals("Draw", gameLogic.getWinner(), "It should be a draw with equal number of pebbles");
    }

    /**
     * Tests the resetTurnCount method.
     * Verifies that the turn count is reset to 0 after calling resetTurnCount.
     */
    @Test
    void testResetTurnCount() {
        gameLogic.incrementTurn();
        gameLogic.resetTurnCount();
        assertEquals(0, gameLogic.getTurnCount(), "Turn count should be reset to 0");
    }
}
