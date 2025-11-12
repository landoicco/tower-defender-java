package licaza.tdefender.demo.scenes;

import java.awt.*;
import java.util.Map;
import java.io.*;

import javax.sound.sampled.*;

import licaza.tdefender.demo.main.Game;
import licaza.tdefender.demo.main.GameStates;

import licaza.tdefender.engine.tools.gui.TextButton;
import licaza.tdefender.engine.commons.api.SceneMethods;
import licaza.tdefender.engine.commons.misc.ColorPalette;

import static licaza.tdefender.engine.tools.helpers.ResourcesLoader.*;
import static licaza.tdefender.demo.configs.Colors.*;

public class Menu extends GameScene implements SceneMethods {
    private static final float BUTTON_FONT_SIZE = 50f;
    private static final float HEADER_FONT_SIZE = 80f;
    private static final float FOOTER_FONT_SIZE = 20f;

    private static final Clip hoverClip = Audio.LoadClipFromFile(new File("equip.wav"));
    private static final Clip clickClip = Audio.LoadClipFromFile(new File("use-item.wav"));
    private static final Clip menuClip = Audio.LoadClipFromFile(new File("menu.wav"));

    private static final Font headerFont = Fonts.LoadFontFromFile(new File("caveat-brush.ttf"));
    private static final Font baseFont = Fonts.LoadFontFromFile(new File("patrick-hand.ttf"));

    // Set color palette
    private static final Color BG_COLOR = GetColorFromPalette(ColorPalette.BACKGROUND);
    private static final Color PRIMARY_COLOR = GetColorFromPalette(ColorPalette.PRIMARY);
    private static final Color ACCENT_COLOR = GetColorFromPalette(ColorPalette.ACCENT);
    private static final Color TEXT_COLOR = GetColorFromPalette(ColorPalette.TEXT);

    private static final Map<ColorPalette, Color> colorPalette = GetColorMap();

    private TextButton bPlaying, bEdit, bSettings, bQuit;

    public Menu(Game game) {
        super(game);

        initButtons();
    }

    @Override
    public void render(Graphics g) {
        // Play background music
        menuClip.loop(Clip.LOOP_CONTINUOUSLY); // Loop indefinitely
        menuClip.start();

        // Draw background
        g.setColor(BG_COLOR);
        g.fillRect(0, 0, 640, 800);

        drawHeader(g);
        drawButtons(g);
        drawFooter(g);
    }

    @Override
    public void mouseClicked(int x, int y) {
        if (bPlaying.getBounds().contains(x, y)) {
            menuClip.stop();
            clickClip.start();
            GameStates.setGameState(GameStates.PLAYING);
        }
        if (bEdit.getBounds().contains(x, y)) {
            clickClip.start();
            GameStates.setGameState(GameStates.EDIT);
        }
        if (bSettings.getBounds().contains(x, y)) {
            clickClip.start();
            GameStates.setGameState(GameStates.SETTINGS);
        }
        if (bQuit.getBounds().contains(x, y)) {
            clickClip.start();
            System.exit(0);
        }
    }

    @Override
    public void mouseMoved(int x, int y) {
        bPlaying.setMouseOver(false);
        bEdit.setMouseOver(false);
        bSettings.setMouseOver(false);
        bQuit.setMouseOver(false);

        if (bPlaying.getBounds().contains(x, y)) {
            hoverClip.start();
            bPlaying.setMouseOver(true);
        } else if (bEdit.getBounds().contains(x, y)) {
            hoverClip.start();
            bEdit.setMouseOver(true);
        } else if (bSettings.getBounds().contains(x, y)) {
            hoverClip.start();
            bSettings.setMouseOver(true);
        } else if (bQuit.getBounds().contains(x, y)) {
            hoverClip.start();
            bQuit.setMouseOver(true);
        } else {
            // Restart position of hover audio clip
            hoverClip.setFramePosition(0);
        }
    }

    @Override
    public void mousePressed(int x, int y) {
        if (bPlaying.getBounds().contains(x, y)) {
            bPlaying.setMousePressed(true);
        }
        if (bEdit.getBounds().contains(x, y)) {
            bEdit.setMousePressed(true);
        }
        if (bSettings.getBounds().contains(x, y)) {
            bSettings.setMousePressed(true);
        }
        if (bQuit.getBounds().contains(x, y)) {
            bQuit.setMousePressed(true);
        }
    }

    @Override
    public void mouseReleased(int x, int y) {
        // Reset buttons
        bPlaying.resetBooleans();
        bEdit.resetBooleans();
        bSettings.resetBooleans();
        bQuit.resetBooleans();

        // Reset click audio clip
        clickClip.setFramePosition(0);
    }

    @Override
    public void mouseDragged(int x, int y) {
    }

    private void initButtons() {
        int height, width, xPos, yPos, padding;
        height = 50;
        width = 120;
        xPos = 270;
        yPos = 300;
        padding = 20;

        bPlaying = new TextButton("Play", xPos, yPos, width, height, colorPalette);
        bEdit = new TextButton("Edit", xPos, yPos + (height + padding), width, height, colorPalette);
        bSettings = new TextButton("Credits", xPos, yPos + 2 * (height + padding), width, height, colorPalette);
        bQuit = new TextButton("Quit", xPos, yPos + 3 * (height + padding), width, height, colorPalette);
    }

    private void drawButtons(Graphics g) {
        g.setColor(PRIMARY_COLOR);
        g.setFont(baseFont.deriveFont(BUTTON_FONT_SIZE));

        bPlaying.draw(g);
        bEdit.draw(g);
        bSettings.draw(g);
        bQuit.draw(g);
    }

    private void drawHeader(Graphics g) {
        g.setFont(headerFont.deriveFont(HEADER_FONT_SIZE));
        g.setColor(TEXT_COLOR);

        int xPos = 120, yPos = 100, yOffset = 60, xOffset = 30;

        g.drawString("Cannons, spells", xPos, yPos);
        g.drawString("& \"zombies\"", xPos + xOffset, (yPos + yOffset));
        g.drawString("(... a draft)", xPos + xOffset, yPos + (yOffset * 2));
    }

    private void drawFooter(Graphics g) {
        g.setColor(PRIMARY_COLOR);
        g.setFont(baseFont.deriveFont(FOOTER_FONT_SIZE));

        g.drawString("v0.0.1", 580, 790);
    }
}
