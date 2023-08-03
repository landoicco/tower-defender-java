package enemies;

import static helpers.Constants.*;

public class Orc extends Enemy {

    private float speed = 0.5f;

    public Orc(float x, float y, int id) {
        super(x, y, id, Enemies.ORC);
    }

    @Override
    public float getEnemySpeed() {
        return speed;
    }

}
