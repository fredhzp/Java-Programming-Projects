package org.assignment;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Player represents a player in the game with properties for position, movement,
 * color, image, rotation, and key bindings for movement.
 */
public class Player {
    private int x, y;
    private int dx, dy;
    private Color color;
    private int up, down, left, right;
    private String name;

    private Image playerImage;
    private int rotationAngle;

    /**
     * Constructs a Player with the given initial position, color, movement keys, and image path.
     *
     * @param startX the starting x-coordinate of the player
     * @param startY the starting y-coordinate of the player
     * @param color the color of the player
     * @param up the key code for moving up
     * @param down the key code for moving down
     * @param left the key code for moving left
     * @param right the key code for moving right
     * @param imagePath the path to the player's image
     */
    public Player(int startX, int startY, Color color, int up, int down, int left, int right, String imagePath) {
        this.x = startX;
        this.y = startY;
        this.color = color;
        this.up = up;
        this.down = down;
        this.left = left;
        this.right = right;
        this.dx = 10;
        this.dy = 0;

        if (imagePath != null)
            this.playerImage = new ImageIcon(getClass().getResource(imagePath)).getImage().getScaledInstance(20, 45, Image.SCALE_SMOOTH);
        else
            System.out.println("Player image is null");
        this.rotationAngle = 0;
    }

    /**
     * Handles the key press to update the player's movement direction.
     *
     * @param keyCode the key code of the pressed key
     */
    public void handleKeyPress(int keyCode) {
        if (keyCode == up && dy == 0) {
            dx = 0; dy = -10;
            rotationAngle = 0;
        }
        if (keyCode == down && dy == 0) {
            dx = 0; dy = 10;
            rotationAngle = 180;
        }
        if (keyCode == left && dx == 0) {
            dx = -10; dy = 0;
            rotationAngle = 270;
        }
        if (keyCode == right && dx == 0) {
            dx = 10; dy = 0;
            rotationAngle = 90;
        }
    }

    /**
     * Moves the player based on its current movement direction.
     */
    public void move() {
        x += dx;
        y += dy;
    }

    /**
     * Checks if the player collides with any point in the given trace.
     *
     * @param trace the list of points representing the trace
     * @return true if the player collides with any point, false otherwise
     */
    public boolean collidesWith(ArrayList<Point> trace) {
        for (int i = 0; i < trace.size() - 1; i++) {
            if (trace.get(i).equals(new Point(x, y))) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if the player is out of bounds of the game area.
     *
     * @param width the width of the game area
     * @param height the height of the game area
     * @return true if the player is out of bounds, false otherwise
     */
    public boolean isOutOfBounds(int width, int height) {
        return x < 0 || x >= width || y < 0 || y >= height;
    }

    /**
     * Returns the current rotation angle of the player's image.
     *
     * @return the rotation angle in degrees
     */
    public int getRotationAngle() {
        return this.rotationAngle;
    }

    /**
     * Sets the rotation angle of the player's image.
     *
     * @param angle the rotation angle in degrees
     */
    public void setRotationAngle(int angle) {
        this.rotationAngle = angle;
    }

    /**
     * Returns the player's image.
     *
     * @return the image of the player
     */
    public Image getPlayerImage() {
        return this.playerImage;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getDx() {
        return dx;
    }

    public void setDx(int dx) {
        this.dx = dx;
    }

    public int getDy() {
        return dy;
    }

    public void setDy(int dy) {
        this.dy = dy;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getUp() {
        return up;
    }

    public void setUp(int up) {
        this.up = up;
    }

    public int getDown() {
        return down;
    }

    public void setDown(int down) {
        this.down = down;
    }

    public int getLeft() {
        return left;
    }

    public void setLeft(int left) {
        this.left = left;
    }

    public int getRight() {
        return right;
    }

    public void setRight(int right) {
        this.right = right;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
