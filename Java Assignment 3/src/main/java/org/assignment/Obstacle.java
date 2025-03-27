package org.assignment;

/**
 * The {@code Obstacle} class represents an obstacle in a game or simulation with a position on a 2D grid.
 * The obstacle has a fixed size defined by a constant {@code SIZE}.
 */
public class Obstacle {
    private int x, y;
    private static final int SIZE = 20;  // Size of the obstacle

    /**
     * Constructs an {@code Obstacle} object with the specified coordinates.
     *
     * @param x the x-coordinate of the obstacle
     * @param y the y-coordinate of the obstacle
     * @param obstacleSize the size of the obstacle (Note: currently not used as size is fixed)
     */
    public Obstacle(int x, int y, int obstacleSize) {
        this.x = x;
        this.y = y;
    }

    /**
     * Gets the x-coordinate of the obstacle.
     *
     * @return the x-coordinate of the obstacle
     */
    public int getX() {
        return x;
    }

    /**
     * Gets the y-coordinate of the obstacle.
     *
     * @return the y-coordinate of the obstacle
     */
    public int getY() {
        return y;
    }

    /**
     * Gets the fixed size of the obstacle.
     *
     * @return the fixed size of the obstacle
     */
    public static int getSize() {
        return SIZE;
    }
}
