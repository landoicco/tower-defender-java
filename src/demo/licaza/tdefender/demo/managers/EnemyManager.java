package licaza.tdefender.demo.managers;

import java.util.List;
import java.util.function.IntBinaryOperator;
import java.util.function.IntConsumer;
import java.awt.image.BufferedImage;

import licaza.tdefender.demo.actors.enemies.Bat;
import licaza.tdefender.demo.actors.enemies.Knight;
import licaza.tdefender.demo.actors.enemies.Orc;
import licaza.tdefender.demo.actors.enemies.Wolf;
import licaza.tdefender.demo.configs.Constants.Enemies;
import licaza.tdefender.engine.commons.actors.Enemy;
import licaza.tdefender.engine.commons.misc.IntArrayProvider;
import licaza.tdefender.engine.commons.objects.PathPoint;

import licaza.tdefender.demo.configs.MediaSource;

public final class EnemyManager extends
        licaza.tdefender.engine.core.managers.EnemyManager {

    private PathPoint start;
    private IntConsumer rewardPlayerCallback;

    // "Callbacks"
    public EnemyManager(IntConsumer rewardPlayerCallback, Runnable removeOneLiveCallback,
            IntBinaryOperator getTileTypeCallback, IntArrayProvider typeArrayCallback,
            PathPoint start, PathPoint end) {
        super(rewardPlayerCallback, removeOneLiveCallback, getTileTypeCallback,
                typeArrayCallback, start, end);

        this.rewardPlayerCallback = rewardPlayerCallback;
        this.start = start;
    }

    @Override
    protected void loadEffectImages() {
        slowEffect = MediaSource.Sprites.GetSprite("LEGACY")
            .getSubimage(32 * 9, 32 * 2, 32, 32);
    }

    // We use 4 because we know we only have 4 enemy sprites at this point
    @Override
    protected void loadEnemyImages() {
        BufferedImage atlas = MediaSource.Sprites.GetSprite("ACTORS");

        for (int i = 0; i < 4; i++) {
            // For position of enemies in spritesheet_actors
            enemyImgs[i] = atlas.getSubimage((i * 32) + (15 * 32), (10 * 32), 32, 32);
        }
    }


    @Override
    public void addEnemy(int enemyType) {
        int x = start.xCord() * 32;
        int y = start.yCord() * 32;
        List<Enemy> enemies = getEnemies();

        switch (enemyType) {
            case Enemies.ORC:
                enemies.add(new Orc(x, y, 0, rewardPlayerCallback));
                break;
            case Enemies.BAT:
                enemies.add(new Bat(x, y, 0, rewardPlayerCallback));
                break;
            case Enemies.KNIGHT:
                enemies.add(new Knight(x, y, 0, rewardPlayerCallback));
                break;
            case Enemies.WOLF:
                enemies.add(new Wolf(x, y, 0, rewardPlayerCallback));
                break;
        }
    }
}
