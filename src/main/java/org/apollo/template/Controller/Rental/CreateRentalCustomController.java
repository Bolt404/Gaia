package org.apollo.template.Controller.Rental;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import org.apollo.template.Controller.MainController;
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
import org.apollo.template.persistence.Dao.DAOAble;
import org.apollo.template.persistence.Dao.DaoAbleImplCustomer;
import org.apollo.template.persistence.Dao.DaoAbleImplRental;
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


    private DaoAbleImplRental rentalDao = new DaoAbleImplRental();
    private Customer customer;
    private boolean newCustomer = true;
    private static Connection con = JDBC.get().getConnection();
    private DAOAble<Autocamper, String> autocamperDaoAble;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        ListenerUtill.attatchIntegerValidation(txCustomerZipcode);

        setPeriod();
        setAutocamper();

        comboBoxSetVal();
        addTextFields();

        emailListener();

    }


    /**
     * Method for setting up a listener on the txCustomerEmail TextField. This listener triggers
     * when the TextField loses focus and fetches customer information using the entered email.
     * If a valid customer is retrieved, their details are populated into the corresponding fields
     * in the form.
     * It also prints the customer's city name to the console and
     * sets a flag indicating that a new customer is not being created.
     */
    private void emailListener() {

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

                    System.out.println(CustomerUtil.getCityName());
                    newCustomer = false;
                }
            }
        });
    }


    /**
     * Method for setting the start and end dates of a rental period in the corresponding TextFields.
     * This method retrieves the start and end dates from the StartedRental class and updates the
     * txStartDate and txEndDate TextFields with these values.
     */
    private void setPeriod() {
        txStartDate.setText(StartedRental.getStartOate());
        txEndDate.setText(StartedRental.getEndOate());
    }


    /**
     * Method for setting the text of the txAutoCamper TextField to the selected autocamper's description.
     * Retrieves the currently selected autocamper from the StartedRental class and sets its string representation
     * as the text in the txAutoCamper TextField.
     */
    private void setAutocamper() {
        txAutoCamper.setText(StartedRental.getSelectedAutocamper().toString());
    }


    /**
     * Method for handling the "Back" button action by returning to the "Create Rental" view
     */
    public void onButtonBack(){
        MainController.getInstance().changeView(ViewList.CREATERENTAL, BorderPaneRegion.CENTER);
    }


    /**
     * Method for handling the cancellation process by navigating back to the home view.
     * Before navigating back, all the data in Started Rental is reset.
     */
    public void onButtonCancel(){

        resetStartedRental();
        MainController.getInstance().changeView(ViewList.ViewAutoCampers, BorderPaneRegion.CENTER);
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

        DaoAbleImplCustomer dao = new DaoAbleImplCustomer();

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

        MainController.getInstance().changeView(ViewList.ViewAutoCampers, BorderPaneRegion.CENTER);

    }





    private void resetStartedRental() {

        StartedRental.setStartOate(null);
        StartedRental.setEndOate(null);
        StartedRental.setSelectedAutocamper(null);

    }

    /**
     * Method for getting the Country code from a given country name
     * @param countryName
     * @return
     */
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

    /**
     * Method for getting zipCityID from a zipcode
     * @param zipCode
     * @return Returns the zipCityID
     */
    private static int getZipCityID(String zipCode){
        int zipCityID = 0;
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
