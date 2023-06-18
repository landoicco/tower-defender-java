package main;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class Game extends JFrame implements Runnable {

    private final double FPS_SET = 120.0;
    private final double UPS_SET = 60.0;

    private GameScreen gameScreen;
    private BufferedImage img;
    private Thread gameThread;

    public Game() {
        importImage();

        setSize(640, 640);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        gameScreen = new GameScreen(img);
        add(gameScreen);
        setVisible(true);
    }

    public void run() {

        double timePerFrame = 1_000_000_000.0 / FPS_SET;
        double timePerUpdate = 1_000_000_000.0 / UPS_SET;
        long lastTimeCheck = System.currentTimeMillis();
        long lastFrame = System.nanoTime();
        long lastTimeUPS = System.nanoTime();
        int frames = 0;
        int updates = 0;

        while (true) {
            // Render
            if (System.nanoTime() - lastFrame >= timePerFrame) {
                lastFrame = System.nanoTime();
                repaint();
                frames++;
            }

            // Update
            if (System.nanoTime() - lastTimeUPS >= timePerUpdate) {
                lastTimeUPS = System.nanoTime();
                updateGame();
                updates++;
            }

            if (System.currentTimeMillis() - lastTimeCheck >= 1000) {
                System.out.println("FPS: " + frames + " | UPS: " + updates);
                frames = 0;
                updates = 0;
                lastTimeCheck = System.currentTimeMillis();
            }
        }
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.start();
    }

    private void start() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    private void updateGame() {
        // System.out.println("Game updated!");
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