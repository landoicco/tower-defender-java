package main;

import javax.swing.JPanel;
import java.util.ArrayList;
import java.util.Random;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class GameScreen extends JPanel {
    private BufferedImage img;
    private ArrayList<BufferedImage> sprites = new ArrayList<>();

    public GameScreen(BufferedImage img) {
        this.img = img;
        loadSprites();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawImage(sprites.get(new Random().nextInt(25)), 0, 0, null);

        // Grid
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 19; j++) {
                g.setColor(Color.ORANGE);
                g.drawRect(i * 32, j * 32, 32, 32);
            }
        }
    }

    private void loadSprites() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                sprites.add(img.getSubimage(j * 32, i * 32, 32, 32));
            }
        }
    }
}
