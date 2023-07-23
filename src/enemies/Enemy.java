package enemies;

import java.awt.Rectangle;

public class Enemy {

    private float x, y;
    private Rectangle bounds;
    private int health, id, enemyType;

    public Enemy(float x, float y, int id, int enemyType) {
        this.x = x;
        this.y = y;
        this.id = id;
        this.enemyType = enemyType;

        bounds = new Rectangle((int) x, (int) y, 32, 32);
    }

    public void move(float x, float y) {
        this.x += x;
        this.y += y;
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

    public Rectangle getRectangle() {
        return bounds;
    }

}
