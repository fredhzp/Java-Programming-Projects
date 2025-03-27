package org.assignment;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * GameOverPanel is the panel displayed when the game ends, showing the result message and options
 * to restart, quit, or go back to the main menu.
 */
public class GameOverPanel extends JPanel {
    private String player1Name;
    private String player2Name;

    /**
     * Constructs the GameOverPanel with the provided message, player names, and frame.
     * Initializes the panel with the message and action buttons for restarting, quitting, or returning to the menu.
     *
     * @param frame the JFrame that contains this panel
     * @param message the message to display (indicating the winner or game over message)
     * @param player1Name the name of player 1
     * @param player2Name the name of player 2
     */
    public GameOverPanel(JFrame frame, String message, String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.BLACK);

        JLabel messageLabel = new JLabel(message, SwingConstants.CENTER);
        messageLabel.setForeground(Color.WHITE);
        messageLabel.setFont(new Font("Arial", Font.BOLD, 36));
        messageLabel.setAlignmentX(CENTER_ALIGNMENT);

        JButton restartButton = createButton("Restart Game");
        JButton quitButton = createButton("Quit Game");
        JButton backButton = createButton("Back to Menu");

        restartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getContentPane().removeAll();
                frame.add(new GamePanel(frame, player1Name, player2Name));
                frame.revalidate();
                frame.repaint();
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getContentPane().removeAll();
                frame.add(new MenuPanel(frame));
                frame.revalidate();
                frame.repaint();
            }
        });

        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        add(Box.createVerticalStrut(100));
        add(messageLabel);
        add(Box.createVerticalStrut(40));
        add(restartButton);
        add(Box.createVerticalStrut(20));
        add(backButton);
        add(Box.createVerticalStrut(20));
        add(quitButton);
    }

    /**
     * Creates a button with customized styles.
     *
     * @param text the text displayed on the button
     * @return a JButton with the desired properties
     */
    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 20));
        button.setBackground(new Color(0, 102, 204));
        button.setFocusPainted(false);
        button.setPreferredSize(new Dimension(250, 50));
        button.setAlignmentX(CENTER_ALIGNMENT);
        return button;
    }
}
