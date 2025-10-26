package licaza.tdefender.engine.tools.managers;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import licaza.tdefender.engine.commons.objects.Tile;
import licaza.tdefender.engine.tools.helpers.LoadSave;

/**
 * Takes care of loading all images that will be displayed when playing.
 *
 * Abstract, since user needs to define and categorize the tiles for
 * his game.
 */
public abstract class TileManager {

        private final String atlasPath;
        private BufferedImage atlas;

        protected final List<Tile> tiles;

        public TileManager(String atlasPath) {
                this.atlasPath = atlasPath;
                this.tiles = new ArrayList<>();

                loadAtlas();
        }

        /**
         * On charge of loading all 'List' objects with the images needed for the game.
         *
         * This method should generate the 'tiles' object with each list entry being a
         * sprite to be showed on game.
         */
        public abstract void createTiles();

        public final List<Tile> getTiles() {
                return tiles;
        }

        public final void loadAtlas() {
                atlas = LoadSave.GetSpriteAtlas();
        }

        public final BufferedImage getSprite(int xCord, int yCord) {
                return atlas.getSubimage(xCord * 32, yCord * 32, 32, 32);
        }

        public final BufferedImage[] getAnimatedSprite(int xCord, int yCord, int amount) {
                BufferedImage[] arr = new BufferedImage[amount];
                for (int i = 0; i < amount; i++)
                        arr[i] = getSprite(xCord + i, yCord);

                return arr;
        }

        public final int[][] getTypeArray() {
                int[][] idArray = LoadSave.GetLevelData(atlasPath.toString());
                int[][] typeArray = new int[idArray.length][idArray[0].length];

                for (int j = 0; j < idArray.length; j++)
                        for (int i = 0; i < idArray[j].length; i++) {
                                int id = idArray[j][i];
                                typeArray[j][i] = tiles.get(id).getTileType();
                        }
                return typeArray;
        }

        public final Tile getTile(int id) {
                return tiles.get(id);
        }

        public final BufferedImage getSprite(int id) {
                return tiles.get(id).getSprite();
        }

        public final BufferedImage getAnimatedSprite(int id, int animationIndex) {
                return tiles.get(id).getSprite(animationIndex);
        }

        public final boolean isAnimatedSprite(int spriteId) {
                return tiles.get(spriteId).isAnimated();
        }
}
