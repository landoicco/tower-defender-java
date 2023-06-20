package scenes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;

import main.Game;

public class Menu extends GameScene implements SceneMethods {

    private ArrayList<BufferedImage> sprites = new ArrayList<>();
    private BufferedImage img;

    public Menu(Game game) {
        super(game);
        importImage();
        loadSprites();
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(sprites.get(new Random().nextInt(25)), 0, 0, null);

        // Grid
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                g.setColor(Color.ORANGE);
                g.drawRect(i * 32, j * 32, 32, 32);
            }
        }
    }

    private void importImage() {
        InputStream is = getClass().getResourceAsStream("../res/spriteatlas.png");
        try {
            img = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
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
