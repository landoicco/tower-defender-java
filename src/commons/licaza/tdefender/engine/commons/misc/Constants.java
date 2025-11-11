package licaza.tdefender.engine.commons.misc;

/**
 * Bundle of all constants needed for other engine modules
 */
public class Constants {
    public static final class Directions {
        public static final int LEFT = 0;
        public static final int UP = 1;
        public static final int RIGHT = 2;
        public static final int DOWN = 3;
    }

    /**
     * Useful for detect between "walkable" and "no-walkable" tile paths...
     */
    public static final class Tiles {
        // TODO: Refine this...
        public static final int ROAD_TILE = 2;

        public static final int WATER = 0;
        public static final int GRASS = 1;
        public static final int ROAD = 2;
    }

    public static class Towers {
        public static final int CANNON = 0;
        public static final int ARCHER = 1;
        public static final int WIZARD = 2;
    }

    public static class Projectiles {
        public static final int ARROW = 0;
        public static final int BOMB = 1;
        public static final int CHAINS = 2;

        // TODO: Move this function to demo module
        public static float GetSpeed(int type) {
            return switch (type) {
                case ARROW -> 8f;
                case BOMB -> 4f;
                case CHAINS -> 2f;
                default -> 0;
            };
        }
    }

    public static class Enemies {
        public static final int ORC = 0;
        public static final int BAT = 1;
        public static final int KNIGHT = 2;
        public static final int WOLF = 3;

        // public static float GetReward(int enemyType) {
        // return switch (enemyType) {
        // case ORC -> 5;
        // case BAT -> 5;
        // case KNIGHT -> 25;
        // case WOLF -> 10;
        // default -> 0;
        // };
        // }
    }
}
