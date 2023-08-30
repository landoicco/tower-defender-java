package main;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.JFrame;

import helpers.LoadSave;
import managers.TileManager;
import scenes.*;

public class Game extends JFrame {

    private final double FPS_SET = 120.0;
    private final double UPS_SET = 60.0;

    private GameScreen gameScreen;
    private TileManager tileManager;

    // Scene classes
    private Render render;
    private Menu menu;
    private Playing playing;
    private Credits credits;
    private Editing editing;

    public Game() {
        super("Zombie Defenders");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        createDefaultLevel();
        initClasses();

        add(gameScreen);
        pack();
        setVisible(true);

        initGameLoopThread();
    }

    public static void main(String[] args) {
        new Game().gameScreen.initInputs();
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

    private void initClasses() {
        tileManager = new TileManager();
        render = new Render(this);
        gameScreen = new GameScreen(this);
        menu = new Menu(this);
        playing = new Playing(this);
        credits = new Credits(this);
        editing = new Editing(this);
    }

    private void createDefaultLevel() {
        int[] arr = new int[400];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = 0;
        }

        LoadSave.CreateLevel("default_level", arr);
    }

    private void updateGame() {
        switch (GameStates.gameState) {
            case EDIT:
                editing.update();
                break;
            case MENU:
                break;
            case PLAYING:
                playing.update();
                break;
            case SETTINGS:
                break;
            default:
                break;

        }
    }

    // Getters
    public Render getRender() {
        return render;
    }

    public Menu getMenu() {
        return menu;
    }

    public Playing getPlaying() {
        return playing;
    }

    public Credits getCredits() {
        return credits;
    }

    public Editing getEditing() {
        return editing;
    }

    public TileManager getTileManager() {
        return tileManager;
    }
}