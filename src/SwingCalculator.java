import javax.swing.*;
import java.awt.*;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class SwingCalculator extends JFrame {

    private JTextField display;
    private Theme currentTheme;
    private final ThemeManager themeManager = new ThemeManager();
    private JButton themeToggleButton;
    private JPanel buttonPanel;

    public SwingCalculator() {
        setTitle("Calculadora");
        setSize(320, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        setResizable(false);

        currentTheme = themeManager.getCurrentTheme();

        initDisplayPanel();
        initButtonPanel();

        applyTheme();
        setVisible(true);
    }

    private void initDisplayPanel() {
        display = new JTextField();
        display.setEditable(false);
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setFont(new Font("Arial", Font.BOLD, 30));
        display.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel displayPanel = new JPanel(new BorderLayout());
        displayPanel.add(display, BorderLayout.CENTER);

        add(displayPanel, BorderLayout.NORTH);
    }

    private void initButtonPanel() {
        buttonPanel = new JPanel(new GridLayout(5, 4, 10, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        buttonPanel.setBackground(currentTheme.getBackgroundColor());

        String[] buttons = {
                "@", "C", "<-", "%",
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                ".", "0", "=", "+"
        };

        for (String text : buttons) {
            JButton button = new JButton(text);
            button.setFont(new Font("Arial", Font.BOLD, 20));
            button.setFocusPainted(false);
            button.setBorder(BorderFactory.createLineBorder(Color.GRAY));

            if (text.equals("@")) {
                themeToggleButton = button;
            }

            buttonPanel.add(button);

            button.addActionListener(e -> {
                switch (text) {
                    case "C" -> display.setText("");
                    case "<-" -> {
                        String current = display.getText();
                        if (!current.isEmpty()) {
                            display.setText(current.substring(0, current.length() - 1));
                        }
                    }
                    case "=" -> {
                        try {
                            String expressionText = display.getText();
                            Expression expression = new ExpressionBuilder(expressionText).build();
                            double result = expression.evaluate();
                            display.setText(String.valueOf(result));
                        } catch (Exception ex) {
                            display.setText("Erro");
                        }
                    }
                    case "%" -> {
                        try {
                            String expression = display.getText();
                            String[] tokens = expression.split("(?<=[+\\-*/])");
                            if (tokens.length >= 2) {
                                String baseStr = tokens[tokens.length - 2].replaceAll("[^0-9.]", "");
                                String percentStr = tokens[tokens.length - 1].replaceAll("[^0-9.]", "");

                                double base = Double.parseDouble(baseStr);
                                double percent = Double.parseDouble(percentStr);

                                double result = base * percent / 100;
                                expression = expression.substring(0, expression.length() - percentStr.length()) + result;

                                display.setText(expression);
                            }
                        } catch (Exception ex) {
                            display.setText("Erro");
                        }
                    }
                    case "@" -> {
                        themeManager.switchTheme();
                        applyTheme();
                    }
                    default -> display.setText(display.getText() + text);
                }
            });
        }

        add(buttonPanel, BorderLayout.CENTER);
    }

    private void applyTheme() {
        currentTheme = themeManager.getCurrentTheme();

        getContentPane().setBackground(currentTheme.getBackgroundColor());
        buttonPanel.setBackground(currentTheme.getBackgroundColor());
        display.setBackground(currentTheme.getDisplayColor());
        display.setForeground(currentTheme.getTextColor());

        Component[] components = getContentPane().getComponents();
        for (Component comp : components) {
            if (comp instanceof JPanel) {
                JPanel panel = (JPanel) comp;
                for (Component btn : panel.getComponents()) {
                    if (btn instanceof JButton) {
                        JButton button = (JButton) btn;
                        button.setBackground(
                                button.getText().equals("=")
                                        ? currentTheme.getEqualButtonColor()
                                        : currentTheme.getButtonColor()
                        );
                        button.setForeground(currentTheme.getTextColor());
                    }
                }
            }
        }
    }


    public JTextField getDisplay() {
        return display;
    }
}
