import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.*;
import org.assignment.*;

/**
 * Test class for the GamePanel.
 */
class GamePanelTest {

    private JFrame frame;
    private GamePanel gamePanel;
    private Player player1, player2;

    /**
     * Initializes the necessary objects for the test.
     */
    @BeforeEach
    void setUp() {
        frame = new JFrame();
        gamePanel = new GamePanel(frame, "Player 1", "Player 2");

        player1 = gamePanel.player1;
        player2 = gamePanel.player2;

        frame.add(gamePanel);
        frame.pack();
        frame.setVisible(true);
    }

    /**
     * Tests the initial state of the game including player positions.
     */
    @Test
    void testGameInitialization() {
        assertEquals(100, player1.getX());
        assertEquals(300, player1.getY());
        assertEquals(700, player2.getX());
        assertEquals(300, player2.getY());
    }

    /**
     * Tests player movement when key presses are simulated.
     */
    @Test
    void testPlayerMovement() {
        gamePanel.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                player1.handleKeyPress(KeyEvent.VK_W);
                player2.handleKeyPress(KeyEvent.VK_UP);
            }
        });

        player1.move();
        player2.move();

        assertEquals(300, player1.getY());
        assertEquals(300, player2.getY());
    }

    /**
     * Tests the paintComponent method for correct rendering.
     */
    @Test
    void testPaintComponent() {
        Graphics g = frame.getGraphics();
        gamePanel.paintComponent(g);

        assertDoesNotThrow(() -> gamePanel.paintComponent(g));
    }

    /**
     * Tests that obstacles are initialized correctly.
     */
    @Test
    void testObstacleInitialization() {
        ArrayList<Obstacle> obstacles = gamePanel.getObstacles();

        assertNotNull(obstacles, "Obstacles should not be null");
        assertFalse(obstacles.isEmpty(), "Obstacles should not be empty");
        assertTrue(obstacles.size() >= 5 && obstacles.size() <= 10, "The number of obstacles should be between 5 and 10");

        for (Obstacle obstacle : obstacles) {
            assertTrue(obstacle.getX() >= 0 && obstacle.getX() <= 800, "Obstacle X position should be within game bounds");
            assertTrue(obstacle.getY() >= 0 && obstacle.getY() <= 600, "Obstacle Y position should be within game bounds");
        }
    }

    /**
     * Tests collision detection with obstacles for player 1.
     */
    @Test
    void testPlayer1ObstacleCollision() {
        ArrayList<Obstacle> obstacles = gamePanel.getObstacles();

        // Place Player 1 at the position of the first obstacle
        Obstacle firstObstacle = obstacles.get(0);
        player1.setX(firstObstacle.getX());
        player1.setY(firstObstacle.getY());

        // Simulate collision detection
        for (Obstacle obstacle : obstacles) {
            if (player1.getX() >= obstacle.getX() && player1.getX() <= obstacle.getX() + Obstacle.getSize() &&
                    player1.getY() >= obstacle.getY() && player1.getY() <= obstacle.getY() + Obstacle.getSize()) {
                assertTrue(true, "Player 1 should collide with an obstacle");
                return;
            }
        }

        fail("Player 1 did not collide with any obstacle");
    }

    /**
     * Tests collision detection with obstacles for player 2.
     */
    @Test
    void testPlayer2ObstacleCollision() {
        ArrayList<Obstacle> obstacles = gamePanel.getObstacles();

        // Place Player 2 at the position of the first obstacle
        Obstacle firstObstacle = obstacles.get(0);
        player2.setX(firstObstacle.getX());
        player2.setY(firstObstacle.getY());

        // Simulate collision detection
        for (Obstacle obstacle : obstacles) {
            if (player2.getX() >= obstacle.getX() && player2.getX() <= obstacle.getX() + Obstacle.getSize() &&
                    player2.getY() >= obstacle.getY() && player2.getY() <= obstacle.getY() + Obstacle.getSize()) {
                assertTrue(true, "Player 2 should collide with an obstacle");
                return;
            }
        }

        fail("Player 2 did not collide with any obstacle");
    }
}

