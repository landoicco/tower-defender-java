package ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import main.GameStates;
import objects.Tile;
import scenes.Playing;

public class BottomBar {

    private MyButton bMenu;
    private Playing playing;
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
        }
    }

    public void mouseMoved(int x, int y) {
        bMenu.setMouseOver(false);
        if (bMenu.getBounds().contains(x, y)) {
            bMenu.setMouseOver(true);
        }
    }

    public void mousePressed(int x, int y) {
        if (bMenu.getBounds().contains(x, y)) {
            bMenu.setMousePressed(true);
        }
    }

    public void mouseReleased(int x, int y) {
        // Reset buttons
        bMenu.resetBooleans();
    }

    private void initButtons() {
        bMenu = new MyButton("Menu", 10, 680, 100, 30);

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
        drawTileButtons(g);
    }

    private void drawTileButtons(Graphics g) {
        for (MyButton b : tileButtons) {
            g.drawImage(getButtonImage(b.id), b.x, b.y, b.width, b.height, null);
        }
    }

    private BufferedImage getButtonImage(int id) {
        return playing.getTileManager().getSprite(id);
    }

}
