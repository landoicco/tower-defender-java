package enemies;

import static helpers.Constants.*;

public class Orc extends Enemy {

    private static final float speed = 0.5f;
    private static final int health = 50;

    public Orc(float x, float y, int id) {
        super(x, y, id, Enemies.ORC, health, speed);
    }
}
