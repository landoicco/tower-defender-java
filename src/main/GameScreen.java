package main;

import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Graphics;

public class GameScreen extends JPanel {
    private Game game;
    private Dimension size;

    public GameScreen(Game game) {
        this.game = game;
        setPanelSize();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        game.getRender().render(g);
    }

    private void setPanelSize() {
        size = new Dimension(640, 640);
        setMinimumSize(size);
        setPreferredSize(size);
        setMaximumSize(size);
    }
}
