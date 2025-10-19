package licaza.tdefender.demo.actors.enemies;

import static licaza.tdefender.demo.configs.Constants.*;

import licaza.tdefender.demo.managers.EnemyManager;

public class Wolf extends Enemy {

    private final static float speed = 0.75f;
    private final static int startHealth = 90;

    public Wolf(float x, float y, int id, EnemyManager enemyManager) {
        super(x, y, id, Enemies.WOLF, startHealth, enemyManager);
    }

    @Override
    public float getEnemySpeed() {
        return speed;
    }

}
