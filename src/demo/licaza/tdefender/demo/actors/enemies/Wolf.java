package licaza.tdefender.demo.actors.enemies;

import static licaza.tdefender.demo.configs.Constants.*;

public class Wolf extends Enemy {

    private final static float speed = 0.75f;
    private final static int startHealth = 90;

    public Wolf(float x, float y, int id) {
        super(x, y, id, Enemies.WOLF, startHealth);
    }

    @Override
    public float getEnemySpeed() {
        return speed;
    }

}