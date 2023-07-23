package managers;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import enemies.Enemy;
import helpers.LoadSave;
import scenes.Playing;

public class EnemyManager {

    private Playing playing;
    private BufferedImage[] enemyImgs;

    private ArrayList<Enemy> enemies = new ArrayList<>();

    public EnemyManager(Playing playing) {
        this.playing = playing;
        this.enemyImgs = new BufferedImage[4];
        addEnemy(3 * 32, 9 * 32);
        loadEnemyImgs();
    }

    public void update() {
        for (Enemy e : enemies) {
            e.move(0.5f, 0f);
        }
    }

    public void draw(Graphics g) {
        for (Enemy e : enemies) {
            drawEnemy(e, g);
        }
    }

    public void addEnemy(int x, int y) {
        enemies.add(new Enemy(x, y, 0, 0));
    }

    private void drawEnemy(Enemy e, Graphics g) {
        g.drawImage(enemyImgs[1], (int) e.getX(), (int) e.getY(), null);
    }

    // We use 4 because we know we only have 4 enemy sprites at this point
    private void loadEnemyImgs() {
        BufferedImage atlas = LoadSave.GetSpriteAtlas();
        enemyImgs[0] = atlas.getSubimage(32 * 0, 32, 32, 32);
        enemyImgs[1] = atlas.getSubimage(32 * 1, 32, 32, 32);
        enemyImgs[2] = atlas.getSubimage(32 * 2, 32, 32, 32);
        enemyImgs[3] = atlas.getSubimage(32 * 3, 32, 32, 32);
    }

}
