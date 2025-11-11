package licaza.tdefender.engine.commons.actors;

import java.awt.Rectangle;
import java.util.function.IntConsumer;

import static licaza.tdefender.engine.commons.misc.Constants.Directions.*;

public class Enemy {

    private float x, y;
    private Rectangle bounds;
    private boolean alive = true;
    private float speed;
    private int health, maxHealth, id, enemyType;
    private int lastDirection, slowTickLimit = 120, slowTick = slowTickLimit;

    private IntConsumer rewardPlayer;

    public Enemy(float x, float y, int id, int enemyType, IntConsumer rewardPlayer) {
        this.x = x;
        this.y = y;
        this.id = id;
        this.enemyType = enemyType;
        this.rewardPlayer = rewardPlayer;

        bounds = new Rectangle((int) x, (int) y, 32, 32);
        lastDirection = -1;
    }

    public Enemy(float x, float y, int id, int enemyType, int startHealth, IntConsumer rewardPlayer) {
        this(x, y, id, enemyType, rewardPlayer);
        this.maxHealth = this.health = startHealth;
    }

    public Enemy(float x, float y, int id, int enemyType, int startHealth, float speed,
            IntConsumer rewardPlayer) {
        this(x, y, enemyType, startHealth, rewardPlayer);
        this.speed = speed;
    }

    public void move(float speed, int direction) {
        lastDirection = direction;

        if (slowTick < slowTickLimit) {
            slowTick++;
            speed *= 0.25f;
        }

        switch (direction) {
            case LEFT:
                this.x -= speed;
                break;
            case UP:
                this.y -= speed;
                break;
            case RIGHT:
                this.x += speed;
                break;
            case DOWN:
                this.y += speed;
                break;
        }

        updateHitbox();
    }

    public void slow() {
        slowTick = 0;
    }

    public void hurt(int damage) {
        this.health -= damage;
        if (health <= 0) {
            alive = false;
            rewardPlayer.accept(enemyType);
        }
    }

    public void kill() {
        alive = false;
        health = 0;
    }

    // For position fix
    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setLastDirection(int newDirection) {
        this.lastDirection = newDirection;
    }

    public float getEnemySpeed() {
        return speed;
    }

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

    public boolean isAlive() {
        return alive;
    }

    public boolean isSlowed() {
        return slowTick < slowTickLimit;
    }

    private void updateHitbox() {
        bounds.x = (int) x;
        bounds.y = (int) y;
    }
}
