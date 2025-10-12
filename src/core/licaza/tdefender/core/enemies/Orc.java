package licaza.tdefender.core.enemies;

import licaza.tdefender.commons.helpers.Constants.Enemies;

public class Orc extends Enemy {

    private final static float speed = 0.5f;
    private final static int startHealth = 50;

    public Orc(float x, float y, int id) {
        super(x, y, id, Enemies.ORC, startHealth);
    }

    @Override
    public float getEnemySpeed() {
        return speed;
    }

}
