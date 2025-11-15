package licaza.tdefender.demo.configs;

import licaza.tdefender.engine.commons.misc.IntPoint2D;

/**
 * This class holds the values that define where each element on
 * each of the scenes needs to be placed.
 */
public final class UIPositions {
    private UIPositions() {
    }

    public static final class Menu {
        // Header
        private static final IntPoint2D HEADER = new IntPoint2D(260, 330);
        private static final float HEADER_X_OFFSET = 30;
        private static final float HEADER_Y_OFFSET = 60;

        public static final IntPoint2D GetHeaderPoint() {
            return HEADER;
        }

        public static final float GetHeaderOffset(String s) {
            return switch (s) {
                case "X" -> HEADER_X_OFFSET;
                case "Y" -> HEADER_Y_OFFSET;
                default -> throw new IllegalArgumentException("Value not defined!");
            };
        }

        // Buttons
        private static final IntPoint2D PLAY_BTN = new IntPoint2D(270, 300);
        private static final int BTN_WIDTH = 120, BTN_HEIGHT = 50;
        private static final int BTNS_Y_PADDING = 20;

        public static final IntPoint2D GetPlayButtonPoint() {
            return PLAY_BTN;
        }

        public static final int GetValue(String s) {
            return switch (s) {
                case "BTN_WIDTH" -> BTN_WIDTH;
                case "BTN_HEIGHT" -> BTN_HEIGHT;
                case "BTNS_PADDING" -> BTNS_Y_PADDING;
                default -> throw new IllegalArgumentException("Value not defined!");
            };
        }
    }

    public static final class Editing {
    }

    public static final class Playing {
        public static final class ActionBar {
            private static final int BTNS_HEIGHT = 30;

            // Non-gameplay buttons
            private static final IntPoint2D MENU_BTN = new IntPoint2D(10, 735);
            private static final IntPoint2D PAUSE_BTN = new IntPoint2D(10, 760);
            private static final int NON_GAMEPLAY_BTNS_WIDTH = 100;
            private static final float NON_GAMEPLAY_BTNS_FONT_SIZE = 25f;

            // Tower buttons
            private static final IntPoint2D FIRST_TOWER_BTN = new IntPoint2D(220, 670);
            private static final int IMAGE_BTN_SIZE = 50,
                    X_OFFSET = (int) (IMAGE_BTN_SIZE * 1.1f);

            // Sell/Upgrade tower buttons
            private static final IntPoint2D SELL_BTN = new IntPoint2D(480, 745);
            private static final IntPoint2D UPGRADE_BTN = new IntPoint2D(540, 745);
            private static final int SELL_BTN_WIDTH = 50, UPGRADE_BTN_WIDTH = 80;

            // Draw displayed tower
            private static IntPoint2D DISPLAYED_TOWER_BACKGROUND_RECT = new IntPoint2D(470, 670),
                    SPRITE = new IntPoint2D(480, 680);
            private static final float DISPLAYED_TOWER_FONT_SIZE = 18f;
            private static final int DISPLAYED_TOWER_BACKGROUND_RECT_WIDTH = 160,
                    DISPLAYED_TOWER_BACKGROUND_RECT_HEIGHT = 80,
                    SPRITE_SIZE = 50;
            // Text
            private static IntPoint2D TOWER_NAME = new IntPoint2D(560, 695),
                    TOWER_ID = new IntPoint2D(560, 715),
                    TOWER_TIER = new IntPoint2D(560, 735);
            // Sell/Upgrade tower labels
            private static final IntPoint2D SELL_LABEL = new IntPoint2D(500, 795),
                    UPGRADE_LABEL = new IntPoint2D(500, 795);

            // Enemies/Wave info
            private static final IntPoint2D WAVE_INFO_BACKGROUND = new IntPoint2D(0, 640),
                    ENEMIES_LEFT_LABEL = new IntPoint2D(0, 0),
                    WAVE_TIMER_LABEL = new IntPoint2D(100, 150),
                    WAVES_LEFT_LABEL = new IntPoint2D(210, 200);
            private static final int WAVE_INFO_BACKGROUND_WIDTH = 650,
                    WAVE_INFO_BACKGROUND_HEIGHT = 25;

            // Gold and lives info
            private static final IntPoint2D GOLD_AND_LIVES_BACKGROUND = new IntPoint2D(10, 670),
                    LIVES_LABEL = new IntPoint2D(20, 700),
                    GOLD_LABEL = new IntPoint2D(20, 725);
            private static final int GOLD_AND_LIVES_BACKGROUND_WIDTH = 120,
                    GOLD_AND_LIVES_BACKGROUND_HEIGHT = 70;
            private static final float GOLD_AND_LIVES_FONT_SIZE = 30f;

            /*
             * ===============================================
             * Public "getter" methods
             * ===============================================
             */
            public static final int GetValue(String s) {
                return switch (s) {
                    case "IMAGE_BTN_SIZE" -> IMAGE_BTN_SIZE;
                    case "X_OFFSET" -> X_OFFSET;
                    case "SELL_BTN_WIDTH" -> SELL_BTN_WIDTH;
                    case "UPGRADE_BTN_WIDTH" -> UPGRADE_BTN_WIDTH;
                    case "BTNS_HEIGHT" -> BTNS_HEIGHT;
                    case "SPRITE_SIZE" -> SPRITE_SIZE;
                    case "NON_GAMEPLAY_BTNS_WIDTH" -> NON_GAMEPLAY_BTNS_WIDTH;
                    case "DISPLAYED_TOWER_BACKGROUND_RECT_WIDTH" -> DISPLAYED_TOWER_BACKGROUND_RECT_WIDTH;
                    case "DISPLAYED_TOWER_BACKGROUND_RECT_HEIGHT" -> DISPLAYED_TOWER_BACKGROUND_RECT_HEIGHT;
                    case "WAVE_INFO_BACKGROUND_WIDTH" -> WAVE_INFO_BACKGROUND_WIDTH;
                    case "WAVE_INFO_BACKGROUND_HEIGHT" -> WAVE_INFO_BACKGROUND_HEIGHT;
                    case "GOLD_AND_LIVES_BACKGROUND_WIDTH" -> GOLD_AND_LIVES_BACKGROUND_WIDTH;
                    case "GOLD_AND_LIVES_BACKGROUND_HEIGHT" -> GOLD_AND_LIVES_BACKGROUND_HEIGHT;
                    default -> throw new IllegalArgumentException("Value not defined!");
                };
            }

            public static final float GetFloatValue(String s) {
                return switch (s) {
                    case "DISPLAYED_TOWER_FONT_SIZE" -> DISPLAYED_TOWER_FONT_SIZE;
                    case "GOLD_AND_LIVES_FONT_SIZE" -> GOLD_AND_LIVES_FONT_SIZE;
                    case "NON_GAMEPLAY_BTNS_FONT_SIZE" -> NON_GAMEPLAY_BTNS_FONT_SIZE;
                    default -> throw new IllegalArgumentException("Value not defined!");
                };
            }

            public static final IntPoint2D GetNonGameplayButtonPoint(String s) {
                return switch (s) {
                    case "MENU" -> MENU_BTN;
                    case "PAUSE" -> PAUSE_BTN;
                    default -> throw new IllegalArgumentException("Button not defined!");
                };
            }

            public static final IntPoint2D GetGameplayButtonPoint(String s) {
                return switch (s) {
                    case "TOWER" -> FIRST_TOWER_BTN;
                    case "SELL" -> SELL_BTN;
                    case "UPGRADE" -> UPGRADE_BTN;
                    default -> throw new IllegalArgumentException("Button not defined!");
                };
            }

            public static final IntPoint2D GetLabelPoint(String s) {
                return switch (s) {
                    case "SELL" -> SELL_LABEL;
                    case "UPGRADE" -> UPGRADE_LABEL;
                    case "SPRITE" -> SPRITE;
                    case "TOWER_NAME" -> TOWER_NAME;
                    case "TOWER_ID" -> TOWER_ID;
                    case "TOWER_TIER" -> TOWER_TIER;
                    case "LIVES" -> LIVES_LABEL;
                    case "GOLD" -> GOLD_LABEL;
                    default -> throw new IllegalArgumentException("Button not defined!");
                };
            }

            public static final IntPoint2D GetBackgroundPoint(String s) {
                return switch (s) {
                    case "DISPLAYED_TOWER" -> DISPLAYED_TOWER_BACKGROUND_RECT;
                    case "WAVE_INFO_BACKGROUND" -> WAVE_INFO_BACKGROUND;
                    case "GOLD_AND_LIVES_BACKGROUND" -> GOLD_AND_LIVES_BACKGROUND;
                    default -> throw new IllegalArgumentException("Background not defined!");
                };
            }
        }
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
                default -> throw new IllegalArgumentException("Button not defined!");
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
