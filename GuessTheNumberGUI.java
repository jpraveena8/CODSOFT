import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class GuessTheNumberGUI extends JFrame {

    private int targetNumber;
    private int attemptsLimit = 7;
    private int attempts;
    private int score;

    private JLabel infoLabel;
    private JTextField guessField;
    private JButton submitButton;

    public GuessTheNumberGUI() {
        super("Guess the Number Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 150);
        setLocationRelativeTo(null);

        initUI();

        newGame();

        setVisible(true);
    }

    private void initUI() {
        setLayout(new GridLayout(3, 1));

        infoLabel = new JLabel("", SwingConstants.CENTER);
        guessField = new JTextField();
        submitButton = new JButton("Submit");

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkGuess();
            }
        });

        add(infoLabel);
        add(guessField);
        add(submitButton);
    }

    private void newGame() {
        targetNumber = new Random().nextInt(100) + 1;
        attempts = 0;
        updateInfoLabel("I have selected a random number between 1 and 100. Try to guess it!");
    }

    private void checkGuess() {
        try {
            int userGuess = Integer.parseInt(guessField.getText());
            attempts++;

            if (userGuess == targetNumber) {
                score++;
                updateInfoLabel("Congratulations! You guessed the correct number " + targetNumber + " in " + attempts + " attempts. Score: " + score);
                int option = JOptionPane.showConfirmDialog(this, "Do you want to play again?", "Play Again", JOptionPane.YES_NO_OPTION);

                if (option == JOptionPane.YES_OPTION) {
                    newGame();
                } else {
                    JOptionPane.showMessageDialog(this, "Game over. Your total score is: " + score, "Game Over", JOptionPane.INFORMATION_MESSAGE);
                    System.exit(0);
                }
            } else if (userGuess < targetNumber) {
                updateInfoLabel("Too low. Try again.");
            } else {
                updateInfoLabel("Too high. Try again.");
            }

            if (attempts == attemptsLimit) {
                updateInfoLabel("Sorry, you've reached the maximum number of attempts. The correct number was " + targetNumber + ". Try again.");
                int option = JOptionPane.showConfirmDialog(this, "Do you want to play again?", "Play Again", JOptionPane.YES_NO_OPTION);

                if (option == JOptionPane.YES_OPTION) {
                    newGame();
                } else {
                    JOptionPane.showMessageDialog(this, "Game over. Your total score is: " + score, "Game Over", JOptionPane.INFORMATION_MESSAGE);
                    System.exit(0);
                }
            }
        } catch (NumberFormatException ex) {
            updateInfoLabel("Please enter a valid number.");
        }
    }

    private void updateInfoLabel(String message) {
        infoLabel.setText(message);
        guessField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GuessTheNumberGUI();
            }
        });
    }
}
