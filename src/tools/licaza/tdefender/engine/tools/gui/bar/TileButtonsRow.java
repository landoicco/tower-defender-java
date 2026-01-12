package licaza.tdefender.engine.tools.gui.bar;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.stream.Stream;
import java.awt.image.BufferedImage;
import java.awt.*;

import licaza.tdefender.engine.commons.objects.SpriteSheet;
import licaza.tdefender.engine.commons.objects.Tile;
import licaza.tdefender.engine.commons.misc.IntPoint2D;
import licaza.tdefender.engine.commons.misc.ColorPalette;

import licaza.tdefender.engine.tools.gui.MyButton;

public final class TileButtonsRow {
    private final SpriteSheet spritesheet;
    private final IntPoint2D position;
    private final Map<MyButton, List<Tile>> tileButtonsMap;
    private final Map<ColorPalette, Color> colorMap;
    private final int btnSize;

    private int xOffset = 0;

    public TileButtonsRow(SpriteSheet s, IntPoint2D pos, Map<ColorPalette, Color> colorMap,
                          int btnSize, int xOffset) {
        this.spritesheet = s;
        this.position = pos;
        this.colorMap = colorMap;
        this.btnSize = btnSize;
        this.xOffset = xOffset;

        tileButtonsMap = new HashMap<>();

        init();
    }

    final Map<MyButton, List<Tile>> getTileButtonsMap() {
        return new HashMap<>(tileButtonsMap);
    }

    final Stream<MyButton> tileButtons() {
        return tileButtonsMap.keySet().stream();
    }

    // Intended to be called on Panel
    final void draw(Graphics g) {
        for (Map.Entry<MyButton, List<Tile>> entry : tileButtonsMap.entrySet()) {
            MyButton b = entry.getKey();
            BufferedImage img = entry.getValue().get(0).getSprite();

            g.drawImage(img, b.x, b.y, b.width, b.height, null);

            drawButtonFeedback(g, b);
        }
    }

    final void setXOffset(int o) {
        xOffset = o;
    }

    private final void init() {
        int id = 0;

        for(Map.Entry<String, List<Tile>> e : spritesheet
                .spritesheet().entrySet()) {
            MyButton b = new MyButton(e.getKey(), (position.x() + xOffset * id),
                                      position.y(), btnSize, btnSize, id);

            tileButtonsMap.put(b, e.getValue());

            id++;
        }
    }

    private void drawButtonFeedback(Graphics g, MyButton b) {
        if (b.isMouseOver()) {
            g.setColor(colorMap.get(ColorPalette.ACCENT_TWO));
        } else {
            g.setColor(colorMap.get(ColorPalette.ACCENT_THREE));
        }

        // Set border color on MousePressed
        if (b.isMousePressed()) {
            g.setColor(colorMap.get(ColorPalette.ACCENT_ONE));
        }

        // Draw border
        g.drawRect(b.x + 1, b.y + 1, b.width - 2, b.height - 2);
        g.drawRect(b.x + 2, b.y + 2, b.width - 4, b.height - 4);

    }
}
