package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;

public class TextButton {
    public final int x, y, width, height, id;

    private boolean mouseOver, mousePressed;
    private String text;
    private Rectangle bounds;

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
        g.setFont(new Font("Hack", Font.BOLD, 50));
        int textWidth = g.getFontMetrics().stringWidth(text);
        int textHeight = g.getFontMetrics().getHeight();

        if (mouseOver)
            g.setColor(new Color(231, 127, 9));
        else
            g.setColor(new Color(24, 17, 1));

        if (mousePressed) {
            g.setColor(new Color(234, 202, 0));
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

    public void resetBooleans() {
        mouseOver = false;
        mousePressed = false;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    private void initBounds() {
        // +10 added on y to center collider
        this.bounds = new Rectangle(x, y + 10, width, height);
    }

}
