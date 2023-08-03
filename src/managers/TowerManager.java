package managers;

import scenes.Playing;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import helpers.LoadSave;
import helpers.Constants.Towers;
import objects.Tower;

public class TowerManager {

    private Playing playing;
    private BufferedImage[] towerImgs;
    private Tower tower;

    public TowerManager(Playing playing) {
        this.playing = playing;
        loadTowerImages();
        initTowers();
    }

    public void update() {

    }

    public void draw(Graphics g) {
        g.drawImage(towerImgs[Towers.ARCHER], tower.getX(), tower.getY(), null);
    }

    private void loadTowerImages() {
        BufferedImage atlas = LoadSave.GetSpriteAtlas();
        towerImgs = new BufferedImage[3];
        for (int i = 0; i < 3; i++) {
            towerImgs[i] = atlas.getSubimage((4 + i) * 32, 32, 32, 32);
        }
    }

    private void initTowers() {
        tower = new Tower(3 * 32, 6 * 32, 0, Towers.ARCHER);
    }

}
