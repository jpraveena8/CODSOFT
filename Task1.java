import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class Task1 {
    private JFrame mainFrame;
    private JLabel promptLabel;
    private JTextField inputField;
    private JButton guessButton;
    private int secretNumber;
    private int attempts;
    private int maxAttempts;
    private int score;

    public Task1() {
        mainFrame = new JFrame("Guess the Number Game");
        promptLabel = new JLabel("Guess the number:");
        inputField = new JTextField(10);
        guessButton = new JButton("Guess");
        secretNumber = generateRandomNumber();
        maxAttempts = 10;
        attempts = 0;
        score = 0;

        guessButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                processGuess();
            }
        });

        mainFrame.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        mainFrame.getContentPane().setBackground(new Color(200, 220, 240));

        promptLabel.setFont(new Font("Arial", Font.BOLD, 16));
        promptLabel.setForeground(Color.DARK_GRAY);

        inputField.setFont(new Font("Arial", Font.PLAIN, 16));
        inputField.setForeground(Color.BLACK);

        guessButton.setFont(new Font("Arial", Font.PLAIN, 16));
        guessButton.setForeground(Color.WHITE);
        guessButton.setBackground(new Color(80, 150, 220));

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);
        mainFrame.add(promptLabel, gbc);

        gbc.gridy = 1;
        mainFrame.add(inputField, gbc);

        gbc.gridy = 2;
        mainFrame.add(guessButton, gbc);

        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(300, 200);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);
    }

    private int generateRandomNumber() {
        Random random = new Random();
        return random.nextInt(100) + 1;
    }

    private void processGuess() {
        int userGuess = Integer.parseInt(inputField.getText());
        attempts++;

        if (userGuess == secretNumber) {
            JOptionPane.showMessageDialog(mainFrame, "Congratulations! You guessed the correct number.");
            score++;
        } else if (attempts < maxAttempts) {
            String message = (userGuess < secretNumber) ? "Too low! Try a higher number." : "Too high! Try a lower number.";
            JOptionPane.showMessageDialog(mainFrame, message);
        } else {
            JOptionPane.showMessageDialog(mainFrame, "Sorry! You've used all your attempts. The correct number was " + secretNumber);
        }

        if (attempts < maxAttempts) {
            secretNumber = generateRandomNumber();
            inputField.setText("");
        } else {
            showScore();
        }
    }

    private void showScore() {
        JOptionPane.showMessageDialog(mainFrame, "Your final score is: " + score);
        mainFrame.dispose();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Task1());
    }
}
