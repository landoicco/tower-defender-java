package enemies;

import helpers.Constants.Enemies;

public class Knight extends Enemy {

    private static final float speed = 0.3f;
    private static final int health = 100;

    public Knight(float x, float y, int id) {
        super(x, y, id, Enemies.KNIGHT, health, speed);
    }
}
