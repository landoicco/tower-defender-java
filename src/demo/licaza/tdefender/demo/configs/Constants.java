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
    }

    public static class Projectiles {
        public static final int ARROW = 0;
        public static final int BOMB = 1;
        public static final int CHAINS = 2;

        public static float GetSpeed(int type) {
            switch (type) {
                case ARROW:
                    return 3f;
                case BOMB:
                    return 1f;
                case CHAINS:
                    return 2f;
            }
            return 0f;
        }
    }

    // TODO: Move this to separate "Towers" classes
    public static class Towers {
        public static final int CANNON = 0;
        public static final int ARCHER = 1;
        public static final int WIZARD = 2;

        public static String GetName(int towerType) {
            switch (towerType) {
                case CANNON:
                    return "Cannon";
                case ARCHER:
                    return "Archer";
                case WIZARD:
                    return "Wizard";
            }
            return "";
        }

        public static int GetDefaultDamage(int towerType) {
            switch (towerType) {
                case CANNON:
                    return 1;
                case ARCHER:
                    return 1;
                case WIZARD:
                    return 1;
            }
            return 0;
        }

        public static float GetDefaultRange(int towerType) {
            switch (towerType) {
                case CANNON:
                    return 100;
                case ARCHER:
                    return 150;
                case WIZARD:
                    return 100;
            }
            return 0;
        }

        public static float GetDefaultCooldown(int towerType) {
            switch (towerType) {
                case CANNON:
                    return 10;
                case ARCHER:
                    return 10;
                case WIZARD:
                    return 10;
            }
            return 0;
        }
    }

}
