package licaza.tdefender.engine.tools.gui.bar;

import java.util.*;
import java.util.stream.*;
import java.awt.Graphics;

import licaza.tdefender.engine.commons.misc.IntPoint2D;
import licaza.tdefender.engine.commons.objects.Tile;

import licaza.tdefender.engine.tools.gui.MyButton;

public final class Panel {
    private final Map<MyButton, List<Tile>> tileButtonsMap;
    private final Builder b;

    private Panel(Builder b) {
        this.b = b;

        this.tileButtonsMap = new HashMap<>();
    }

    public final void draw(Graphics g) {
        for(TileButtonsRow e : b.rows) {
            e.draw(g);
        }
    }

    public final Stream<MyButton> tileButtons() {
        List<Stream<MyButton>> list = new ArrayList<>();

        for (TileButtonsRow e : b.rows) {
            list.add(e.tileButtons());
        }

       System.out.println("Rows from Panel " + list.size());

       return list.stream().flatMap(s -> s);
    }

    /*  =========================
     *  ======== BUILDER ========
     *  =========================
     */
    public final static class Builder {
        private final IntPoint2D position;
        private final List<TileButtonsRow> rows;

        private int xOffset, yOffset;

        public Builder(IntPoint2D pos) {
            this.position = pos;

            rows = new ArrayList<>();
        }

        /** Define "margins" of tile buttons between them */
        public Builder setTileButtonsOffsets(int x, int y) {
            xOffset = x;
            yOffset = y;

            return this;
        }

        public Builder addTileButtonsRow(TileButtonsRow tbr) {
            rows.add(tbr);

            return this;
        }

        public Panel build() {
            return new Panel(this);
        }
    }
}
