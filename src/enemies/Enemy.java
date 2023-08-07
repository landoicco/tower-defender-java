package enemies;

import java.awt.Rectangle;
import helpers.Constants.Direction;

public abstract class Enemy {

    private float x, y;
    private Rectangle bounds;
    private int health, id, enemyType;
    private int lastDirection;
    private float speed;

    public Enemy(float x, float y, int id, int enemyType) {
        this.x = x;
        this.y = y;
        this.id = id;
        this.enemyType = enemyType;

        bounds = new Rectangle((int) x, (int) y, 32, 32);
        lastDirection = -1;
    }

    public Enemy(float x, float y, int id, int enemyType, int initialHealth) {
        this(x, y, id, enemyType);
        this.health = initialHealth;
    }

    public Enemy(float x, float y, int id, int enemyType, int initialHealth, float speed) {
        this(x, y, id, enemyType, initialHealth);
        this.speed = speed;
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
    }

    // For position fix
    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
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

}
