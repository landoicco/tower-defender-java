package managers;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import enemies.Enemy;
import enemies.*;
import helpers.LoadSave;
import scenes.Playing;
import static helpers.Constants.*;

public class EnemyManager {

    private Playing playing;
    private BufferedImage[] enemyImgs;
    private float speed = 0.5f;

    private ArrayList<Enemy> enemies = new ArrayList<>();

    public EnemyManager(Playing playing) {
        this.playing = playing;
        this.enemyImgs = new BufferedImage[4];

        addEnemy(0 * 32, 10 * 32, Enemies.ORC);
        addEnemy(2 * 32, 10 * 32, Enemies.WOLF);
        addEnemy(8 * 32, 11 * 32, Enemies.KNIGHT);
        addEnemy(10 * 32, 11 * 32, Enemies.BAT);

        loadEnemyImgs();
    }

    public void update() {
        for (Enemy e : enemies) {
            updateEnemyMove(e);
        }
    }

    public void draw(Graphics g) {
        for (Enemy e : enemies) {
            drawEnemy(e, g);
        }
    }

    public void addEnemy(int x, int y, int enemyType) {
        switch (enemyType) {
            case Enemies.ORC:
                enemies.add(new Orc(x, y, 0));
                break;
            case Enemies.BAT:
                enemies.add(new Bat(x, y, 0));
                break;
            case Enemies.KNIGHT:
                enemies.add(new Knight(x, y, 0));
                break;
            case Enemies.WOLF:
                enemies.add(new Wolf(x, y, 0));
                break;

        }
    }

    private void drawEnemy(Enemy e, Graphics g) {
        g.drawImage(enemyImgs[e.getEnemyType()], (int) e.getX(), (int) e.getY(), null);
    }

    // We use 4 because we know we only have 4 enemy sprites at this point
    private void loadEnemyImgs() {
        BufferedImage atlas = LoadSave.GetSpriteAtlas();

        for (int i = 0; i < 4; i++) {
            enemyImgs[i] = atlas.getSubimage(i * 32, 32, 32, 32);
        }
    }

    private void updateEnemyMove(Enemy e) {
        if (e.getLastDirection() == -1) {
            setNewDirectionAndMove(e);
        }

        int newX = (int) (e.getX() + getSpeedAndWidth(e.getLastDirection()));
        int newY = (int) (e.getY() + getSpeedAndHeight(e.getLastDirection()));
        if (getTileType(newX, newY) == Tiles.ROAD) {
            // Continue on the same direction
            e.move(speed, e.getLastDirection());
        } else if (isAtEnd()) {
            // End of road reached
        } else {
            // Find other direction
            setNewDirectionAndMove(e);
        }
    }

    private void setNewDirectionAndMove(Enemy e) {
        int direction = e.getLastDirection();

        // Move into the current till 100%
        int xCord = (int) (e.getX() / 32);
        int yCord = (int) (e.getY() / 32);
        fixEnemyOffsetTile(e, direction, xCord, yCord);

        if (direction == Direction.LEFT || direction == Direction.RIGHT) {
            int newY = (int) (e.getY() + getSpeedAndHeight(Direction.UP));
            if (getTileType((int) e.getX(), newY) == Tiles.ROAD) {
                e.move(speed, Direction.UP);
            } else {
                e.move(speed, Direction.DOWN);
            }
        } else {
            int newX = (int) (e.getX() + getSpeedAndWidth(Direction.RIGHT));
            if (getTileType(newX, (int) e.getY()) == Tiles.ROAD) {
                e.move(speed, Direction.RIGHT);
            } else {
                e.move(speed, Direction.LEFT);
            }
        }
    }

    private void fixEnemyOffsetTile(Enemy e, int direction, int xCord, int yCord) {
        switch (direction) {
            case Direction.RIGHT:
                if (xCord < 19) {
                    xCord++;
                }
                break;
            case Direction.DOWN:
                if (yCord < 19) {
                    yCord++;
                }
                break;
        }
        e.setPosition(xCord * 32, yCord * 32);
    }

    private boolean isAtEnd() {
        return false;
    }

    private int getTileType(int x, int y) {
        return playing.getTileType(x, y);
    }

    private float getSpeedAndWidth(int direction) {
        if (direction == Direction.LEFT) {
            return -speed;
        } else if (direction == Direction.RIGHT) {
            return speed + 32;
        }
        return 0;
    }

    private float getSpeedAndHeight(int direction) {
        if (direction == Direction.UP) {
            return -speed;
        } else if (direction == Direction.DOWN) {
            return speed + 32;
        }
        return 0;
    }

}
