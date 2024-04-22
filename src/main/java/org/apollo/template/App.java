package org.apollo.template;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.apollo.template.Service.ConfigLoader;
import org.apollo.template.View.ViewList;

public class App extends Application {

    public static int screenWidth = 1200;
    public static int screenHeight = 800;
    private String screenTitle = "Apollo - JavaFx Template";
    private Image icon = new Image("file:src/main/resources/org/apollo/template/images/TempLogoBG_shadow.png");
    public static Stage stageRoot;

    @Override
    public void start(Stage stage) {

        stageRoot = stage; // making stage public, for later use.

        ConfigLoader.get(); // initializing configLoader.
        Scene root = new Scene((Parent) ViewLoader.loadView(ViewList.MAIN), screenWidth, screenHeight); // loading main view.

        // setting stage props.
        stage.setTitle(screenTitle);
        stage.setScene(root);
        stage.getIcons().add(icon);
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}