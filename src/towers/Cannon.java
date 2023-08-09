package towers;

public class Cannon extends Tower {

    public static final float DEFAULT_COOLDOWN = 0.0f;
    public static final float DEFAULT_RANGE = 200.0f;
    public static final float DEFAULT_DAMAGE = 3.0f;

    public Cannon(int x, int y, int id, int towerType) {
        super(x, y, id, towerType, DEFAULT_COOLDOWN, DEFAULT_RANGE, DEFAULT_DAMAGE);
    }

}
