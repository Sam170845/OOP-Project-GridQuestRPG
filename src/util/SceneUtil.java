package util;

import javafx.scene.Scene;

public class SceneUtil {
	public static void applyCSS(Scene scene) {
		scene.getStylesheets().add(SceneUtil.class.getResource("/style.css").toExternalForm());
	}
}
