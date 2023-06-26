package main;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.JFrame;

import inputs.*;
import scenes.*;

public class Game extends JFrame {

    private final double FPS_SET = 120.0;
    private final double UPS_SET = 60.0;

    private GameScreen gameScreen;
    private MyMouseListener mouseListener;
    private KeyboardListener keyboardListener;

    // Scene classes
    private Render render;
    private Menu menu;
    private Playing playing;
    private Settings settings;

    public Game() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        initClasses();

        add(gameScreen);
        pack();
        setVisible(true);

        initInputs();
        initGameLoopThread();
    }

    public static void main(String[] args) {
        new Game();
    }

    Runnable gameLoop = () -> {

        double timePerFrame = 1_000_000_000.0 / FPS_SET;
        double timePerUpdate = 1_000_000_000.0 / UPS_SET;
        long lastTimeCheck = System.currentTimeMillis();
        long lastFrame = System.nanoTime();
        long lastTimeUPS = System.nanoTime();
        int frames = 0;
        int updates = 0;

        long now;

        while (true) {
            now = System.nanoTime();

            // Render
            if (now - lastFrame >= timePerFrame) {
                lastFrame = now;
                repaint();
                frames++;
            }

            // Update
            if (now - lastTimeUPS >= timePerUpdate) {
                lastTimeUPS = now;
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
    };

    private void initGameLoopThread() {
        ExecutorService service = null;
        try {
            service = Executors.newSingleThreadExecutor();
            service.execute(gameLoop);
        } finally {
            if (service != null)
                service.shutdown();
        }
    }

    private void initInputs() {
        mouseListener = new MyMouseListener();
        keyboardListener = new KeyboardListener();

        addMouseListener(mouseListener);
        addMouseMotionListener(mouseListener);
        addKeyListener(keyboardListener);

        requestFocus();
    }

    private void initClasses() {
        render = new Render(this);
        gameScreen = new GameScreen(this);
        menu = new Menu(this);
        playing = new Playing(this);
        settings = new Settings(this);
    }

    private void updateGame() {
        // System.out.println("Game updated!");
    }

    // Getters and Setters
    public Render getRender() {
        return render;
    }

    public Menu getMenu() {
        return menu;
    }

    public Playing getPlaying() {
        return playing;
    }

    public Settings getSettings() {
        return settings;
    }
}