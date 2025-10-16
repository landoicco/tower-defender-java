package licaza.tdefender.demo.managers;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import static licaza.tdefender.demo.configs.Constants.*;
import static licaza.tdefender.demo.configs.Constants.Projectiles.*;
import static licaza.tdefender.demo.configs.Constants.Towers.*;

import licaza.tdefender.engine.tools.helpers.LoadSave;
import licaza.tdefender.engine.commons.objects.Projectile;

import licaza.tdefender.demo.actors.towers.Tower;
import licaza.tdefender.demo.actors.enemies.Enemy;
import licaza.tdefender.demo.scenes.Playing;

public class ProjectileManager {
    private Playing playing;
    private ArrayList<Projectile> projectiles = new ArrayList<>();
    private BufferedImage[] projectileImgs;
    private int projectileID = 0;

    public ProjectileManager(Playing playing) {
        this.playing = playing;

        loadProjectileImages();
    }

    public void update() {
        for (Projectile p : projectiles) {
            if (p.isActive()) {
                p.move();
                if (isProjectileHittingEnemy(p)) {
                    p.setActive(false);
                } else {
                    // Do nothing...
                }
            }
        }
    }

    public void draw(Graphics g) {
        for (Projectile p : projectiles) {
            if (p.isActive()) {
                g.drawImage(projectileImgs[p.getProjectileType()],
                        (int) p.getPosition().x, (int) p.getPosition().y, null);
            }
        }
    }

    public void newProjectile(Tower t, Enemy e) {
        int type = getProjectileType(t);

        int xDistance = (int) Math.abs(t.getX() - e.getX());
        int yDistance = (int) Math.abs(t.getY() - e.getY());

        int totalDistance = xDistance + yDistance;

        // Percentage? Take a deeper look
        float xPer = (float) xDistance / totalDistance;

        float xSpeed = xPer * Projectiles.GetSpeed(type);
        float ySpeed = Projectiles.GetSpeed(type) - xSpeed;

        if (t.getX() > e.getX())
            xSpeed *= -1;
        if (t.getY() > e.getY())
            ySpeed *= -1;

        projectiles.add(new Projectile(t.getX() + 16, t.getY() + 16, xSpeed,
                ySpeed, t.getDamage(), projectileID++, type));
    }

    private boolean isProjectileHittingEnemy(Projectile p) {
        for (Enemy e : playing.getEnemyManager().getEnemies()) {
            if (e.getRectangle().contains(p.getPosition())) {
                e.hurt(p.getDamage());
                return true;
            }
        }
        return false;
    }

    private int getProjectileType(Tower t) {
        switch (t.getTowerType()) {
            case ARCHER:
                return ARROW;
            case CANNON:
                return BOMB;
            case WIZARD:
                return CHAINS;
        }

        return 0;
    }

    private void loadProjectileImages() {
        BufferedImage atlas = LoadSave.GetSpriteAtlas("spriteatlas_actors");
        projectileImgs = new BufferedImage[3];

        for (int i = 0; i < 3; i++) {
            projectileImgs[i] = atlas.getSubimage((20 + i) * 32, (10) * 32, 32, 32);
        }
    }
}
