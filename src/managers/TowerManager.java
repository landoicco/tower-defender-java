package managers;

import scenes.Playing;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import helpers.LoadSave;
import helpers.Constants.Towers;
import towers.*;

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
        towers.add(getSelectedTower(selectedTower, xPos, yPos));
    }

    public Tower getSelectedTower(Tower selectedTower, int xPos, int yPos) {
        switch (selectedTower.getTowerType()) {
            case Towers.ARCHER:
                return new Archer(xPos, yPos, towerCount++, selectedTower.getTowerType());
            case Towers.CANNON:
                return new Cannon(xPos, yPos, towerCount++, selectedTower.getTowerType());
            case Towers.WIZARD:
                return new Wizard(xPos, yPos, towerCount++, selectedTower.getTowerType());
            default:
                return null;
        }
    }

    private void loadTowerImages() {
        BufferedImage atlas = LoadSave.GetSpriteAtlas();
        towerImgs = new BufferedImage[3];
        for (int i = 0; i < 3; i++) {
            towerImgs[i] = atlas.getSubimage((4 + i) * 32, 32, 32, 32);
        }
    }
}
