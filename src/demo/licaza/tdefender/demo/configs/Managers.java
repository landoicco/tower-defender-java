package licaza.tdefender.demo.configs;

import java.awt.image.BufferedImage;
import java.util.function.BiConsumer;
import java.util.function.Supplier;
import java.util.concurrent.*;
import java.util.List;
import java.io.File;

import javax.sound.sampled.*;

import licaza.tdefender.engine.commons.actors.*;
import licaza.tdefender.engine.core.managers.*;
import licaza.tdefender.engine.tools.helpers.ImageFix;


import static licaza.tdefender.demo.configs.MediaSource.*;

public final class Managers {
    public static final class Projectile extends ProjectileManager {
        public Projectile(Supplier<List<Enemy>> enemiesSupplier) {
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

    public static final class Tower extends TowerManager {
        public Tower(Supplier<List<Enemy>> enemiesSupplier,BiConsumer<
                     licaza.tdefender.engine.commons.actors.Tower, Enemy> shootEnemyConsumer) {
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
}
