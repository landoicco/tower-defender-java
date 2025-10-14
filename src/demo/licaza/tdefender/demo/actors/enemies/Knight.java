package licaza.tdefender.demo.actors.enemies;

import static licaza.tdefender.demo.configs.Constants.*;

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
