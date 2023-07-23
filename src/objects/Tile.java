package objects;

import java.awt.image.BufferedImage;

public class Tile {

    private BufferedImage[] sprite;
    private String name;
    private int id;

    public Tile(BufferedImage sprite, int id, String name) {
        this.sprite = new BufferedImage[1];
        this.sprite[0] = sprite;

        this.id = id;
        this.name = name;
    }

    public Tile(BufferedImage[] sprite, int id, String name) {
        this.sprite = sprite;
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public boolean isAnimated() {
        return this.sprite.length > 1;
    }

    public String getName() {
        return name;
    }

    public BufferedImage getSprite() {
        return sprite[0];
    }

    public BufferedImage getSprite(int animationIndex) {
        return sprite[animationIndex];
    }

}
