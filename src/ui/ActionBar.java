package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import helpers.Constants.Towers;
import main.GameStates;
import objects.Tower;
import scenes.Playing;

public class ActionBar extends Bar {

    private MyButton bMenu;
    private Playing playing;
    private Tower selectedTower, displayedTower;
    private MyButton[] towerButtons;

    public ActionBar(int x, int y, int width, int height, Playing playing) {
        super(x, y, width, height);
        this.playing = playing;

        initButtons();
    }

    public void draw(Graphics g) {
        g.setColor(new Color(63, 117, 6));
        g.fillRect(x, y, width, height);

        drawButtons(g);
        drawDisplayedTower(g);
    }

    public void mouseClicked(int x, int y) {
        if (bMenu.getBounds().contains(x, y)) {
            GameStates.setGameState(GameStates.MENU);
        } else {
            for (MyButton b : towerButtons) {
                if (b.getBounds().contains(x, y)) {
                    selectedTower = new Tower(0, 0, -1, b.id);
                    playing.setSelectedTower(selectedTower);
                    return;
                }
            }
        }
    }

    public void mouseMoved(int x, int y) {
        bMenu.setMouseOver(false);
        for (MyButton b : towerButtons) {
            b.setMouseOver(false);
        }

        if (bMenu.getBounds().contains(x, y)) {
            bMenu.setMouseOver(true);
        } else {
            for (MyButton b : towerButtons) {
                if (b.getBounds().contains(x, y)) {
                    b.setMouseOver(true);
                }
            }
        }
    }

    public void mousePressed(int x, int y) {
        if (bMenu.getBounds().contains(x, y)) {
            bMenu.setMousePressed(true);
        } else {
            for (MyButton b : towerButtons) {
                if (b.getBounds().contains(x, y)) {
                    b.setMousePressed(true);
                }
            }
        }
    }

    public void mouseReleased(int x, int y) {
        // Reset buttons
        bMenu.resetBooleans();
        for (MyButton b : towerButtons) {
            b.resetBooleans();
        }
    }

    public void displayTower(Tower t) {
        displayedTower = t;
    }

    private void initButtons() {
        bMenu = new MyButton("Menu", 10, 650, 100, 30);
        towerButtons = new MyButton[3];

        int width = 50;
        int height = 50;
        int xStart = 120;
        int yStart = 650;
        int xOffset = (int) (width * 1.1f);

        for (int i = 0; i < towerButtons.length; i++) {
            towerButtons[i] = new MyButton("", xStart + (xOffset * i), yStart, width, height, i);
        }
    }

    private void drawDisplayedTower(Graphics g) {
        if (displayedTower == null) {
            return;
        }

        // Draw background rectangle
        g.setColor(Color.GRAY);
        g.fillRect(390, 660, 160, 80);

        // Draw border
        g.setColor(Color.BLACK);
        g.drawRect(390, 660, 160, 80);

        // Draw tower sprite
        g.drawImage(playing.getTowerManager().getTowerImgs()[displayedTower.getTowerType()],
                400, 670, 50, 50, null);

        // Draw text
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.PLAIN, 15));
        g.drawString(Towers.GetName(displayedTower.getTowerType()), 470, 695);
        g.drawString("ID: " + displayedTower.getId(), 470, 710);
    }

    private void drawButtons(Graphics g) {
        bMenu.draw(g);

        for (MyButton b : towerButtons) {
            g.setColor(Color.GRAY);
            g.fillRect(b.x, b.y, b.width, b.height);
            g.drawImage(playing.getTowerManager().getTowerImgs()[b.id], b.x, b.y, b.width, b.height, null);
            drawButtonFeedback(g, b);
        }
    }
}
