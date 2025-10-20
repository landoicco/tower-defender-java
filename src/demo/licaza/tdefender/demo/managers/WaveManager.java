package licaza.tdefender.demo.managers;

import java.util.ArrayList;
import java.util.Arrays;

import licaza.tdefender.engine.commons.events.Wave;

import licaza.tdefender.demo.scenes.Playing;

public class WaveManager {
    private Playing playing;
    private ArrayList<Wave> waves = new ArrayList<>();
    private int enemySpawnTickLimit = 60 * 1;
    private int enemySpawnTick = enemySpawnTickLimit;
    private int waveTickLimit = 60 * 5;
    private int waveTick = 0;
    private int enemyIndex, waveIndex;
    private boolean waveStartTimer, waveTickTimerOver;

    public WaveManager(Playing playing) {
        this.playing = playing;
        createWaves();
    }

    public void update() {
        if (enemySpawnTick < enemySpawnTickLimit) {
            enemySpawnTick++;
        }

        if (waveStartTimer) {
            waveTick++;
            if (waveTick >= waveTickLimit) {
                waveTickTimerOver = true;
            }
        }
    }

    public void increaseWaveIndex() {
        waveIndex++;
        waveTick = 0;
        waveTickTimerOver = false;
        waveStartTimer = false;
    }

    public void startWaveTimer() {
        waveStartTimer = true;
    }

    public void resetEnemyIndex() {
        enemyIndex = 0;
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

    public boolean isThereMoreWaves() {
        return waveIndex + 1 < waves.size();
    }

    public boolean isWaveTimerOver() {
        return waveTickTimerOver;
    }

    public boolean isWaveTimerStarted() {
        return waveStartTimer;
    }

    public float getTimeLeft() {
        float ticksLeft = waveTickLimit - waveTick;
        return ticksLeft / 60.0f;
    }

    public int getWaveIndex() {
        return waveIndex;
    }

    public int getNextEnemy() {
        enemySpawnTick = 0;
        return waves.get(waveIndex).enemyList().get(enemyIndex++);
    }

    public ArrayList<Wave> getWaves() {
        return waves;
    }

    public void reset() {
        waves.clear();
        createWaves();

        enemyIndex = waveIndex = waveTick = 0;
        waveStartTimer = waveTickTimerOver = false;
        enemySpawnTick = enemySpawnTickLimit;
    }

    private void createWaves() {
        waves.add(new Wave(new ArrayList<Integer>(Arrays
                .asList(1, 1, 1, 1, 1, 1, 1, 1, 1, 1))));

        waves.add(new Wave(new ArrayList<Integer>(Arrays
                .asList(2, 2, 2, 2, 2, 2, 2, 2, 2, 2))));
    }
}
