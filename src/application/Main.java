package application;
	
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.SQLException;

import application.model.UserProfile;
import application.repository.ConnectionManager;
import application.repository.UserProfileManager;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) throws FileNotFoundException {
        BorderPane borderPane = new BorderPane();

        //create a VBox for layout (this will hold the image and login form)
        VBox vbox = new VBox(10);
        vbox.setStyle("-fx-padding: 10; -fx-alignment: center;");
        
      //  create and add the image
        Image image = new Image(new FileInputStream("picture1.jpg"));
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(300);
        imageView.setFitHeight(150);
        
        //create a label as the app title
        Label titleLabel = new Label("Welcome to The Zodiacs");
        titleLabel.setStyle("-fx-font-family: Verdana; -fx-font-weight: bold; -fx-text-fill: black; -fx-font-size: 18px;");
        
        //create text field for user name input
        TextField usernameField = new TextField();
        usernameField.setPromptText("Username");
        usernameField.setLayoutX(130);
        usernameField.setLayoutX(143);
        usernameField.setMaxWidth(150);
        
        //create password field for password input
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");
        passwordField.setLayoutX(130);
        passwordField.setLayoutX(188);
        passwordField.setMaxWidth(150);

        //create login button
        Button loginButton = new Button("Login");
        
        //no account, ask the user if they want to create an account
        Label accountLabel = new Label("No account? Create a new one!");
        Button createAccount = new Button("Create a new account");
        

        //add action handler for login button
        loginButton.setOnAction(event -> {
            String username = usernameField.getText().trim();
            String password = passwordField.getText().trim();

            if (checkCredentials(username, password)) {
                showAlert(AlertType.INFORMATION, "Login Successful", "Welcome, " + username);
            } else {
                showAlert(AlertType.ERROR, "Login Failed", "Invalid username or password");
            }
        });
        
        createAccount.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				
				try {
				FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("add-user.fxml"));
				Scene scene = new Scene(fxmlLoader.load(), 400, 500);
				AddUserController controller = fxmlLoader.getController();
				controller.setPreScene(createAccount.getScene());
				Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
				stage.setTitle("Sign Up");
				stage.setScene(scene);
				stage.show();	
				} catch (Exception e) {
			         e.printStackTrace();
			      }
			}
		});
        
        //add components to VBox layout
        vbox.getChildren().addAll(titleLabel, imageView, usernameField, passwordField, loginButton, accountLabel, createAccount);

        //set VBox layout as the center of BorderPane
        borderPane.setCenter(vbox);
        
        //create the scene and set it on the stage
        Scene scene = new Scene(borderPane, 400, 500);
        primaryStage.setTitle("Login!");
        primaryStage.setScene(scene);
        primaryStage.show();
        
	}
	
	private boolean checkCredentials(String username, String password) {
		UserProfileManager manager = null;
		try {
			manager = new UserProfileManager(ConnectionManager.getConnection());
			UserProfile profile = manager.findUserByUserName(username);
			if(profile!= null && profile.getPassword().equals(password)) {
				return true; 
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
        return false;  // Return false if no matching credentials were found
    }
	
    private void showAlert(AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

	
	public static void main(String[] args) {
		launch(args);
	}
}
