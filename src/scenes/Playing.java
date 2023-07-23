package scenes;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import helpers.LoadSave;
import main.Game;
import managers.EnemyManager;
import ui.ActionBar;

public class Playing extends GameScene implements SceneMethods {

    private int[][] lvl;
    private int mouseX, mouseY;
    private ActionBar actionBar;
    private EnemyManager enemyManager;

    public Playing(Game game) {
        super(game);

        actionBar = new ActionBar(0, 640, 640, 100, this);
        enemyManager = new EnemyManager(this);
        loadLevel();
    }

    public void update() {
        enemyManager.update();
    }

    @Override
    public void render(Graphics g) {
        drawLevel(g);
        actionBar.draw(g);
        enemyManager.draw(g);
    }

    @Override
    public void mouseClicked(int x, int y) {
        // Click on BottomBar
        if (y >= 640) {
            actionBar.mouseClicked(x, y);
            return;
        }
        enemyManager.addEnemy(x, y);
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

    public void setLevel(int[][] lvl) {
        this.lvl = lvl;
    }

    private void loadLevel() {
        lvl = LoadSave.GetLevelData("default_level");
    }

    private void drawLevel(Graphics g) {
        for (int i = 0; i < lvl.length; i++) {
            for (int j = 0; j < lvl[i].length; j++) {
                int id = lvl[i][j];
                g.drawImage(getSprite(id), j * 32, i * 32, null);
            }
        }

    }

    private BufferedImage getSprite(int spriteId) {
        return game.getTileManager().getSprite(spriteId);
    }
}
