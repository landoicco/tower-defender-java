package scenes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import helpers.LoadSave;
import helpers.Constants.Tiles;
import main.Game;
import managers.EnemyManager;
import managers.TowerManager;
import objects.PathPoint;
import towers.Tower;
import ui.ActionBar;

public class Playing extends GameScene implements SceneMethods {

    private int[][] lvl;
    private int mouseX, mouseY;
    private ActionBar actionBar;
    private EnemyManager enemyManager;
    private TowerManager towerManager;
    private Tower selectedTower;
    private PathPoint start, end;

    public Playing(Game game) {
        super(game);

        actionBar = new ActionBar(0, 640, 640, 160, this);
        loadLevel();
        enemyManager = new EnemyManager(this, start, end);
        towerManager = new TowerManager(this);
    }

    public void update() {
        updateTick();
        enemyManager.update();
        towerManager.update();
    }

    public int getTileType(int x, int y) {

        int xCord = x / 32;
        int yCord = y / 32;

        if (xCord < 0 || xCord > 19) {
            return 0;
        }
        if (yCord < 0 || yCord > 19) {
            return 0;
        }

        int id = lvl[y / 32][x / 32];
        return game.getTileManager().getTile(id).getTileType();
    }

    public TowerManager getTowerManager() {
        return towerManager;
    }

    @Override
    public void render(Graphics g) {
        drawLevel(g);
        actionBar.draw(g);
        enemyManager.draw(g);
        towerManager.draw(g);
        drawSelectedTower(g);
        drawHighLight(g);
    }

    @Override
    public void mouseClicked(int x, int y) {
        // Click on BottomBar
        if (y >= 640) {
            actionBar.mouseClicked(x, y);
            return;
        } else {
            if (selectedTower != null) {
                if (isTileGrass(mouseX, mouseY) &&
                        getTowerAt(mouseX, mouseY) == null) {
                    towerManager.addTower(selectedTower, mouseX, mouseY);
                    selectedTower = null;
                    return;
                }
            } else {
                Tower t = getTowerAt(mouseX, mouseY);
                actionBar.displayTower(t);
            }
        }
    }

    @Override
    public void mouseMoved(int x, int y) {
        if (y >= 640) {
            actionBar.mouseMoved(x, y);
        } else {
            mouseX = (x / 32) * 32;
            mouseY = (y / 32) * 32;
        }
    }

    @Override
    public void mousePressed(int x, int y) {
        if (y >= 640) {
            actionBar.mousePressed(x, y);
        }
    }

    @Override
    public void mouseReleased(int x, int y) {
        actionBar.mouseReleased(x, y);
    }

    @Override
    public void mouseDragged(int x, int y) {
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            selectedTower = null;
        }
    }

    public void setSelectedTower(Tower selectedTower) {
        this.selectedTower = selectedTower;
    }

    public void setLevel(int[][] lvl) {
        this.lvl = lvl;
    }

    private void loadLevel() {
        String lvlName = "default_level";
        lvl = LoadSave.GetLevelData(lvlName);
        ArrayList<PathPoint> points = LoadSave.GetLevelPathPoints(lvlName);
        start = points.get(0);
        end = points.get(1);
    }

    private void drawSelectedTower(Graphics g) {
        if (selectedTower == null) {
            return;
        }
        g.drawImage(towerManager.getTowerImgs()[selectedTower.getTowerType()],
                mouseX, mouseY, null);
    }

    private boolean isTileGrass(int x, int y) {
        int id = lvl[y / 32][x / 32];
        int tyleType = game.getTileManager().getTile(id).getTileType();
        return tyleType == Tiles.GRASS;
    }

    private Tower getTowerAt(int x, int y) {
        return towerManager.getTowerAt(x, y);
    }

    private void drawHighLight(Graphics g) {
        if (selectedTower == null) {
            return;
        }
        g.setColor(Color.WHITE);
        g.drawRect(mouseX, mouseY, 32, 32);
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
}
