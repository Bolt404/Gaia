package org.apollo.template.Controller;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import org.apollo.template.Service.Alert.Alert;
import org.apollo.template.Service.Alert.AlertType;
import org.apollo.template.Service.Debugger.DebugMessage;
import org.apollo.template.View.BorderPaneRegion;
import org.apollo.template.View.ViewList;

public class MenuController {

    private static MenuController INSTANCE;
    @FXML
    private AnchorPane root;


    private MenuController() {
        if (INSTANCE == null) {
            DebugMessage.info(this, "Creating an instance of " + this);
        }
    }


    @FXML
    protected void onBtnHome() {
        MainController.getInstance().changeView(ViewList.HOME, BorderPaneRegion.CENTER);
    }

    @FXML
    protected void onBtnSettings( ) {
        MainController.getInstance().changeView(ViewList.SETTINGS, BorderPaneRegion.CENTER);
    }

    @FXML
    protected void onBtnGame( ) {
        MainController.getInstance().changeView(ViewList.GAME, BorderPaneRegion.CENTER);
    }

    @FXML
    protected void message(){
        Alert a = new Alert(MainController.getInstance(), 2, AlertType.ERROR, "Error Dette er line 1");
        a.start();
    }

    public static MenuController getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new MenuController();
        }
        return INSTANCE;
    }

}
