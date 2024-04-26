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
    protected void onBtnGame( ) {
        MainController.getInstance().changeView(ViewList.CREATERENTAL, BorderPaneRegion.CENTER);
    }

    @FXML
    protected void onEditCustomer(){
        MainController.getInstance().changeView(ViewList.EDITCUSTOMER, BorderPaneRegion.CENTER);
    }

    @FXML
    protected void onBtnCreateAutocamper(){
        MainController.getInstance().changeView(ViewList.CreateAutocamper, BorderPaneRegion.CENTER);
    }

    @FXML
    protected void onBtnViewAutocampers(){
        MainController.getInstance().changeView(ViewList.ViewAutoCampers, BorderPaneRegion.CENTER);
    }

    public static MenuController getInstance() {
        if (INSTANCE == null) {

            INSTANCE = new MenuController();
        }
        return INSTANCE;
    }

}
