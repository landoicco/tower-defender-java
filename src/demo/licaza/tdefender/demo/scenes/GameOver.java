package licaza.tdefender.demo.scenes;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;

import licaza.tdefender.engine.awt.ui.MyButton;
import licaza.tdefender.engine.commons.api.SceneMethods;

import licaza.tdefender.demo.main.Game;

import static licaza.tdefender.demo.main.GameStates.*;

public class GameOver extends GameScene implements SceneMethods {
    private MyButton bReplay, bMenu;

    public GameOver(Game game) {
        super(game);
        initButtons();
    }

    @Override
    public void render(Graphics g) {
        bMenu.draw(g);
        bReplay.draw(g);

        // Game over text...
        g.setFont(new Font("LucidaSans", Font.BOLD, 50));
        g.setColor(Color.RED);
        g.drawString("GAME OVER", 200, 150);
    }

    @Override
    public void mouseClicked(int x, int y) {
        if (bMenu.getBounds().contains(x, y)) {
            resetAll();
            setGameState(MENU);
        } else if (bReplay.getBounds().contains(x, y))
            replayGame();
    }

    @Override
    public void mouseMoved(int x, int y) {
        bMenu.setMouseOver(false);
        bReplay.setMouseOver(false);

        if (bMenu.getBounds().contains(x, y))
            bMenu.setMouseOver(true);
        else if (bReplay.getBounds().contains(x, y))
            bReplay.setMouseOver(true);
    }

    @Override
    public void mousePressed(int x, int y) {
        if (bMenu.getBounds().contains(x, y))
            bMenu.setMousePressed(true);
        else if (bReplay.getBounds().contains(x, y))
            bReplay.setMousePressed(true);
    }

    @Override
    public void mouseReleased(int x, int y) {
        bMenu.resetBooleans();
        bReplay.resetBooleans();
    }

    @Override
    public void mouseDragged(int x, int y) {
    }

    private void initButtons() {

        int height, width, xPos, yPos, padding;
        height = 50;
        width = 120;
        xPos = 270;
        yPos = 300;
        padding = 20;

        bMenu = new MyButton("Menu", xPos, yPos, width, height);
        bReplay = new MyButton("Replay", xPos, (yPos + (height + padding)), width, height);
    }

    private void replayGame() {
        resetAll();
        setGameState(PLAYING);
    }

    private void resetAll() {
        game.getPlaying().resetEverything();
    }
}
