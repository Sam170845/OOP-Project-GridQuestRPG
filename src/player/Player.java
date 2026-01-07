package player;

import core.Entity;

public abstract class Player extends Entity {

    public int hp, maxHp, damage, defense;
    public int kills = 0;
    public int potions = 0;

    public int takeDamage(int dmg) {
        int real = Math.max(0, dmg - defense);
        hp -= real;
        return real;
    }

    public boolean isAlive() {
        return hp > 0;
    }

    public boolean usePotion() {
        if (potions <= 0 || hp == maxHp) return false;
        potions--;
        hp = Math.min(maxHp, hp + 40);
        return true;
    }

    public abstract int attack();

    public int getScore(int treasure) {
        return kills * 50 + hp + treasure * 30;
    }
}
