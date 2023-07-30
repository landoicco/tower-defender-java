package scenes;

import java.awt.image.BufferedImage;

import main.Game;

public abstract class GameScene {

    protected int ANIMATION_SPEED = 20;
    protected int animationIndex, tick;
    protected Game game;

    public GameScene(Game game) {
        this.game = game;
    }

    public Game getGame() {
        return game;
    }

    protected boolean isAnimation(int id) {
        return game.getTileManager().isAnimatedSprite(id);
    }

    // Using magic number 4 due we know we only use 4 sprites per animation
    protected void updateTick() {
        tick++;
        if (tick > ANIMATION_SPEED) {
            tick = 0;
            animationIndex++;
            if (animationIndex >= 4) {
                animationIndex = 0;
            }
        }
    }

    protected BufferedImage getSprite(int spriteId) {
        return game.getTileManager().getSprite(spriteId);
    }

    protected BufferedImage getSprite(int spriteId, int animationIndex) {
        return game.getTileManager().getAnimatedSprite(spriteId, animationIndex);
    }
}
