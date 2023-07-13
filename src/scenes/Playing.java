package scenes;

import java.awt.Graphics;

import helpers.LevelBuild;
import helpers.LoadSave;
import main.Game;
import managers.TileManager;
import objects.Tile;
import ui.BottomBar;

public class Playing extends GameScene implements SceneMethods {

    private int[][] lvl;
    private int mouseX, mouseY;
    private int lastTileX, lastTileY, lastTileId;
    private boolean drawSelected;
    private TileManager tileManager;
    private Tile selectedTile;
    private BottomBar bottomBar;

    public Playing(Game game) {
        super(game);

        lvl = LevelBuild.getLevelData();
        tileManager = new TileManager();
        bottomBar = new BottomBar(0, 640, 640, 100, this);

        createDefaultLevel();
    }

    public TileManager getTileManager() {
        return tileManager;
    }

    @Override
    public void render(Graphics g) {
        for (int i = 0; i < lvl.length; i++) {
            for (int j = 0; j < lvl[i].length; j++) {
                int id = lvl[i][j];
                g.drawImage(tileManager.getSprite(id), j * 32, i * 32, null);
            }
        }

        bottomBar.draw(g);
        drawSelectedTile(g);
    }

    @Override
    public void mouseClicked(int x, int y) {
        // Click on BottomBar
        if (y >= 640) {
            bottomBar.mouseClicked(x, y);
        } else {
            changeTile(mouseX, mouseY);
        }
    }

    @Override
    public void mouseMoved(int x, int y) {
        if (y >= 640) {
            bottomBar.mouseMoved(x, y);
            drawSelected = false;
        } else {
            mouseX = (x / 32) * 32;
            mouseY = (y / 32) * 32;
            drawSelected = true;
        }
    }

    @Override
    public void mousePressed(int x, int y) {
        if (y >= 640) {
            bottomBar.mousePressed(x, y);
        }
    }

    @Override
    public void mouseReleased(int x, int y) {
        bottomBar.mouseReleased(x, y);
    }

    @Override
    public void mouseDragged(int x, int y) {
        if (y < 640) {
            changeTile(x, y);
        }
    }

    public void setSelectedTile(Tile tile) {
        this.selectedTile = tile;
        drawSelected = true;
    }

    private void drawSelectedTile(Graphics g) {
        if (selectedTile != null && drawSelected) {
            g.drawImage(selectedTile.getSprite(), mouseX, mouseY, 32, 32, null);
        }
    }

    private void changeTile(int x, int y) {
        if (selectedTile != null) {
            int tileX = x / 32;
            int tileY = y / 32;

            if (lastTileX == tileX &&
                    lastTileY == tileY &&
                    lastTileId == selectedTile.getId())
                return;

            lastTileX = tileX;
            lastTileY = tileY;
            lastTileId = selectedTile.getId();

            lvl[tileY][tileX] = selectedTile.getId();
        }
    }

    private void createDefaultLevel() {
        int[] arr = new int[400];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = 0;
        }

        LoadSave.CreateLevel("Default", arr);
    }
}
