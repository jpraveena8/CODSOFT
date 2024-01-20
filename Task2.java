import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Task2 extends JFrame implements ActionListener {
    private JTextField[] subjectTextFields;
    private JButton calculateButton;

    public Task2() {
        setTitle("Grade Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
        getContentPane().setBackground(new Color(240, 240, 240));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 0;
        gbc.gridy = 0;

        subjectTextFields = new JTextField[5];
        for (int i = 0; i < 5; i++) {
            add(createSubjectPanel(i + 1), gbc);
            gbc.gridy++;
        }

        calculateButton = new JButton("Calculate");
        calculateButton.addActionListener(this);
        calculateButton.setBackground(new Color(50, 120, 200));
        calculateButton.setForeground(Color.WHITE);
        gbc.gridy++;
        add(calculateButton, gbc);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private JPanel createSubjectPanel(int index) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.setBackground(new Color(240, 240, 240));

        subjectTextFields[index - 1] = new JTextField(6);
        panel.add(new JLabel("Subject " + index));
        panel.add(subjectTextFields[index - 1]);

        return panel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == calculateButton) {
            int totalMarks = 0;
            int numSubjects = 0;

            for (int i = 0; i < 5; i++) {
                String marksText = subjectTextFields[i].getText();
                if (!marksText.isEmpty()) {
                    int marks = Integer.parseInt(marksText);
                    totalMarks += marks;
                    numSubjects++;
                }
            }

            double averagePercentage = (double) totalMarks / (numSubjects * 100) * 100;
            String grade = calculateGrade(averagePercentage);

            JOptionPane.showMessageDialog(this, "Total Marks: " + totalMarks +
                    "\nAverage Percentage: " + averagePercentage + "%" +
                    "\nGrade: " + grade);
        }
    }

    private String calculateGrade(double percentage) {
        if (percentage >= 90) {
            return "A+";
        } else if (percentage >= 80) {
            return "A";
        } else if (percentage >= 70) {
            return "B";
        } else if (percentage >= 60) {
            return "C";
        } else if (percentage >= 50) {
            return "D";
        } else {
            return "F";
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Task2());
    }
}
