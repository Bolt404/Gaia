package org.apollo.template.Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import org.apollo.template.View.BorderPaneRegion;
import org.apollo.template.View.ViewList;

import java.net.URL;
import java.util.ResourceBundle;

public class CreateRentalCustomController implements Initializable {


    @FXML
    private TextField txStartDate, txEndDate, txAutoCamper, txCustomerEmail, txCustomerFirstName, txCustomerLastName,
                      txCustomerStreet, txCustomerCountry, txCustomerZipcode, txCustomerCity, txCustomerPhoneNo, txCustomerDriverLicense;

    @FXML
    private ListView listViewCoDrivers;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }



    public void onButtonAddCoDriver(){

    }

    public void onButtonBack(){
        MainController.getInstance().changeView(ViewList.CREATERENTAL, BorderPaneRegion.CENTER);
    }

    public void onButtonCancel(){
        MainController.getInstance().changeView(ViewList.MENU, BorderPaneRegion.CENTER);
    }

    public void onButtonConfirm(){
        System.out.println("Printing rental");
    }



}
