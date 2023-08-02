package managers;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import enemies.Enemy;
import enemies.*;
import helpers.LoadSave;
import objects.PathPoint;
import scenes.Playing;
import static helpers.Constants.*;

public class EnemyManager {

    private Playing playing;
    private BufferedImage[] enemyImgs;
    private PathPoint start, end;
    private float speed = 0.5f;

    private ArrayList<Enemy> enemies = new ArrayList<>();

    public EnemyManager(Playing playing, PathPoint start, PathPoint end) {
        this.playing = playing;
        this.start = start;
        this.end = end;
        this.enemyImgs = new BufferedImage[4];

        // addEnemy(Enemies.ORC);
        // addEnemy(Enemies.WOLF);
        // addEnemy(Enemies.KNIGHT);
        addEnemy(Enemies.BAT);

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

    public void addEnemy(int enemyType) {
        int x = start.getxCord() * 32;
        int y = start.getyCord() * 32;
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
        if (getTileType(newX, newY) == Tiles.ROAD && !isAtEnd(e)) {
            // Continue on the same direction
            e.move(speed, e.getLastDirection());
        } else if (isAtEnd(e)) {
            // End of road reached
            System.out.println("End of path reached!");
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

        if (isAtEnd(e)) {
            return;
        }

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

    private boolean isAtEnd(Enemy e) {
        return (e.getX() == (end.getxCord() * 32)) &&
                (e.getY() == (end.getyCord() * 32));
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
