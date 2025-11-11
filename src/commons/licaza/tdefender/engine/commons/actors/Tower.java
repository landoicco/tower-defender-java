package licaza.tdefender.engine.commons.actors;

public class Tower {

    private int x, y, id, towerType, cdTick, damage, tier;
    private float range, cooldown;

    public Tower(int x, int y, int id, int towerType, int damage,
            int range, int cooldown) {
        this.x = x;
        this.y = y;
        this.id = id;
        this.towerType = towerType;
        this.damage = damage;
        this.range = range;
        this.cooldown = cooldown;

        this.tier = 1;

    }

    public Tower(int x, int y, int id, int towerType) {
        this(x, y, id, towerType, -1, -1, -1);
    }

    public void update() {
        cdTick++;
    }

    public void upgradeTower(int damage, float range, float cooldown) {
        tier++;
        this.damage += damage;
        this.range += range;
        this.cooldown -= cooldown;
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

    public int getTier() {
        return tier;
    }

    public int getTowerType() {
        return towerType;
    }

    public int getDamage() {
        return damage;
    }

    public float getRange() {
        return range;
    }

    public float getCooldown() {
        return cooldown;
    }

    public boolean isCooldownOver() {
        return cdTick >= cooldown;
    }

    public void resetCooldown() {
        cdTick = 0;
    }
}