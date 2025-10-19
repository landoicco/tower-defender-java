package licaza.tdefender.demo.actors.towers;

import static licaza.tdefender.demo.configs.Constants.Towers.*;

public class Tower {

    private int x, y, id, towerType, cdTick, damage, tier;
    private float range, cooldown;

    public Tower(int x, int y, int id, int towerType) {
        this.x = x;
        this.y = y;
        this.id = id;
        this.towerType = towerType;
        this.tier = 1;

        /*
         * TODO: Define specific Tower classes for this, do similar as the Enemy
         * classes, where the specs for each Tower is defined on it's own Tower subclass
         */
        setDefaultDamage();
        setDefaultRange();
        setDefaultCooldown();
    }

    public void update() {
        cdTick++;
    }

    public void upgradeTower() {
        tier++;

        switch (towerType) {
            case ARCHER:
                damage += 2;
                range += 20;
                cooldown -= 5;
                break;
            case CANNON:
                damage += 5;
                range += 20;
                cooldown -= 15;
                break;
            case WIZARD:
                range += 20;
                cooldown -= 10;
                break;
        }
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

    private void setDefaultDamage() {
        damage = GetDefaultDamage(towerType);
    }

    private void setDefaultRange() {
        range = GetDefaultRange(towerType);
    }

    private void setDefaultCooldown() {
        cooldown = GetDefaultCooldown(towerType);
    }
}
