package application;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import application.repository.ConnectionManager;
import application.repository.UserProfileManager;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
						
						Alert errorAlert = new Alert(AlertType.INFORMATION);
						errorAlert.setHeaderText("Data Updated");
						errorAlert.showAndWait();
					}
					
				} catch (ClassNotFoundException | SQLException e) {
					e.printStackTrace();
				}
				
				
			}
		});
		

	}
	
	private void showError(String msg)
	{
		
	}

}
