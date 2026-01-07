package ui;

import data.Score;
import data.ScoreDAO;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import util.SceneUtil;

public class LeaderboardScreen {

    public static Scene getScene(Stage stage) {
        TableView<Score> table = new TableView<>();

        TableColumn<Score, String> u = new TableColumn<>("User");
        u.setCellValueFactory(new PropertyValueFactory<>("username"));

        TableColumn<Score, Integer> s = new TableColumn<>("Score");
        s.setCellValueFactory(new PropertyValueFactory<>("score"));

        table.getColumns().addAll(u, s);
        table.setItems(FXCollections.observableArrayList(ScoreDAO.getTopScores()));

        Button back = new Button("Back");
        back.setOnAction(e -> stage.setScene(MainMenu.getScene(stage)));

        VBox root = new VBox(10, table, back);
        root.setStyle("-fx-padding:20");
        Scene scene = new Scene(root, 400, 400);
        SceneUtil.applyCSS(scene);
        return scene;
    }
}
