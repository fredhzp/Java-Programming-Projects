package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit test class for the GameBoard class.
 * Tests the functionality of initializing the game board and verifying initial counts of pebbles.
 */
class GameBoardTest {

    private GameBoard gameBoard;

    /**
     * Sets up a new GameBoard instance before each test.
     * Initializes a 3x3 board for testing.
     */
    @BeforeEach
    void setUp() {
        gameBoard = new GameBoard(3); // Testing with a 3x3 board
    }

    /**
     * Tests the initializeBoard method for a 3x3 board.
     * Verifies that the initial counts for white and black pebbles are correct.
     */
    @Test
    void testInitializeBoard3x3() {
        gameBoard.initializeBoard();
        assertEquals(3, gameBoard.getWhiteCount(), "Expected 3 white pebbles on a 3x3 board");
        assertEquals(3, gameBoard.getBlackCount(), "Expected 3 black pebbles on a 3x3 board");
    }

    /**
     * Tests the initializeBoard method for a 4x4 board.
     * Verifies that the initial counts for white and black pebbles are correct.
     */
    @Test
    void testInitializeBoard4x4() {
        gameBoard = new GameBoard(4); // Testing with a 4x4 board
        gameBoard.initializeBoard();
        assertEquals(4, gameBoard.getWhiteCount(), "Expected 4 white pebbles on a 4x4 board");
        assertEquals(4, gameBoard.getBlackCount(), "Expected 4 black pebbles on a 4x4 board");
    }

    /**
     * Tests the initializeBoard method for a 6x6 board.
     * Verifies that the initial counts for white and black pebbles are correct.
     */
    @Test
    void testInitializeBoard6x6() {
        gameBoard = new GameBoard(6); // Testing with a 6x6 board
        gameBoard.initializeBoard();
        assertEquals(6, gameBoard.getWhiteCount(), "Expected 6 white pebbles on a 6x6 board");
        assertEquals(6, gameBoard.getBlackCount(), "Expected 6 black pebbles on a 6x6 board");
    }

    /**
     * Tests the turn limit for a 3x3 board.
     * Verifies that the turn limit is correctly set to 15 for a 3x3 board.
     */
    @Test
    void testTurnLimit3x3() {
        gameBoard = new GameBoard(3); // Testing with a 3x3 board
        GameLogic gameLogic = new GameLogic(gameBoard, 3);
        for (int i = 0; i < 30; i++) {
            gameLogic.incrementTurn();
        }
        assertTrue(gameLogic.isGameEnd(), "Game should end when the turn count reaches the limit.");
    }

    /**
     * Tests the turn limit for a 4x4 board.
     * Verifies that the turn limit is correctly set to 20 for a 4x4 board.
     */
    @Test
    void testTurnLimit4x4() {
        gameBoard = new GameBoard(4); // Testing with a 4x4 board
        GameLogic gameLogic = new GameLogic(gameBoard, 4);
        for (int i = 0; i < 40; i++) {
            gameLogic.incrementTurn();
        }
        assertTrue(gameLogic.isGameEnd(), "Game should end when the turn count reaches the limit.");
    }

    /**
     * Tests the turn limit for a 6x6 board.
     * Verifies that the turn limit is correctly set to 30 for a 6x6 board.
     */
    @Test
    void testTurnLimit6x6() {
        gameBoard = new GameBoard(6); // Testing with a 6x6 board
        GameLogic gameLogic = new GameLogic(gameBoard, 6);
        for (int i = 0; i < 60; i++) {
            gameLogic.incrementTurn();
        }
        assertTrue(gameLogic.isGameEnd(), "Game should end when the turn count reaches the limit.");
    }
}

