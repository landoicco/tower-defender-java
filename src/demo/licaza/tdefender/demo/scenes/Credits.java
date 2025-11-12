package licaza.tdefender.demo.scenes;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.io.File;
import java.util.Map;

import javax.sound.sampled.*;

import static licaza.tdefender.engine.tools.helpers.ResourcesLoader.*;
import static licaza.tdefender.demo.configs.Colors.*;

import licaza.tdefender.engine.tools.gui.TextButton;
import licaza.tdefender.engine.commons.api.SceneMethods;
import licaza.tdefender.engine.commons.misc.ColorPalette;

import licaza.tdefender.demo.main.Game;
import licaza.tdefender.demo.main.GameStates;

public class Credits extends GameScene implements SceneMethods {

    private static final Clip hoverClip = Audio.LoadClipFromFile(new File("equip.wav"));
    private static final Clip clickClip = Audio.LoadClipFromFile(new File("use-item.wav"));
    private static final Clip menuClip = Audio.LoadClipFromFile(new File("menu.wav"));

    private static final Color BG_COLOR = GetColorFromPalette(ColorPalette.BACKGROUND);
    private static final Color PRIMARY_COLOR = GetColorFromPalette(ColorPalette.PRIMARY);
    private static final Color TEXT_COLOR = GetColorFromPalette(ColorPalette.TEXT);

    private static final Font HEADER_FONT = Fonts.LoadFontFromFile(new File("caveat-brush.ttf"));
    private static final Font BASE_FONT = Fonts.LoadFontFromFile(new File("patrick-hand.ttf"));

    private static final Map<ColorPalette, Color> colorPalette = GetColorMap();
    private static final float HEADER_FONT_SIZE = 80f;

    private TextButton bMenu;

    public Credits(Game game) {
        super(game);

        // Init menu button
        bMenu = new TextButton("Menu", 270, 745, 100, 30, colorPalette);
    }

    @Override
    public void render(Graphics g) {
        // Draw background
        g.setColor(BG_COLOR);
        g.fillRect(0, 0, 640, 800);

        // Draw credits
        drawCodersSection(g);
        drawAssetsSection(g);
        drawFooterSection(g);
    }

    @Override
    public void mouseClicked(int x, int y) {
        if (bMenu.getBounds().contains(x, y)) {
            // menuClip.stop();
            // clickClip.start();
            System.out.println("Go home");
            GameStates.setGameState(GameStates.MENU);
        }
    }

    @Override
    public void mouseMoved(int x, int y) {
        bMenu.setMouseOver(false);

        if (bMenu.getBounds().contains(x, y)) {
            hoverClip.start();
            bMenu.setMouseOver(true);
        } else {
            hoverClip.setFramePosition(0);
        }
    }

    @Override
    public void mousePressed(int x, int y) {
        if (bMenu.getBounds().contains(x, y)) {
            bMenu.setMousePressed(true);
        }
    }

    @Override
    public void mouseReleased(int x, int y) {
        bMenu.resetBooleans();

        clickClip.setFramePosition(0);
    }

    @Override
    public void mouseDragged(int x, int y) {
    }

    private void drawCodersSection(Graphics g) {
        float fontSize = 60f;

        // Header
        g.setColor(PRIMARY_COLOR);
        g.setFont(HEADER_FONT.deriveFont(HEADER_FONT_SIZE));
        g.drawString("Code & Design", 120, 100);

        // Coder
        g.setColor(TEXT_COLOR);
        g.setFont(BASE_FONT.deriveFont(fontSize));
        g.drawString("Lando Icaza C.", 180, 170);
    }

    private void drawAssetsSection(Graphics g) {
        float subheaderFontSize = 40f;
        float fontSize = 20f;
        int subheadersYPos = 325;

        // Header
        g.setColor(PRIMARY_COLOR);
        g.setFont(HEADER_FONT.deriveFont(HEADER_FONT_SIZE));
        g.drawString("Assets", 230, 270);

        // Audio
        g.setColor(PRIMARY_COLOR);
        g.setFont(HEADER_FONT.deriveFont(subheaderFontSize));
        g.drawString("Music", 120, subheadersYPos);

        g.setColor(TEXT_COLOR);
        g.setFont(BASE_FONT.deriveFont(20f));
        g.drawString("051_use_item_01.wav", 90, 350);
        g.drawString("070_Equip_10.wav", 90, 370);
        g.drawString("Menu-3-1.mp3", 90, 390);

        // Sprites
        g.setColor(PRIMARY_COLOR);
        g.setFont(HEADER_FONT.deriveFont(subheaderFontSize));
        g.drawString("Sprites", 400, subheadersYPos);

        g.setColor(TEXT_COLOR);
        g.setFont(BASE_FONT.deriveFont(fontSize));
        g.drawString("spriteAtlas.png", 385, 350);
        g.drawString("sprite_legacy.png.todo", 385, 370);
        g.drawString("sprite_roads.png.todo", 385, 390);

        // Fonts
        g.setColor(PRIMARY_COLOR);
        g.setFont(HEADER_FONT.deriveFont(subheaderFontSize));
        g.drawString("Fonts", 280, 450);

        g.setColor(TEXT_COLOR);
        g.setFont(BASE_FONT.deriveFont(fontSize));
        g.drawString("Caveat Brush", 270, 470);
        g.drawString("Patrick Hand", 270, 490);
    }

    private void drawFooterSection(Graphics g) {
        float fontSize = 30f;

        g.setFont(BASE_FONT.deriveFont(fontSize));
        g.drawString("Made with love and Java 17", 170, 740);

        // Draw menu button
        bMenu.draw(g);
    }

}
