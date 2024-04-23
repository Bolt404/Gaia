package org.apollo.template.Controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;
import org.apollo.template.Service.Debugger.DebugMessage;
import org.apollo.template.View.BorderPaneRegion;
import org.apollo.template.View.ViewList;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;



public class CreateRentalController implements Initializable{

    @FXML
    private DatePicker dpStartDate, dpEndDate;
    @FXML
    private ComboBox cbType;

    /*
        public List<Resturent> loadAllResturents(){

            resturentDAO = new ResturenDAODB();
            return resturentDAO.readall();
        }

         */
    @FXML
    private ListView lvFreeAutoCampers;




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
