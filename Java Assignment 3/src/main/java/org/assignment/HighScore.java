package org.assignment;

import java.sql.*;

/**
 * HighScore is a utility class for interacting with a database to update and retrieve player high scores.
 */
public class HighScore {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/testdb";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";

    /**
     * Updates the score of a player in the database. If the player already exists, the score is incremented by 1.
     * If the player does not exist, a new record is created with an initial score of 1.
     *
     * @param playerName the name of the player whose score is to be updated
     */
    public static void updateScore(String playerName) {
        String insertOrUpdate = "INSERT INTO highscores (player_name, score)\n" +
                "VALUES (?, 1)\n" +
                "ON DUPLICATE KEY UPDATE score = score + 1;";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(insertOrUpdate)) {

            pstmt.setString(1, playerName);
            int rowsAffected = pstmt.executeUpdate();
            System.out.println("Rows affected: " + rowsAffected);  // Log the number of rows affected

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Retrieves the top 10 high scores from the database, ordered by score in descending order.
     *
     * @return a formatted string containing the top 10 high scores
     */
    public static String getHighScores() {
        StringBuilder scores = new StringBuilder();
        String query = "SELECT player_name, score \n" +
                "FROM highscores \n" +
                "ORDER BY score DESC \n" +
                "LIMIT 10;";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                scores.append(rs.getString("player_name"))
                        .append(": ")
                        .append(rs.getInt("score"))
                        .append(" Wins! \n");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return scores.toString();
    }
}
