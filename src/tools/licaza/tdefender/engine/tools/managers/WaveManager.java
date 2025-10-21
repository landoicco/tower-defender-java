package licaza.tdefender.engine.tools.managers;

import java.util.ArrayList;
import java.util.Arrays;

import licaza.tdefender.engine.commons.events.Wave;

public class WaveManager {
    private ArrayList<Wave> waves = new ArrayList<>();
    private int enemySpawnTickLimit = 60 * 1;
    private int enemySpawnTick = enemySpawnTickLimit;
    private int waveTickLimit = 60 * 5;
    private int waveTick = 0;
    private int enemyIndex, waveIndex;
    private boolean waveStartTimer, waveTickTimerOver;

    public WaveManager() {
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

    public boolean shouldSpawnNewEnemy() {
        return enemySpawnTick >= enemySpawnTickLimit;
    }

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
