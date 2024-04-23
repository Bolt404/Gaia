package org.apollo.template.Controller;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import org.apollo.template.App;
import org.apollo.template.Service.Debugger.DebugMessage;
import org.apollo.template.UI.EmailTextField;
import org.controlsfx.control.RangeSlider;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class GameController implements Initializable {
    private static GameController INSTANCE;
    private ArrayList<TextField> textFieldArr = new ArrayList<>();
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

        EmailTextField emailTextField = new EmailTextField();

        emailTextField.setLayoutX(200);
        emailTextField.setLayoutX(200);

        TextField textField = new TextField();

        textField.setLayoutY(250);
        textField.setLayoutX(200);
        textField.setEditable(false);
        textField.setBackground(Background.fill(Color.GRAY));

        TextField textField2 = new TextField();

        textField2.setLayoutY(215);
        textField2.setLayoutX(200);
        textField2.setEditable(false);
        textField2.setBackground(Background.fill(Color.GRAY));

        textFieldArr.add(textField);
        textFieldArr.add(textField2);

        emailTextField.setOnKeyTyped(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {

                if(emailTextField.emailVerificationProperty().getValue()){
                    changeBackgroundColor(Color.WHITE, textFieldArr);
                } else changeBackgroundColor(Color.GRAY, textFieldArr);

                textField.editableProperty().bind(emailTextField.emailVerificationProperty());
                textField2.editableProperty().bind(emailTextField.emailVerificationProperty());

            }
        });


        emailTextField.focusedProperty().addListener((observable, oldValue, newValue)->{

            if(!newValue){



            }

        });

        root.getChildren().addAll(emailTextField, textField, textField2);



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

    /**
     * Method for recoloring the background, of all TextFields in a given ArrayList, to a given color.
     * @param color
     * @param textFieldArr
     */
    public static void changeBackgroundColor(Color color, ArrayList<TextField> textFieldArr){

        for (TextField textField : textFieldArr) {
            textField.setBackground(Background.fill(color));
            textField.setStyle("-fx-border-color: -fx-box-border, -fx-inner-border, -fx-body-color;\n" +
                            "-fx-border-insets: 0;\n" +
                            "-fx-border-radius: 3;\n" +
                            "-fx-border-style: solid;\n" +
                            "-fx-border-width: 1;");
        }

    }

}
