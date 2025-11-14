package licaza.tdefender.demo.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.text.DecimalFormat;
import java.util.Map;

import licaza.tdefender.demo.configs.Constants.Towers;

import licaza.tdefender.engine.tools.gui.*;
import licaza.tdefender.engine.commons.actors.Tower;
import licaza.tdefender.engine.commons.misc.ColorPalette;

import static licaza.tdefender.demo.main.GameStates.*;
import static licaza.tdefender.demo.configs.MediaSource.*;
import static licaza.tdefender.demo.configs.Colors.*;

import licaza.tdefender.demo.scenes.Playing;

public class ActionBar extends Bar {

    // Get All Colors
    private static final Color BG_COLOR = GetColorFromPalette(ColorPalette.BACKGROUND);
    private static final Color ACCENT_ONE = GetColorFromPalette(ColorPalette.ACCENT_ONE);
    private static final Color ACCENT_TWO = GetColorFromPalette(ColorPalette.ACCENT_TWO);
    private static final Color ACCENT_THREE = GetColorFromPalette(ColorPalette.ACCENT_THREE);
    private static final Color TEXT_COLOR = GetColorFromPalette(ColorPalette.TEXT);
    private static final Map<ColorPalette, Color> COLOR_MAP = GetColorMap();

    // Get All Fonts
    private static final Font BASE_FONT = Fonts.GetBaseFont();
    private static final Font HEADER_FONT = Fonts.GetHeaderFont();

    private TextButton bMenu, bPause, bSellTower, bUpgradeTower;
    private Playing playing;
    private Tower selectedTower, displayedTower;
    private MyButton[] towerButtons;
    private DecimalFormat formatter;

    /**
     * We use gold as the currency of this game
     */
    private int gold = 100, towerCostType;
    private int lives = 25;
    private boolean showTowerCost;

    public ActionBar(int x, int y, int width, int height, Playing playing) {
        super(x, y, width, height);
        this.playing = playing;

        formatter = new DecimalFormat("0.0");

        initButtons();
    }

    public void draw(Graphics g) {
        g.setColor(BG_COLOR);
        g.fillRect(x, y, width, height);

        drawButtons(g);
        drawDisplayedTower(g);

        drawWaveInfo(g);
        drawGoldAmount(g);

        if (showTowerCost)
            drawTowerCost(g);

        // Game pause text
        if (playing.isGamePaused()) {
            g.setColor(Color.ORANGE);
            g.drawString("Game is paused!", 200, 350);
        }

        // Lives label
        float livesLabelSize = 25f;
        g.setColor(ACCENT_TWO);
        g.setFont(HEADER_FONT.deriveFont(livesLabelSize));

        g.drawString("Lives: " + lives, 10, 700);
    }

    public void mouseClicked(int x, int y) {
        if (bMenu.getBounds().contains(x, y)) {
            setGameState(MENU);
        } else if (bPause.getBounds().contains(x, y)) {
            togglePause();
        } else {

            if (displayedTower != null) {
                if (bSellTower.getBounds().contains(x, y)) {
                    sellTowerClicked();
                    return;
                } else if (bUpgradeTower.getBounds().contains(x, y) &&
                        (displayedTower.getTier() < 3) &&
                        hasEnoughGoldToBuy(displayedTower)) {
                    upgradeTowerClicked();
                    return;
                }
            }

            for (MyButton b : towerButtons) {
                if (b.getBounds().contains(x, y)) {
                    if (!isGoldEnoughForTower(b.getId()))
                        return;

                    selectedTower = new Tower(0, 0, -1, b.id);
                    playing.setSelectedTower(selectedTower);
                    return;
                }
            }
        }
    }

    public void mouseMoved(int x, int y) {
        bMenu.setMouseOver(false);
        bPause.setMouseOver(false);

        bSellTower.setMouseOver(false);
        bUpgradeTower.setMouseOver(false);

        showTowerCost = false;
        for (MyButton b : towerButtons) {
            b.setMouseOver(false);
        }

        if (bMenu.getBounds().contains(x, y)) {
            bMenu.setMouseOver(true);
        } else if (bPause.getBounds().contains(x, y)) {
            bPause.setMouseOver(true);
        } else {
            // Tower buttons...
            if (displayedTower != null) {
                if (bSellTower.getBounds().contains(x, y)) {
                    bSellTower.setMouseOver(true);
                    return;
                } else if (bUpgradeTower.getBounds().contains(x, y) && displayedTower.getTier() < 3) {
                    bUpgradeTower.setMouseOver(true);
                    return;
                }
            }

            for (MyButton b : towerButtons) {
                if (b.getBounds().contains(x, y)) {
                    b.setMouseOver(true);
                    showTowerCost = true;
                    towerCostType = b.getId();
                }
            }
        }
    }

    public void mousePressed(int x, int y) {
        if (bMenu.getBounds().contains(x, y)) {
            bMenu.setMousePressed(true);
        } else if (bPause.getBounds().contains(x, y)) {
            bPause.setMousePressed(true);
        } else {
            // Tower buttons...
            if (displayedTower != null) {
                if (bSellTower.getBounds().contains(x, y)) {
                    bSellTower.setMousePressed(true);
                    return;
                } else if (bUpgradeTower.getBounds().contains(x, y) && displayedTower.getTier() < 3) {
                    bUpgradeTower.setMousePressed(true);
                    return;
                }
            }

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
        bPause.resetBooleans();

        bSellTower.resetBooleans();
        bUpgradeTower.resetBooleans();

        for (MyButton b : towerButtons) {
            b.resetBooleans();
        }
    }

    public void displayTower(Tower t) {
        displayedTower = t;
    }

    public void payForTower(int towerType) {
        gold -= Towers.GetTowerCost(towerType);
    }

    private void togglePause() {
        playing.setGamePaused(!playing.isGamePaused());

        if (playing.isGamePaused())
            bPause.setText("Unpause");
        else
            bPause.setText("Pause");
    }

    public void addGold(int amount) {
        this.gold += amount;
    }

    public void resetEverything() {
        lives = 25;
        towerCostType = 0;
        showTowerCost = false;
        gold = 100;
        selectedTower = displayedTower = null;
    }

    public void removeOneLive() {
        lives--;
        // Game over...
        if (lives <= 0)
            setGameState(GAME_OVER);
    }

    public int getLives() {
        return lives;
    }

    private void initButtons() {
        bMenu = new TextButton("Menu", 0, 710, 100, 30, COLOR_MAP);
        bPause = new TextButton("Pause", 0, 740, 100, 30, COLOR_MAP);

        towerButtons = new MyButton[3];

        int width = 50;
        int height = 50;
        int xStart = 220;
        int yStart = 670;
        int xOffset = (int) (width * 1.1f);

        for (int i = 0; i < towerButtons.length; i++) {
            towerButtons[i] = new MyButton("", xStart + (xOffset * i), yStart, width, height, i);
        }

        bSellTower = new TextButton("Sell", 480, 745, 50, 30, COLOR_MAP);
        bUpgradeTower = new TextButton("Upgrade", 540, 745, 80, 30, COLOR_MAP);
    }

    private void drawDisplayedTower(Graphics g) {
        float fontSize = 18f;

        if (displayedTower == null) {
            return;
        }

        // Draw background rectangle
        g.setColor(ACCENT_TWO);
        g.fillRect(470, 670, 160, 80);

        // Draw tower sprite
        g.drawImage(playing.getTowerManager().getTowerImgs()[displayedTower.getTowerType()],
                480, 680, 50, 50, null);

        // Draw text
        g.setColor(TEXT_COLOR);
        g.setFont(BASE_FONT.deriveFont(fontSize));

        g.drawString(Towers.GetName(displayedTower.getTowerType()), 560, 695);
        g.drawString("ID: " + displayedTower.getId(), 560, 715);
        g.drawString("Tier: " + displayedTower.getTier(), 560, 735);

        drawDisplayedTowerBorder(g);
        drawDisplayedTowerRange(g);

        bSellTower.draw(g);

        if ((displayedTower.getTier() < 3) && hasEnoughGoldToBuy(displayedTower)) {
            bUpgradeTower.draw(g);
        }

        g.setColor(TEXT_COLOR);
        if (bSellTower.isMouseOver()) {
            g.drawString("Sell for: " + getSellAmount(displayedTower) + "g", 500, 795);
        } else if (bUpgradeTower.isMouseOver() && hasEnoughGoldToBuy(displayedTower)) {
            g.drawString("Upgrade for: " + getUpgradeAmount(displayedTower) + "g", 500, 795);
        }
    }

    private void drawButtons(Graphics g) {
        float menuButtonsSize = 30f;
        g.setFont(BASE_FONT.deriveFont(menuButtonsSize));

        bMenu.draw(g);
        bPause.draw(g);

        for (MyButton b : towerButtons) {
            g.setColor(ACCENT_TWO);
            g.fillRect(b.x, b.y, b.width, b.height);
            g.drawImage(playing.getTowerManager().getTowerImgs()[b.id], b.x, b.y, b.width, b.height, null);
            drawButtonFeedback(g, b);
        }
    }

    private void drawDisplayedTowerBorder(Graphics g) {
        g.setColor(Color.GREEN);
        g.drawRect(displayedTower.getX(), displayedTower.getY(), 32, 32);
    }

    private void drawDisplayedTowerRange(Graphics g) {
        g.setColor(Color.RED);

        // Logic to centre the tower range
        g.drawOval(displayedTower.getX() + 16 - (int) (displayedTower.getRange()),
                displayedTower.getY() + 16 - (int) (displayedTower.getRange()),
                (int) displayedTower.getRange() * 2,
                (int) displayedTower.getRange() * 2);
    }

    private void drawWaveInfo(Graphics g) {
        float fontSize = 20f;

        g.setColor(ACCENT_THREE);
        g.setFont(HEADER_FONT.deriveFont(fontSize));

        drawWaveTimerInfo(g);
        drawEnemiesLeftInfo(g);
        drawWavesLeftInfo(g);
    }

    private void drawGoldAmount(Graphics g) {
        float textSize = 25f;
        g.setFont(HEADER_FONT.deriveFont(textSize));
        g.setColor(ACCENT_TWO);

        g.drawString("Gold: " + gold, 10, 680);
    }

    private boolean isGoldEnoughForTower(int towerType) {
        return gold >= Towers.GetTowerCost(towerType);
    }

    private void drawEnemiesLeftInfo(Graphics g) {
        int remaining = playing.getEnemyManager().getAmountOfAliveEnemies();
        g.drawString("Enemies left: " + remaining, 10, 660);
    }

    private void drawWavesLeftInfo(Graphics g) {
        int current = playing.getWaveManager().getWaveIndex();
        int size = playing.getWaveManager().getWaves().size();

        g.drawString("Wave " + (current + 1) + " / " + size, 560, 660);
    }

    private void drawWaveTimerInfo(Graphics g) {
        if (playing.getWaveManager().isWaveTimerStarted()) {
            float timeLeft = playing.getWaveManager().getTimeLeft();
            String formattedTimeLeft = formatter.format(timeLeft);

            g.drawString("Next wave in: " + formattedTimeLeft + "s", 260, 660);
        }
    }

    private void drawTowerCost(Graphics g) {
        float towerNameFontSize = 25f;
        float fontSize = 18f;

        g.setColor(ACCENT_THREE);
        g.fillRect(470, 670, 160, 120);

        g.setColor(TEXT_COLOR);
        g.setFont(BASE_FONT.deriveFont(fontSize));

        // Show tower name
        g.setFont(HEADER_FONT.deriveFont(towerNameFontSize));
        g.drawString("" + getTowerCostName(), 520, 695);

        // Show tower specs
        g.setFont(BASE_FONT.deriveFont(fontSize));
        g.drawString("Cost: " + getTowerCostCost() + "g", 480, 730);

        // Show if player cannot afford a tower
        if (isTowerCostMoreThanCurrentGold()) {
            g.setColor(Color.RED);
            g.drawString("Not enough gold...", 490, 780);
        }
    }

    private void sellTowerClicked() {
        playing.removeTower(displayedTower);

        gold += getSellAmount(displayedTower);

        displayedTower = null;
    }

    private void upgradeTowerClicked() {
        playing.upgradeTower(displayedTower);
        gold -= getUpgradeAmount(displayedTower);
    }

    private boolean hasEnoughGoldToBuy(Tower t) {
        return gold >= getUpgradeAmount(t);
    }

    private boolean isTowerCostMoreThanCurrentGold() {
        return getTowerCostCost() > gold;
    }

    private String getTowerCostName() {
        return Towers.GetName(towerCostType);
    }

    // Pay back some gold to player when selling a tower...
    private int getSellAmount(Tower tower) {
        // Add upgrades money to tower cost when selling...
        int towerCost = Towers.GetTowerCost(tower.getTowerType());
        int upgradesCost = (tower.getTier() - 1) * getUpgradeAmount(tower);

        return (int) ((towerCost + upgradesCost) * 0.5f);
    }

    private int getUpgradeAmount(Tower tower) {
        return (int) (Towers.GetTowerCost(tower.getTowerType()) * 0.3f);
    }

    private int getTowerCostCost() {
        return Towers.GetTowerCost(towerCostType);
    }
}
