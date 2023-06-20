package inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import main.GameStates;

public class KeyboardListener implements KeyListener {

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_A) {
            GameStates.gameState = GameStates.MENU;
            System.out.println("Changed to Menu Scene");
        } else if (e.getKeyCode() == KeyEvent.VK_S) {
            GameStates.gameState = GameStates.PLAYING;
            System.out.println("Changed to Playing scene");
        } else if (e.getKeyCode() == KeyEvent.VK_D) {
            GameStates.gameState = GameStates.SETTINGS;
            System.out.println("Changed to Settings scene");
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

}
