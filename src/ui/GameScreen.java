package ui;

import core.*;
import data.ScoreDAO;
import enemy.Enemy;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import player.*;

public class GameScreen {

    private static ImageView[][] cells = new ImageView[10][10];
    private static Label info = new Label();

    private static Enemy currentEnemy;
    private static boolean inBattle;

    public static Scene getScene(Stage stage, Player player, String username) {

        GameMap map = new GameMap(player);
        GridPane grid = new GridPane();
        info.getStyleClass().add("info-label");

        for (int y = 0; y < 10; y++) {
            for (int x = 0; x < 10; x++) {
                ImageView iv = new ImageView();
                iv.setFitWidth(32);
                iv.setFitHeight(32);
                cells[y][x] = iv;
                grid.add(iv, x, y);
            }
        }

        VBox root = new VBox(10, info, grid);
        Scene scene = new Scene(root, 420, 460);
        scene.getStylesheets().add("/style.css");

        draw(map, player);

        scene.setOnKeyPressed(e -> {

            if (inBattle && currentEnemy != null) {

                if (e.getCode() == KeyCode.F) {
                    BattleSystem.playerAttack(player, currentEnemy);

                    if (!currentEnemy.isAlive()) {
                        map.enemies.remove(currentEnemy);
                        map.map[currentEnemy.y][currentEnemy.x] = TileType.EMPTY;
                        player.kills++;
                        inBattle = false;
                        currentEnemy = null;
                        draw(map, player);
                        return;
                    }

                    BattleSystem.enemyAttack(player, currentEnemy);
                }

                if (e.getCode() == KeyCode.H) {
                    player.usePotion();
                    BattleSystem.enemyAttack(player, currentEnemy);
                }

                if (e.getCode() == KeyCode.R) {
                    inBattle = false;
                    currentEnemy = null;
                    draw(map, player);
                    return;
                }

                if (!player.isAlive()) {
                    stage.setScene(MainMenu.getScene(stage));
                    return;
                }

                info.setText(
                    "HP: " + player.hp +
                    " | Enemy HP: " + currentEnemy.hp +
                    " | Potions: " + player.potions +
                    "\n[F]Attack [H]Heal [R]Run"
                );
                return;
            }

            int newX = player.x;
            int newY = player.y;

            if (e.getCode() == KeyCode.W) newY--;
            if (e.getCode() == KeyCode.S) newY++;
            if (e.getCode() == KeyCode.A) newX--;
            if (e.getCode() == KeyCode.D) newX++;

            if (newX < 0 || newY < 0 || newX >= 10 || newY >= 10) return;
            if (map.map[newY][newX] == TileType.WALL) return;

            if (map.map[newY][newX] == TileType.TREASURE) {
                map.treasure++;
                if (Math.random() < 0.5) player.potions++;
            }

            if (map.map[newY][newX] == TileType.ENEMY) {
            	final int targetX = newX;
            	final int targetY = newY;
                currentEnemy = map.enemies.stream()
                        .filter(en -> en.x == targetX && en.y == targetY)
                        .findFirst()
                        .orElse(null);
                if (currentEnemy != null) {
                    inBattle = true;
                    return;
                }
            }

            if (map.map[newY][newX] == TileType.EXIT) {
                ScoreDAO.insertScore(username, player.getScore(map.treasure));
                stage.setScene(LeaderboardScreen.getScene(stage));
                return;
            }

            map.map[player.y][player.x] = TileType.EMPTY;
            player.x = newX;
            player.y = newY;
            map.map[newY][newX] = TileType.PLAYER;

            draw(map, player);
        });

        return scene;
    }

    private static void draw(GameMap map, Player player) {

        for (int y = 0; y < 10; y++) {
            for (int x = 0; x < 10; x++) {

                switch (map.map[y][x]) {

                    case PLAYER:
                        cells[y][x].setImage(
                                player instanceof Warrior ?
                                        ImageAssets.WARRIOR :
                                        ImageAssets.MAGE
                        );
                        break;

                    case ENEMY:
                    	final int X = x;
                    	final int Y = y;
                        Enemy e = map.enemies.stream()
                                .filter(en -> en.x == X && en.y == Y)
                                .findFirst()
                                .orElse(null);
                        if (e != null) {
                            cells[y][x].setImage(
                                    e instanceof enemy.Goblin ?
                                            ImageAssets.GOBLIN :
                                            ImageAssets.ORC
                            );
                        }
                        break;

                    case WALL: cells[y][x].setImage(ImageAssets.WALL); break;
                    case TREASURE: cells[y][x].setImage(ImageAssets.TREASURE); break;
                    case EXIT: cells[y][x].setImage(ImageAssets.EXIT); break;
                    default: cells[y][x].setImage(null);
                }
            }
        }

        info.setText(
            "HP: " + player.hp +
            " | Kills: " + player.kills +
            " | Treasure: " + map.treasure +
            " | Potions: " + player.potions 
        );
    }
}
