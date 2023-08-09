package towers;

public class Wizard extends Tower {

    public static final float DEFAULT_COOLDOWN = 0.0f;
    public static final float DEFAULT_RANGE = 70.0f;
    public static final float DEFAULT_DAMAGE = 1.0f;

    public Wizard(int x, int y, int id, int towerType) {
        super(x, y, id, towerType, DEFAULT_COOLDOWN, DEFAULT_RANGE, DEFAULT_DAMAGE);
    }

}
