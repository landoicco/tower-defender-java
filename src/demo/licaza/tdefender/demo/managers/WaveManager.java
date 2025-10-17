package licaza.tdefender.demo.managers;

import java.util.ArrayList;
import java.util.Arrays;

import licaza.tdefender.demo.events.Wave;
import licaza.tdefender.demo.scenes.Playing;

public class WaveManager {
    private Playing playing;
    private ArrayList<Wave> waves = new ArrayList<>();
    private int enemySpawnTickLimit = 60 * 1;
    private int enemySpawnTick = enemySpawnTickLimit;
    private int enemyIndex, waveIndex;

    public WaveManager(Playing playing) {
        this.playing = playing;
        createWaves();
    }

    public void update() {
        if (enemySpawnTick < enemySpawnTickLimit) {
            enemySpawnTick++;
        }
    }

    /**
     * On the tutorial, this name is called 'isTimeForNewEnemy'
     */
    public boolean shouldSpawnNewEnemy() {
        return enemySpawnTick >= enemySpawnTickLimit;
    }

    /**
     * In tutorial, this method is called 'isThereMoreEnemiesInWave'
     */
    public boolean isThereEnemiesLeft() {
        return enemyIndex < waves.get(waveIndex)
                .enemyList().size();
    }

    public int getNextEnemy() {
        enemySpawnTick = 0;
        return waves.get(waveIndex).enemyList().get(enemyIndex++);
    }

    public ArrayList<Wave> getWaves() {
        return waves;
    }

    private void createWaves() {
        waves.add(new Wave(new ArrayList<Integer>(Arrays
                .asList(1, 2, 1, 3, 1, 1, 2, 1, 3, 1))));
    }
}
