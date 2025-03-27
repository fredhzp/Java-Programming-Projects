package org.assignment;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the Player class.
 */
class PlayerTest {

    private Player player;
    private ArrayList<Point> trace;

    /**
     * Sets up a new Player instance and a trace list before each test.
     */
    @BeforeEach
    void setUp() {
        player = new Player(100, 100, Color.RED, KeyEvent.VK_W, KeyEvent.VK_S, KeyEvent.VK_A, KeyEvent.VK_D, null);
        player.setName("Player");
        trace = new ArrayList<>();
    }

    /**
     * Tests the initial position of the player.
     */
    @Test
    void testInitialPosition() {
        assertEquals(100, player.getX(), "Player X position should be 100.");
        assertEquals(100, player.getY(), "Player Y position should be 100.");
    }

    /**
     * Tests the player's upward movement.
     */
    @Test
    void testMoveUp() {
        player.handleKeyPress(KeyEvent.VK_W);
        player.move();
        assertEquals(100, player.getX(), "Player X position should remain unchanged.");
        assertEquals(90, player.getY(), "Player should move up by 10 units.");
    }

    /**
     * Tests the player's downward movement.
     */
    @Test
    void testMoveDown() {
        player.handleKeyPress(KeyEvent.VK_S);
        player.move();
        assertEquals(100, player.getX(), "Player X position should remain unchanged.");
        assertEquals(110, player.getY(), "Player should move down by 10 units.");
    }

    /**
     * Tests the player's leftward movement.
     */
    @Test
    void testMoveLeft() {
        player.handleKeyPress(KeyEvent.VK_A);
        player.move();
        assertEquals(110, player.getX(), "Player should move left by 10 units.");
        assertEquals(100, player.getY(), "Player Y position should remain unchanged.");
    }

    /**
     * Tests the player's rightward movement.
     */
    @Test
    void testMoveRight() {
        player.handleKeyPress(KeyEvent.VK_D);
        player.move();
        assertEquals(110, player.getX(), "Player should move right by 10 units.");
        assertEquals(100, player.getY(), "Player Y position should remain unchanged.");
    }

    /**
     * Tests if the player correctly detects going out of bounds to the left.
     */
    @Test
    void testOutOfBoundsLeft() {
        player = new Player(0, 100, Color.RED, KeyEvent.VK_W, KeyEvent.VK_S, KeyEvent.VK_A, KeyEvent.VK_D, null);
        player.handleKeyPress(KeyEvent.VK_A);
        for (int i = 0; i < 800; i++) {
            player.move();
        }
        assertTrue(player.isOutOfBounds(800, 600), "Player should be out of bounds when moving left.");
    }

    /**
     * Tests if the player correctly detects going out of bounds to the right.
     */
    @Test
    void testOutOfBoundsRight() {
        player = new Player(799, 100, Color.RED, KeyEvent.VK_W, KeyEvent.VK_S, KeyEvent.VK_A, KeyEvent.VK_D, "bike3.png");
        player.handleKeyPress(KeyEvent.VK_D);
        for (int i = 0; i < 800; i++) {
            player.move();
        }
        assertTrue(player.isOutOfBounds(800, 600), "Player should be out of bounds when moving right.");
    }

    /**
     * Tests if the player correctly detects going out of bounds to the top.
     */
    @Test
    void testOutOfBoundsTop() {
        player = new Player(100, 0, Color.RED, KeyEvent.VK_W, KeyEvent.VK_S, KeyEvent.VK_A, KeyEvent.VK_D, "bike3.png");
        player.handleKeyPress(KeyEvent.VK_W);
        for (int i = 0; i < 800; i++) {
            player.move();
        }
        assertTrue(player.isOutOfBounds(800, 600), "Player should be out of bounds when moving up.");
    }

    /**
     * Tests if the player correctly detects going out of bounds to the bottom.
     */
    @Test
    void testOutOfBoundsBottom() {
        player = new Player(100, 599, Color.RED, KeyEvent.VK_W, KeyEvent.VK_S, KeyEvent.VK_A, KeyEvent.VK_D, "bike3.png");
        player.handleKeyPress(KeyEvent.VK_S);
        for (int i = 0; i < 800; i++) {
            player.move();
        }
        assertTrue(player.isOutOfBounds(800, 600), "Player should be out of bounds when moving down.");
    }

    /**
     * Tests the player's direction change while moving.
     */
    @Test
    void testChangeDirectionWhileMoving() {
        player.handleKeyPress(KeyEvent.VK_D);
        player.move();
        assertEquals(110, player.getX(), "Player should move right initially.");
        player.handleKeyPress(KeyEvent.VK_A);
        player.move();
        assertEquals(120, player.getX(), "Player should continue moving right.");
    }

    /**
     * Tests collision detection with a trace.
     */
    @Test
    void testCollisionWithTrace() {
        trace.add(new Point(100, 90));
        trace.add(new Point(100, 110));

        player.handleKeyPress(KeyEvent.VK_W);
        player.move();

        assertTrue(player.collidesWith(trace), "Player should detect collision with the trace.");
    }
}
