package licaza.tdefender.demo.configs;

import java.awt.Color;
import java.util.Map;

import licaza.tdefender.engine.commons.misc.ColorPalette;

public class Colors {
    private static final Color BACKGROUND = Color.decode("#121212");
    private static final Color PRIMARY = Color.decode("#4B0082");
    private static final Color ACCENT = Color.decode("#39FF14");
    private static final Color TEXT = Color.decode("#E0E0E0");

    private static Map<ColorPalette, Color> colorMap;

    static {
        colorMap = Map.of(
                ColorPalette.BACKGROUND, BACKGROUND,
                ColorPalette.PRIMARY, PRIMARY,
                ColorPalette.ACCENT, ACCENT,
                ColorPalette.TEXT, TEXT);
    }

    public static final Color GetColorFromPalette(ColorPalette c) {
        return switch (c) {
            case BACKGROUND -> BACKGROUND;
            case PRIMARY -> PRIMARY;
            case ACCENT -> ACCENT;
            case TEXT -> TEXT;
        };
    }

    public static final Map<ColorPalette, Color> GetColorMap() {
        return colorMap;
    }

    private Colors() {
    } // No instances of this...
}
