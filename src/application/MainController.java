package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MainController implements Initializable {
	
	@FXML
	private Button btn_sign_up;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		btn_sign_up.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				goToNextScene(event, "add-user.fxml", "Sign Up");				
			}
		});
		
	}
	
	private void goToNextScene(ActionEvent event, String fileName, String title) {

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
