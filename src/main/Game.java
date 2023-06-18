package main;

import javax.swing.JFrame;

public class Game extends JFrame {

    private GameScreen gameScreen;

    public Game() {
        setSize(400, 400);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        gameScreen = new GameScreen();
        add(gameScreen);
    }

    public static void main(String[] args) {
        System.out.println("Tower Defender Java - running");

        Game game = new Game();
    }
}