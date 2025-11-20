package licaza.tdefender.demo.configs;

import java.awt.Color;
import java.util.Map;

import licaza.tdefender.engine.commons.misc.ColorPalette;

public class Colors {

    // First Theme
    // private static final Color BACKGROUND = Color.decode("#121212");
    // private static final Color PRIMARY = Color.decode("#4B0082");
    // private static final Color ACCENT = Color.decode("#39FF14");
    // private static final Color TEXT = Color.decode("#E0E0E0");

    // Cozy theme
    private static final Color BACKGROUND = Color.decode("#D3D3D3");
    private static final Color PRIMARY = Color.decode("#C47E5A");
    private static final Color ACCENT_ONE = Color.decode("#FFF8F0");
    private static final Color ACCENT_TWO = Color.decode("#A8B88C");
    private static final Color ACCENT_THREE = Color.decode("#D4C07A");
    private static final Color TEXT = Color.decode("#3E322A");

    private static Map<ColorPalette, Color> colorMap;

    static {
        colorMap = Map.of(
                ColorPalette.BACKGROUND, BACKGROUND,
                ColorPalette.PRIMARY, PRIMARY,
                ColorPalette.ACCENT_ONE, ACCENT_ONE,
                ColorPalette.ACCENT_TWO, ACCENT_TWO,
                ColorPalette.ACCENT_THREE, ACCENT_THREE,
                ColorPalette.TEXT, TEXT);
    }

    public static final Color GetColorFromPalette(ColorPalette c) {
        return switch (c) {
            case BACKGROUND -> BACKGROUND;
            case PRIMARY -> PRIMARY;
            case ACCENT_ONE -> ACCENT_ONE;
            case ACCENT_TWO -> ACCENT_TWO;
            case ACCENT_THREE -> ACCENT_THREE;
            case TEXT -> TEXT;
        };
    }

    public static final Map<ColorPalette, Color> GetColorMap() {
        return colorMap;
    }

    private Colors() {
    } // No instances of this...
}
