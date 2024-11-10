package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SceneManager {

	public static void changeScene(ActionEvent event, String fileName, String title, int userId) {

		Parent root = null;
		try {
			root = FXMLLoader.load(SceneManager.class.getResource(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setTitle(title);
		stage.setScene(new Scene(root, 800, 600));
		stage.show();

	}

}
