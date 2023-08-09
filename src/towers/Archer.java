package towers;

public class Archer extends Tower {

    public static final float DEFAULT_COOLDOWN = 0.0f;
    public static final float DEFAULT_RANGE = 100.0f;
    public static final float DEFAULT_DAMAGE = 2.0f;

    public Archer(int x, int y, int id, int towerType) {
        super(x, y, id, towerType, DEFAULT_COOLDOWN, DEFAULT_RANGE, DEFAULT_DAMAGE);
    }
}
