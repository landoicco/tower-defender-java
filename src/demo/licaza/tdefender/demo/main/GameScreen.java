package licaza.tdefender.demo.main;

import javax.swing.JPanel;

import licaza.tdefender.demo.inputs.*;

import java.awt.Dimension;
import java.awt.Graphics;

public class GameScreen extends JPanel {
    private Game game;
    private Dimension size;
    private MyMouseListener mouseListener;
    private KeyboardListener keyboardListener;

    public GameScreen(Game game) {
        this.game = game;
        setPanelSize();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        game.getRender().render(g);
    }

    public void initInputs() {
        mouseListener = new MyMouseListener(game);
        keyboardListener = new KeyboardListener(game);

        addMouseListener(mouseListener);
        addMouseMotionListener(mouseListener);
        addKeyListener(keyboardListener);

        requestFocus();
    }

    private void setPanelSize() {
        size = new Dimension(640, 800);
        setMinimumSize(size);
        setPreferredSize(size);
        setMaximumSize(size);
    }
}
