package licaza.tdefender.engine.tools.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Map;

import licaza.tdefender.engine.commons.misc.ColorPalette;

public class TextButton {
    public final int x, y, width, height, id;

    private boolean mouseOver, mousePressed;
    private String text;
    private Rectangle bounds;
    private Map<ColorPalette, Color> colorPalette;

    public TextButton(String text, int x, int y, int width, int height,
            Map<ColorPalette, Color> colors) {
        this(text, x, y, width, height);
        this.colorPalette = colors;
    }

    // Normal buttons
    public TextButton(String text, int x, int y, int width, int height) {
        this.text = text;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.id = -1;

        initBounds();
    }

    public void draw(Graphics g) {
        // Draw text
        int textWidth = g.getFontMetrics().stringWidth(text);
        int textHeight = g.getFontMetrics().getHeight();

        if (colorPalette != null) {
            if (mouseOver)
                g.setColor(colorPalette.get(ColorPalette.ACCENT_ONE));
            else
                g.setColor(colorPalette.get(ColorPalette.PRIMARY));

            if (mousePressed) {
                g.setColor(colorPalette.get(ColorPalette.PRIMARY));
            }
        }

        g.drawString(
                text, x - (textWidth / 2) + (width / 2),
                y + (textHeight / 2) + (height / 2));
    }

    public void setMouseOver(boolean mouseOver) {
        this.mouseOver = mouseOver;
    }

    public void setMousePressed(boolean mousePressed) {
        this.mousePressed = mousePressed;
    }

    public void setText(String t) {
        this.text = t;
    }

    public void resetBooleans() {
        mouseOver = false;
        mousePressed = false;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public boolean isMouseOver() {
        return mouseOver;
    }

    private void initBounds() {
        // +10 added on y to center collider
        this.bounds = new Rectangle(x, y + 10, width, height);
    }

}
