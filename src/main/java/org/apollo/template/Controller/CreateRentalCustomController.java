package org.apollo.template.Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import org.apollo.template.Domain.Rental.Customer;
import org.apollo.template.Domain.Rental.Rental;
import org.apollo.template.Service.Alert.Alert;
import org.apollo.template.Service.Alert.AlertType;
import org.apollo.template.Service.StartedRental;
import org.apollo.template.View.BorderPaneRegion;
import org.apollo.template.View.ViewList;
import org.apollo.template.persistence.Dao.DAO;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class CreateRentalCustomController implements Initializable {


    @FXML
    private TextField txStartDate, txEndDate, txAutoCamper, txCustomerEmail, txCustomerFirstName, txCustomerLastName,
                      txCustomerStreet, txCustomerCountry, txCustomerZipcode, txCustomerCity, txCustomerPhoneNo, txCustomerDriverLicense;
    @FXML
    private ListView listViewCoDrivers;
    @FXML
    private ComboBox cbInsurance;

    List<TextField> textFields = new ArrayList<>();


    private DAO<Rental, String> rentalDao;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        setPeriod();
        setAutocamper();

        comboBoxSetVal();

        addTextFields();

    }



    private void setPeriod() {
        txStartDate.setText(StartedRental.getStartOate());
        txEndDate.setText(StartedRental.getEndOate());
    }



    private void setAutocamper() {
        txAutoCamper.setText(StartedRental.getSelectedAutocamper().toString());
    }






    public void onButtonBack(){
        MainController.getInstance().changeView(ViewList.CREATERENTAL, BorderPaneRegion.CENTER);
    }

    public void onButtonCancel(){

        resetStartedRental();
        MainController.getInstance().changeView(ViewList.HOME, BorderPaneRegion.CENTER);
    }


    public void onButtonConfirm(){

        if (!checkIfEmptyTextFields() || cbInsurance.getSelectionModel().isEmpty()){
            final Alert ALERT_INFO = new Alert(MainController.getInstance(), 5, AlertType.INFO, "Some information is missing.");
            ALERT_INFO.start();
        }
        else {
            saveRentalInformation();

            resetStartedRental();
            System.out.println("Printing rental");
        }
    }

    private boolean checkIfEmptyTextFields() {

        for (TextField textField : textFields) {
            if (textField.getText().isEmpty()) {
                return false;
            }
        }
        return true;
    }


    /**
     * Method for
     */
    private void comboBoxSetVal(){

        cbInsurance.getItems().addAll("Basic", "Super Cover Plus");
        cbInsurance.setValue("Basic");
    }


    private void addTextFields() {

        textFields.add(txStartDate);
        textFields.add(txEndDate);
        textFields.add(txAutoCamper);
        textFields.add(txCustomerEmail);
        textFields.add(txCustomerFirstName);
        textFields.add(txCustomerLastName);
        textFields.add(txCustomerStreet);
        textFields.add(txCustomerCountry);
        textFields.add(txCustomerZipcode);
        textFields.add(txCustomerCity);
        textFields.add(txCustomerPhoneNo);
        textFields.add(txCustomerDriverLicense);
    }


    private void saveRentalInformation() {

        Date startDate = Date.valueOf(txStartDate.getText());
        Date endDate = Date.valueOf(txEndDate.getText());
        String chassisNo = StartedRental.getSelectedAutocamper().getChassisNo();
        String insurance = (String) cbInsurance.getSelectionModel().getSelectedItem();

        Rental rental = new Rental(startDate, endDate, chassisNo, insurance);

        rentalDao.add(rental);
    }


    private void saveCustomInformation(){

        String custFirstName = txCustomerFirstName.getText();
        String custLastName = txCustomerLastName.getText();
        String custPhoneNo = txCustomerPhoneNo.getText();
        String custEmail = txCustomerEmail.getText();
        String custDrivLic = txCustomerDriverLicense.getText();
        String adress = txCustomerStreet.getText();
        String country = txCustomerCountry.getText();
        int zipcode = Integer.parseInt(txCustomerZipcode.getText());

        Customer customer = new Customer();
    }




    private void resetStartedRental() {

        StartedRental.setStartOate(null);
        StartedRental.setEndOate(null);
        StartedRental.setSelectedAutocamper(null);

    }



}
