package licaza.tdefender.demo.configs;

import licaza.tdefender.engine.commons.misc.IntPoint2D;

/**
 * This class holds the values that define where each element on
 * each of the scenes needs to be placed.
 */
public class UIPositions {
    private UIPositions() {
    }

    public static final class Editing {
    }

    public static final class Playing {

    }

    public static final class Credits {
        private static final int TEXT_Y_OFFSET = 20;

        // PANEL 1: Coders
        private static final IntPoint2D PANEL1_HEADER = new IntPoint2D(120, 100);
        private static final IntPoint2D PANEL1_TEXT = new IntPoint2D(180, 170);

        public static final IntPoint2D GetPanel1ElementPoint(String s) {
            return switch (s) {
                case "HEADER" -> PANEL1_HEADER;
                case "BASE" -> PANEL1_TEXT;
                default -> new IntPoint2D(-1, -1);
            };
        }

        // PANEL 2: Assets
        private static final float PANEL2_TEXT_FONT_SIZE = 20f;
        private static final float PANEL2_SUBHEADER_FONT_SIZE = 40f;

        private static final IntPoint2D PANEL2_HEADER = new IntPoint2D(230, 270);

        private static final IntPoint2D PANEL2_MUSIC_SUBHEADER = new IntPoint2D(120, 325);
        private static final IntPoint2D PANEL2_MUSIC_TEXT = new IntPoint2D(90, 350);

        private static final IntPoint2D PANEL2_SPRITES_SUBHEADER = new IntPoint2D(400, 325);
        private static final IntPoint2D PANEL2_SPRITES_TEXT = new IntPoint2D(385, 350);

        private static final IntPoint2D PANEL2_FONTS_SUBHEADER = new IntPoint2D(280, 450);
        private static final IntPoint2D PANEL2_FONTS_TEXT = new IntPoint2D(270, 470);

        public static final IntPoint2D GetPanel2ElementPoint(String s) {
            return switch (s) {
                case "HEADER" -> PANEL2_HEADER;
                case "MUSIC_SUBHEADER" -> PANEL2_MUSIC_SUBHEADER;
                case "MUSIC_TEXT" -> PANEL2_MUSIC_TEXT;
                case "SPRITES_SUBHEADER" -> PANEL2_SPRITES_SUBHEADER;
                case "SPRITES_TEXT" -> PANEL2_SPRITES_TEXT;
                case "FONTS_SUBHEADER" -> PANEL2_FONTS_SUBHEADER;
                case "FONTS_TEXT" -> PANEL2_FONTS_TEXT;
                default -> PANEL2_HEADER;
            };
        }

        public static final float GetPanel2FontSize(String s) {
            return switch (s) {
                case "SUBHEADER" -> PANEL2_SUBHEADER_FONT_SIZE;
                case "TEXT" -> PANEL2_TEXT_FONT_SIZE;
                default -> 0f;
            };
        }

        public static final int GetTextYOffset() {
            return TEXT_Y_OFFSET;
        }

        // PANEL 3: Footer
        private static final float PANEL3_FONT_SIZE = 30f;

        private static final IntPoint2D PANEL3_MESSAGE = new IntPoint2D(170, 740);

        public static final float GetPanel3FontSize() {
            return PANEL3_FONT_SIZE;
        }

        public static final IntPoint2D GetPanel3ElementPoint() {
            return PANEL3_MESSAGE;
        }
    }
}
