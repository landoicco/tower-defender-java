package managers;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import helpers.ImageFix;
import helpers.LoadSave;
import objects.Tile;

import static helpers.Constants.*;

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

        public BufferedImage getAnimatedSprite(int id, int animationIndex) {
                return tiles.get(id).getSprite(animationIndex);
        }

        public Tile getTile(int id) {
                return tiles.get(id);
        }

        public boolean isAnimatedSprite(int spriteId) {
                return tiles.get(spriteId).isAnimated();
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
                tiles.add(GRASS = new Tile(getSprite(9, 0), id++, Tiles.GRASS));
                tiles.add(WATER = new Tile(getAnimatedSprite(0, 0, 4), id++, Tiles.WATER));

                // Plain roads
                plainRoads.add(ROAD_L_TO_R = new Tile(ImageFix.GetRotatedImage(getSprite(8, 0),
                                0), id++, Tiles.ROAD));
                plainRoads.add(ROAD_B_TO_T = new Tile(ImageFix.GetRotatedImage(getSprite(8, 0),
                                90), id++, Tiles.ROAD));

                // Corner roads
                cornerRoads.add(BR_ROAD = new Tile(ImageFix.GetRotatedImage(getSprite(7, 0), 0),
                                id++, Tiles.ROAD));
                cornerRoads.add(LB_ROAD = new Tile(ImageFix.GetRotatedImage(getSprite(7, 0), 90),
                                id++, Tiles.ROAD));
                cornerRoads.add(TL_ROAD = new Tile(ImageFix.GetRotatedImage(getSprite(7, 0), 180),
                                id++, Tiles.ROAD));
                cornerRoads.add(RT_ROAD = new Tile(ImageFix.GetRotatedImage(getSprite(7, 0), 270),
                                id++, Tiles.ROAD));

                // Water corners
                corners.add(BL_WATER_CORNER = new Tile(
                                ImageFix.GetRotatedBuildedImage(getAnimatedSprite(0, 0, 4), getSprite(5, 0), 0, 0),
                                id++, Tiles.WATER));
                corners.add(TL_WATER_CORNER = new Tile(
                                ImageFix.GetRotatedBuildedImage(getAnimatedSprite(0, 0, 4), getSprite(5, 0), 90, 0),
                                id++,
                                Tiles.WATER));
                corners.add(TR_WATER_CORNER = new Tile(
                                ImageFix.GetRotatedBuildedImage(getAnimatedSprite(0, 0, 4), getSprite(5, 0), 180, 0),
                                id++,
                                Tiles.WATER));
                corners.add(BR_WATER_CORNER = new Tile(
                                ImageFix.GetRotatedBuildedImage(getAnimatedSprite(0, 0, 4), getSprite(5, 0), 270, 0),
                                id++,
                                Tiles.WATER));

                // Coasts
                coasts.add(T_WATER = new Tile(
                                ImageFix.GetRotatedBuildedImage(getAnimatedSprite(0, 0, 4), getSprite(6, 0), 0, 0),
                                id++, Tiles.WATER));
                coasts.add(R_WATER = new Tile(
                                ImageFix.GetRotatedBuildedImage(getAnimatedSprite(0, 0, 4), getSprite(6, 0), 90, 0),
                                id++, Tiles.WATER));
                coasts.add(B_WATER = new Tile(
                                ImageFix.GetRotatedBuildedImage(getAnimatedSprite(0, 0, 4), getSprite(6, 0), 180, 0),
                                id++, Tiles.WATER));
                coasts.add(L_WATER = new Tile(
                                ImageFix.GetRotatedBuildedImage(getAnimatedSprite(0, 0, 4), getSprite(6, 0), 270, 0),
                                id++, Tiles.WATER));

                // Island corners
                islands.add(TL_ISLAND = new Tile(
                                ImageFix.GetRotatedBuildedImage(getAnimatedSprite(0, 0, 4), getSprite(4, 0), 0, 0),
                                id++, Tiles.WATER));
                islands.add(TR_ISLAND = new Tile(
                                ImageFix.GetRotatedBuildedImage(getAnimatedSprite(0, 0, 4), getSprite(4, 0), 90, 0),
                                id++, Tiles.WATER));
                islands.add(BR_ISLAND = new Tile(
                                ImageFix.GetRotatedBuildedImage(getAnimatedSprite(0, 0, 4), getSprite(4, 0), 180, 0),
                                id++, Tiles.WATER));
                islands.add(BL_ISLAND = new Tile(
                                ImageFix.GetRotatedBuildedImage(getAnimatedSprite(0, 0, 4), getSprite(4, 0), 270, 0),
                                id++, Tiles.WATER));

                // Add all tiles to original ArrayList
                tiles.addAll(plainRoads);
                tiles.addAll(cornerRoads);
                tiles.addAll(corners);
                tiles.addAll(coasts);
                tiles.addAll(islands);
        }

        private void loadAtlas() {
                atlas = LoadSave.GetSpriteAtlas();
        }

        // TODO: amount may be redundant at this point, we know that we only have 4
        // sprites per animation
        private BufferedImage[] getAnimatedSprite(int xCord, int yCord, int amount) {
                BufferedImage[] arr = new BufferedImage[amount];
                for (int i = 0; i < amount; i++) {
                        arr[i] = getSprite(xCord + i, yCord);
                }

                return arr;
        }

        private BufferedImage getSprite(int xCord, int yCord) {
                return atlas.getSubimage(xCord * 32, yCord * 32, 32, 32);
        }

}
