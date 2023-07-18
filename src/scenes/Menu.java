package scenes;

import java.awt.Graphics;
import main.Game;
import main.GameStates;
import ui.MyButton;

public class Menu extends GameScene implements SceneMethods {

    private MyButton bPlaying, bEdit, bSettings, bQuit;

    public Menu(Game game) {
        super(game);

        initButtons();
    }

    @Override
    public void render(Graphics g) {
        drawButtons(g);
    }

    @Override
    public void mouseClicked(int x, int y) {
        if (bPlaying.getBounds().contains(x, y)) {
            GameStates.setGameState(GameStates.PLAYING);
        }
        if (bEdit.getBounds().contains(x, y)) {
            GameStates.setGameState(GameStates.EDIT);
        }
        if (bSettings.getBounds().contains(x, y)) {
            GameStates.setGameState(GameStates.SETTINGS);
        }
        if (bQuit.getBounds().contains(x, y)) {
            System.exit(0);
        }
    }

    @Override
    public void mouseMoved(int x, int y) {
        bPlaying.setMouseOver(false);
        bEdit.setMouseOver(false);
        bSettings.setMouseOver(false);
        bQuit.setMouseOver(false);

        if (bPlaying.getBounds().contains(x, y)) {
            bPlaying.setMouseOver(true);
        }
        if (bEdit.getBounds().contains(x, y)) {
            bEdit.setMouseOver(true);
        }
        if (bSettings.getBounds().contains(x, y)) {
            bSettings.setMouseOver(true);
        }
        if (bQuit.getBounds().contains(x, y)) {
            bQuit.setMouseOver(true);
        }
    }

    @Override
    public void mousePressed(int x, int y) {
        if (bPlaying.getBounds().contains(x, y)) {
            bPlaying.setMousePressed(true);
        }
        if (bEdit.getBounds().contains(x, y)) {
            bEdit.setMousePressed(true);
        }
        if (bSettings.getBounds().contains(x, y)) {
            bSettings.setMousePressed(true);
        }
        if (bQuit.getBounds().contains(x, y)) {
            bQuit.setMousePressed(true);
        }
    }

    @Override
    public void mouseReleased(int x, int y) {
        // Reset buttons
        bPlaying.resetBooleans();
        bEdit.resetBooleans();
        bSettings.resetBooleans();
        bQuit.resetBooleans();
    }

    @Override
    public void mouseDragged(int x, int y) {
    }

    private void initButtons() {

        int height, width, xPos, yPos, padding;
        height = 30;
        width = 100;
        xPos = 270;
        yPos = 250;
        padding = 15;

        bPlaying = new MyButton("Play", xPos, yPos, width, height);
        bEdit = new MyButton("Edit", xPos, yPos + (height + padding), width, height, width);
        bSettings = new MyButton("Settings", xPos, yPos + 2 * (height + padding), width, height);
        bQuit = new MyButton("Quit", xPos, yPos + 3 * (height + padding), width, height);
    }

    private void drawButtons(Graphics g) {
        bPlaying.draw(g);
        bEdit.draw(g);
        bSettings.draw(g);
        bQuit.draw(g);
    }
}
