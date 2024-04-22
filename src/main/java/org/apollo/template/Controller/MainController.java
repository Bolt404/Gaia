package org.apollo.template.Controller;

/*

    This it the main controller of the View, all other views are precedent with in this view.

 */

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import org.apollo.template.Service.Alert.AlertComp;
import org.apollo.template.Service.Alert.Alertable;
import org.apollo.template.Service.Debugger.DebugMessage;
import org.apollo.template.View.BorderPaneRegion;
import org.apollo.template.View.ViewList;
import java.net.URL;
import java.util.ResourceBundle;

import static org.apollo.template.ViewLoader.loadView;

public class MainController implements Initializable, Alertable<VBox> {
    private static MainController INSTANCE;
    @FXML
    private BorderPane borderPane;
    @FXML
    private VBox VBAlert;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        changeView(ViewList.HOME, BorderPaneRegion.CENTER);
        changeView(ViewList.MENU, BorderPaneRegion.LEFT);
    }

    /**
     * Method for loading a container from a fxml file into the main Borderpane
     * @param viewList FXMLView
     * @param borderPaneRegion Region to load the fxml view into.
     */
    public void changeView(ViewList viewList, BorderPaneRegion borderPaneRegion) {

        switch (borderPaneRegion){
            case TOP -> borderPane.setTop(loadView(viewList));
            case LEFT -> borderPane.setLeft(loadView(viewList));
            case RIGHT -> borderPane.setRight(loadView(viewList));
            case BOTTOM -> borderPane.setBottom(loadView(viewList));
            case CENTER -> borderPane.setCenter(loadView(viewList));
        }

    }



    private MainController() {
        if (INSTANCE == null) {
            DebugMessage.info(this, "Creating an instance of " + this);
        }
    }

    public static MainController getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new MainController();
        }
        return INSTANCE;
    }
    @Override
    public void getAlertArea(){
    }
    @Override
    public void clearAlertArea() {
        VBAlert.getChildren().clear();
    }
    @Override
    public void addAlert(AlertComp alertComp) {
        VBAlert.getChildren().add(alertComp);
    }
    @Override
    public void removeAlert(AlertComp alertComp) {
        VBAlert.getChildren().remove(alertComp);
    }
}