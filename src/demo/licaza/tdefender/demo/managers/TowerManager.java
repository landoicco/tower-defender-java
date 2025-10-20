package licaza.tdefender.demo.managers;

import licaza.tdefender.demo.actors.enemies.Enemy;
import licaza.tdefender.demo.scenes.Playing;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import licaza.tdefender.engine.tools.helpers.ImageFix;
import licaza.tdefender.engine.tools.helpers.LoadSave;

import static licaza.tdefender.engine.tools.math.Functions.Pythagoras.*;

import licaza.tdefender.demo.actors.towers.Tower;

public class TowerManager {

    private Playing playing;
    private BufferedImage[] towerImgs;
    private int towerCount = 0;
    private ArrayList<Tower> towers = new ArrayList<>();

    public TowerManager(Playing playing) {
        this.playing = playing;
        loadTowerImages();
    }

    public void update() {
        for (Tower t : towers) {
            t.update();
            attackEnemyIfClose(t);
        }

    }

    public void draw(Graphics g) {
        for (Tower t : towers) {
            g.drawImage(towerImgs[t.getTowerType()], t.getX(), t.getY(), null);
        }
    }

    public Tower getTowerAt(int x, int y) {
        for (Tower t : towers) {
            if (t.getX() == x && t.getY() == y) {
                return t;
            }
        }
        return null;
    }

    public BufferedImage[] getTowerImgs() {
        return towerImgs;
    }

    public void addTower(Tower selectedTower, int xPos, int yPos) {
        towers.add(new Tower(xPos, yPos, towerCount++, selectedTower.getTowerType()));
    }

    public void removeTower(Tower displayedTower) {
        for (int i = 0; i < towers.size(); i++)
            if (towers.get(i).getId() == displayedTower.getId())
                towers.remove(i);
    }

    public void upgradeTower(Tower displayedTower) {
        for (Tower t : towers) {
            if (t.getId() == displayedTower.getId())
                t.upgradeTower();
        }

    }

    public void reset() {
        towers.clear();
        towerCount = 0;
    }

    private void loadTowerImages() {
        BufferedImage atlas = LoadSave.GetSpriteAtlas("spriteatlas_actors");
        towerImgs = new BufferedImage[3];
        for (int i = 0; i < 3; i++) {
            BufferedImage topImg = atlas.getSubimage((20 + i) * 32, (8) * 32, 32, 32);
            BufferedImage backgroundImg = atlas.getSubimage((20 + i) * 32, (7) * 32, 32, 32);
            towerImgs[i] = ImageFix.BuildImage(new BufferedImage[] { backgroundImg, topImg });
        }
    }

    private void attackEnemyIfClose(Tower t) {
        for (Enemy e : playing.getEnemyManager().getEnemies()) {
            if (e.isAlive()) {
                if (isEnemyInRange(t, e)) {
                    if (t.isCooldownOver()) {
                        playing.shootEnemy(t, e);
                        t.resetCooldown();
                    }
                } else {
                    // Do nothing
                }
            }
        }
    }

    private boolean isEnemyInRange(Tower t, Enemy e) {
        int range = GetHypotenuseDistance(t.getX(), t.getY(), e.getX(), e.getY());
        return range < t.getRange();
    }
}
