package application;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import application.model.UserProfile;
import application.repository.ConnectionManager;
import application.repository.UserProfileManager;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class AddUserController implements Initializable {
	
	
	@FXML
	private TextField tf_username;
	
	@FXML
	private TextField tf_password;
	
	@FXML
	private Button btn_sign_up;
	
	@FXML
    private Button btn_login;
	
	private Scene loginScene;
	
	
	public void setPreScene(Scene loginScene) {
        this.loginScene = loginScene;
    }
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		btn_sign_up.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
				boolean hasError = false;
				
				if(tf_username.getText().isBlank() && tf_password.getText().isBlank()) {
					hasError = true;
					showError("All fields are required.");
				}
				else if (tf_username.getText().isBlank()) {
					hasError = true;
					showError("User Name is required");
				}
				else if (tf_password.getText().isBlank()) {
					hasError = true;
					showError("Password is required");
				}
				else {
					UserProfileManager manager = null;
					try {
						manager = new UserProfileManager(ConnectionManager.getConnection());
					} catch (SQLException e) {
						e.printStackTrace();
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					}

					UserProfile foundProfile = manager.findUserByUserName(tf_username.getText());

					if (foundProfile == null) {
						UserProfile profile = new UserProfile();
						profile.setUserName(tf_username.getText());
						profile.setPassword(tf_password.getText());
						manager.addUser(profile);
					} else {
						hasError = true;
						showError("The user name already exist.");
					}
				} 
				
				if (!hasError)
					goToNextScene(event, "user-details.fxml", "User Details", tf_username.getText() );
			}
		});
		
		
		btn_login.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

				Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
				stage.setScene(loginScene);
				stage.show();
			}
		});

	}
	
	private void showError(String msg)
	{
		Alert errorAlert = new Alert(AlertType.ERROR);
		errorAlert.setHeaderText(msg);
		errorAlert.showAndWait();
	}
	
	private void goToNextScene(ActionEvent event, String fileName, String title, String userName) {

		Parent root = null;
		try {
			root = FXMLLoader.load(getClass().getResource(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setTitle(title);
		stage.setUserData(userName);
		stage.setScene(new Scene(root, 400, 500));
		stage.show();

	}

}
