package licaza.tdefender.demo.actors.enemies;

import java.awt.Rectangle;

import static licaza.tdefender.demo.configs.Constants.*;

public abstract class Enemy {

    private float x, y;
    private Rectangle bounds;
    private boolean alive = true;
    private int health, maxHealth, id, enemyType;
    private int lastDirection;

    public Enemy(float x, float y, int id, int enemyType) {
        this.x = x;
        this.y = y;
        this.id = id;
        this.enemyType = enemyType;

        bounds = new Rectangle((int) x, (int) y, 32, 32);
        lastDirection = -1;
    }

    public Enemy(float x, float y, int id, int enemyType, int startHealth) {
        this(x, y, id, enemyType);
        this.maxHealth = this.health = startHealth;
    }

    public void move(float speed, int direction) {
        lastDirection = direction;
        switch (direction) {
            case Direction.LEFT:
                this.x -= speed;
                break;
            case Direction.UP:
                this.y -= speed;
                break;
            case Direction.RIGHT:
                this.x += speed;
                break;
            case Direction.DOWN:
                this.y += speed;
                break;
        }

        updateHitbox();
    }

    // For position fix
    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public abstract float getEnemySpeed();

    // Getters

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public int getId() {
        return id;
    }

    public int getHealth() {
        return health;
    }

    public int getEnemyType() {
        return enemyType;
    }

    public int getLastDirection() {
        return lastDirection;
    }

    public Rectangle getRectangle() {
        return bounds;
    }

    public float getHealthBarFloat() {
        return health / (float) maxHealth;
    }

    public void hurt(int damage) {
        this.health -= damage;
        if (health <= 0) {
            alive = false;
        }
    }

    public boolean isAlive() {
        return alive;
    }

    private void updateHitbox() {
        bounds.x = (int) x;
        bounds.y = (int) y;
    }
}
