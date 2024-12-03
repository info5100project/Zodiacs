package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class DashboardAnalyticsController {
	
	private Scene logOutScene;
	
    @FXML
    private TextField mon;

    @FXML
    private TextField tue;

    @FXML
    private TextField wed;

    @FXML
    private TextField thur;

    @FXML
    private TextField fri;

    @FXML
    private BarChart<String, Number> chart;

    @FXML
    private void onShowChartClick() {
        try {
            float mondayWeight = parseWeight(mon.getText(), "Monday");
            float tuesdayWeight = parseWeight(tue.getText(), "Tuesday");
            float wednesdayWeight = parseWeight(wed.getText(), "Wednesday");
            float thursdayWeight = parseWeight(thur.getText(), "Thursday");
            float fridayWeight = parseWeight(fri.getText(), "Friday");

            // Clear previous data
            chart.getData().clear();

            // Add data to the chart
            XYChart.Series<String, Number> series = new XYChart.Series<>();
            series.setName("Weight");

            series.getData().add(new XYChart.Data<>("Monday", mondayWeight));
            series.getData().add(new XYChart.Data<>("Tuesday", tuesdayWeight));
            series.getData().add(new XYChart.Data<>("Wednesday", wednesdayWeight));
            series.getData().add(new XYChart.Data<>("Thursday", thursdayWeight));
            series.getData().add(new XYChart.Data<>("Friday", fridayWeight));

            chart.getData().add(series);

        } catch (NumberFormatException e) {
            // Show error alert if parsing fails
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Input Error");
            alert.setHeaderText("Invalid Input");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }

    // Method to parse weight and show a custom error message if invalid
    private float parseWeight(String weightText, String day) throws NumberFormatException {
        try {
            return Float.parseFloat(weightText);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Please enter a valid number for " + day + ".");
        }
    }


    @FXML
    private void weightAnalyticsPage(ActionEvent event){
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
    private void runningTimerPage(ActionEvent event){
        try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("runningTimer.fxml"));
			Scene scene = new Scene(fxmlLoader.load(), 821.0, 591.0);

			RunningTimerController controller = fxmlLoader.getController();
			controller.setLogOutScene(logOutScene);

			Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			stage.setTitle("Running Timer");
			stage.setUserData("");
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    @FXML
    private void bikingTimerPage(ActionEvent event){
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
