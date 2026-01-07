package player;

public class Warrior extends Player {
    public Warrior() {
        maxHp = 150;
        hp = maxHp;
        damage = 20;
        defense = 10;
    }

    public int attack() {
        return damage;
    }
}
