package managers;

import scenes.Playing;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import helpers.LoadSave;
import objects.Tower;

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

    private void loadTowerImages() {
        BufferedImage atlas = LoadSave.GetSpriteAtlas("spriteatlas_actors");
        towerImgs = new BufferedImage[3];
        for (int i = 0; i < 3; i++) {
            towerImgs[i] = atlas.getSubimage((20 + i) * 32, (8) * 32, 32, 32);
        }
    }
}
