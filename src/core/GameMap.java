package core;

import enemy.*;
import player.Player;
import java.util.*;

public class GameMap {

    public static final int SIZE = 10;

    public TileType[][] map = new TileType[SIZE][SIZE];
    public ArrayList<Enemy> enemies = new ArrayList<>();
    public Player player;
    public int treasure = 0;

    public GameMap(Player p) {
        player = p;

        for (int y = 0; y < SIZE; y++)
            for (int x = 0; x < SIZE; x++)
                map[y][x] = TileType.EMPTY;

        buildBoundaryWalls();
        buildMazeWalls();
        ensurePathToExit();


        player.x = 1;
        player.y = 1;
        map[player.y][player.x] = TileType.PLAYER;

        map[SIZE - 2][SIZE - 2] = TileType.EXIT;

        placeTreasures();
        placeEnemies();
    }

    private void buildBoundaryWalls() {
        for (int i = 0; i < SIZE; i++) {
            map[0][i] = TileType.WALL;
            map[SIZE - 1][i] = TileType.WALL;
            map[i][0] = TileType.WALL;
            map[i][SIZE - 1] = TileType.WALL;
        }
    }

    private void buildMazeWalls() {
        Random r = new Random();

        for (int y = 2; y < SIZE - 2; y += 2) {
            for (int x = 2; x < SIZE - 2; x += 2) {
                map[y][x] = TileType.WALL;

                switch (r.nextInt(4)) {
                    case 0 : map[y - 1][x] = TileType.WALL; break;
                    case 1 : map[y + 1][x] = TileType.WALL; break;
                    case 2 : map[y][x - 1] = TileType.WALL; break;
                    case 3 : map[y][x + 1] = TileType.WALL; break;
                }
            }
        }
    }

    private void placeTreasures() {
        Random r = new Random();
        int placed = 0;

        while (placed < 3) {
            int x = r.nextInt(SIZE);
            int y = r.nextInt(SIZE);

            if (map[y][x] == TileType.EMPTY) {
                map[y][x] = TileType.TREASURE;
                placed++;
            }
        }
    }

    private void placeEnemies() {
        Random r = new Random();
        int placed = 0;

        while (placed < 4) {
            int x = r.nextInt(SIZE);
            int y = r.nextInt(SIZE);

            if (map[y][x] == TileType.EMPTY) {
                Enemy e = (placed % 2 == 0)
                        ? new Goblin(x, y)
                        : new Orc(x, y);

                enemies.add(e);
                map[y][x] = TileType.ENEMY;
                placed++;
            }
        }
    }
    private void ensurePathToExit() {
        int x = 1;
        int y = 1;

        while (x < SIZE - 2) {
            map[y][x] = TileType.EMPTY;
            x++;
        }

        while (y < SIZE - 2) {
            map[y][x] = TileType.EMPTY;
            y++;
        }
    }

}
