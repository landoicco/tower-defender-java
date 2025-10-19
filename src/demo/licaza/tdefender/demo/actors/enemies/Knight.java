package licaza.tdefender.demo.actors.enemies;

import static licaza.tdefender.demo.configs.Constants.*;

import licaza.tdefender.demo.managers.EnemyManager;

public class Knight extends Enemy {

    private final static float speed = 0.3f;
    private final static int startHealth = 60;

    public Knight(float x, float y, int id, EnemyManager enemyManager) {
        super(x, y, id, Enemies.KNIGHT, startHealth, enemyManager);
    }

    @Override
    public float getEnemySpeed() {
        return speed;
    }

}
