package org.assignment;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * GamePanel is responsible for handling the gameplay, including player movement, collision detection,
 * obstacle generation, and rendering the game screen.
 */
public class GamePanel extends JPanel implements ActionListener {
    private Timer timer;
    public Player player1;
    public Player player2;
    private ArrayList<Point> trace1, trace2;
    private ArrayList<Obstacle> obstacles;
    private Image backgroundImage;

    /**
     * Constructs the game panel with two players, their movement controls, and obstacle generation.
     *
     * @param frame the JFrame for the game
     * @param player1Name the name of player 1
     * @param player2Name the name of player 2
     */
    public GamePanel(JFrame frame, String player1Name, String player2Name) {
        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(800, 600));

        try {
            backgroundImage = ImageIO.read(new File("src/main/resources/background.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        player1 = new Player(100, 300, Color.RED, KeyEvent.VK_W, KeyEvent.VK_S, KeyEvent.VK_A, KeyEvent.VK_D, "/bike3.png");
        player2 = new Player(700, 300, Color.BLUE, KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT, "/bike3.png");
        player2.setDx(-10);
        player1.setRotationAngle(90);
        player2.setRotationAngle(270);

        player1.setName(player1Name);
        player2.setName(player2Name);

        trace1 = new ArrayList<>();
        trace2 = new ArrayList<>();
        obstacles = new ArrayList<>();

        generateRandomObstacleMap();

        timer = new Timer(100, this);
        timer.start();
    }

    @Override
    public void addNotify() {
        super.addNotify();
        requestFocusInWindow();
        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                player1.handleKeyPress(e.getKeyCode());
                player2.handleKeyPress(e.getKeyCode());
            }
        });
    }

    /**
     * Generates obstacles based on a random predefined map.
     */
    private void generateRandomObstacleMap() {
        List<int[][]> obstacleMaps = new ArrayList<>();

        obstacleMaps.add(new int[][]{{200, 150}, {400, 300}, {600, 450}, {100, 500}, {700, 100}});
        obstacleMaps.add(new int[][]{{250, 100}, {450, 350}, {650, 500}, {150, 400}, {350, 200}});
        obstacleMaps.add(new int[][]{{100, 100}, {300, 300}, {500, 500}, {700, 400}, {200, 200}});
        obstacleMaps.add(new int[][]{{200, 450}, {400, 150}, {600, 300}, {100, 350}, {700, 250}});
        obstacleMaps.add(new int[][]{{250, 500}, {450, 100}, {650, 400}, {150, 200}, {350, 350}});
        obstacleMaps.add(new int[][]{{300, 100}, {500, 300}, {700, 500}, {100, 400}, {200, 200}});
        obstacleMaps.add(new int[][]{{400, 200}, {600, 400}, {100, 100}, {200, 500}, {700, 300}});
        obstacleMaps.add(new int[][]{{150, 150}, {350, 350}, {550, 550}, {750, 450}, {250, 250}});
        obstacleMaps.add(new int[][]{{200, 300}, {400, 500}, {600, 100}, {100, 200}, {700, 400}});
        obstacleMaps.add(new int[][]{{100, 500}, {300, 200}, {500, 400}, {700, 300}, {200, 100}});

        Random random = new Random();
        int[][] selectedMap = obstacleMaps.get(random.nextInt(obstacleMaps.size()));

        int obstacleSize = 20;
        for (int[] pos : selectedMap) {
            obstacles.add(new Obstacle(pos[0], pos[1], obstacleSize));
        }
    }

    /**
     * Checks for collisions between players, traces, and obstacles.
     */
    private void checkCollision() {
        if (player1.collidesWith(trace2) || player1.collidesWith(trace1) || player1.isOutOfBounds(getWidth(), getHeight())) {
            gameOver(player2.getName() + " Wins!");
        } else if (player2.collidesWith(trace1) || player2.collidesWith(trace2) || player2.isOutOfBounds(getWidth(), getHeight())) {
            gameOver(player1.getName() + " Wins!");
        }

        for (Obstacle obstacle : obstacles) {
            if (player1.getX() >= obstacle.getX() && player1.getX() <= obstacle.getX() + Obstacle.getSize() &&
                    player1.getY() >= obstacle.getY() && player1.getY() <= obstacle.getY() + Obstacle.getSize()) {
                gameOver(player2.getName() + " Wins!");
                return;
            }
            if (player2.getX() >= obstacle.getX() && player2.getX() <= obstacle.getX() + Obstacle.getSize() &&
                    player2.getY() >= obstacle.getY() && player2.getY() <= obstacle.getY() + Obstacle.getSize()) {
                gameOver(player1.getName() + " Wins!");
                return;
            }
        }
    }

    /**
     * Ends the game and displays a message with the winner.
     *
     * @param message the message to be displayed in the game over dialog
     */
    public void gameOver(String message) {
        timer.stop();
        JOptionPane.showMessageDialog(this, message);

        String winnerName = message.contains(player1.getName()) ? player1.getName() : player2.getName();
        HighScore.updateScore(winnerName);

        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
        frame.getContentPane().removeAll();
        frame.add(new GameOverPanel(frame, message, player1.getName(), player2.getName()));
        frame.revalidate();
        frame.repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }

        Graphics2D g2d = (Graphics2D) g;

        g.setColor(player1.getColor());
        for (Point p : trace1) {
            g.fillRect(p.x, p.y, 12, 12);
        }

        g.setColor(player2.getColor());
        for (Point p : trace2) {
            g.fillRect(p.x, p.y, 12, 12);
        }

        drawRotatedImage(g2d, player1);
        drawRotatedImage(g2d, player2);

        g.setColor(Color.GREEN);
        for (Obstacle obstacle : obstacles) {
            g.fillRect(obstacle.getX(), obstacle.getY(), Obstacle.getSize(), Obstacle.getSize());
        }
    }

    /**
     * Draws the player images rotated according to their movement direction.
     *
     * @param g2d the Graphics2D object used for drawing
     * @param player the player whose image is being drawn
     */
    private void drawRotatedImage(Graphics2D g2d, Player player) {
        Image playerImage = player.getPlayerImage();
        int rotationAngle = player.getRotationAngle();

        int playerX = player.getX();
        int playerY = player.getY();

        int imageWidth = 20;
        int imageHeight = 45;
        int offsetX = imageWidth / 2;
        int offsetY = imageHeight / 2;

        g2d.rotate(Math.toRadians(rotationAngle), playerX, playerY);
        g2d.drawImage(playerImage, playerX - offsetX, playerY - offsetY, this);
        g2d.rotate(-Math.toRadians(rotationAngle), playerX, playerY);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        player1.move();
        player2.move();

        trace1.add(new Point(player1.getX(), player1.getY()));
        trace2.add(new Point(player2.getX(), player2.getY()));

        checkCollision();
        repaint();
    }

    /**
     * Returns the list of obstacles in the game.
     *
     * @return a List of Obstacle objects
     */
    public ArrayList<Obstacle> getObstacles() {
        return obstacles;
    }
}
