package ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import helpers.LoadSave;
import main.GameStates;
import objects.Tile;
import scenes.Editing;

public class ToolBar extends Bar {

    private MyButton bMenu, bSave;
    private MyButton bPathStart, bPathEnd;
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

        initPathImages();
        initButtons();
    }

    public void draw(Graphics g) {
        g.setColor(new Color(63, 117, 6));
        g.fillRect(x, y, width, height);

        drawButtons(g);
    }

    public BufferedImage getStartPathImg() {
        return pathStart;
    }

    public BufferedImage getEndPathImg() {
        return pathEnd;
    }

    public void mouseClicked(int x, int y) {
        if (bMenu.getBounds().contains(x, y)) {
            GameStates.setGameState(GameStates.MENU);
        } else if (bSave.getBounds().contains(x, y)) {
            saveLevel();
        } else if (bGrass.getBounds().contains(x, y)) {
            selectedTile = editing.getGame().getTileManager().getTile(bGrass.id);
            editing.setSelectedTile(selectedTile);
        } else if (bWater.getBounds().contains(x, y)) {
            selectedTile = editing.getGame().getTileManager().getTile(bWater.id);
            editing.setSelectedTile(selectedTile);
        } else if (bPathStart.getBounds().contains(x, y)) {
            selectedTile = new Tile(pathStart, -1, -1);
            editing.setSelectedTile(selectedTile);
        } else if (bPathEnd.getBounds().contains(x, y)) {
            selectedTile = new Tile(pathEnd, -2, -2);
            editing.setSelectedTile(selectedTile);
        } else {
            for (MyButton b : tilesMap.keySet()) {
                if (b.getBounds().contains(x, y)) {
                    selectedTile = tilesMap.get(b).get(0);
                    editing.setSelectedTile(selectedTile);
                    currentButton = b;
                    currentBtnIndex = 0;
                    return;
                }
            }
        }
    }

    public void mouseMoved(int x, int y) {
        bMenu.setMouseOver(false);
        bSave.setMouseOver(false);

        // Reset basic tile/path definer buttons
        bGrass.setMouseOver(false);
        bWater.setMouseOver(false);
        bPathStart.setMouseOver(false);
        bPathEnd.setMouseOver(false);

        // Reset all tile buttons
        for (MyButton b : tilesMap.keySet()) {
            b.setMouseOver(false);
        }

        if (bMenu.getBounds().contains(x, y)) {
            bMenu.setMouseOver(true);
        } else if (bSave.getBounds().contains(x, y)) {
            bSave.setMouseOver(true);
        } else if (bGrass.getBounds().contains(x, y)) {
            bGrass.setMouseOver(true);
        } else if (bWater.getBounds().contains(x, y)) {
            bWater.setMouseOver(true);
        } else if (bPathStart.getBounds().contains(x, y)) {
            bPathStart.setMouseOver(true);
        } else if (bPathEnd.getBounds().contains(x, y)) {
            bPathEnd.setMouseOver(true);
        } else {
            for (MyButton b : tilesMap.keySet()) {
                if (b.getBounds().contains(x, y)) {
                    b.setMouseOver(true);
                }
            }
        }
    }

    public void mousePressed(int x, int y) {
        if (bMenu.getBounds().contains(x, y)) {
            bMenu.setMousePressed(true);
        } else if (bSave.getBounds().contains(x, y)) {
            bSave.setMousePressed(true);
        } else if (bGrass.getBounds().contains(x, y)) {
            bGrass.setMousePressed(true);
        } else if (bWater.getBounds().contains(x, y)) {
            bWater.setMousePressed(true);
        } else if (bPathStart.getBounds().contains(x, y)) {
            bPathStart.setMousePressed(true);
        } else if (bPathEnd.getBounds().contains(x, y)) {
            bPathEnd.setMousePressed(true);
        } else {
            for (MyButton b : tilesMap.keySet()) {
                if (b.getBounds().contains(x, y)) {
                    b.setMousePressed(true);
                }
            }
        }
    }

    public void mouseReleased(int x, int y) {
        // Reset buttons
        bMenu.resetBooleans();
        bSave.resetBooleans();

        // Reset basic tile/path definer buttons
        bGrass.resetBooleans();
        bWater.resetBooleans();
        bPathStart.resetBooleans();
        bPathEnd.resetBooleans();

        // Reset tile buttons
        for (MyButton b : tilesMap.keySet()) {
            b.resetBooleans();
        }
    }

    public void rotateSprite() {
        currentBtnIndex++;
        if (currentBtnIndex >= tilesMap.get(currentButton).size()) {
            currentBtnIndex = 0;
        }
        selectedTile = tilesMap.get(currentButton).get(currentBtnIndex);
        editing.setSelectedTile(selectedTile);
    }

    private void initButtons() {
        bMenu = new MyButton("Menu", 10, 650, 100, 30);
        bSave = new MyButton("Save Level", 10, 680, 100, 30);

        // Tile buttons properties
        int width = 50;
        int height = 50;
        int xStart = 120;
        int yStart = 650;
        int xOffset = (int) (width * 1.1f);
        int id = 0;

        // Init basic tile buttons
        bGrass = new MyButton("Grass", xStart, yStart, width, height, id++);
        bWater = new MyButton("Water", xStart + xOffset, yStart, width, height, id++);

        // Init tile ArrayLists
        initMapButtons(bPlainRoads, editing.getGame().getTileManager().getPlainRoads(), xStart, yStart, xOffset, width,
                height, id++);
        initMapButtons(bCornerRoads, editing.getGame().getTileManager().getCornerRoads(), xStart, yStart, xOffset,
                width, height, id++);
        initMapButtons(bWaterCorners, editing.getGame().getTileManager().getCorners(), xStart, yStart, xOffset, width,
                height, id++);
        initMapButtons(bWaterCoasts, editing.getGame().getTileManager().getCoasts(), xStart, yStart, xOffset, width,
                height, id++);
        initMapButtons(bWaterIslands, editing.getGame().getTileManager().getIslands(), xStart, yStart, xOffset, width,
                height, id++);

        // Init path definer buttons
        bPathStart = new MyButton("Start", xStart, yStart + xOffset, width, height, id++);
        bPathEnd = new MyButton("End", xStart + xOffset, yStart + xOffset, width, height, id++);
    }

    private void initPathImages() {
        pathStart = LoadSave.GetSpriteAtlas("spriteatlas_legacy").getSubimage(7 * 32, 2 * 32, 32, 32);
        pathEnd = LoadSave.GetSpriteAtlas("spriteatlas_legacy").getSubimage(8 * 32, 2 * 32, 32, 32);
    }

    private void drawButtons(Graphics g) {
        bMenu.draw(g);
        bSave.draw(g);

        // Draw basic tile buttons
        drawBasicTileButtons(g, bGrass);
        drawBasicTileButtons(g, bWater);

        // Draw Map tile buttons
        drawMapButtons(g);
        drawSelectedTile(g);

        // Draw path definer buttons
        drawPathButton(g, bPathStart, pathStart);
        drawPathButton(g, bPathEnd, pathEnd);
    }

    private void drawPathButton(Graphics g, MyButton b, BufferedImage img) {
        g.drawImage(img, b.x, b.y, b.width, b.height, null);
        drawButtonFeedback(g, b);
    }

    private void drawBasicTileButtons(Graphics g, MyButton b) {
        g.drawImage(getButtonImage(b.id), b.x, b.y, b.width, b.height, null);
        drawButtonFeedback(g, b);
    }

    private void drawMapButtons(Graphics g) {

        for (Map.Entry<MyButton, ArrayList<Tile>> entry : tilesMap.entrySet()) {
            MyButton b = entry.getKey();
            BufferedImage img = entry.getValue().get(0).getSprite();

            g.drawImage(img, b.x, b.y, b.width, b.height, null);

            drawButtonFeedback(g, b);
        }
    }

    private void drawSelectedTile(Graphics g) {
        if (selectedTile != null) {
            g.drawImage(selectedTile.getSprite(), 550, 670, 50, 50, null);
            g.setColor(Color.BLACK);
            g.drawRect(550, 670, 50, 50);
        }
    }

    private void initMapButtons(MyButton b, ArrayList<Tile> list, int x, int y, int xOffset, int width, int height,
            int id) {
        b = new MyButton("", (x + xOffset * id), y, width, height, id);
        tilesMap.put(b, list);
    }

    private void saveLevel() {
        editing.saveLevel();
    }

    private BufferedImage getButtonImage(int id) {
        return editing.getGame().getTileManager().getSprite(id);
    }

}
