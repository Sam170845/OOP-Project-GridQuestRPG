package ui;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import player.*;

public class MainMenu {

    public static Scene getScene(Stage stage) {
        TextField name = new TextField();
        name.setPromptText("Enter username");

        ChoiceBox<String> cls = new ChoiceBox<>();
        cls.getItems().addAll("Warrior", "Mage");
        cls.setValue("Warrior");

        Label alert = new Label();
        alert.setStyle("-fx-text-fill: red;");

        Button start = new Button("Start Game");
        Button lb = new Button("Leaderboard");
        Button exit = new Button("Exit Game");

        start.setOnAction(e -> {
            if (name.getText().trim().isEmpty()) {
                alert.setText("Username cannot be empty!");
                return;
            }

            Player p = cls.getValue().equals("Warrior")
                    ? new Warrior()
                    : new Mage();

            stage.setScene(GameScreen.getScene(stage, p, name.getText()));
        });

        lb.setOnAction(e -> stage.setScene(LeaderboardScreen.getScene(stage)));

        exit.setOnAction(e -> Platform.exit());

        VBox root = new VBox(10,
                new Label("Username"),
                name,
                new Label("Class"),
                cls,
                start,
                lb,
                exit,
                alert
        );

        root.setStyle("-fx-padding:30; -fx-alignment:center;");
        Scene scene = new Scene(root, 300, 350);
        scene.getStylesheets().add(
                MainMenu.class.getResource("/style.css").toExternalForm()
        );

        return scene;
    }
}
