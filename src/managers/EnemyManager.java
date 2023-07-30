package managers;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import enemies.Enemy;
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
        addEnemy(0 * 32, 10 * 32);
        loadEnemyImgs();
    }

    public void update() {
        for (Enemy e : enemies) {
            // Is next tile road?
            if (isNextTileRoad(e)) {
            }
        }
    }

    public void draw(Graphics g) {
        for (Enemy e : enemies) {
            drawEnemy(e, g);
        }
    }

    public void addEnemy(int x, int y) {
        enemies.add(new Enemy(x, y, 0, 0));
    }

    private void drawEnemy(Enemy e, Graphics g) {
        g.drawImage(enemyImgs[1], (int) e.getX(), (int) e.getY(), null);
    }

    // We use 4 because we know we only have 4 enemy sprites at this point
    private void loadEnemyImgs() {
        BufferedImage atlas = LoadSave.GetSpriteAtlas();
        enemyImgs[0] = atlas.getSubimage(32 * 0, 32, 32, 32);
        enemyImgs[1] = atlas.getSubimage(32 * 1, 32, 32, 32);
        enemyImgs[2] = atlas.getSubimage(32 * 2, 32, 32, 32);
        enemyImgs[3] = atlas.getSubimage(32 * 3, 32, 32, 32);
    }

    private boolean isNextTileRoad(Enemy e) {
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
        return false;
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
            // case Direction.LEFT:
            // if (xCord > 0) {
            // xCord--;
            // }
            // break;
            // case Direction.UP:
            // if (yCord > 0) {
            // yCord--;
            // }
            // break;
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
