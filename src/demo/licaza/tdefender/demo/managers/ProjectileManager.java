package licaza.tdefender.demo.managers;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import static licaza.tdefender.demo.configs.Constants.*;
import static licaza.tdefender.demo.configs.Constants.Projectiles.*;
import static licaza.tdefender.demo.configs.Constants.Towers.*;

import licaza.tdefender.engine.tools.helpers.LoadSave;
import licaza.tdefender.engine.commons.objects.Projectile;
import licaza.tdefender.engine.commons.actors.Enemy;
import licaza.tdefender.engine.commons.actors.Tower;

import licaza.tdefender.demo.scenes.Playing;

public class ProjectileManager {
    private Playing playing;
    private ArrayList<Projectile> projectiles = new ArrayList<>();
    private ArrayList<Explosion> explosions = new ArrayList<>();
    private BufferedImage[] projectileImages, explosionImages;

    private int projectileID;

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

                    if (p.getProjectileType() == BOMB) {
                        explosions.add(new Explosion(p.getPosition()));
                        explodeOnEnemies(p);
                    }
                } else if (isProjectileOutOfBounds(p)) {
                    p.setActive(false);
                }
            }

            for (Explosion e : explosions) {
                if (e.getIndex() < 7)
                    e.update();
            }
        }
    }

    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        for (Projectile p : projectiles) {
            if (p.isActive()) {
                if (shouldProjectileRotate(p.getProjectileType())) {
                    g2d.translate(p.getPosition().x, p.getPosition().y);
                    g2d.rotate(Math.toRadians(p.getRotation()));

                    g2d.drawImage(projectileImages[p.getProjectileType()], -16, -16, null);

                    g2d.rotate(-Math.toRadians(p.getRotation()));
                    g2d.translate(-p.getPosition().x, -p.getPosition().y);
                } else {
                    g2d.drawImage(projectileImages[p.getProjectileType()], (int) p.getPosition().x - 16,
                            (int) p.getPosition().y - 16, null);
                }

            }
        }

        drawExplosions(g2d);
    }

    public void newProjectile(Tower t, Enemy e) {
        int type = getProjectileType(t);

        int xDistance = (int) (t.getX() - e.getX());
        int yDistance = (int) (t.getY() - e.getY());

        int totalDistance = Math.abs(xDistance) + Math.abs(yDistance);

        // Percentage? Take a deeper look
        float xPer = (float) Math.abs(xDistance) / totalDistance;

        float xSpeed = xPer * Projectiles.GetSpeed(type);
        float ySpeed = Projectiles.GetSpeed(type) - xSpeed;

        if (t.getX() > e.getX())
            xSpeed *= -1;
        if (t.getY() > e.getY())
            ySpeed *= -1;

        float rotate = 0;

        if (shouldProjectileRotate(type)) {
            float arcValue = (float) Math.atan(yDistance / (float) xDistance);
            rotate = (float) Math.toDegrees(arcValue);

            if (xDistance < 0)
                rotate += 90;
            else
                rotate += 270;
        }

        for (Projectile p : projectiles) {
            if (!p.isActive())
                if (p.getProjectileType() == type) {
                    System.out.println("Reusing projectile");
                    p.reuse(t.getX() + 16, t.getY() + 16, xSpeed, ySpeed, t.getDamage(), rotate);
                    return;
                }
        }

        projectiles.add(new Projectile(t.getX() + 16, t.getY() + 16, xSpeed,
                ySpeed, t.getDamage(), rotate, projectileID++, type));

        System.out.println("Projectiles amount: " + projectiles.size());
    }

    public void reset() {
        projectiles.clear();
        explosions.clear();

        projectileID = 0;
    }

    private boolean isProjectileHittingEnemy(Projectile p) {
        for (Enemy e : playing.getEnemyManager().getEnemies()) {
            if (e.isAlive()) {
                if (e.getRectangle().contains(p.getPosition())) {
                    e.hurt(p.getDamage());

                    /**
                     * While developing, we are assuming the 'CHAINS' makes the
                     * enemy to move slower
                     */
                    if (p.getProjectileType() == CHAINS) {
                        e.slow();
                    }

                    return true;
                }
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
        projectileImages = new BufferedImage[3];

        for (int i = 0; i < 3; i++) {
            projectileImages[i] = atlas.getSubimage((20 + i) * 32, (10) * 32, 32, 32);
        }

        importExplosionImages();
    }

    private void importExplosionImages() {
        BufferedImage atlas = LoadSave.GetSpriteAtlas("spriteatlas_legacy");
        explosionImages = new BufferedImage[7];

        for (int i = 0; i < 7; i++) {
            explosionImages[i] = atlas.getSubimage(i * 32, 2 * 32, 32, 32);
        }
    }

    private void explodeOnEnemies(Projectile p) {
        for (Enemy e : playing.getEnemyManager().getEnemies()) {
            if (e.isAlive()) {
                float radius = 40.0f;

                float xDistance = Math.abs(p.getPosition().x - e.getX());
                float yDistance = Math.abs(p.getPosition().y - e.getY());

                float realDistance = (float) Math.hypot(xDistance, yDistance);

                if (realDistance <= radius) {
                    e.hurt(p.getDamage());
                }
            }
        }
    }

    /**
     * This method is useful to distinct the projectiles that should not have any
     * rotation when moving
     * 
     * In this example, we assume 'ARROW' should rotate, 'CHAINS' and 'BOMB' not
     * 
     * @param type The integer value that represents the expected projectile
     */
    private boolean shouldProjectileRotate(int type) {
        return type == ARROW;
    }

    private void drawExplosions(Graphics2D g2d) {
        for (Explosion e : explosions)
            if (e.getIndex() < 7)
                g2d.drawImage(explosionImages[e.getIndex()], (int) e.getPosition().x - 16,
                        (int) e.getPosition().y - 16, null);
    }

    private boolean isProjectileOutOfBounds(Projectile p) {
        if (p.getPosition().x >= 0)
            if (p.getPosition().x <= 640)
                if (p.getPosition().y >= 0)
                    if (p.getPosition().y <= 800)
                        return false;

        return true;
    }

    /**
     * Since only here we care about explosions, it stays here
     */
    private class Explosion {
        private Point2D.Float position;
        private int tick, index;

        public Explosion(Point2D.Float position) {
            this.position = position;
        }

        public void update() {
            tick++;
            if (tick >= 12) {
                tick = 0;
                index++;
            }
        }

        public int getIndex() {
            return index;
        }

        public Point2D.Float getPosition() {
            return position;
        }
    }
}
