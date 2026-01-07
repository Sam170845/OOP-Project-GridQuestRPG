package enemy;

import core.Entity;

public abstract class Enemy extends Entity {
    public int hp, damage, defense;
    protected int scoreValue;

    public Enemy(int x, int y, int hp, int damage, int defense, int scoreValue) {
        this.x = x;
        this.y = y;
        this.hp = hp;
        this.damage = damage;
        this.defense = defense;
        this.scoreValue = scoreValue;
    }

    public int takeDamage(int dmg) {
        int real = Math.max(0, dmg - defense);
        hp -= real;
        return real;
    }

    public boolean isAlive() {
        return hp > 0;
    }

    public int getScoreValue() {
        return scoreValue;
    }

    public abstract int attack();
}
