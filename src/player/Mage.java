package player;

public class Mage extends Player {
    public Mage() {
        maxHp = 100;
        hp = maxHp;
        damage = 35;
        defense = 3;
    }

    public int attack() {
        return damage;
    }
}
