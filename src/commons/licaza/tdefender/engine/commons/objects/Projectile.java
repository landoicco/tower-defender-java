package licaza.tdefender.engine.commons.objects;

import java.awt.geom.Point2D;

public class Projectile {
    private Point2D.Float position;
    private int id, projectileType, damage;
    private boolean active = true;
    private float xSpeed, ySpeed;

    public Projectile(float x, float y, float xSpeed, float ySpeed,
            int damage, int id, int projectileType) {
        position = new Point2D.Float(x, y);
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
        this.damage = damage;
        this.id = id;
        this.projectileType = projectileType;
    }

    public void move() {
        position.x += xSpeed;
        position.y += ySpeed;
    }

    public Point2D.Float getPosition() {
        return position;
    }

    public void setPosition(Point2D.Float position) {
        this.position = position;
    }

    public int getId() {
        return id;
    }

    public int getProjectileType() {
        return projectileType;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getDamage() {
        return damage;
    }
}
