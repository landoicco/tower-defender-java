package enemies;

import static helpers.Constants.*;

public class Knight extends Enemy {

    private float speed = 0.3f;

    public Knight(float x, float y, int id) {
        super(x, y, id, Enemies.KNIGHT);
    }

    @Override
    public float getEnemySpeed() {
        return speed;
    }

}
