package licaza.tdefender.engine.commons.objects;

import java.util.*;
import java.util.stream.*;

// import licaza.tdefender.engine.commons.objects.Tile;

/** By design, SpriteSheet should be inmmutable once created! */
public abstract class SpriteSheet {

    private Map<String, List<Tile>> spritesheet;

    protected abstract void init();

    protected final void setSpritesheet(Map<String, List<Tile>> s) {
        if(spritesheet != null)
            throw new IllegalStateException("Spritesheet had been set already!");

        this.spritesheet = s;
    }

    public final Stream<Tile> tiles() {
        if(spritesheet == null)
            throw new IllegalStateException("Sprites have not been initialized yet!");

        return spritesheet
            .values()
            .stream()
            .flatMap(List::stream);
    }

    public final Map<String, List<Tile>> spritesheet() {
        if(spritesheet == null)
            throw new IllegalStateException("Sprites have not been initialized yet!");

        return new HashMap<String, List<Tile>>(spritesheet);
    }
}
