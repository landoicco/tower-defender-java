package licaza.tdefender.demo.ui;

import java.awt.Color;
import java.awt.Graphics;

import licaza.tdefender.engine.tools.gui.MyButton;
import licaza.tdefender.engine.commons.misc.ColorPalette;

import static licaza.tdefender.demo.configs.Colors.*;

public abstract class Bar {

    private final static Color BORDER_BASE_COLOR = GetColorFromPalette(ColorPalette.ACCENT_ONE);
    private final static Color BORDER_HOVER_COLOR = GetColorFromPalette(ColorPalette.PRIMARY);

    public int x, y, width, height;

    public Bar(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

    }

    protected void drawButtonFeedback(Graphics g, MyButton b) {
        // Set border color on MouseOver
        if (b.isMouseOver()) {
            g.setColor(BORDER_BASE_COLOR);
        } else {
            g.setColor(BORDER_HOVER_COLOR);
        }

        // Set border color on MousePressed
        if (b.isMousePressed()) {
            g.setColor(BORDER_BASE_COLOR);
        }

        // Draw border
        g.drawRect(b.x + 1, b.y + 1, b.width - 2, b.height - 2);
        g.drawRect(b.x + 2, b.y + 2, b.width - 4, b.height - 4);

    }
}
