package org.apollo.template.Controller;

import javafx.fxml.FXML;
import org.apollo.template.View.BorderPaneRegion;
import org.apollo.template.View.ViewList;

public class LoginController {

    @FXML
    protected void onLogin(){
        MainController.getInstance().changeView(ViewList.HOME, BorderPaneRegion.CENTER);
        MainController.getInstance().changeView(ViewList.MENU, BorderPaneRegion.RIGHT);
        MainController.getInstance().changeView(ViewList.TOPBAR, BorderPaneRegion.TOP);
    }

}
