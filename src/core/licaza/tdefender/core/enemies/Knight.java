package licaza.tdefender.core.enemies;

import licaza.tdefender.commons.helpers.Constants.Enemies;

public class Knight extends Enemy {

    private final static float speed = 0.3f;
    private final static int startHealth = 60;

    public Knight(float x, float y, int id) {
        super(x, y, id, Enemies.KNIGHT, startHealth);
    }

    @Override
    public float getEnemySpeed() {
        return speed;
    }

}
