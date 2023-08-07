package towers;

public class Tower {
    private final int x, y, id, towerType;
    private float defaultCooldown, defaultRange, defaultDamage;

    public Tower(int x, int y, int id, int towerType) {
        this.x = x;
        this.y = y;
        this.id = id;
        this.towerType = towerType;
    }

    protected Tower(int x, int y, int id, int towerType, float defaulCooldown) {
        this(x, y, id, towerType);
        this.defaultCooldown = defaulCooldown;
    }

    protected Tower(int x, int y, int id, int towerType, float defaultCooldown, float defaultRange) {
        this(x, y, id, towerType, defaultCooldown);
        this.defaultRange = defaultRange;
    }

    protected Tower(int x, int y, int id, int towerType, float defaultCooldown, float defaultRange,
            float defaultDamage) {
        this(x, y, id, towerType, defaultCooldown, defaultRange);
        this.defaultDamage = defaultDamage;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getId() {
        return id;
    }

    public float getDefaultCooldown() {
        return defaultCooldown;
    }

    public float getDefaulRange() {
        return defaultRange;
    }

    public float getDefaultDamage() {
        return defaultDamage;
    }

    public int getTowerType() {
        return towerType;
    }

}
