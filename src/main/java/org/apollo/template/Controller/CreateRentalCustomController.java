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
import org.apollo.template.Service.CustomerUtil;
import org.apollo.template.Service.Debugger.DebugMessage;
import org.apollo.template.Service.StartedRental;
import org.apollo.template.View.BorderPaneRegion;
import org.apollo.template.View.ViewList;
import org.apollo.template.persistence.Dao.DAO;
import org.apollo.template.persistence.Dao.DaoImplCustomer;

import java.net.URL;
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


    private Customer customer;
    private boolean newCustomer = true;
    private static Connection con = JDBC.get().getConnection();
    private DAO<Autocamper, String> autocamperDao;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        setPeriod();
        setAutocamper();

        comboBoxSetVal();

        // Listener that checks if the TextField txCustomerEmail gets unfocused
        txCustomerEmail.focusedProperty().addListener((obs, oldVal, newVal) ->{
            if(!newVal){

                customer = CustomerUtil.getCustomerInformationFromEmail(txCustomerEmail.getText());
                if(customer != null){
                    txCustomerFirstName.setText(customer.getCustomerFirstName());
                    txCustomerLastName.setText(customer.getCustomerLastName());
                    txCustomerStreet.setText(customer.getCustomerAddress());
                    txCustomerCountry.setText(customer.getCustomerCountry());
                    txCustomerZipcode.setText(CustomerUtil.getZipCode());
                    txCustomerCity.setText(customer.getCustomerCountry());
                    txCustomerPhoneNo.setText(customer.getCustomerPhoneNumber());
                    txCustomerDriverLicense.setText(customer.getCustomerDrivingLicenceNo());

                    newCustomer = false;
                }
            }
        });

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
        saveCustomInformation();

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


    private void saveCoDriverInformation(){

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
