package licaza.tdefender.demo.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import java.text.DecimalFormat;

import licaza.tdefender.demo.configs.Constants.Towers;

import licaza.tdefender.engine.tools.gui.MyButton;
import licaza.tdefender.engine.commons.actors.Tower;

import static licaza.tdefender.demo.main.GameStates.*;
import licaza.tdefender.demo.scenes.Playing;

public class ActionBar extends Bar {

    private MyButton bMenu, bPause;
    private Playing playing;
    private Tower selectedTower, displayedTower;
    private MyButton[] towerButtons;
    private MyButton sellTower, upgradeTower;
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
        g.setColor(new Color(63, 117, 6));
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

        // Lives
        g.setColor(Color.BLUE);
        g.drawString("Lives: " + lives, 10, 50);
    }

    public void mouseClicked(int x, int y) {
        if (bMenu.getBounds().contains(x, y)) {
            setGameState(MENU);
        } else if (bPause.getBounds().contains(x, y)) {
            togglePause();
        } else {

            if (displayedTower != null) {
                if (sellTower.getBounds().contains(x, y)) {
                    sellTowerClicked();
                    return;
                } else if (upgradeTower.getBounds().contains(x, y) &&
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

        sellTower.setMouseOver(false);
        upgradeTower.setMouseOver(false);

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
                if (sellTower.getBounds().contains(x, y)) {
                    sellTower.setMouseOver(true);
                    return;
                } else if (upgradeTower.getBounds().contains(x, y) && displayedTower.getTier() < 3) {
                    upgradeTower.setMouseOver(true);
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
                if (sellTower.getBounds().contains(x, y)) {
                    sellTower.setMousePressed(true);
                    return;
                } else if (upgradeTower.getBounds().contains(x, y) && displayedTower.getTier() < 3) {
                    upgradeTower.setMousePressed(true);
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

        sellTower.resetBooleans();
        upgradeTower.resetBooleans();

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
        bMenu = new MyButton("Menu", 10, 645, 100, 30);
        bPause = new MyButton("Pause", 10, 675, 100, 30);

        towerButtons = new MyButton[3];

        int width = 50;
        int height = 50;
        int xStart = 120;
        int yStart = 650;
        int xOffset = (int) (width * 1.1f);

        for (int i = 0; i < towerButtons.length; i++) {
            towerButtons[i] = new MyButton("", xStart + (xOffset * i), yStart, width, height, i);
        }

        sellTower = new MyButton("Sell", 490, 750, 50, 30);
        upgradeTower = new MyButton("Upgrade", 550, 750, 80, 30);
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
        g.drawString("Tier: " + displayedTower.getTier(), 470, 725);

        drawDisplayedTowerBorder(g);
        drawDisplayedTowerRange(g);

        sellTower.draw(g);
        drawButtonFeedback(g, sellTower);

        if ((displayedTower.getTier() < 3) && hasEnoughGoldToBuy(displayedTower)) {
            upgradeTower.draw(g);
            drawButtonFeedback(g, upgradeTower);
        }

        if (sellTower.isMouseOver()) {
            g.setColor(Color.RED);
            g.drawString("Sell for: " + getSellAmount(displayedTower) + "g", 490, 795);
        } else if (upgradeTower.isMouseOver() && hasEnoughGoldToBuy(displayedTower)) {
            g.setColor(Color.BLUE);
            g.drawString("Upgrade for: " + getUpgradeAmount(displayedTower) + "g", 490, 795);
        }
    }

    private void drawButtons(Graphics g) {
        bMenu.draw(g);
        bPause.draw(g);

        for (MyButton b : towerButtons) {
            g.setColor(Color.GRAY);
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
        // Draw background
        g.setColor(Color.GRAY);
        g.fillRect(30, 710, 250, 85);

        // Font settings...
        g.setFont(new Font("LucidaSans", Font.BOLD, 20));
        g.setColor(Color.BLACK);

        drawWaveTimerInfo(g);
        drawEnemiesLeftInfo(g);
        drawWavesLeftInfo(g);
    }

    private void drawGoldAmount(Graphics g) {
        g.drawString("Gold: " + gold, 10, 25);
    }

    private boolean isGoldEnoughForTower(int towerType) {
        return gold >= Towers.GetTowerCost(towerType);
    }

    private void drawEnemiesLeftInfo(Graphics g) {
        int remaining = playing.getEnemyManager().getAmountOfAliveEnemies();
        g.drawString("Enemies left: " + remaining, 35, 730);
    }

    private void drawWavesLeftInfo(Graphics g) {
        int current = playing.getWaveManager().getWaveIndex();
        int size = playing.getWaveManager().getWaves().size();

        g.drawString("Wave " + (current + 1) + " / " + size, 35, 760);
    }

    private void drawWaveTimerInfo(Graphics g) {
        if (playing.getWaveManager().isWaveTimerStarted()) {
            float timeLeft = playing.getWaveManager().getTimeLeft();
            String formattedTimeLeft = formatter.format(timeLeft);

            g.drawString("Next wave in: " + formattedTimeLeft + "s", 35, 790);
        }
    }

    private void drawTowerCost(Graphics g) {
        g.setColor(Color.GRAY);
        g.fillRect(280, 650, 120, 50);
        g.setColor(Color.BLACK);
        g.drawRect(280, 650, 120, 50);

        g.drawString("" + getTowerCostName(), 285, 670);
        g.drawString("Cost: " + getTowerCostCost() + "g", 285, 695);

        // Show if player cannot afford a tower
        if (isTowerCostMoreThanCurrentGold()) {
            g.setColor(Color.RED);
            g.drawString("Not enough gold...", 285, 725);
            // g.setColor(Color.BLACK);
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
