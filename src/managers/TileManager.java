package managers;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import helpers.ImageFix;
import helpers.LoadSave;
import objects.Tile;

public class TileManager {

    public Tile GRASS, WATER, BL_WATER_CORNER, TL_WATER_CORNER, TR_WATER_CORNER, BR_WATER_CORNER, TL_ISLAND,
            TR_ISLAND, BR_ISLAND, BL_ISLAND, T_WATER, R_WATER, B_WATER, L_WATER, ROAD_L_TO_R, ROAD_B_TO_T,
            BR_ROAD, LB_ROAD, TL_ROAD, RT_ROAD;
    public BufferedImage atlas;

    public ArrayList<Tile> tiles = new ArrayList<>();
    public ArrayList<Tile> plainRoads = new ArrayList<>();
    public ArrayList<Tile> cornerRoads = new ArrayList<>();
    public ArrayList<Tile> corners = new ArrayList<>();
    public ArrayList<Tile> coasts = new ArrayList<>();
    public ArrayList<Tile> islands = new ArrayList<>();

    public TileManager() {
        loadAtlas();
        createTiles();
    }

    public BufferedImage getSprite(int id) {
        return tiles.get(id).getSprite();
    }

    public Tile getTile(int id) {
        return tiles.get(id);
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

    private void createTiles() {
        int id = 0;

        // Basics
        tiles.add(GRASS = new Tile(getSprite(9, 0), id++, "Grass"));
        tiles.add(WATER = new Tile(getSprite(0, 0), id++, "Water"));

        // Plain roads
        plainRoads.add(ROAD_L_TO_R = new Tile(ImageFix.GetRotatedImage(getSprite(8, 0),
                0), id++, "Left_To_Right_Road"));
        plainRoads.add(ROAD_B_TO_T = new Tile(ImageFix.GetRotatedImage(getSprite(8, 0),
                90), id++, "Bottom_To_Top_Road"));

        // Corner roads
        cornerRoads.add(BR_ROAD = new Tile(ImageFix.GetRotatedImage(getSprite(7, 0), 0),
                id++, "Bottom_To_Right_Road"));
        cornerRoads.add(LB_ROAD = new Tile(ImageFix.GetRotatedImage(getSprite(7, 0), 90),
                id++, "Left_To_Bottom_Road"));
        cornerRoads.add(TL_ROAD = new Tile(ImageFix.GetRotatedImage(getSprite(7, 0), 180),
                id++, "Top_To_Left_Road"));
        cornerRoads.add(RT_ROAD = new Tile(ImageFix.GetRotatedImage(getSprite(7, 0), 270),
                id++, "Right_To_Top_Road"));

        // Water corners
        corners.add(BL_WATER_CORNER = new Tile(ImageFix.BuildImage(getImages(0, 0, 5,
                0)), id++, "BL_Water_Corner"));
        corners.add(TL_WATER_CORNER = new Tile(ImageFix.GetRotatedBuildedImage(getImages(0, 0, 5, 0), 90, 1), id++,
                "TL_Water_Corner"));
        corners.add(TR_WATER_CORNER = new Tile(ImageFix.GetRotatedBuildedImage(getImages(0, 0, 5, 0), 180, 1), id++,
                "TR_Water_Corner"));
        corners.add(BR_WATER_CORNER = new Tile(ImageFix.GetRotatedBuildedImage(getImages(0, 0, 5, 0), 270, 1), id++,
                "BR_Water_Corner"));

        // Coasts
        coasts.add(T_WATER = new Tile(ImageFix.BuildImage(getImages(0, 0, 6, 0)),
                id++,
                "Top_Water"));
        coasts.add(R_WATER = new Tile(ImageFix.GetRotatedBuildedImage(getImages(0, 0,
                6, 0), 90, 1), id++,
                "Right_Water"));
        coasts.add(B_WATER = new Tile(ImageFix.GetRotatedBuildedImage(getImages(0, 0,
                6, 0), 180, 1), id++,
                "Bottom_Water"));
        coasts.add(L_WATER = new Tile(ImageFix.GetRotatedBuildedImage(getImages(0, 0,
                6, 0), 270, 1), id++,
                "Left_Water"));

        // Island corners
        islands.add(TL_ISLAND = new Tile(ImageFix.BuildImage(getImages(0, 0, 4, 0)),
                id++,
                "TL_Island"));
        islands.add(TR_ISLAND = new Tile(ImageFix.GetRotatedBuildedImage(getImages(0,
                0, 4, 0), 90, 1), id++,
                "TR_Island"));
        islands.add(BR_ISLAND = new Tile(ImageFix.GetRotatedBuildedImage(getImages(0,
                0, 4, 0), 180, 1), id++,
                "BR_Island"));
        islands.add(BL_ISLAND = new Tile(ImageFix.GetRotatedBuildedImage(getImages(0,
                0, 4, 0), 270, 1), id++,
                "BL_Island"));

        // Add all tiles to original ArrayList
        tiles.addAll(plainRoads);
        tiles.addAll(cornerRoads);
        tiles.addAll(corners);
        tiles.addAll(coasts);
        tiles.addAll(islands);
    }

    private BufferedImage[] getImages(int firstX, int firstY,
            int secondX, int secondY) {
        return new BufferedImage[] {
                getSprite(firstX, firstY),
                getSprite(secondX, secondY)
        };
    }

    private void loadAtlas() {
        atlas = LoadSave.getSpriteAtlas();
    }

    private BufferedImage getSprite(int xCord, int yCord) {
        return atlas.getSubimage(xCord * 32, yCord * 32, 32, 32);
    }

}
