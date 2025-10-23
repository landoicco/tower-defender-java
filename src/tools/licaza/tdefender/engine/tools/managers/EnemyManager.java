package licaza.tdefender.engine.tools.managers;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.function.IntBinaryOperator;
import java.util.function.IntConsumer;

import licaza.tdefender.engine.tools.helpers.LoadSave;
import licaza.tdefender.engine.commons.objects.PathPoint;

import static licaza.tdefender.engine.tools.math.PathFinding.*;
import static licaza.tdefender.engine.commons.misc.Constants.Directions.*;
import static licaza.tdefender.engine.commons.misc.Constants.Tiles.*;

import licaza.tdefender.engine.commons.actors.Enemy;
import licaza.tdefender.engine.commons.misc.IntArrayProvider;

public abstract class EnemyManager {

    private BufferedImage[] enemyImgs;
    private int[][] roadDirectionArray;
    private BufferedImage slowEffect;
    private PathPoint start, end;
    private int HPbarWidth = 20;

    private ArrayList<Enemy> enemies = new ArrayList<>();

    private final IntConsumer rewardPlayerCallback;
    private final Runnable removeOneLiveCallback;
    private final IntBinaryOperator getTileTypeCallback;
    private final IntArrayProvider typeArrayCallback;

    public EnemyManager(IntConsumer rewardPlayerCallback, Runnable removeOneLiveCallback,
            IntBinaryOperator getTileTypeCallback, IntArrayProvider typeArrayCallback,
            PathPoint start, PathPoint end) {
        this.start = start;
        this.end = end;
        this.enemyImgs = new BufferedImage[4];

        this.rewardPlayerCallback = rewardPlayerCallback;
        this.removeOneLiveCallback = removeOneLiveCallback;
        this.getTileTypeCallback = getTileTypeCallback;
        this.typeArrayCallback = typeArrayCallback;

        loadEffectImages();
        loadEnemyImages();
        loadRoadDirectionArray();

    }

    public void update() {
        for (Enemy e : enemies) {
            if (e.isAlive()) {
                updateEnemyMoveNew(e);
            }
        }
    }

    public void draw(Graphics g) {
        for (Enemy e : enemies) {
            if (e.isAlive()) {
                drawEnemy(e, g);
                drawHealthBar(e, g);
                drawEffects(e, g);
            }
        }
    }

    public abstract void addEnemy(int enemyType);

    public void spawnEnemy(int nextEnemy) {
        addEnemy(nextEnemy);
    }

    public void rewardPlayer(int enemyType) {
        rewardPlayerCallback.accept(enemyType);
    }

    public int getAmountOfAliveEnemies() {
        int size = 0;
        for (Enemy e : enemies)
            if (e.isAlive())
                size++;

        return size;
    }

    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }

    public void reset() {
        enemies.clear();
    }

    private void drawEnemy(Enemy e, Graphics g) {
        g.drawImage(enemyImgs[e.getEnemyType()], (int) e.getX(), (int) e.getY(), null);
    }

    private void drawEffects(Enemy e, Graphics g) {
        if (e.isSlowed()) {
            g.drawImage(slowEffect, (int) e.getX(), (int) e.getY(), null);
        }
    }

    private void loadEffectImages() {
        slowEffect = LoadSave.GetSpriteAtlas("spriteatlas_legacy")
                .getSubimage(32 * 9, 32 * 2, 32, 32);
    }

    // We use 4 because we know we only have 4 enemy sprites at this point
    private void loadEnemyImages() {
        BufferedImage atlas = LoadSave.GetSpriteAtlas("spriteatlas_actors");

        for (int i = 0; i < 4; i++) {
            // For position of enemies in spritesheet_actors
            enemyImgs[i] = atlas.getSubimage((i * 32) + (15 * 32), (10 * 32), 32, 32);
        }
    }

    private void loadRoadDirectionArray() {
        roadDirectionArray = GetRoadDirectionArray(typeArrayCallback.get(),
                start, end);
    }

    /**
     * This is the PathFinding algorithm 2.0
     */
    private void updateEnemyMoveNew(Enemy e) {
        PathPoint currentTile = getEnemyTile(e);
        int direction = roadDirectionArray[currentTile.yCord()][currentTile.xCord()];

        e.move(e.getEnemySpeed(), direction);

        PathPoint newTile = getEnemyTile(e);
        if (isTilesTheSame(newTile, end)) {
            e.kill();
            removeOneLiveCallback.run();
        }
        if (!isTilesTheSame(currentTile, newTile)) {
            int newDirection = roadDirectionArray[newTile.yCord()][newTile.xCord()];
            if (newDirection != direction) {
                e.setPosition(newTile.xCord() * 32, newTile.yCord() * 32);
                e.setLastDirection(newDirection);
            }
        }
    }

    /**
     * This method is the legacy PathFinding algorithm
     */
    @Deprecated
    private void updateEnemyMove(Enemy e) {
        if (e.getLastDirection() == -1) {
            setNewDirectionAndMove(e);
        }

        int newX = (int) (e.getX() + getSpeedAndWidth(e.getLastDirection(), e.getEnemySpeed()));
        int newY = (int) (e.getY() + getSpeedAndHeight(e.getLastDirection(), e.getEnemySpeed()));
        if (getTileType(newX, newY) == ROAD && !isAtEnd(e)) {
            // Continue on the same direction
            e.move(e.getEnemySpeed(), e.getLastDirection());
        } else if (isAtEnd(e)) {
            // Kill enemy
            e.kill();

            // When enemy reach end of path, player lost one live
            removeOneLiveCallback.run();
        } else {
            setNewDirectionAndMove(e);
        }
    }

    private void setNewDirectionAndMove(Enemy e) {
        int direction = e.getLastDirection();

        // Move into the current till 100%
        int xCord = (int) (e.getX() / 32);
        int yCord = (int) (e.getY() / 32);
        fixEnemyOffsetTile(e, direction, xCord, yCord);

        if (isAtEnd(e))
            return;

        if (direction == LEFT || direction == RIGHT) {
            int newY = (int) (e.getY() + getSpeedAndHeight(UP, e.getEnemySpeed()));
            if (getTileType((int) e.getX(), newY) == ROAD) {
                e.move(e.getEnemySpeed(), UP);
            } else {
                e.move(e.getEnemySpeed(), DOWN);
            }
        } else {
            int newX = (int) (e.getX() + getSpeedAndWidth(RIGHT, e.getEnemySpeed()));
            if (getTileType(newX, (int) e.getY()) == ROAD) {
                e.move(e.getEnemySpeed(), RIGHT);
            } else {
                e.move(e.getEnemySpeed(), LEFT);
            }
        }
    }

    private void fixEnemyOffsetTile(Enemy e, int direction, int xCord, int yCord) {
        switch (direction) {
            case RIGHT:
                if (xCord < 19) {
                    xCord++;
                }
                break;
            case DOWN:
                if (yCord < 19) {
                    yCord++;
                }
                break;
        }
        e.setPosition(xCord * 32, yCord * 32);
    }

    private boolean isAtEnd(Enemy e) {
        return ((int) e.getX() == ((int) end.xCord() * 32)) &&
                (e.getY() == (end.yCord() * 32));
    }

    private boolean isTilesTheSame(PathPoint currentTile, PathPoint newTile) {
        if (currentTile.xCord() == newTile.xCord())
            if (currentTile.yCord() == newTile.yCord())
                return true;

        return false;
    }

    private PathPoint getEnemyTile(Enemy e) {
        return switch (e.getLastDirection()) {
            case LEFT -> new PathPoint((int) ((e.getX() + 31) / 32), (int) (e.getY() / 32));
            case UP -> new PathPoint((int) (e.getX() / 32), (int) ((e.getY() + 31) / 32));
            case RIGHT, DOWN -> new PathPoint((int) (e.getX() / 32), (int) (e.getY() / 32));
            default -> new PathPoint((int) (e.getX() / 32), (int) (e.getY() / 32));

        };
    }

    private int getTileType(int x, int y) {
        return getTileTypeCallback.applyAsInt(x, y);
    }

    private float getSpeedAndWidth(int direction, float speed) {
        if (direction == LEFT) {
            return -speed;
        } else if (direction == RIGHT) {
            return speed + 32;
        }
        return 0;
    }

    private float getSpeedAndHeight(int direction, float speed) {
        if (direction == UP) {
            return -speed;
        } else if (direction == DOWN) {
            return speed + 32;
        }
        return 0;
    }

    private void drawHealthBar(Enemy e, Graphics g) {
        g.setColor(Color.red);
        g.fillRect((int) e.getX() + 16 - (getNewBarWidth(e) / 2), (int) e.getY() - 10, getNewBarWidth(e), 3);

    }

    private int getNewBarWidth(Enemy e) {
        return (int) (HPbarWidth * e.getHealthBarFloat());
    }

}
