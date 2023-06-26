package scenes;

import java.awt.Graphics;

import helpers.LevelBuild;
import main.Game;
import managers.TileManager;

public class Playing extends GameScene implements SceneMethods {

    private int[][] lvl;
    private TileManager tileManager;

    public Playing(Game game) {
        super(game);

        lvl = LevelBuild.getLevelData();
        tileManager = new TileManager();
    }

    @Override
    public void render(Graphics g) {
        for (int i = 0; i < lvl.length; i++) {
            for (int j = 0; j < lvl[i].length; j++) {
                int id = lvl[i][j];
                g.drawImage(tileManager.getSprite(id), j * 32, i * 32, null);
            }
        }
    }

}
