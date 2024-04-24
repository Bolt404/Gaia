package org.apollo.template.Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.apollo.template.Service.Debugger.DebugMessage;
import org.apollo.template.View.BorderPaneRegion;
import org.apollo.template.View.ViewList;

import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;



public class CreateRentalController implements Initializable{

    @FXML
    private DatePicker dpStartDate, dpEndDate;
    @FXML
    private ComboBox cbType;
    @FXML
    private ListView lvFreeAutoCampers;


    private Date startDate;

    public String getStartDate() {
        return startDate.toString();
    }

    public void setStartDate() {
        this.startDate = Date.valueOf(dpStartDate.getValue());
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    private Date endDate;




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        comboBoxSetVal();




    }

    private static CreateRentalController INSTANCE;


    public void onButtonConfirm(){
        MainController.getInstance().changeView(ViewList.CREATERENTALCUSTOM, BorderPaneRegion.CENTER);
    }

    public void onButtonCancel(){
        MainController.getInstance().changeView(ViewList.MENU, BorderPaneRegion.CENTER);
    }




    private void comboBoxSetVal(){

        cbType.getItems().addAll("Show all", "Luxury", "Standard", "Basic");
        cbType.setValue("Show all");
    }






    private CreateRentalController() {
        if (INSTANCE == null) {
            DebugMessage.info(this, "Creating an instance of " + this);
        }
    }

    public static CreateRentalController getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new CreateRentalController();
        }
        return INSTANCE;
    }


}
