package licaza.tdefender.demo.managers;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import licaza.tdefender.engine.tools.helpers.LoadSave;
import licaza.tdefender.demo.actors.enemies.*;
import licaza.tdefender.demo.configs.Constants.Direction;
import licaza.tdefender.demo.configs.Constants.Enemies;
import licaza.tdefender.demo.configs.Constants.Tiles;
import licaza.tdefender.engine.commons.objects.PathPoint;
import licaza.tdefender.demo.scenes.Playing;

public class EnemyManager {

    private Playing playing;
    private BufferedImage[] enemyImgs;
    private BufferedImage slowEffect;
    private PathPoint start, end;
    private int HPbarWidth = 20;

    private ArrayList<Enemy> enemies = new ArrayList<>();

    public EnemyManager(Playing playing, PathPoint start, PathPoint end) {
        this.playing = playing;
        this.start = start;
        this.end = end;
        this.enemyImgs = new BufferedImage[4];

        loadEffectImages();
        loadEnemyImages();
    }

    public void update() {
        for (Enemy e : enemies) {
            updateEnemyMove(e);
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

    public void addEnemy(int enemyType) {
        int x = start.xCord() * 32;
        int y = start.yCord() * 32;
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

    public void spawnEnemy(int nextEnemy) {
        addEnemy(nextEnemy);
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

    private void updateEnemyMove(Enemy e) {
        if (e.getLastDirection() == -1) {
            setNewDirectionAndMove(e);
        }

        int newX = (int) (e.getX() + getSpeedAndWidth(e.getLastDirection(), e.getEnemySpeed()));
        int newY = (int) (e.getY() + getSpeedAndHeight(e.getLastDirection(), e.getEnemySpeed()));
        if (getTileType(newX, newY) == Tiles.ROAD && !isAtEnd(e)) {
            // Continue on the same direction
            e.move(e.getEnemySpeed(), e.getLastDirection());
        } else if (isAtEnd(e)) {
            e.kill();
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

        if (direction == Direction.LEFT || direction == Direction.RIGHT) {
            int newY = (int) (e.getY() + getSpeedAndHeight(Direction.UP, e.getEnemySpeed()));
            if (getTileType((int) e.getX(), newY) == Tiles.ROAD) {
                e.move(e.getEnemySpeed(), Direction.UP);
            } else {
                e.move(e.getEnemySpeed(), Direction.DOWN);
            }
        } else {
            int newX = (int) (e.getX() + getSpeedAndWidth(Direction.RIGHT, e.getEnemySpeed()));
            if (getTileType(newX, (int) e.getY()) == Tiles.ROAD) {
                e.move(e.getEnemySpeed(), Direction.RIGHT);
            } else {
                e.move(e.getEnemySpeed(), Direction.LEFT);
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
        return ((int) e.getX() == ((int) end.xCord() * 32)) &&
                (e.getY() == (end.yCord() * 32));
    }

    private int getTileType(int x, int y) {
        return playing.getTileType(x, y);
    }

    private float getSpeedAndWidth(int direction, float speed) {
        if (direction == Direction.LEFT) {
            return -speed;
        } else if (direction == Direction.RIGHT) {
            return speed + 32;
        }
        return 0;
    }

    private float getSpeedAndHeight(int direction, float speed) {
        if (direction == Direction.UP) {
            return -speed;
        } else if (direction == Direction.DOWN) {
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
