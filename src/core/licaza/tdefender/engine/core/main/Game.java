package licaza.tdefender.engine.core.main;

import javax.swing.JFrame;

import licaza.tdefender.engine.tools.helpers.LoadSave;

public class Game extends JFrame {

    private final double FPS_SET = 120.0;
    private final double UPS_SET = 60.0;
    private Runnable updateGameCallback;
    private final String STATS_MSG = """
            ==============
                FPS: %d
                UPS: %d
                """;

    protected Game() {
        this("Tower Defender - Java");
    }

    protected Game(String title) {
        super(title);

        // from JFrame...
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

    }

    protected void setUpdateGameCallback(Runnable updateGameCallback) {
        this.updateGameCallback = updateGameCallback;
    }

    protected void createDefaultLevel(String fileName) {
        int[] arr = new int[400];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = 0;
        }

        LoadSave.CreateLevel(fileName, arr);
    }

    protected Runnable getGameLoop() {
        return gameLoop;
    }

    private final Runnable gameLoop = () -> {

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
                updateGameCallback.run();
                updates++;
            }

            if (System.currentTimeMillis() - lastTimeCheck >= 1000) {
                frames = 0;
                updates = 0;
                lastTimeCheck = System.currentTimeMillis();
            }
        }
    };
}
