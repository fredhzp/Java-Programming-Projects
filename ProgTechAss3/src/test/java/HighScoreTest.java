import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.sql.*;
import org.assignment.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the HighScore class.
 */
public class HighScoreTest {

    private static String DB_URL = "jdbc:mysql://localhost:3306/testdb";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";

    private Connection conn;

    /**
     * Sets up the test database connection before each test.
     */
    @BeforeEach
    void setUp() throws SQLException {
        conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

        try (Statement stmt = conn.createStatement()) {
            stmt.execute("DELETE FROM highscores");
        }
    }

    /**
     * Tests updating score for a new player.
     */
    @Test
    void testUpdateScore_NewPlayer() {
        HighScore.updateScore("Player 1");

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT score FROM highscores WHERE player_name = 'Player 1'")) {

            assertTrue(rs.next());
            assertEquals(1, rs.getInt("score"));
        } catch (SQLException e) {
            fail("Database query failed: " + e.getMessage());
        }
    }

    /**
     * Tests updating score for an existing player.
     */
    @Test
    void testUpdateScore_ExistingPlayer() {
        HighScore.updateScore("Player 2");
        HighScore.updateScore("Player 2");

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT score FROM highscores WHERE player_name = 'Player 2'")) {

            assertTrue(rs.next());
            assertEquals(2, rs.getInt("score"));
        } catch (SQLException e) {
            fail("Database query failed: " + e.getMessage());
        }
    }

    /**
     * Tests retrieving high scores from the database.
     */
    @Test
    void testGetHighScores() {
        HighScore.updateScore("Player 3");
        HighScore.updateScore("Player 4");
        HighScore.updateScore("Player 5");
        HighScore.updateScore("Player 6");
        HighScore.updateScore("Player 7");

        String scores = HighScore.getHighScores();

        assertNotNull(scores);
        assertTrue(scores.contains("Player 3"));
        assertTrue(scores.contains("Player 4"));
        assertTrue(scores.contains("Player 5"));
        assertTrue(scores.contains("Player 6"));
        assertTrue(scores.contains("Player 7"));
        assertTrue(scores.indexOf("Player 3") < scores.indexOf("Player 4"));
    }

    /**
     * Tests retrieving high scores when the database is empty.
     */
    @Test
    void testGetHighScores_EmptyDatabase() {
        try (Statement stmt = conn.createStatement()) {
            stmt.execute("DELETE FROM highscores");
        } catch (SQLException e) {
            fail("Failed to clear high scores table: " + e.getMessage());
        }

        String scores = HighScore.getHighScores();
        assertEquals("", scores);
    }

    /**
     * Tests that the high scores are limited to the top 10 entries.
     */
    @Test
    void testHighScoresAreLimitedToTop10() {
        for (int i = 0; i < 15; i++) {
            HighScore.updateScore("Player " + (i + 1));
        }

        String scores = HighScore.getHighScores();

        String[] scoreLines = scores.split("\n");
        assertTrue(scoreLines.length <= 10);
    }
}
