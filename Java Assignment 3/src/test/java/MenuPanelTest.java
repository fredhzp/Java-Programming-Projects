import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.assignment.*;

/**
 * Test class for the MenuPanel.
 */
class MenuPanelTest {

    private JFrame frame;
    private MenuPanel menuPanel;

    /**
     * Initializes the necessary objects for the test.
     */
    @BeforeEach
    void setUp() {
        frame = new JFrame();
        menuPanel = new MenuPanel(frame);
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(menuPanel);
    }

    /**
     * Tests if the Player 1 name field retrieves the correct text.
     */
    @Test
    void testPlayer1NameField() {
        JTextField player1NameField = menuPanel.getPlayer1NameField();
        player1NameField.setText("Test Player 1");
        assertEquals("Test Player 1", player1NameField.getText());
    }

    /**
     * Tests if the Player 2 name field retrieves the correct text.
     */
    @Test
    void testPlayer2NameField() {
        JTextField player2NameField = menuPanel.getPlayer2NameField();
        player2NameField.setText("Test Player 2");
        assertEquals("Test Player 2", player2NameField.getText());
    }

    /**
     * Tests the action when the Start Game button is clicked.
     */
    @Test
    void testStartButtonAction() {
        assertTrue(frame.getContentPane() instanceof MenuPanel);

        JButton startButton = menuPanel.getStartButton();
        ActionListener[] actionListeners = startButton.getActionListeners();
        assertEquals(1, actionListeners.length);

        ActionEvent actionEvent = new ActionEvent(startButton, ActionEvent.ACTION_PERFORMED, null);
        actionListeners[0].actionPerformed(actionEvent);

        assertTrue(frame.getContentPane().getComponent(0) instanceof GamePanel);
    }

    /**
     * Tests the action when the High Score button is clicked.
     */
    @Test
    void testHighScoreButtonAction() {
        JButton highScoreButton = menuPanel.getHighScoreButton();
        ActionListener[] actionListeners = highScoreButton.getActionListeners();
        assertEquals(1, actionListeners.length);

        ActionEvent actionEvent = new ActionEvent(highScoreButton, ActionEvent.ACTION_PERFORMED, null);
        actionListeners[0].actionPerformed(actionEvent);
    }

    /**
     * Tests the layout of the components in the MenuPanel.
     */
    @Test
    void testLayout() {
        assertNotNull(menuPanel.getPlayer1NameField());
        assertNotNull(menuPanel.getPlayer2NameField());
        assertNotNull(menuPanel.getStartButton());
        assertNotNull(menuPanel.getHighScoreButton());
        assertNotNull(menuPanel.getExitButton());

        for (int i = 0; i < menuPanel.getComponentCount(); i++) {
            Component component = menuPanel.getComponent(i);

            if (component instanceof JTextField) {
                JTextField textField = (JTextField) component;
                assertTrue(textField.getFont().getSize() > 0);
            }

            if (component instanceof JButton) {
                JButton button = (JButton) component;
                assertTrue(button.getFont().getSize() > 0);
            }

            if (component instanceof Box.Filler) {
                continue;
            }
        }
    }
}
