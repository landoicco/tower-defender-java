package licaza.tdefender.demo.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.function.Consumer;
import java.util.*;

import javax.sound.sampled.Clip;

import licaza.tdefender.engine.tools.helpers.LoadSave;
import licaza.tdefender.engine.tools.gui.TextButton;
import licaza.tdefender.engine.tools.gui.MyButton;
import licaza.tdefender.engine.tools.gui.bar.TileButtonsRow;
import licaza.tdefender.engine.tools.gui.bar.Panel;
import licaza.tdefender.engine.commons.objects.Tile;
import licaza.tdefender.engine.commons.objects.SpriteSheet;
import licaza.tdefender.engine.commons.misc.ColorPalette;
import licaza.tdefender.engine.commons.misc.IntPoint2D;

import licaza.tdefender.demo.main.GameStates;
import licaza.tdefender.demo.scenes.Editing;

import static licaza.tdefender.demo.configs.Colors.*;
import static licaza.tdefender.demo.configs.MediaSource.*;
import static licaza.tdefender.demo.configs.UIPositions.Editing.ToolBar.*;

public final class ToolBar extends Bar {

    private static final Color BACKGROUND_COLOR = GetColorFromPalette(ColorPalette.BACKGROUND);
    private static final Color PRIMARY_COLOR = GetColorFromPalette(ColorPalette.PRIMARY);
    private static final Color ACCENT_THREE = GetColorFromPalette(ColorPalette.ACCENT_THREE);
    private static final Color TEXT_COLOR = GetColorFromPalette(ColorPalette.TEXT);
    private static final Map COLOR_MAP = GetColorMap();

    private static final Clip HOVER_CLIP = Sounds.GetAudioClip("HOVER");
    private static final Clip CLICK_CLIP = Sounds.GetAudioClip("CLICK");


    private static final Font BASE_FONT = Fonts.GetBaseFont();
    private static final Font HEADER_FONT = Fonts.GetHeaderFont();

    private Panel tbrPanel;
    private TextButton bMenu, bSave;
    private MyButton bPathStart, bPathEnd;

    private MyButton bNewSand, bNewGrass, bNewStone, bNewIce, bNewChalk;

    private BufferedImage pathStart, pathEnd;
    private Tile selectedTile;
    private Editing editing;

    private Map<MyButton, ArrayList<Tile>> tilesMap = new HashMap<>();
    private MyButton bGrass, bWater, bPlainRoads, bCornerRoads, bWaterCorners, bWaterCoasts, bWaterIslands,
            currentButton;
    private int currentBtnIndex;

    public ToolBar(int x, int y, int width, int height, Editing editing) {
        super(x, y, width, height);

        this.editing = editing;

        initMenuButtons();
        initTileButtonsPanel();
    }

    public void draw(Graphics g) {
        g.setColor(BACKGROUND_COLOR);
        g.fillRect(x, y, width, height);

        drawTileDescriptor(g);
        drawMenuButtons(g);

        tbrPanel.draw(g);
    }

    public BufferedImage getStartPathImg() {
        return pathStart;
    }

    public BufferedImage getEndPathImg() {
        return pathEnd;
    }

    public void mouseClicked(int x, int y) {
        if (bMenu.getBounds().contains(x, y)) {
            CLICK_CLIP.start();
            GameStates.setGameState(GameStates.MENU);
        } else if (bSave.getBounds().contains(x, y)) {
            CLICK_CLIP.start();
            saveLevel();
        } else {
            tbrPanel.tileButtons().forEach(tb -> {
                    System.out.println("Mouse clicked on tile buttons");
                    if (tb.getBounds().contains(x, y)) {
                        selectedTile = tilesMap.get(tb).get(0);
                        editing.setSelectedTile(selectedTile);
                        currentButton = tb;
                        currentBtnIndex = 0;
                        return;
                    }
                });
        }
    }

    public void mouseMoved(int x, int y) {
        bMenu.setMouseOver(false);
        bSave.setMouseOver(false);

        // Reset all tile buttons
        tbrPanel.tileButtons()
            .forEach(tb -> tb.setMouseOver(false));

        if (bMenu.getBounds().contains(x, y)) {
            HOVER_CLIP.start();
            bMenu.setMouseOver(true);
        } else if (bSave.getBounds().contains(x, y)) {
            HOVER_CLIP.start();
            bSave.setMouseOver(true);
        } else {
            HOVER_CLIP.setFramePosition(0);

            // New Stream approach
            tbrPanel.tileButtons().forEach(tb -> {
                    if(tb.getBounds().contains(x, y)) {
                        System.out.println("Mouse over tile button");
                        tb.setMouseOver(true);
                        return;
                    }
                });
        }
    }

    public void mousePressed(int x, int y) {
        if (bMenu.getBounds().contains(x, y)) {
            bMenu.setMousePressed(true);
        } else if (bSave.getBounds().contains(x, y)) {
            bSave.setMousePressed(true);
        } else {
            tbrPanel.tileButtons().forEach(tb -> {
                    if (tb.getBounds().contains(x, y)) {
                        tb.setMousePressed(true);
                    }
                });
        }
    }

    public void mouseReleased(int x, int y) {
        // Reset audio effects
        CLICK_CLIP.setFramePosition(0);

        // Reset buttons
        bMenu.resetBooleans();
        bSave.resetBooleans();
        tbrPanel.tileButtons().forEach(tb -> {
                tb.resetBooleans();
            });
    }

    public void rotateSprite() {
        currentBtnIndex++;
        if (currentBtnIndex >= tilesMap.get(currentButton).size()) {
            currentBtnIndex = 0;
        }
        selectedTile = tilesMap.get(currentButton).get(currentBtnIndex);
        editing.setSelectedTile(selectedTile);
    }

    private final void initTileButtonsPanel() {
        IntPoint2D pos1 = GetElementPoint("FIRST_TILE_BTN");
        IntPoint2D pos2 = new IntPoint2D(GetElementPoint("FIRST_TILE_BTN").x(),
                                         GetElementPoint("FIRST_TILE_BTN").y() + 50);

        // get tiles from TileManager
        Map<String, SpriteSheet> tilemap = editing.getGame().getTileManager().tilemap();

        // init spritesheets
        SpriteSheet s1 = tilemap.get("SAND");

        // Create tbr's
        TileButtonsRow tbr1 = new TileButtonsRow(s1, pos1, COLOR_MAP, 32, 50);
        TileButtonsRow tbr2 = new TileButtonsRow(s1, pos2, COLOR_MAP, 32, 50);

        // Build Panel
        tbrPanel = new Panel.Builder(pos1)
            .setTileButtonsOffsets(50, 20)
            .addTileButtonsRow(tbr1)
            .addTileButtonsRow(tbr2)
            .build();
    }

    private void initMenuButtons() {
        IntPoint2D pMenuButton = GetElementPoint("MENU_BTN"),
            pSaveButton = GetElementPoint("SAVE_BTN");
        int buttonsWidth = GetValue("BTNS_WIDTH"),
            buttonsHeight = GetValue("BTNS_HEIGHT");

        bMenu = new TextButton("Menu", pMenuButton.x(), pMenuButton.y(), buttonsWidth,
                               buttonsHeight, COLOR_MAP);
        bSave = new TextButton("Save", pSaveButton.x(), pSaveButton.y(), buttonsWidth,
                               buttonsHeight, COLOR_MAP);
    }

    private void drawTileDescriptor(Graphics g) {
        // Background
        IntPoint2D pBackground = GetElementPoint("TILE_DESCRIPTOR"),
            pDescription = GetElementPoint("TILE_DESCRIPTION");
        int backgroundWidth = GetValue("TILE_DESCRIPTOR_WIDTH"),
            backgroundHeight = GetValue("TILE_DESCRIPTOR_HEIGHT");
        float fontSize = GetFloatValue("TILE_DESCRIPTOR_FONT_SIZE");
        g.setColor(ACCENT_THREE);
        g.fillRect(pBackground.x(), pBackground.y(), backgroundWidth, backgroundHeight);

        // Description
        g.setFont(HEADER_FONT.deriveFont(fontSize));
        g.setColor(TEXT_COLOR);
        g.drawString("description...", pDescription.x(), pDescription.y());
    }

    private void drawMenuButtons(Graphics g) {
        g.setFont(BASE_FONT.deriveFont(30f)); //TODO: change this magic number

        bMenu.draw(g);
        bSave.draw(g);
    }

    private void saveLevel() {
        editing.saveLevel();
    }

    private BufferedImage getButtonImage(int id) {
        return editing.getGame().getTileManager().getSprite(id);
    }
}
