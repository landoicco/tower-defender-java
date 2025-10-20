package licaza.tdefender.demo.actors.enemies;

import static licaza.tdefender.demo.configs.Constants.*;

import licaza.tdefender.demo.managers.EnemyManager;

public class Orc extends Enemy {

    private final static float speed = 0.5f;
    private final static int startHealth = 50;

    public Orc(float x, float y, int id, EnemyManager enemyManager) {
        super(x, y, id, Enemies.ORC, startHealth, enemyManager);
    }

    @Override
    public float getEnemySpeed() {
        return speed;
    }

}
