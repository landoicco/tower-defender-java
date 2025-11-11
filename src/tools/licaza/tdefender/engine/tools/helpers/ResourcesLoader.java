package licaza.tdefender.engine.tools.helpers;

import java.awt.*;
import java.io.*;

public class ResourcesLoader {

    private ResourcesLoader() {
    } // No instance should exist

    public static class Audio {
    }

    public static class Fonts {
        public static Font LoadFontFromFile(File source) {
            Font font = null;

            try (InputStream is = Fonts.class.getClassLoader().getResourceAsStream(source.toString())) {
                font = Font.createFont(Font.TRUETYPE_FONT, is);
            } catch (IOException | FontFormatException e) {
                e.printStackTrace();
            }

            return font;
        }
    }
}
