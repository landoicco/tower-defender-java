package ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class MyButton {
    public final int x, y, width, height, id;

    private boolean mouseOver, mousePressed;
    private String text;
    private Rectangle bounds;

    // Normal buttons
    public MyButton(String text, int x, int y, int width, int height) {
        this.text = text;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.id = -1;

        initBounds();
    }

    // Tile buttons
    public MyButton(String text, int x, int y, int width, int height, int id) {
        this.text = text;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.id = id;

        initBounds();
    }

    public void draw(Graphics g) {
        // Body
        drawBody(g);

        // Border
        drawBorder(g);

        // Text
        drawText(g);
    }

    public void setMouseOver(boolean mouseOver) {
        this.mouseOver = mouseOver;
    }

    public void setMousePressed(boolean mousePressed) {
        this.mousePressed = mousePressed;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public boolean isMouseOver() {
        return mouseOver;
    }

    public boolean isMousePressed() {
        return mousePressed;
    }

    public void resetBooleans() {
        mouseOver = false;
        mousePressed = false;
    }

    private void drawBorder(Graphics g) {
        g.setColor(Color.BLACK);
        g.drawRect(x, y, width, height);

        if (mousePressed) {
            g.drawRect(x + 1, y + 1, width - 2, height - 2);
            g.drawRect(x + 2, y + 2, width - 4, height - 4);
        }
    }

    private void drawBody(Graphics g) {
        if (mouseOver)
            g.setColor(Color.CYAN);
        else
            g.setColor(Color.ORANGE);

        g.fillRect(x, y, width, height);
    }

    private void drawText(Graphics g) {
        int textWidth = g.getFontMetrics().stringWidth(text);
        int textHeight = g.getFontMetrics().getHeight();

        g.drawString(
                text, x - (textWidth / 2) + (width / 2),
                y + (textHeight / 2) + (height / 2));
    }

    private void initBounds() {
        this.bounds = new Rectangle(x, y, width, height);
    }
}
