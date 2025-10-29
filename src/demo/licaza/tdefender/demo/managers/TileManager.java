package licaza.tdefender.demo.managers;

import static licaza.tdefender.demo.configs.Constants.*;

import java.util.ArrayList;

import licaza.tdefender.engine.tools.helpers.ImageFix;
import licaza.tdefender.engine.commons.objects.Tile;

public class TileManager extends
                licaza.tdefender.engine.core.managers.TileManager {

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
