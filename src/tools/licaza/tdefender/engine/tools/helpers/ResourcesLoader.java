package licaza.tdefender.engine.tools.helpers;

import java.awt.*;
import java.io.*;

import javax.sound.sampled.*;

public class ResourcesLoader {

    private ResourcesLoader() {
    } // No instance should exist

    public static class Audio {
        public static Clip LoadClipFromFile(File source) {
            Clip clip = null;

            try (InputStream is = Audio.class.getClassLoader().getResourceAsStream(source.toString());
                    AudioInputStream audioIn = AudioSystem.getAudioInputStream(new BufferedInputStream(is))) {
                clip = AudioSystem.getClip();
                clip.open(audioIn);
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            return clip;
        }
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
