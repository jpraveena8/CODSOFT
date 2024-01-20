import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Task3 extends JFrame implements ActionListener {
    private BankAccount userAccount;
    private JTextField amountField;
    private JTextArea displayArea;

    public Task3(BankAccount account) {
        this.userAccount = account;

        setTitle("ATM Machine");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
        getContentPane().setBackground(new Color(240, 240, 240));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 0;
        gbc.gridy = 0;

        JLabel amountLabel = new JLabel("Enter Amount:");
        add(amountLabel, gbc);

        amountField = new JTextField(10);
        gbc.gridx++;
        add(amountField, gbc);

        JButton withdrawButton = new JButton("Withdraw");
        withdrawButton.addActionListener(this);
        gbc.gridx = 0;
        gbc.gridy++;
        add(withdrawButton, gbc);

        JButton depositButton = new JButton("Deposit");
        depositButton.addActionListener(this);
        gbc.gridx++;
        add(depositButton, gbc);

        JButton checkBalanceButton = new JButton("Check Balance");
        checkBalanceButton.addActionListener(this);
        gbc.gridx++;
        add(checkBalanceButton, gbc);

        displayArea = new JTextArea(10, 30);
        displayArea.setEditable(false);
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 3;
        add(new JScrollPane(displayArea), gbc);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String amountText = amountField.getText();

        if (e.getActionCommand().equals("Withdraw")) {
            if (!amountText.isEmpty()) {
                double amount = Double.parseDouble(amountText);
                if (userAccount.withdraw(amount)) {
                    displayMessage("Withdrawal successful. New balance: $" + userAccount.getBalance());
                } else {
                    displayMessage("Insufficient funds for withdrawal.");
                }
            } else {
                displayMessage("Please enter a valid amount for withdrawal.");
            }
        } else if (e.getActionCommand().equals("Deposit")) {
            if (!amountText.isEmpty()) {
                double amount = Double.parseDouble(amountText);
                userAccount.deposit(amount);
                displayMessage("Deposit successful. New balance: $" + userAccount.getBalance());
            } else {
                displayMessage("Please enter a valid amount for deposit.");
            }
        } else if (e.getActionCommand().equals("Check Balance")) {
            displayMessage("Current balance: $" + userAccount.getBalance());
        }
        amountField.setText("");
    }

    private void displayMessage(String message) {
        displayArea.append(message + "\n");
    }

    public static void main(String[] args) {
        BankAccount userAccount = new BankAccount(1000); // Initial balance of $1000
        SwingUtilities.invokeLater(() -> new Task3(userAccount));
    }
}

class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    public double getBalance() {
        return balance;
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            return true;
        }
        return false;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
        }
    }
}
