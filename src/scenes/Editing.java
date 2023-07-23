package scenes;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import helpers.LoadSave;
import main.Game;
import objects.Tile;
import ui.ToolBar;

public class Editing extends GameScene implements SceneMethods {

    private int[][] lvl;
    private int mouseX, mouseY, animationIndex, tick;
    private int lastTileX, lastTileY, lastTileId;
    private int ANIMATION_SPEED = 20;
    private boolean drawSelected;
    private Tile selectedTile;
    private ToolBar toolBar;

    public Editing(Game game) {
        super(game);

        loadLevel();
        toolBar = new ToolBar(0, 640, 640, 100, this);
    }

    @Override
    public void render(Graphics g) {
        updateTick();

        drawLevel(g);
        toolBar.draw(g);
        drawSelectedTile(g);
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
        LoadSave.SaveLevel("default_level", lvl);
        game.getPlaying().setLevel(lvl);
    }

    private void loadLevel() {
        lvl = LoadSave.GetLevelData("default_level");
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

    private boolean isAnimation(int id) {
        return game.getTileManager().isAnimatedSprite(id);
    }

    private void drawSelectedTile(Graphics g) {
        if (selectedTile != null && drawSelected) {
            g.drawImage(selectedTile.getSprite(), mouseX, mouseY, 32, 32, null);
        }
    }

    // Using magic number 4 due we know we only use 4 sprites per animation
    private void updateTick() {
        tick++;
        if (tick > ANIMATION_SPEED) {
            tick = 0;
            animationIndex++;
            if (animationIndex >= 4) {
                animationIndex = 0;
            }
        }
    }

    private BufferedImage getSprite(int spriteId) {
        return game.getTileManager().getSprite(spriteId);
    }

    private BufferedImage getSprite(int spriteId, int animationIndex) {
        return game.getTileManager().getAnimatedSprite(spriteId, animationIndex);
    }

    public void setSelectedTile(Tile tile) {
        this.selectedTile = tile;
        drawSelected = true;
    }

}
