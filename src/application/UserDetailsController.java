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
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class UserDetailsController implements Initializable {
	
	@FXML
	private TextField tf_first_name;
	
	@FXML
	private TextField tf_last_name;
	
	@FXML
	private TextField tf_age;
	
	@FXML
	private TextField tf_height;
	
	@FXML
	private TextField tf_weight;
	
	@FXML
	private Button btn_save;
	
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		btn_save.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
				Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
				String userName = (String) stage.getUserData();
				try {
					UserProfileManager manager = new UserProfileManager(ConnectionManager.getConnection());
					UserProfile profile = manager.findUserByUserName(userName);
					if (profile != null) {
						profile.setFirstName(tf_first_name.getText());
						profile.setLastName(tf_last_name.getText());
						profile.setAge(tf_age.getText());
						profile.setHeightInInches(tf_height.getText());
						profile.setWeightInLbs(tf_weight.getText());

						manager.updateUser(profile);
						
					}
					
				} catch (ClassNotFoundException | SQLException e) {
					e.printStackTrace();
				}
				
				goToNextScene(event, "logged-in-page.fxml", "Welcome To Zodiacs", userName);
				
			}
		});
		

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
