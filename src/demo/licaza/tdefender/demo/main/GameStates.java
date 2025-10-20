package licaza.tdefender.demo.main;

public enum GameStates {
    PLAYING, MENU, SETTINGS, EDIT, GAME_OVER;

    public static GameStates gameState = MENU;

    public static void setGameState(GameStates state) {
        gameState = state;
    }
}
