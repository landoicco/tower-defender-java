package scenes;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import helpers.LoadSave;
import main.Game;
import ui.MyButton;

public class Credits extends GameScene implements SceneMethods {

    private BufferedImage homeIcon;
    private MyButton bMenu;

    public Credits(Game game) {
        super(game);

        // Init Buttons
        bMenu = new MyButton("Menu", 10, 650, 100, 30);
    }

    @Override
    public void render(Graphics g) {
        // Draw background
        g.setColor(new Color(195, 204, 128));
        g.fillRect(0, 0, 640, 800);

        // Draw credits
        g.setColor(Color.BLACK);
        g.setFont(new Font("Hack", Font.BOLD, 50));
        g.drawString("Coded by:", 180, 100);
        g.drawString("Lando Icaza", 150, 160);

        // homeIcon = LoadSave.GetSpriteAtlas("game_icons.png");
        // g.setFont(new Font("Arial", Font.PLAIN, 15));
        // bMenu.draw(g);

        // Draw footer
        g.setFont(new Font("Hack", Font.BOLD, 30));
        g.drawString("Made with love and Java", 120, 750);
    }

    @Override
    public void mouseClicked(int x, int y) {
    }

    @Override
    public void mouseMoved(int x, int y) {
    }

    @Override
    public void mousePressed(int x, int y) {
    }

    @Override
    public void mouseReleased(int x, int y) {
    }

    @Override
    public void mouseDragged(int x, int y) {
    }

}
