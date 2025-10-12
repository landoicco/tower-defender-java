package licaza.tdefender.core.enemies;

import static licaza.tdefender.commons.helpers.Constants.*;

public class Bat extends Enemy {

    private final static float speed = 0.65f;
    private final static int startHealth = 60;

    public Bat(float x, float y, int id) {
        super(x, y, id, Enemies.BAT, startHealth);
    }

    @Override
    public float getEnemySpeed() {
        return speed;
    }

}