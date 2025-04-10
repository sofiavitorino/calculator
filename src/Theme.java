import java.awt.Color;

public class Theme {
    private Color backgroundColor;
    private Color buttonColor;
    private Color textColor;
    private Color displayColor;
    private Color equalButtonColor;

    public Theme(Color backgroundColor, Color buttonColor, Color textColor, Color displayColor, Color equalButtonColor) {
        this.backgroundColor = backgroundColor;
        this.buttonColor = buttonColor;
        this.textColor = textColor;
        this.displayColor = displayColor;
        this.equalButtonColor = equalButtonColor;
    }

    public Color getBackgroundColor() {
        return backgroundColor;
    }

    public Color getButtonColor() {
        return buttonColor;
    }

    public Color getTextColor() {
        return textColor;
    }

    public Color getDisplayColor() {
        return displayColor;
    }

    public Color getEqualButtonColor() {
        return equalButtonColor;
    }
}
