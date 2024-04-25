package org.apollo.template.Controller;

import javafx.fxml.FXML;
import org.apollo.template.View.BorderPaneRegion;
import org.apollo.template.View.ViewList;

public class TopbarController {

    private boolean toggleState = true;

    @FXML
    protected void onBtnToggleMenu(){


        if (!toggleState) {
            MainController.getInstance().changeView(ViewList.MENU, BorderPaneRegion.RIGHT);

        }

        else {
            MainController.getInstance().removeView(BorderPaneRegion.RIGHT);
        }

        toggleState = !toggleState;



    }

}
