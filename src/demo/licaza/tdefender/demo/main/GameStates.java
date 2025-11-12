package licaza.tdefender.demo.main;

import javax.sound.sampled.Clip;

import static licaza.tdefender.demo.configs.MediaSource.*;

public enum GameStates {
    PLAYING, MENU, SETTINGS, EDIT, GAME_OVER;

    private static final Clip menuClip = Sounds.GetAudioClip("MENU");
    private static final Clip playClip = Sounds.GetAudioClip("PLAY");

    public static GameStates gameState = MENU;

    public static void setGameState(GameStates state) {

        // Select background music to play...
        if(state == MENU) {
            playClip.stop();
            menuClip.start();
        }
        if (state == PLAYING) {
            menuClip.stop();
            playClip.start();
        }

        gameState = state;
    }
}
