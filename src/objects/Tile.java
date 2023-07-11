package objects;

import java.awt.image.BufferedImage;

public class Tile {

    private BufferedImage sprite;
    private String name;
    private int id;

    public Tile(BufferedImage sprite, int id, String name) {
        this.sprite = sprite;
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BufferedImage getSprite() {
        return sprite;
    }

}
