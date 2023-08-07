package enemies;

import helpers.Constants.Enemies;

public class Bat extends Enemy {

    private static final float speed = 0.65f;
    private static final int health = 30;

    public Bat(float x, float y, int id) {
        super(x, y, id, Enemies.BAT, health, speed);
    }
}