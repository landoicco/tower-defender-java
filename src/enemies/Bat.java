package enemies;

import static helpers.Constants.*;

public class Bat extends Enemy {

    private float speed = 0.65f;

    public Bat(float x, float y, int id) {
        super(x, y, id, Enemies.BAT);
    }

    @Override
    public float getEnemySpeed() {
        return speed;
    }

}