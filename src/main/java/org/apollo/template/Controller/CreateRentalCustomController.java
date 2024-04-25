package org.apollo.template.Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import org.apollo.template.Database.JDBC;
import org.apollo.template.Domain.Autocamper;
import org.apollo.template.Domain.Rental.Customer;
import org.apollo.template.Domain.Rental.Rental;
import org.apollo.template.Service.Alert.Alert;
import org.apollo.template.Service.Alert.AlertType;
import org.apollo.template.Service.CustomerUtil;
import org.apollo.template.Service.Debugger.DebugMessage;
import org.apollo.template.Service.ListenerUtill;
import org.apollo.template.Service.StartedRental;
import org.apollo.template.View.BorderPaneRegion;
import org.apollo.template.View.ViewList;
import org.apollo.template.persistence.Dao.DAO;
import org.apollo.template.persistence.Dao.DaoImplCustomer;
import org.apollo.template.persistence.Dao.DaoImplRental;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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


    private DaoImplRental rentalDao = new DaoImplRental();
    private Customer customer;
    private boolean newCustomer = true;
    private static Connection con = JDBC.get().getConnection();
    private DAO<Autocamper, String> autocamperDao;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        ListenerUtill.attatchIntegerValidation(txCustomerZipcode);

        setPeriod();
        setAutocamper();

        comboBoxSetVal();
        addTextFields();
        // Listener that checks if the TextField txCustomerEmail gets unfocused
        txCustomerEmail.focusedProperty().addListener((obs, oldVal, newVal) ->{
            if(!newVal){

                customer = CustomerUtil.getCustomerInformationFromEmail(txCustomerEmail.getText());
                if(customer != null){
                    txCustomerFirstName.setText(customer.getCustomerFirstName());
                    txCustomerLastName.setText(customer.getCustomerLastName());
                    txCustomerStreet.setText(customer.getCustomerAddress());
                    txCustomerCountry.setText(CustomerUtil.getCountryName());
                    txCustomerZipcode.setText(CustomerUtil.getZipCode());
                    txCustomerCity.setText(CustomerUtil.getCityName());
                    txCustomerPhoneNo.setText(customer.getCustomerPhoneNumber());
                    txCustomerDriverLicense.setText(customer.getCustomerDrivingLicenceNo());

                    newCustomer = false;
                }
            }
        });


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
            saveCustomInformation();

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

        DaoImplCustomer dao = new DaoImplCustomer();

        Customer addCustomer = new Customer(
                getCountryCode(txCustomerCountry.getText()) + txCustomerDriverLicense.getText(),
                txCustomerFirstName.getText(),
                txCustomerLastName.getText(),
                txCustomerPhoneNo.getText(),
                txCustomerEmail.getText(),
                txCustomerDriverLicense.getText(),
                txCustomerStreet.getText(),
                txCustomerCountry.getText(),
                getZipCityID(txCustomerZipcode.getText()),
                0
        );

        if(!newCustomer){

            dao.update(addCustomer);
            DebugMessage.info(new CreateRentalCustomController(), "Updated Customer");
        }else {

            dao.add(addCustomer);
            DebugMessage.info(new CreateRentalCustomController(), "Added new Customer");
        }

        MainController.getInstance().changeView(ViewList.HOME, BorderPaneRegion.CENTER);

    }





    private void resetStartedRental() {

        StartedRental.setStartOate(null);
        StartedRental.setEndOate(null);
        StartedRental.setSelectedAutocamper(null);

    }

    private static String getCountryCode(String countryName){

        try {
            PreparedStatement ps = con.prepareStatement("SELECT fld_countryCode FROM tbl_country WHERE fld_countryName = ?");
            ps.setString(1, countryName);

            ResultSet rs = ps.executeQuery();

            if(rs.next()){
                return rs.getString(1);
            }

        }catch (SQLException e){
            DebugMessage.error(new CreateRentalCustomController(), "getCountryCode; Failed to get country code");
        }

        return null;
    }

    private static int getZipCityID(String zipCode){
        int zipCityID = -1;
        try {
            try {
                zipCityID = Integer.parseInt(zipCode);
            }catch (NumberFormatException e){
                System.out.println("Failed to parse zipcode");
            }

            PreparedStatement ps = con.prepareStatement("SELECT fld_zipCityID FROM tbl_zipCity WHERE fld_zipcode = ?");
            ps.setInt(1, zipCityID);

            ResultSet rs = ps.executeQuery();

            if (rs.next()){
                return rs.getInt(1);
            }

        }catch(SQLException e){
            DebugMessage.error(new CreateRentalCustomController(), "getZipCityID; Failed to get zipCityID");
        }

        return -1;
    }



}
