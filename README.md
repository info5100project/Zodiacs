# INSTRUCTIONS ON SETUP
### Step 1: Install Eclipse
If not already installed, download and install Eclipse IDE for Java Developers from the official website.

### Step 2: Install e(fx)clipse
e(fx)clipse is an Eclipse plugin for JavaFX that makes it easier to create JavaFX applications. Install it via the Eclipse Marketplace:
Go to Help > Eclipse Marketplace.
Search for "e(fx)clipse" and click Install.

### Step 3: Install JavaFX SDK
Download the JavaFX SDK from the Gluon website or 
OpenJFX site  [Download Link](https://gluonhq.com/products/javafx/) , and extract it to a known location on your system.

### Step 4: Configure JavaFX in Eclipse
Create a new Java Project: File > New > Java Project.

Name the project, for example, "MyJavaFXApp", and click Finish.
Right-click on the project folder, go to Build Path > Configure Build Path.
In the Libraries tab, click Add Library... > JavaFX SDK.
Click Next and then Browse to select the JavaFX SDK lib folder you downloaded. Click Finish.

### Step 5: Configure JavaFX Project
Right click project -> Properties -> Java Build Path

In Libraries tab: 
click “Modulepath” 
select “Add Library …” button 
choose “User Library”
choose “JavaFX” -> “Finish” -> “Apply” -> “Apply and Close”
At this moment, all errors should be gone.

### Step 6: Set Arguments for Running Project
 Right click on “Main.java” -> “Run As” -> “Run Configurations”

In “(x) = Arguments” tab -> VM arguments, type in following arguments:
--add-modules javafx.controls,javafx.fxml

### Step 7: Configure Scene Builder in IDE. [Download](https://gluonhq.com/products/scene-builder/) JavaFX Scene Builder.

Configuration:
Open Eclipse, go to Settings/Preferences and select JavaFX

Enter the path of your executable SceneBuilder file

Mac: /Application/SceneBuilder.app

Windows: C:\Users\YOURNAME\AppData\Local\SceneBuilder 
Note for Windows: Because “AppData” directory is hidden, I would recommend to move “SceneBuilder” to another location, such as C:\Program Files\SceneBuilder) 
Enter the path of JavaFX 11 + SDK
Mac: /Users/YOURNAME/JavaFX-SDK/javafx-sdk-20
Windows: C:\Users\YOURNAME\JavaFX-SDK\javafx-sdk-20

### Example:
```
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        Label label = new Label("Hello World!");
        StackPane root = new StackPane();
        root.getChildren().add(label);
        Scene scene = new Scene(root, 300, 250);

        primaryStage.setTitle("MyJavaFX");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
```
