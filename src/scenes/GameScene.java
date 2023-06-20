package scenes;

import main.Game;

public abstract class GameScene {
    private Game game;

    public GameScene(Game game) {
        this.game = game;
    }

    public Game getGame() {
        return game;
    }
}
