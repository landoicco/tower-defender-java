package licaza.tdefender.demo.configs;

import java.awt.Font;
import java.io.File;
import javax.sound.sampled.Clip;

import licaza.tdefender.engine.tools.helpers.ResourcesLoader;

public class MediaSource {
    public final static class Fonts {
        private static final Font HEADER_FONT = ResourcesLoader.Fonts.LoadFontFromFile(new File("caveat-brush.ttf"));
        private static final Font BASE_FONT = ResourcesLoader.Fonts.LoadFontFromFile(new File("patrick-hand.ttf"));

        public final static Font GetHeaderFont() {
            return HEADER_FONT;
        }

        public final static Font GetBaseFont() {
            return BASE_FONT;
        }
    }

    public final static class Sounds {
        private static final Clip HOVER = ResourcesLoader.Audio.LoadClipFromFile(new File("equip.wav"));
        private static final Clip CLICK = ResourcesLoader.Audio.LoadClipFromFile(new File("use-item.wav"));
        private static final Clip MENU = ResourcesLoader.Audio.LoadClipFromFile(new File("menu.wav"));
        private static final Clip PLAY = ResourcesLoader.Audio.LoadClipFromFile(new File("playing.wav"));

        public final static Clip GetAudioClip(String s) {
            return switch (s) {
                case "HOVER" -> HOVER;
                case "CLICK" -> CLICK;
                case "MENU" -> MENU;
                case "PLAY" -> PLAY;
                default -> CLICK;
            };
        }
    }

    private MediaSource() {
    }
}
