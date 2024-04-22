package org.apollo.template.Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;
import org.apollo.template.App;
import org.apollo.template.Service.Debugger.DebugMessage;

import java.net.URL;
import java.util.ResourceBundle;

public class GameController implements Initializable {
    private static GameController INSTANCE;
    @FXML
    private AnchorPane root;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        AnchorPane ap = new AnchorPane();

        ap.getChildren().add(new Label("HELLO WORLD"));

        ap.setLayoutX(600);
        ap.setLayoutY(600);


        ap.setBackground(Background.fill(Color.RED));
        root.getChildren().add(ap);

    }

    private GameController() {
        if (INSTANCE == null) {
            DebugMessage.info(this, "Creating an instance of " + this);
        }
    }

    public static GameController getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new GameController();
        }
        return INSTANCE;
    }

}
