package licaza.tdefender.demo.scenes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import licaza.tdefender.engine.tools.helpers.LoadSave;
import licaza.tdefender.engine.tools.managers.WaveManager;

import licaza.tdefender.engine.commons.objects.PathPoint;
import licaza.tdefender.engine.commons.api.SceneMethods;

import licaza.tdefender.demo.main.Game;
import licaza.tdefender.demo.managers.EnemyManager;
import licaza.tdefender.demo.managers.ProjectileManager;
import licaza.tdefender.demo.managers.TowerManager;
import licaza.tdefender.demo.actors.enemies.Enemy;
import licaza.tdefender.demo.actors.towers.Tower;
import licaza.tdefender.demo.configs.Constants.*;
import licaza.tdefender.demo.ui.ActionBar;

public class Playing extends GameScene implements SceneMethods {

    private int[][] lvl;
    private int mouseX, mouseY, goldTick;
    private boolean isGamePaused;

    private ActionBar actionBar;
    private EnemyManager enemyManager;
    private WaveManager waveManager;
    private TowerManager towerManager;
    private ProjectileManager projectileManager;
    private Tower selectedTower;
    private PathPoint start, end;

    public Playing(Game game) {
        super(game);

        actionBar = new ActionBar(0, 640, 640, 160, this);
        loadLevel();

        enemyManager = new EnemyManager(this, start, end);
        towerManager = new TowerManager(this);
        projectileManager = new ProjectileManager(this);
        waveManager = new WaveManager();
    }

    public void update() {
        if (isGamePaused)
            return;

        updateTick();
        waveManager.update();

        // Gold tick (3 secs...)
        goldTick++;
        if (goldTick % (60 * 3) == 0)
            actionBar.addGold(1);

        if (isAllEnemiesDead()) {
            if (isThereMoreWaves()) {
                waveManager.startWaveTimer();

                if (isWaveTimerOver()) {
                    waveManager.increaseWaveIndex();
                    enemyManager.getEnemies().clear();
                    waveManager.resetEnemyIndex();
                }

            }
        }
        if (shouldSpawnNewEnemy()) {
            spawnEnemy();
        }

        enemyManager.update();
        towerManager.update();
        projectileManager.update();
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

    public void shootEnemy(Tower t, Enemy e) {
        projectileManager.newProjectile(t, e);
    }

    public void rewardPlayer(int enemyType) {
        actionBar.addGold((int) Enemies.GetReward(enemyType));
    }

    public void removeTower(Tower displayedTower) {
        towerManager.removeTower(displayedTower);
    }

    public void upgradeTower(Tower displayedTower) {
        towerManager.upgradeTower(displayedTower);
    }

    public void setGamePaused(boolean gamePaused) {
        this.isGamePaused = gamePaused;
    }

    public boolean isGamePaused() {
        return isGamePaused;
    }

    public TowerManager getTowerManager() {
        return towerManager;
    }

    public EnemyManager getEnemyManager() {
        return enemyManager;
    }

    public WaveManager getWaveManager() {
        return waveManager;
    }

    @Override
    public void render(Graphics g) {
        drawLevel(g);

        actionBar.draw(g);
        enemyManager.draw(g);
        towerManager.draw(g);
        projectileManager.draw(g);

        drawSelectedTower(g);
        drawHighlight(g);
        drawWaveInfos(g);
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

                    removeGold(selectedTower.getTowerType());
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

    public void removeOneLive() {
        actionBar.removeOneLive();
    }

    public void resetEverything() {
        actionBar.resetEverything();

        // Managers
        enemyManager.reset();
        towerManager.reset();
        projectileManager.reset();
        waveManager.reset();

        mouseX = mouseY = goldTick = 0;
        selectedTower = null;
        isGamePaused = false;
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

    private void drawHighlight(Graphics g) {
        g.setColor(Color.WHITE);
        g.drawRect(mouseX, mouseY, 32, 32);
    }

    private boolean isTileGrass(int x, int y) {
        int id = lvl[y / 32][x / 32];
        int tyleType = game.getTileManager().getTile(id).getTileType();
        return tyleType == Tiles.GRASS;
    }

    private boolean isAllEnemiesDead() {

        if (waveManager.isThereEnemiesLeft())
            return false;

        for (Enemy e : enemyManager.getEnemies())
            if (e.isAlive())
                return false;

        return true;
    }

    private boolean isThereMoreWaves() {
        return waveManager.isThereMoreWaves();
    }

    private boolean isWaveTimerOver() {
        return waveManager.isWaveTimerOver();
    }

    private Tower getTowerAt(int x, int y) {
        return towerManager.getTowerAt(x, y);
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

    private void drawWaveInfos(Graphics g) {
    }

    private void removeGold(int towerType) {
        actionBar.payForTower(towerType);
    }

    private void spawnEnemy() {
        enemyManager.spawnEnemy(waveManager.getNextEnemy());
    }

    /**
     * On the tutorial, this name is called 'isTimeForNewEnemy'
     */
    private boolean shouldSpawnNewEnemy() {
        if (waveManager.shouldSpawnNewEnemy()) {
            if (waveManager.isThereEnemiesLeft()) {
                return true;
            }
        }
        return false;
    }
}
