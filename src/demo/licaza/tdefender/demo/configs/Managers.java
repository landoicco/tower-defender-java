package licaza.tdefender.demo.configs;

import java.awt.image.BufferedImage;
import java.util.function.*;
import java.util.concurrent.*;
import java.util.*;
import java.io.File;

import javax.sound.sampled.*;

import licaza.tdefender.demo.actors.enemies.*;
import licaza.tdefender.demo.configs.Constants.*;
import licaza.tdefender.demo.configs.tiles.Tilemap;

import licaza.tdefender.engine.commons.objects.PathPoint;
import licaza.tdefender.engine.commons.objects.Tile;
import licaza.tdefender.engine.commons.misc.IntArrayProvider;
import licaza.tdefender.engine.commons.actors.*;
import licaza.tdefender.engine.tools.helpers.ImageFix;


import static licaza.tdefender.demo.configs.MediaSource.*;

public final class Managers {
    public static final class ProjectileManager
        extends licaza.tdefender.engine.core.managers.ProjectileManager {
        public ProjectileManager(Supplier<List<Enemy>> enemiesSupplier) {
            super(enemiesSupplier);
        }

        @Override
        protected void loadProjectileImages() {
            BufferedImage atlas = Sprites.GetSprite("ACTORS");
            projectileImages = new BufferedImage[3];

            for (int i = 0; i < 3; i++) {
                projectileImages[i] = atlas.getSubimage((20 + i) * 32, (10) * 32, 32, 32);
            }

            loadExplosionImages();

        }

        @Override
        protected void loadExplosionImages() {
            BufferedImage atlas = Sprites.GetSprite("LEGACY");
            explosionImages = new BufferedImage[7];

            for (int i = 0; i < 7; i++) {
                explosionImages[i] = atlas.getSubimage(i * 32, 2 * 32, 32, 32);
            }

        }
    }

    public static final class TowerManager
        extends licaza.tdefender.engine.core.managers.TowerManager {
        public TowerManager(Supplier<List<Enemy>> enemiesSupplier,
                            BiConsumer<Tower, Enemy> shootEnemyConsumer) {
            super(enemiesSupplier, shootEnemyConsumer);
        }

        @Override
        protected void loadTowerImages() {
            BufferedImage atlas = Sprites.GetSprite("ACTORS");
            towerImgs = new BufferedImage[3];
            for (int i = 0; i < 3; i++) {
                BufferedImage topImg = atlas.getSubimage((20 + i) * 32, (8) * 32, 32, 32);
                BufferedImage backgroundImg = atlas.getSubimage((20 + i) * 32, (7) * 32, 32, 32);
                towerImgs[i] = ImageFix.BuildImage(new BufferedImage[] { backgroundImg, topImg });
            }
        }
    }

    public static final class EnemyManager
        extends licaza.tdefender.engine.core.managers.EnemyManager {
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
            BufferedImage atlas = MediaSource.Sprites.GetSprite("ENEMIES");

            for (int i = 0; i < 4; i++) {
                // For position of enemies in spritesheet_actors
                enemyImgs[i] = atlas.getSubimage((i * 32), 0, 32, 32);
            }
        }


        @Override
        public void addEnemy(int enemyType) {
            int x = start.xCord() * 32;
            int y = start.yCord() * 32;
            List<licaza.tdefender.engine.commons.actors.Enemy> enemies = getEnemies();

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

    public static final class TileManager
        extends licaza.tdefender.engine.core.managers.TileManager {

        public final static String TILE_ATLAS_PATH = "default_level";

        private Tilemap tilemap;

        public TileManager() {
            super(TILE_ATLAS_PATH);
            tilemap = new Tilemap();

            createTiles();
        }

         public void createTiles() {
            int id = 0;
            tiles.addAll(tilemap.getAllTiles());
        }

        // Getters

        public List<Tile> getSandTiles() {
            return tilemap.getSandTiles();
        }

    }
}
