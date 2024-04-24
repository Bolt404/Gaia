package org.apollo.template.Controller;

import com.sun.tools.javac.Main;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.apollo.template.App;
import org.apollo.template.Domain.Rental;
import org.apollo.template.View.BorderPaneRegion;
import org.apollo.template.View.ViewList;

import java.net.URL;
import java.util.ConcurrentModificationException;
import java.util.ResourceBundle;

public class CreateRentalCustomController implements Initializable {


    @FXML
    private TextField txStartDate, txEndDate, txAutoCamper, txCustomerEmail, txCustomerFirstName, txCustomerLastName,
                      txCustomerStreet, txCustomerCountry, txCustomerZipcode, txCustomerCity, txCustomerPhoneNo, txCustomerDriverLicense;
    @FXML
    private ListView listViewCoDrivers;

    private Rental rental;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setRental();
        setPeriod();
        setAutocamper();

    }
    public void setRental(){
        this.rental = MainController.getInstance().rental;
    }

    private void setPeriod() {
        txStartDate.setText(rental.getStartDate().toString());
        txEndDate.setText(rental.getEndDate().toString());
    }

    private void setAutocamper() {
        txAutoCamper.setText(rental.getChassisNo());
    }


    public void onButtonAddCoDriver(){
        MainController.getInstance().changeView(ViewList.CODRIVER, BorderPaneRegion.CENTER);
    }

    public void onButtonBack(){
        MainController.getInstance().changeView(ViewList.CREATERENTAL, BorderPaneRegion.CENTER);
    }

    public void onButtonCancel(){
        MainController.getInstance().changeView(ViewList.HOME, BorderPaneRegion.CENTER);
    }

    public void onButtonConfirm(){
        System.out.println("Printing rental");
    }




}
