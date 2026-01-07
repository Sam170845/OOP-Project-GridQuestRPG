package app;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ui.MainMenu;

public class MainApp extends Application {

    public void start(Stage stage) {
        Scene scene = MainMenu.getScene(stage);
        stage.setTitle("GridQuest RPG");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
