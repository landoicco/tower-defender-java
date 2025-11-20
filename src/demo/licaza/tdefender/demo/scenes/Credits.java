package licaza.tdefender.demo.scenes;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Map;

import javax.sound.sampled.*;

import static licaza.tdefender.demo.configs.MediaSource.*;
import static licaza.tdefender.demo.configs.UIPositions.Credits.*;
import static licaza.tdefender.demo.configs.Colors.*;

import licaza.tdefender.engine.tools.gui.TextButton;
import licaza.tdefender.engine.commons.api.SceneMethods;
import licaza.tdefender.engine.commons.misc.ColorPalette;
import licaza.tdefender.engine.commons.misc.IntPoint2D;

import licaza.tdefender.demo.main.Game;
import licaza.tdefender.demo.main.GameStates;

public class Credits extends GameScene implements SceneMethods {

    private static final Clip hoverClip = Sounds.GetAudioClip("HOVER");
    private static final Clip clickClip = Sounds.GetAudioClip("CLICK");

    private static final Color BG_COLOR = GetColorFromPalette(ColorPalette.BACKGROUND);
    private static final Color PRIMARY_COLOR = GetColorFromPalette(ColorPalette.PRIMARY);
    private static final Color TEXT_COLOR = GetColorFromPalette(ColorPalette.TEXT);

    private static final Font HEADER_FONT = Fonts.GetHeaderFont();
    private static final Font BASE_FONT = Fonts.GetBaseFont();

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
        IntPoint2D headerPos, contentsPos;
        headerPos = GetPanel1ElementPoint("HEADER");
        contentsPos = GetPanel1ElementPoint("BASE");

        // Header
        g.setColor(PRIMARY_COLOR);
        g.setFont(HEADER_FONT.deriveFont(HEADER_FONT_SIZE));
        g.drawString("Code & Design", headerPos.x(), headerPos.y());

        // Coder
        g.setColor(TEXT_COLOR);
        g.setFont(BASE_FONT.deriveFont(fontSize));
        g.drawString("Lando Icaza C.", contentsPos.x(), contentsPos.y());
    }

    private void drawAssetsSection(Graphics g) {
        float subheaderFontSize = GetPanel2FontSize("SUBHEADER");
        float fontSize = GetPanel2FontSize("TEXT");
        int textYOffset = GetTextYOffset();

        // Header
        IntPoint2D headerPos = GetPanel2ElementPoint("HEADER");
        g.setColor(PRIMARY_COLOR);
        g.setFont(HEADER_FONT.deriveFont(HEADER_FONT_SIZE));
        g.drawString("Assets", headerPos.x(), headerPos.y());

        // Music
        IntPoint2D musicSubheaderPos = GetPanel2ElementPoint("MUSIC_SUBHEADER");
        g.setColor(PRIMARY_COLOR);
        g.setFont(HEADER_FONT.deriveFont(subheaderFontSize));
        g.drawString("Music", musicSubheaderPos.x(), musicSubheaderPos.y());

        IntPoint2D musicTextPos = GetPanel2ElementPoint("MUSIC_TEXT");
        g.setColor(TEXT_COLOR);
        g.setFont(BASE_FONT.deriveFont(20f));
        g.drawString("051_use_item_01.wav", musicTextPos.x(), musicTextPos.y());
        g.drawString("070_Equip_10.wav", musicTextPos.x(), (musicTextPos.y() + textYOffset));
        g.drawString("Menu-3-1.mp3", musicTextPos.x(), (musicTextPos.y() + (textYOffset * 2)));
        g.drawString("Menu1.mp3", musicTextPos.x(), (musicTextPos.y() + (textYOffset * 3)));

        // Sprites
        IntPoint2D spritesSubheaderPos = GetPanel2ElementPoint("SPRITES_SUBHEADER");
        g.setColor(PRIMARY_COLOR);
        g.setFont(HEADER_FONT.deriveFont(subheaderFontSize));
        g.drawString("Sprites", spritesSubheaderPos.x(), spritesSubheaderPos.y());

        IntPoint2D spritesTextPos = GetPanel2ElementPoint("SPRITES_TEXT");
        g.setColor(TEXT_COLOR);
        g.setFont(BASE_FONT.deriveFont(fontSize));
        g.drawString("spriteAtlas.png", spritesTextPos.x(), spritesTextPos.y());
        g.drawString("sprite_legacy.png.todo", spritesTextPos.x(), (spritesTextPos.y() + textYOffset));
        g.drawString("sprite_roads.png.todo", spritesTextPos.x(), (spritesTextPos.y() + (textYOffset * 2)));

        // Fonts
        IntPoint2D fontSubheaderPos = GetPanel2ElementPoint("FONTS_SUBHEADER");
        g.setColor(PRIMARY_COLOR);
        g.setFont(HEADER_FONT.deriveFont(subheaderFontSize));
        g.drawString("Fonts", fontSubheaderPos.x(), fontSubheaderPos.y());

        IntPoint2D fontTextPos = GetPanel2ElementPoint("FONTS_TEXT");
        g.setColor(TEXT_COLOR);
        g.setFont(BASE_FONT.deriveFont(fontSize));
        g.drawString("Caveat Brush", fontTextPos.x(), fontTextPos.y());
        g.drawString("Patrick Hand", fontTextPos.x(), (fontTextPos.y() + textYOffset));
    }

    private void drawFooterSection(Graphics g) {
        float fontSize = GetPanel3FontSize();

        IntPoint2D messagePos = GetPanel3ElementPoint();
        g.setFont(BASE_FONT.deriveFont(fontSize));
        g.drawString("Made with love and Java 17", messagePos.x(), messagePos.y());

        // Draw menu button
        bMenu.draw(g);
    }

}
