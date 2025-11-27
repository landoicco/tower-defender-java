package licaza.tdefender.demo.configs.tiles;

import java.util.*;
import java.awt.image.BufferedImage;
import java.util.function.BiFunction;

import licaza.tdefender.engine.commons.objects.Tile;
import licaza.tdefender.engine.commons.objects.SpriteSheet;

public final class Sand extends SpriteSheet {
    private final BiFunction<Integer, Integer, BufferedImage> spriteSource;
    private final Map<String, List<Tile>> spritesheet;
    private final List<Tile> cornedTiles, flatTiles, coastFlatTiles, coastCornedTiles;

    private int id;

    public Sand(BiFunction<Integer, Integer, BufferedImage> f) {
        // super();
        this.spriteSource = f;
        this.spritesheet = new HashMap<>();

        cornedTiles = new ArrayList<Tile>();
        flatTiles = new ArrayList<Tile>();
        coastFlatTiles = new ArrayList<Tile>();
        coastCornedTiles = new ArrayList<Tile>();

        init();
    }

    private Tile
        TL_SAND, TR_SAND, BR_SAND, BL_SAND,
        T_SAND, R_SAND, B_SAND, L_SAND,
        SAND,
        C_B_SAND, C_L_SAND, C_T_SAND, C_R_SAND,
        C_BR_SAND, C_BL_SAND, C_TL_SAND, C_TR_SAND;

    protected void init() {
        // Add single tile
        spritesheet.put("PLAIN", List.of(SAND = new Tile(spriteSource.apply(0, 2), id++, 0)));

        initCornedTiles();
        initFlatTiles();
        initCoastFlatTiles();
        initCoastCornedTiles();

        spritesheet.put("CORNED", cornedTiles);
        spritesheet.put("FLAT", flatTiles);
        spritesheet.put("FLAT_COAST", coastFlatTiles);
        spritesheet.put("CORNED_COAST", coastCornedTiles);

        setSpritesheet(spritesheet);
    }

    private void initCornedTiles() {
        cornedTiles.add(TL_SAND = new Tile(spriteSource.apply(0, 0), id++, 0));
    }

    private void initFlatTiles() {
        flatTiles.add(T_SAND = new Tile(spriteSource.apply(1, 0), id++, 0));
    }

    private void initCoastFlatTiles() {
        coastFlatTiles.add(C_B_SAND = new Tile(spriteSource.apply(3, 0), id++, 0));
    }

    private void initCoastCornedTiles() {
        coastCornedTiles.add(C_BR_SAND = new Tile(spriteSource.apply(4, 0), id++, 0));
    }
}
