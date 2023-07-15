package ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import main.GameStates;
import objects.Tile;
import scenes.Playing;

public class BottomBar {

    private MyButton bMenu, bSave;
    private Playing playing;
    private Tile selectedTile;
    private ArrayList<MyButton> tileButtons = new ArrayList<>();

    int x, y, width, height;

    public BottomBar(int x, int y, int width, int height, Playing playing) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.playing = playing;

        initButtons();
    }

    public void draw(Graphics g) {
        g.setColor(new Color(63, 117, 6));
        g.fillRect(x, y, width, height);

        drawButtons(g);
    }

    public void mouseClicked(int x, int y) {
        if (bMenu.getBounds().contains(x, y)) {
            GameStates.setGameState(GameStates.MENU);
        } else if (bSave.getBounds().contains(x, y)) {
            saveLevel();
        } else {
            for (MyButton b : tileButtons) {
                if (b.getBounds().contains(x, y)) {
                    selectedTile = playing.getTileManager().getTile(b.id);
                    playing.setSelectedTile(selectedTile);
                    return;
                }
            }
        }
    }

    public void mouseMoved(int x, int y) {
        bMenu.setMouseOver(false);
        bSave.setMouseOver(false);

        // Reset all tile buttons
        for (MyButton b : tileButtons) {
            b.setMouseOver(false);
        }

        if (bMenu.getBounds().contains(x, y)) {
            bMenu.setMouseOver(true);
        } else if (bSave.getBounds().contains(x, y)) {
            bSave.setMouseOver(true);
        } else {
            for (MyButton b : tileButtons) {
                if (b.getBounds().contains(x, y)) {
                    b.setMouseOver(true);
                    return;
                }
            }
        }
    }

    public void mousePressed(int x, int y) {
        if (bMenu.getBounds().contains(x, y)) {
            bMenu.setMousePressed(true);
        } else if (bSave.getBounds().contains(x, y)) {
            bSave.setMousePressed(true);
        } else {
            for (MyButton b : tileButtons) {
                if (b.getBounds().contains(x, y)) {
                    b.setMousePressed(true);
                    return;
                }
            }
        }
    }

    public void mouseReleased(int x, int y) {
        // Reset buttons
        bMenu.resetBooleans();
        bSave.resetBooleans();

        // Reset tile buttons
        for (MyButton b : tileButtons) {
            b.resetBooleans();
        }
    }

    private void initButtons() {
        bMenu = new MyButton("Menu", 10, 650, 100, 30);
        bSave = new MyButton("Save Level", 10, 680, 100, 30);

        // Tile buttons properties
        int width = 50;
        int height = 50;
        int xStart = 300;
        int yStart = 650;
        int xOffset = 0;
        int id = 0;

        for (Tile tile : playing.getTileManager().tiles) {
            tileButtons.add(new MyButton(tile.getName(), xStart + xOffset, yStart, width, height, id++));

            // Set offset and space between tile buttons
            xOffset += width + 5;
        }
    }

    private void drawButtons(Graphics g) {
        bMenu.draw(g);
        bSave.draw(g);

        drawTileButtons(g);
        drawSelectedTile(g);
    }

    private void drawSelectedTile(Graphics g) {
        if (selectedTile != null) {
            g.drawImage(selectedTile.getSprite(), 550, 670, 50, 50, null);
            g.setColor(Color.BLACK);
            g.drawRect(550, 670, 50, 50);
        }
    }

    private void drawTileButtons(Graphics g) {
        for (MyButton b : tileButtons) {
            // Draw sprite
            g.drawImage(getButtonImage(b.id), b.x, b.y, b.width, b.height, null);

            // Set border color on MouseOver
            if (b.isMouseOver()) {
                g.setColor(Color.CYAN);
            } else {
                g.setColor(Color.WHITE);
            }

            // Set border color on MousePressed
            if (b.isMousePressed()) {
                g.setColor(Color.GREEN);
            }

            // Draw border
            g.drawRect(b.x + 1, b.y + 1, b.width - 2, b.height - 2);
            g.drawRect(b.x + 2, b.y + 2, b.width - 4, b.height - 4);

        }
    }

    private void saveLevel() {
        playing.saveLevel();
    }

    private BufferedImage getButtonImage(int id) {
        return playing.getTileManager().getSprite(id);
    }

}
