package licaza.tdefender.demo.configs.tiles;

import licaza.tdefender.engine.commons.objects.Tile;
import java.util.*;
import java.awt.image.BufferedImage;

import licaza.tdefender.engine.commons.objects.Tile;

import static licaza.tdefender.demo.configs.MediaSource.*;

public class Tilemap {
    private static final BufferedImage ATLAS = Sprites.GetSprite("GROUNDS");
    public Tile SAND, GRASS, STONE, ICE, CHALK;
    private List<Tile> sandTiles, allTiles = new ArrayList<>();
    private int id;

    public Tilemap() {
        initTiles();
    }

    public List<Tile> getSandTiles() {
        return sandTiles;
    }

    public List<Tile> getAllTiles() {
        // TODO: Should be inmmutable, this list
        return allTiles;
    }

    private void initTiles() {
        List<Tile> sandTiles;

        sandTiles = new Sand(this::getSprite).getTiles();

        allTiles.addAll(sandTiles);

        System.out.println(sandTiles.size());
    }

    private BufferedImage getSprite(Integer x, Integer y) {
        return ATLAS.getSubimage((x * 32), (y * 32), 32, 32);
    }
}
