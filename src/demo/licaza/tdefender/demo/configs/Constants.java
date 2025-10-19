package licaza.tdefender.demo.configs;

public class Constants {

    public static class Direction {
        public static final int LEFT = 0;
        public static final int UP = 1;
        public static final int RIGHT = 2;
        public static final int DOWN = 3;
    }

    public static class Tiles {
        public static final int WATER = 0;
        public static final int GRASS = 1;
        public static final int ROAD = 2;
    }

    public static class Enemies {
        public static final int ORC = 0;
        public static final int BAT = 1;
        public static final int KNIGHT = 2;
        public static final int WOLF = 3;

        public static float GetReward(int enemyType) {
            return switch (enemyType) {
                case ORC -> 5;
                case BAT -> 5;
                case KNIGHT -> 25;
                case WOLF -> 10;
                default -> 0;
            };
        }
    }

    public static class Projectiles {
        public static final int ARROW = 0;
        public static final int BOMB = 1;
        public static final int CHAINS = 2;

        public static float GetSpeed(int type) {
            return switch (type) {
                case ARROW -> 8f;
                case BOMB -> 4f;
                case CHAINS -> 2f;
                default -> 0;
            };
        }
    }

    // TODO: Move this to separate "Towers" classes
    public static class Towers {
        public static final int CANNON = 0;
        public static final int ARCHER = 1;
        public static final int WIZARD = 2;

        public static int GetTowerCost(int towerType) {
            return switch (towerType) {
                case CANNON -> 65;
                case ARCHER -> 30;
                case WIZARD -> 45;
                default -> 0;
            };
        }

        public static String GetName(int towerType) {
            return switch (towerType) {
                case CANNON -> "Cannon";
                case ARCHER -> "Archer";
                case WIZARD -> "Wizard";
                default -> "";
            };
        }

        public static int GetDefaultDamage(int towerType) {
            return switch (towerType) {
                case CANNON -> 1;
                case ARCHER -> 1;
                case WIZARD -> 1;
                default -> 0;
            };
        }

        public static float GetDefaultRange(int towerType) {
            return switch (towerType) {
                case CANNON -> 100;
                case ARCHER -> 150;
                case WIZARD -> 100;
                default -> 0;
            };
        }

        public static float GetDefaultCooldown(int towerType) {
            return switch (towerType) {
                case CANNON -> 50;
                case ARCHER -> 25;
                case WIZARD -> 40;
                default -> 0;
            };
        }
    }

}
