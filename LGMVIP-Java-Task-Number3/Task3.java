import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Task3 extends JFrame implements ActionListener {
    private JTextField displayField;
    private double currentResult = 0;
    private String currentOperator = "";

    public Task3() {
        setTitle("Scientific Calculator");
        setSize(300, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        displayField = new JTextField();
        displayField.setEditable(false);
        displayField.setFont(new Font("Arial", Font.PLAIN, 24));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(6, 4));

        String[] buttons = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", ".", "=", "+",
                "√", "C", "sin", "cos",
                "tan", "log", "x^2", "x^y"
        };

        for (String button : buttons) {
            JButton btn = new JButton(button);
            btn.setFont(new Font("Arial", Font.PLAIN, 20));
            btn.addActionListener(this);
            buttonPanel.add(btn);
        }

        add(displayField, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    @Override
    
public void actionPerformed(ActionEvent e) {
    String actionCommand = e.getActionCommand();

    if ("0123456789.".contains(actionCommand)) {
        displayField.setText(displayField.getText() + actionCommand);
    } else if ("+-*/".contains(actionCommand)) {
        performCalculation();
        currentOperator = actionCommand;
        currentResult = Double.parseDouble(displayField.getText());
        displayField.setText("");
    } else if (actionCommand.equals("=")) {
        performCalculation();
        currentOperator = "";
        currentResult = 0;
    } else if (actionCommand.equals("√")) {
        double operand = Double.parseDouble(displayField.getText());
        double result = Math.sqrt(operand);
        displayField.setText(String.valueOf(result));
    } else if (actionCommand.equals("C")) {
        displayField.setText("");
        currentResult = 0;
        currentOperator = "";
    } else if (actionCommand.equals("sin")) {
        double operand = Double.parseDouble(displayField.getText());
        double result = Math.sin(Math.toRadians(operand)); // Convert degrees to radians
        displayField.setText(String.valueOf(result));
    } else if (actionCommand.equals("cos")) {
        double operand = Double.parseDouble(displayField.getText());
        double result = Math.cos(Math.toRadians(operand)); // Convert degrees to radians
        displayField.setText(String.valueOf(result));
    } else if (actionCommand.equals("tan")) {
        double operand = Double.parseDouble(displayField.getText());
        double result = Math.tan(Math.toRadians(operand)); // Convert degrees to radians
        displayField.setText(String.valueOf(result));
    } else if (actionCommand.equals("log")) {
        double operand = Double.parseDouble(displayField.getText());
        double result = Math.log10(operand);
        displayField.setText(String.valueOf(result));
    } else if (actionCommand.equals("x^2")) {
        double operand = Double.parseDouble(displayField.getText());
        double result = Math.pow(operand, 2);
        displayField.setText(String.valueOf(result));
    } else if (actionCommand.equals("x^y")) {
        performCalculation();
        currentOperator = "^";
        currentResult = Double.parseDouble(displayField.getText());
        displayField.setText("");
    }
}
private void performCalculation() {
    if (!currentOperator.isEmpty()) {
        double operand = Double.parseDouble(displayField.getText());
        switch (currentOperator) {
            case "+":
                currentResult += operand;
                break;
            case "-":
                currentResult -= operand;
                break;
            case "*":
                currentResult *= operand;
                break;
            case "/":
                if (operand != 0) {
                    currentResult /= operand;
                } else {
                    displayField.setText("Error");
                    currentResult = 0;
                    currentOperator = "";
                    return;
                }
                break;
            case "^":
                currentResult = Math.pow(currentResult, operand);
                break;
        }
        displayField.setText(String.valueOf(currentResult));
    }
}

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Task3());
    }
}
