package org.assignment;

import javax.swing.*;
import java.awt.*;

/**
 * MenuPanel is the main menu screen of the game that allows players to input their names,
 * start the game, view high scores, or exit the application.
 */
public class MenuPanel extends JPanel {

    private JTextField player1NameField;
    private JTextField player2NameField;
    private JButton startButton;
    private JButton highScoreButton;
    private JButton exitButton;
    private JButton colorPickerButton1;
    private JButton colorPickerButton2;
    private Color player1Color = Color.RED;
    private Color player2Color = Color.BLUE;

    /**
     * Constructs the MenuPanel with text fields for player names and buttons for starting the game,
     * viewing high scores, or exiting the application.
     *
     * @param frame The JFrame to which this panel will be added.
     */
    public MenuPanel(JFrame frame) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(new Color(0, 0, 0));

        JLabel titleLabel = new JLabel("Welcome to Tron!", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setAlignmentX(CENTER_ALIGNMENT);

        JPanel player1Panel = new JPanel();
        player1Panel.setLayout(new BoxLayout(player1Panel, BoxLayout.X_AXIS));
        player1Panel.setBackground(new Color(0, 0, 0));

        JPanel player2Panel = new JPanel();
        player2Panel.setLayout(new BoxLayout(player2Panel, BoxLayout.X_AXIS));
        player2Panel.setBackground(new Color(0, 0, 0));

        player1NameField = new JTextField("Player 1");
        player2NameField = new JTextField("Player 2");
        styleTextField(player1NameField);
        styleTextField(player2NameField);

        colorPickerButton1 = new JButton("Pick Color");
        colorPickerButton2 = new JButton("Pick Color");
        styleButton(colorPickerButton1);
        styleButton(colorPickerButton2);

        player1Panel.add(player1NameField);
        player1Panel.add(Box.createHorizontalStrut(10));
        player1Panel.add(colorPickerButton1);

        player2Panel.add(player2NameField);
        player2Panel.add(Box.createHorizontalStrut(10));
        player2Panel.add(colorPickerButton2);

        startButton = new JButton("Start Game");
        highScoreButton = new JButton("High Scores");
        exitButton = new JButton("Exit");
        styleButton(startButton);
        styleButton(highScoreButton);
        styleButton(exitButton);

        colorPickerButton1.addActionListener(e -> {
            Color selectedColor = JColorChooser.showDialog(this, "Choose Player 1 Color", player1Color);
            if (selectedColor != null) {
                player1Color = selectedColor;
                player1NameField.setForeground(player1Color);
            }
        });

        colorPickerButton2.addActionListener(e -> {
            Color selectedColor = JColorChooser.showDialog(this, "Choose Player 2 Color", player2Color);
            if (selectedColor != null) {
                player2Color = selectedColor;
                player2NameField.setForeground(player2Color);
            }
        });

        add(Box.createVerticalStrut(50));
        add(titleLabel);
        add(Box.createVerticalStrut(30));
        add(player1Panel);
        add(Box.createVerticalStrut(10));
        add(player2Panel);
        add(Box.createVerticalStrut(20));
        add(startButton);
        add(Box.createVerticalStrut(10));
        add(highScoreButton);
        add(Box.createVerticalStrut(10));
        add(exitButton);
        add(Box.createVerticalStrut(30));

        startButton.addActionListener(e -> {
            String player1Name = player1NameField.getText();
            String player2Name = player2NameField.getText();

            frame.getContentPane().removeAll();
            GamePanel gamePanel = new GamePanel(frame, player1Name, player2Name);
            gamePanel.player1.setColor(player1Color);
            gamePanel.player2.setColor(player2Color);
            frame.add(gamePanel);

            frame.revalidate();
            frame.repaint();
        });

        highScoreButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, HighScore.getHighScores(), "High Scores", JOptionPane.PLAIN_MESSAGE);
        });

        exitButton.addActionListener(e -> System.exit(0));

        frame.setVisible(true);
    }

    /**
     * Styles the text field by setting font, size, and alignment.
     *
     * @param textField The JTextField to be styled.
     */
    private void styleTextField(JTextField textField) {
        textField.setFont(new Font("Arial", Font.PLAIN, 18));
        textField.setPreferredSize(new Dimension(200, 40));
        textField.setBackground(Color.LIGHT_GRAY);
        textField.setForeground(Color.BLACK);
        textField.setHorizontalAlignment(JTextField.CENTER);
    }

    /**
     * Styles the button by setting font, size, background color, and border.
     *
     * @param button The JButton to be styled.
     */
    private void styleButton(JButton button) {
        button.setFont(new Font("Arial", Font.BOLD, 18));
        button.setPreferredSize(new Dimension(200, 50));
        button.setBackground(Color.GREEN);
        button.setForeground(Color.BLACK);
        button.setFocusPainted(false);
        button.setAlignmentX(CENTER_ALIGNMENT);
    }

    // Testing Utility Methods

    /**
     * Returns the JTextField for Player 1's name.
     *
     * @return The JTextField for Player 1's name.
     */
    public JTextField getPlayer1NameField() {
        return player1NameField;
    }

    /**
     * Returns the JTextField for Player 2's name.
     *
     * @return The JTextField for Player 2's name.
     */
    public JTextField getPlayer2NameField() {
        return player2NameField;
    }

    /**
     * Returns the Start Game button.
     *
     * @return The Start Game button.
     */
    public JButton getStartButton() {
        return startButton;
    }

    /**
     * Returns the High Scores button.
     *
     * @return The High Scores button.
     */
    public JButton getHighScoreButton() {
        return highScoreButton;
    }

    /**
     * Returns the Exit button.
     *
     * @return The Exit button.
     */
    public JButton getExitButton() {
        return exitButton;
    }
}
