package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for PebbleGame functionality.
 */
public class PebbleGameTest {
    private PebbleGame pebbleGame;
    private GameBoard gameBoard;
    private GameLogic gameLogic;
    private Player player;

    @BeforeEach
    public void setUp() {
        int boardSize = 3; // Use a 5x5 board for testing
        pebbleGame = new PebbleGame(boardSize);
        gameBoard = pebbleGame.getGameBoard();
        gameLogic = new GameLogic(gameBoard, boardSize);
        player = new Player();
    }

    /**
     * Test that the game board initializes with the correct setup.
     */
    @Test
    public void testGameBoardInitialization() {
        assertNotNull(gameBoard.getBoard(), "Game board should not be null after initialization.");
        assertEquals(3, gameBoard.getBoard().length, "Game board should have 5 rows.");
        assertEquals(3, gameBoard.getBoard()[0].length, "Game board should have 5 columns.");
    }

    /**
     * Test that the turn count increments correctly after a move.
     */
    @Test
    public void testTurnIncrement() {
        int initialTurnCount = gameLogic.getTurnCount();
        gameLogic.incrementTurn();
        assertEquals(initialTurnCount + 1, gameLogic.getTurnCount(), "Turn count should increment by 1 after each move.");
    }

    /**
     * Test that the game resets correctly, with all components in their initial states.
     */
    @Test
    public void testGameReset() {
        gameLogic.incrementTurn();
        gameBoard.setPebble(0, 0, "White");
        pebbleGame.resetGame();

        assertEquals(1, gameLogic.getTurnCount(), "Turn count should be reset to 1.");
    }

    /**
     * Test that the player switch happens correctly after each move.
     */
    @Test
    public void testPlayerSwitch() {
        String initialPlayer = player.getCurrentPlayer();
        player.switchPlayer();
        String switchedPlayer = player.getCurrentPlayer();

        assertNotEquals(initialPlayer, switchedPlayer, "Player should switch after each turn.");
        player.switchPlayer();
        assertEquals(initialPlayer, player.getCurrentPlayer(), "Player should switch back after two turns.");
    }


    /**
     * Test that the game ends when the turn count reaches the maximum limit.
     */
    @Test
    public void testGameEndByTurnLimit() {
        for (int i = 0; i < 30; i++) {
            gameLogic.incrementTurn();
        }
        assertTrue(gameLogic.isGameEnd(), "Game should end when the turn count reaches the limit.");
    }

    /**
     * Test that a player can only move their own pebble.
     */
    @Test
    public void testInvalidMove() {
        gameBoard.setPebble(0, 0, "Black");
        player.setCurrentPlayer("White");

        boolean isValid = pebbleGame.isValidMove(0, 0);
        assertFalse(isValid, "White player should not be able to move Black's pebble.");
    }

    /**
     * Test that moving a pebble updates the board state correctly.
     */
    @Test
    public void testMovePebble() {
        gameBoard.setPebble(2, 2, "White");
        player.setCurrentPlayer("White");

        pebbleGame.processMove(2, 2, -1, 0);  // Move up
        assertNull(gameBoard.getBoard()[2][2], "Original position should be empty after moving pebble.");
        assertEquals("White", gameBoard.getBoard()[1][2], "New position should contain the moved pebble.");
    }
}
