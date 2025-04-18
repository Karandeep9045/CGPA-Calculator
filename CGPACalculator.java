import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CGPACalculator extends JFrame {

    private JTextField[] markFields;
    private JTextField[] creditFields;
    private JButton calculateButton;
    private JLabel resultLabel;

    // function of cgpa calculation

    public CGPACalculator() {
        setTitle("CGPA Calculator (Marks Based)");
        setSize(400, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(8, 3, 10, 10));

        markFields = new JTextField[5];
        creditFields = new JTextField[5];

        // Labels
        add(new JLabel("Subject"));
        add(new JLabel("Marks (out of 100)"));
        add(new JLabel("Credit"));

        for (int i = 0; i < 5; i++) {
            add(new JLabel("Subject " + (i + 1)));
            markFields[i] = new JTextField();
            creditFields[i] = new JTextField();
            add(markFields[i]);
            add(creditFields[i]);
        }

        calculateButton = new JButton("Calculate CGPA");
        resultLabel = new JLabel("Your CGPA will be shown here");

        calculateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                calculateCGPA();
            }
        });

        add(calculateButton);
        add(resultLabel);
    }

    private int convertMarksToGradePoint(double marks) {
        if (marks >= 90) return 10;
        else if (marks >= 80) return 9;
        else if (marks >= 70) return 8;
        else if (marks >= 60) return 7;
        else if (marks >= 50) return 6;
        else if (marks >= 40) return 5;
        else return 0;
    }

    private void calculateCGPA() {
        double totalPoints = 0;
        double totalCredits = 0;

        try {
            for (int i = 0; i < 5; i++) {
                String marksStr = markFields[i].getText().trim();
                String creditStr = creditFields[i].getText().trim();

                if (!marksStr.isEmpty() && !creditStr.isEmpty()) {
                    double marks = Double.parseDouble(marksStr);
                    double credit = Double.parseDouble(creditStr);

                    int gradePoint = convertMarksToGradePoint(marks);
                    totalPoints += gradePoint * credit;
                    totalCredits += credit;
                }
            }

            if (totalCredits == 0) {
                resultLabel.setText("Please enter valid credits.");
            } else {
                double cgpa = totalPoints / totalCredits;
                resultLabel.setText("Your CGPA is: " + String.format("%.2f", cgpa));
            }
        } catch (NumberFormatException e) {
            resultLabel.setText("Invalid input. Enter numbers only.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new CGPACalculator().setVisible(true);
        });
    }
}
