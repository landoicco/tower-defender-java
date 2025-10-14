package licaza.tdefender.demo.inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

// import licaza.tdefender.engine.core.*;

import licaza.tdefender.demo.main.Game;
import licaza.tdefender.demo.main.GameStates;

public class KeyboardListener implements KeyListener {

    private Game game;

    public KeyboardListener(Game game) {
        this.game = game;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (GameStates.gameState) {
            case EDIT:
                game.getEditing().keyPressed(e);
                break;
            case MENU:
                break;
            case PLAYING:
                game.getPlaying().keyPressed(e);
                break;
            case SETTINGS:
                break;
            default:
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

}
