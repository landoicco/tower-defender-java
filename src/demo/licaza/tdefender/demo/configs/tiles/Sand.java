package licaza.tdefender.demo.configs.tiles;

import java.util.*;
import java.awt.image.BufferedImage;
import java.util.function.BiFunction;

import licaza.tdefender.engine.commons.objects.Tile;

public class Sand {
    private BiFunction<Integer, Integer, BufferedImage> spriteSource;
    private List<Tile> tiles;

    // Default, only Tilemap should be used outside
    Sand(BiFunction<Integer, Integer, BufferedImage> f) {
      this.tiles = new ArrayList<>();
      this.spriteSource = f;

      initTiles();
    }

    List<Tile> getTiles() {
        return tiles;
    }

    private Tile
        TL_SAND, // Define all other tiles
        T_SAND,
        SAND,
        C_B_SAND,
        C_BR_SAND;

    private void initTiles() {
        int id = 0;
        tiles.add(TL_SAND = new Tile(spriteSource.apply(0, 0), id++, 0));
        tiles.add(T_SAND = new Tile(spriteSource.apply(1, 0), id++, 0));
        tiles.add(SAND = new Tile(spriteSource.apply(2, 0), id++, 0));
        tiles.add(C_B_SAND = new Tile(spriteSource.apply(3, 0), id++, 0));
        tiles.add(C_BR_SAND = new Tile(spriteSource.apply(4, 0), id++, 0));
    }
}
