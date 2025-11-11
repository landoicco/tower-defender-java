package licaza.tdefender.demo.actors.enemies;

import static licaza.tdefender.demo.configs.Constants.*;

import java.util.function.IntConsumer;

import licaza.tdefender.engine.commons.actors.Enemy;

public class Knight extends Enemy {

    private final static float speed = 0.3f;
    private final static int startHealth = 60;

    public Knight(float x, float y, int id, IntConsumer callback) {
        super(x, y, id, Enemies.KNIGHT, startHealth, callback);
    }

    @Override
    public float getEnemySpeed() {
        return speed;
    }

}
