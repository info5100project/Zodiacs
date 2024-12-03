package application;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class RunningTimerController {
	
	private Scene logOutScene;

    @FXML
    private Label timeDisplay;

    @FXML
    private ListView<String> intervalList;

    @FXML
    private Button startStopButton;

    @FXML
    private Button pauseResumeButton;

    private Timeline timeline;
    private int seconds = 0;
    private boolean running = false;
    private boolean paused = false;

    @FXML
    public void initialize() {
        // Initialize the timeline with a 1-second interval
        timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> updateTimer()));
        timeline.setCycleCount(Timeline.INDEFINITE);

        updateDisplay();
        pauseResumeButton.setDisable(true); // Pause/Resume only enabled during active stopwatch
    }

    // Start or stop the stopwatch
    @FXML
    private void onStartStopClick() {
        if (!running) {
            // Start the stopwatch
            seconds = 0;
            running = true;
            paused = false;
            timeline.play();
            startStopButton.setText("Stop");
            pauseResumeButton.setDisable(false); // Enable Pause/Resume button
            updateDisplay();
        } else {
            // Stop the stopwatch
            running = false;
            paused = false;
            timeline.stop();
            startStopButton.setText("Start");
            pauseResumeButton.setText("Pause");
            pauseResumeButton.setDisable(true);
            intervalList.getItems().clear(); // Clear intervals
            seconds = 0;
            updateDisplay();
        }
    }

    // Pause or resume the stopwatch
    @FXML
    private void onPauseResumeClick() {
        if (paused) {
            // Resume
            timeline.play();
            paused = false;
            pauseResumeButton.setText("Pause");
        } else {
            // Pause
            timeline.pause();
            paused = true;
            pauseResumeButton.setText("Resume");
        }
    }

    // Capture the current time interval
    @FXML
    private void onCaptureIntervalClick() {
        if (running && !paused) {
            String intervalTime = formatTime(seconds);
            intervalList.getItems().add("Interval: " + intervalTime);
        }
    }

    // Update timer display
    private void updateTimer() {
        seconds++;
        updateDisplay();
    }

    // Update the time display label
    private void updateDisplay() {
        timeDisplay.setText(formatTime(seconds));
    }

    // Format time in hh:mm:ss format
    private String formatTime(int totalSeconds) {
        int hours = totalSeconds / 3600;
        int minutes = (totalSeconds % 3600) / 60;
        int secs = totalSeconds % 60;
        return String.format("%02d:%02d:%02d", hours, minutes, secs);
    }


//    private void goToNextScene(ActionEvent event, String fileName, String title, String userName) {
//
//        Parent root = null;
//        try {
//            root = FXMLLoader.load(getClass().getResource(fileName));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//        stage.setTitle(title);
//        stage.setUserData(userName);
//        stage.setScene(new Scene(root, 821, 591));
//        stage.show();
//
//    }

    @FXML
    private void weightAnalyticsPage(ActionEvent event){
//        goToNextScene(event, "dashboard-analytics.fxml", "Dashboard", "");
    	try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("dashboard-analytics.fxml"));
			Scene scene = new Scene(fxmlLoader.load(), 821.0, 591.0);

			DashboardAnalyticsController controller = fxmlLoader.getController();
			controller.setLogOutScene(logOutScene);

			Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			stage.setTitle("Dashboard");
			stage.setUserData("");
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    @FXML
    private void bikingTimerPage(ActionEvent event){
//        goToNextScene(event, "bikingTimer.fxml", "Biking Timer", "");
    	try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("bikingTimer.fxml"));
			Scene scene = new Scene(fxmlLoader.load(), 821.0, 591.0);

			BikingTimerController controller = fxmlLoader.getController();
			controller.setLogOutScene(logOutScene);

			Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			stage.setTitle("Biking Timer");
			stage.setUserData("");
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    @FXML
    private void logOutButton(ActionEvent event){
    	Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setScene(logOutScene);
		stage.show();
    }
    
	public void setLogOutScene(Scene logOutScene) {
		this.logOutScene = logOutScene;
	}

}
