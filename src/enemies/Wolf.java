package enemies;

import helpers.Constants.Enemies;

public class Wolf extends Enemy {

    private static final float speed = 0.75f;
    private static final int health = 100;

    public Wolf(float x, float y, int id) {
        super(x, y, id, Enemies.WOLF, health, speed);
    }
}