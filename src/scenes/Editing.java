package scenes;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import static helpers.Constants.*;

import helpers.LoadSave;
import main.Game;
import objects.PathPoint;
import objects.Tile;
import ui.ToolBar;

public class Editing extends GameScene implements SceneMethods {

    private int[][] lvl;
    private int mouseX, mouseY;
    private int lastTileX, lastTileY, lastTileId;
    private boolean drawSelected;
    private Tile selectedTile;
    private PathPoint start, end;
    private ToolBar toolBar;

    public Editing(Game game) {
        super(game);

        loadLevel();
        toolBar = new ToolBar(0, 640, 640, 160, this);
    }

    public void update() {
        updateTick();
    }

    @Override
    public void render(Graphics g) {
        drawLevel(g);
        toolBar.draw(g);
        drawSelectedTile(g);
        drawPathPoints(g);
    }

    @Override
    public void mouseClicked(int x, int y) {
        // Click on BottomBar
        if (y >= 640) {
            toolBar.mouseClicked(x, y);
        } else {
            changeTile(mouseX, mouseY);
        }
    }

    @Override
    public void mouseMoved(int x, int y) {
        if (y >= 640) {
            toolBar.mouseMoved(x, y);
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
            toolBar.mousePressed(x, y);
        }
    }

    @Override
    public void mouseReleased(int x, int y) {
        toolBar.mouseReleased(x, y);
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_R) {
            toolBar.rotateSprite();
        }
    }

    @Override
    public void mouseDragged(int x, int y) {
        if (y < 640) {
            changeTile(x, y);
        }
    }

    public void saveLevel() {
        LoadSave.SaveLevel("default_level", lvl, start, end);
        game.getPlaying().setLevel(lvl);
    }

    public void setSelectedTile(Tile tile) {
        this.selectedTile = tile;
        drawSelected = true;
    }

    private void loadLevel() {
        String lvlName = "default_level";
        lvl = LoadSave.GetLevelData(lvlName);
        ArrayList<PathPoint> points = LoadSave.GetLevelPathPoints(lvlName);
        start = points.get(0);
        end = points.get(1);
    }

    private void changeTile(int x, int y) {
        if (selectedTile == null) {
            return;
        }

        int tileX = x / 32;
        int tileY = y / 32;

        if (selectedTile.getId() >= 0) {

            if (lastTileX == tileX &&
                    lastTileY == tileY &&
                    lastTileId == selectedTile.getId())
                return;

            lastTileX = tileX;
            lastTileY = tileY;
            lastTileId = selectedTile.getId();

            lvl[tileY][tileX] = selectedTile.getId();

        } else {
            int id = lvl[tileY][tileX];
            if (game.getTileManager().getTile(id).getTileType() == Tiles.ROAD) {
                if (selectedTile.getId() == -1) {
                    start = new PathPoint(tileX, tileY);
                } else {
                    end = new PathPoint(tileX, tileY);
                }
            }
        }
    }

    private void drawLevel(Graphics g) {
        for (int i = 0; i < lvl.length; i++) {
            for (int j = 0; j < lvl[i].length; j++) {
                int id = lvl[i][j];
                if (isAnimation(id)) {
                    g.drawImage(getSprite(id, animationIndex), j * 32, i * 32, null);
                } else {
                    g.drawImage(getSprite(id), j * 32, i * 32, null);
                }
            }
        }

    }

    private void drawSelectedTile(Graphics g) {
        if (selectedTile != null && drawSelected) {
            g.drawImage(selectedTile.getSprite(), mouseX, mouseY, 32, 32, null);
        }
    }

    private void drawPathPoints(Graphics g) {
        if (start != null) {
            g.drawImage(toolBar.getStartPathImg(), start.getxCord() * 32, start.getyCord() * 32, null);
        }
        if (end != null) {
            g.drawImage(toolBar.getEndPathImg(), end.getxCord() * 32, end.getyCord() * 32, null);
        }
    }

}
