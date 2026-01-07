package core;

import enemy.Enemy;
import player.Player;

public class BattleSystem {

    public static void playerAttack(Player p, Enemy e) {
        e.takeDamage(p.attack());
    }

    public static void enemyAttack(Player p, Enemy e) {
        p.takeDamage(e.attack());
    }
}
