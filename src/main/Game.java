package main;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class Game extends JFrame {

    private GameScreen gameScreen;
    private BufferedImage img;

    public Game() {
        importImage();

        setSize(640, 640);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        gameScreen = new GameScreen(img);
        add(gameScreen);
    }

    public static void main(String[] args) {

        Game game = new Game();
    }

    private void importImage() {
        InputStream is = getClass().getResourceAsStream("../res/spriteatlas.png");
        try {
            img = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}