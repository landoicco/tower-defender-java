package licaza.tdefender.demo.actors.towers;

import static licaza.tdefender.demo.configs.Constants.Towers.*;

public class Tower {

    private int x, y, id, towerType;
    private float damage, range, cooldown;

    public Tower(int x, int y, int id, int towerType) {
        this.x = x;
        this.y = y;
        this.id = id;
        this.towerType = towerType;

        /*
         * TODO: Define specific Tower classes for this, do similar as the Enemy
         * classes, where the specs for each Tower is defined on it's own Tower subclass
         */
        setDefaultDamage();
        setDefaultRange();
        setDefaultCooldown();
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

    public int getTowerType() {
        return towerType;
    }

    public float getDamage() {
        return damage;
    }

    public float getRange() {
        return range;
    }

    public float getCooldown() {
        return cooldown;
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
