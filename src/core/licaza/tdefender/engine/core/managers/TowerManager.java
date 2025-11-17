package licaza.tdefender.engine.core.managers;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

import licaza.tdefender.engine.tools.helpers.ImageFix;
import licaza.tdefender.engine.tools.helpers.LoadSave;

import licaza.tdefender.engine.commons.actors.Enemy;
import licaza.tdefender.engine.commons.actors.Tower;

import static licaza.tdefender.engine.tools.math.Functions.Pythagoras.*;

public abstract class TowerManager {

    private int towerCount = 0;
    private ArrayList<Tower> towers = new ArrayList<>();

    // "Callbacks"
    private final Supplier<List<Enemy>> enemiesSupplier;
    private final BiConsumer<Tower, Enemy> shootEnemyConsumer;

    protected BufferedImage[] towerImgs;

    public TowerManager(Supplier<List<Enemy>> enemiesSupplier,
            BiConsumer<Tower, Enemy> shootEnemyConsumer) {
        this.enemiesSupplier = enemiesSupplier;
        this.shootEnemyConsumer = shootEnemyConsumer;

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
        int damage, range, cooldown;

        // TODO: Find a good way to get tower specs. Maybe a TowerSpecs class
        damage = 50; // GetDefaultDamage(selectedTower.getTowerType());
        range = 100; // (int) GetDefaultRange(selectedTower.getTowerType());
        cooldown = 60;// (int) GetDefaultCooldown(selectedTower.getTowerType());

        towers.add(new Tower(xPos, yPos, towerCount++, selectedTower.getTowerType(), damage, range, cooldown));
    }

    public void removeTower(Tower displayedTower) {
        for (int i = 0; i < towers.size(); i++)
            if (towers.get(i).getId() == displayedTower.getId())
                towers.remove(i);
    }

    public void upgradeTower(Tower displayedTower) {
        for (Tower t : towers) {
            if (t.getId() == displayedTower.getId())
                // TODO: Add check for each kinf of tower
                t.upgradeTower(1, 10.0f, 3.0f);
        }

    }

    public void reset() {
        towers.clear();
        towerCount = 0;
    }

    protected abstract void loadTowerImages();

    private void attackEnemyIfClose(Tower t) {
        for (Enemy e : enemiesSupplier.get()) {
            if (e.isAlive()) {
                if (isEnemyInRange(t, e)) {
                    if (t.isCooldownOver()) {
                        shootEnemyConsumer.accept(t, e);
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
