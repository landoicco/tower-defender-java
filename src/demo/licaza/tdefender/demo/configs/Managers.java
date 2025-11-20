package licaza.tdefender.demo.configs;

import java.awt.image.BufferedImage;
import java.util.function.*;
import java.util.concurrent.*;
import java.util.*;
import java.io.File;

import javax.sound.sampled.*;

import licaza.tdefender.demo.actors.enemies.*;
import licaza.tdefender.demo.configs.Constants.*;

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

        public Tile GRASS, WATER, BL_WATER_CORNER, TL_WATER_CORNER, TR_WATER_CORNER, BR_WATER_CORNER, TL_ISLAND,
            TR_ISLAND, BR_ISLAND, BL_ISLAND, T_WATER, R_WATER, B_WATER, L_WATER, ROAD_L_TO_R, ROAD_B_TO_T,
            BR_ROAD, LB_ROAD, TL_ROAD, RT_ROAD;

        public static ArrayList<Tile> plainRoads = new ArrayList<>();
        public static ArrayList<Tile> cornerRoads = new ArrayList<>();
        public static ArrayList<Tile> corners = new ArrayList<>();
        public static ArrayList<Tile> coasts = new ArrayList<>();
        public static ArrayList<Tile> islands = new ArrayList<>();

        public TileManager() {
            super(TILE_ATLAS_PATH);

            createTiles();
        }

        // Getters

        public ArrayList<Tile> getPlainRoads() {
            return plainRoads;
        }

        public ArrayList<Tile> getCornerRoads() {
            return cornerRoads;
        }

        public ArrayList<Tile> getCorners() {
            return corners;
        }

        public ArrayList<Tile> getCoasts() {
            return coasts;
        }

        public ArrayList<Tile> getIslands() {
            return islands;
        }

        public void createTiles() {
            int id = 0;

            // Basics
            tiles.add(GRASS = new Tile(getSprite(0, 2), id++, Tiles.GRASS));
            tiles.add(WATER = new Tile(getSprite(0, 3), id++, Tiles.WATER));

            // Plain roads
            plainRoads.add(ROAD_L_TO_R = new Tile(ImageFix.GetRotatedImage(getSprite(0, 1),
                                                                           0), id++, Tiles.ROAD));
            plainRoads.add(ROAD_B_TO_T = new Tile(ImageFix.GetRotatedImage(getSprite(0, 1),
                                                                           90), id++, Tiles.ROAD));

            // Corner roads
            cornerRoads.add(BR_ROAD = new Tile(ImageFix.GetRotatedImage(getSprite(5, 0), 0),
                                               id++, Tiles.ROAD));
            cornerRoads.add(LB_ROAD = new Tile(ImageFix.GetRotatedImage(getSprite(5, 0), 90),
                                               id++, Tiles.ROAD));
            cornerRoads.add(TL_ROAD = new Tile(ImageFix.GetRotatedImage(getSprite(5, 0), 180),
                                               id++, Tiles.ROAD));
            cornerRoads.add(RT_ROAD = new Tile(ImageFix.GetRotatedImage(getSprite(5, 0), 270),
                                               id++, Tiles.ROAD));

            // Water corners
            corners.add(BL_WATER_CORNER = new Tile(
                                                   ImageFix.GetRotatedImage(getSprite(5, 5), 0),
                                                   id++, Tiles.WATER));
            corners.add(TL_WATER_CORNER = new Tile(
                                                   ImageFix.GetRotatedImage(getSprite(5, 5), 90),
                                                   id++, Tiles.WATER));
            corners.add(TR_WATER_CORNER = new Tile(
                                                   ImageFix.GetRotatedImage(getSprite(5, 5), 180),
                                                   id++, Tiles.WATER));
            corners.add(BR_WATER_CORNER = new Tile(
                                                   ImageFix.GetRotatedImage(getSprite(5, 5), 270),
                                                   id++, Tiles.WATER));

            // Coasts
            coasts.add(T_WATER = new Tile(
                                          ImageFix.GetRotatedImage(getSprite(2, 7), 0),
                                          id++, Tiles.WATER));
            coasts.add(R_WATER = new Tile(
                                          ImageFix.GetRotatedImage(getSprite(2, 7), 90),
                                          id++, Tiles.WATER));
            coasts.add(B_WATER = new Tile(
                                          ImageFix.GetRotatedImage(getSprite(2, 7), 180),
                                          id++, Tiles.WATER));
            coasts.add(L_WATER = new Tile(
                                          ImageFix.GetRotatedImage(getSprite(2, 7), 270),
                                          id++, Tiles.WATER));

            // Island corners
            islands.add(TL_ISLAND = new Tile(
                                             ImageFix.GetRotatedImage(getSprite(9, 7), 0),
                                             id++, Tiles.WATER));
            islands.add(TR_ISLAND = new Tile(
                                             ImageFix.GetRotatedImage(getSprite(9, 7), 90),
                                             id++, Tiles.WATER));
            islands.add(BR_ISLAND = new Tile(
                                             ImageFix.GetRotatedImage(getSprite(9, 7), 180),
                                             id++, Tiles.WATER));
            islands.add(BL_ISLAND = new Tile(
                                             ImageFix.GetRotatedImage(getSprite(9, 7), 270),
                                             id++, Tiles.WATER));

            // Add all tiles to original ArrayList
            tiles.addAll(plainRoads);
            tiles.addAll(cornerRoads);
            tiles.addAll(corners);
            tiles.addAll(coasts);
            tiles.addAll(islands);
        }
    }
}
