package licaza.tdefender.core.enemies;

import static licaza.tdefender.commons.helpers.Constants.*;

public class Wolf extends Enemy {

    private float speed = 0.75f;

    public Wolf(float x, float y, int id) {
        super(x, y, id, Enemies.WOLF);
    }

    @Override
    public float getEnemySpeed() {
        return speed;
    }

}