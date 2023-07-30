package objects;

import java.awt.image.BufferedImage;

public class Tile {

    private BufferedImage[] sprite;
    private int id, tileType;

    public Tile(BufferedImage sprite, int id, int tileType) {
        this.sprite = new BufferedImage[1];
        this.tileType = tileType;
        this.sprite[0] = sprite;

        this.id = id;
    }

    public Tile(BufferedImage[] sprite, int id, int tileType) {
        this.sprite = sprite;
        this.tileType = tileType;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public int getTileType() {
        return tileType;
    }

    public boolean isAnimated() {
        return this.sprite.length > 1;
    }

    public BufferedImage getSprite() {
        return sprite[0];
    }

    public BufferedImage getSprite(int animationIndex) {
        return sprite[animationIndex];
    }

}
