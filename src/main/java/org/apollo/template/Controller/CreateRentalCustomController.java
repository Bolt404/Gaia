package org.apollo.template.Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import org.apollo.template.Domain.Autocamper;
import org.apollo.template.Domain.Rental.Rental;
import org.apollo.template.Service.StartedRental;
import org.apollo.template.View.BorderPaneRegion;
import org.apollo.template.View.ViewList;
import org.apollo.template.persistence.Dao.DAO;
import java.net.URL;
import java.util.ResourceBundle;

public class CreateRentalCustomController implements Initializable {


    @FXML
    private TextField txStartDate, txEndDate, txAutoCamper, txCustomerEmail, txCustomerFirstName, txCustomerLastName,
                      txCustomerStreet, txCustomerCountry, txCustomerZipcode, txCustomerCity, txCustomerPhoneNo, txCustomerDriverLicense;
    @FXML
    private ListView listViewCoDrivers;
    @FXML
    private ComboBox cbInsurance;


    private DAO<Autocamper, String> autocamperDao;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        setPeriod();
        setAutocamper();

        comboBoxSetVal();

        testStatic();

    }

    private void testStatic() {

        System.out.println(StartedRental.getStartOate());
        System.out.println(StartedRental.getEndOate());
        System.out.println(StartedRental.getSelectedAutocamper());

    }


    private void setPeriod() {
        txStartDate.setText(StartedRental.getStartOate());
        txEndDate.setText(StartedRental.getEndOate());
    }

    private void setAutocamper() {
        txAutoCamper.setText(StartedRental.getSelectedAutocamper().toString());
    }






    public void onButtonAddCoDriver(){
        MainController.getInstance().changeView(ViewList.CODRIVER, BorderPaneRegion.CENTER);
    }

    public void onButtonBack(){
        MainController.getInstance().changeView(ViewList.CREATERENTAL, BorderPaneRegion.CENTER);
    }

    public void onButtonCancel(){

        resetStartedRental();

        MainController.getInstance().changeView(ViewList.HOME, BorderPaneRegion.CENTER);
    }


    public void onButtonConfirm(){

        saveRentalInformation();

        resetStartedRental();
        System.out.println("Printing rental");
    }


    /**
     * Method for
     */
    private void comboBoxSetVal(){

        cbInsurance.getItems().addAll("Basic", "Super Cover Plus");
        cbInsurance.setValue("Basic");
    }


    private void saveRentalInformation() {

        Rental rental = new Rental();

        txStartDate.getText();
        txEndDate.getText();
        Autocamper autocamper = StartedRental.getSelectedAutocamper();
        cbInsurance.getSelectionModel().getSelectedItem();
    }


    private void saveCustomInformation(){

    }


    private void saveCoDriverInformation(){

    }


    private void resetStartedRental() {

        StartedRental.setStartOate(null);
        StartedRental.setEndOate(null);
        StartedRental.setSelectedAutocamper(null);

    }



}
