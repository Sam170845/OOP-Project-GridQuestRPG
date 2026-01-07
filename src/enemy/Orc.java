package enemy;

public class Orc extends Enemy {

    public Orc(int x, int y) {
        super(x, y, 120, 25, 8, 60);
    }

    @Override
    public int attack() {
        return damage;
    }
}
