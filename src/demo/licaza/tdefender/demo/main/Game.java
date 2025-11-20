package licaza.tdefender.demo.main;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static licaza.tdefender.demo.configs.Managers.TileManager;

import licaza.tdefender.demo.scenes.*;

public class Game extends
        licaza.tdefender.engine.core.main.Game {

    private GameScreen gameScreen;
    private TileManager tileManager;

    // Scene classes
    private Render render;
    private Menu menu;
    private Playing playing;
    private Credits credits;
    private Editing editing;
    private GameOver gameOver;

    private final Runnable updateGameCallback = this::updateGame;

    public Game() {
        // from parent 'Game' class
        super("Cannons... fuck yeah!");
        setUpdateGameCallback(updateGameCallback);
        createDefaultLevel("default_level");

        initClasses();

        // from JFrame...
        add(gameScreen);
        pack();
        setVisible(true);

        initGameLoopThread();
    }

    public static void main(String[] args) {
        new Game().gameScreen.initInputs();
    }

    private void initGameLoopThread() {
        ExecutorService service = null;
        try {
            service = Executors.newSingleThreadExecutor();
            service.execute(getGameLoop());
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
        gameOver = new GameOver(this);
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

    public GameOver getGameOver() {
        return gameOver;
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
