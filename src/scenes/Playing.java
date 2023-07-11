package scenes;

import java.awt.Graphics;

import helpers.LevelBuild;
import main.Game;
import managers.TileManager;
import ui.BottomBar;

public class Playing extends GameScene implements SceneMethods {

    private int[][] lvl;
    private TileManager tileManager;
    private BottomBar bottomBar;

    public Playing(Game game) {
        super(game);

        lvl = LevelBuild.getLevelData();
        tileManager = new TileManager();
        bottomBar = new BottomBar(0, 640, 640, 100, this);
    }

    public TileManager getTileManager() {
        return tileManager;
    }

    @Override
    public void render(Graphics g) {
        for (int i = 0; i < lvl.length; i++) {
            for (int j = 0; j < lvl[i].length; j++) {
                int id = lvl[i][j];
                g.drawImage(tileManager.getSprite(id), j * 32, i * 32, null);
            }
        }

        bottomBar.draw(g);
    }

    @Override
    public void mouseClicked(int x, int y) {
        // Click on BottomBar
        if (y >= 640) {
            bottomBar.mouseClicked(x, y);
        }
    }

    @Override
    public void mouseMoved(int x, int y) {
        if (y >= 640) {
            bottomBar.mouseMoved(x, y);
        }
    }

    @Override
    public void mousePressed(int x, int y) {
        if (y >= 640) {
            bottomBar.mousePressed(x, y);
        }
    }

    @Override
    public void mouseReleased(int x, int y) {
        bottomBar.mouseReleased(x, y);
    }
}
