import java.util.ArrayList;
import java.util.List;

public class ThemeManager {

    private static final List<Theme> themes = new ArrayList<>();
    private static int currentThemeIndex = 0;

    static {
        // tema claro
        Theme lightTheme = new Theme(
                java.awt.Color.LIGHT_GRAY,
                java.awt.Color.WHITE,
                java.awt.Color.BLACK,
                java.awt.Color.WHITE,
                new java.awt.Color(139, 185, 255)
        );

        // tema escuro
        Theme darkTheme = new Theme(
                new java.awt.Color(102, 101, 101),
                new java.awt.Color(60, 60, 60),
                java.awt.Color.WHITE,
                new java.awt.Color(30, 30, 30),
                new java.awt.Color(41, 70, 117)
        );

        // tema rosa
        Theme pinkTheme = new Theme(
                new java.awt.Color(254, 240, 239),
                new java.awt.Color(251, 195, 188),
                java.awt.Color.BLACK,
                new java.awt.Color(255, 255, 255),
                new java.awt.Color(240, 128, 128)
        );

        themes.add(lightTheme);
        themes.add(darkTheme);
        themes.add(pinkTheme);
    }

    public static Theme getCurrentTheme() {
        return themes.get(currentThemeIndex);
    }

    public static void switchTheme() {
        currentThemeIndex = (currentThemeIndex + 1) % themes.size();
    }
}
