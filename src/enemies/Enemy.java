package enemies;

import java.awt.Rectangle;
import static helpers.Constants.*;

public class Enemy {

    private float x, y;
    private Rectangle bounds;
    private int health, id, enemyType;
    private int lastDirection;

    public Enemy(float x, float y, int id, int enemyType) {
        this.x = x;
        this.y = y;
        this.id = id;
        this.enemyType = enemyType;

        bounds = new Rectangle((int) x, (int) y, 32, 32);
        lastDirection = Direction.RIGHT;
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
