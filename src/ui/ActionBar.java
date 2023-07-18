package ui;

import java.awt.Color;
import java.awt.Graphics;

import main.GameStates;
import scenes.Playing;

public class ActionBar extends Bar {

    private MyButton bMenu;

    public ActionBar(int x, int y, int width, int height, Playing playing) {
        super(x, y, width, height);

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
        bMenu = new MyButton("Menu", 10, 650, 100, 30);
    }

    private void drawButtons(Graphics g) {
        bMenu.draw(g);
    }
}
