package enemy;

public class Goblin extends Enemy {

    public Goblin(int x, int y) {
        super(x, y, 50, 15, 2, 25);
    }

    @Override
    public int attack() {
        return damage;
    }
}
