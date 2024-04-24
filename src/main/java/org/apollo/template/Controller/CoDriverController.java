package org.apollo.template.Controller;

import org.apollo.template.View.BorderPaneRegion;
import org.apollo.template.View.ViewList;

public class CoDriverController {


    public void onButtonCancel(){
        MainController.getInstance().changeView(ViewList.CREATERENTALCUSTOM, BorderPaneRegion.CENTER);
    }

    public void onButtonConfirm(){

    }


}
