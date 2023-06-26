package scenes;

import java.awt.Color;
import java.awt.Graphics;
import main.Game;
import managers.TileManager;

public class Menu extends GameScene implements SceneMethods {

    private TileManager tileManager;

    public Menu(Game game) {
        super(game);

        tileManager = new TileManager();
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(tileManager.getSprite(2), 0, 0, null);

        // Grid
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                g.setColor(Color.ORANGE);
                g.drawRect(i * 32, j * 32, 32, 32);
            }
        }
    }

}
