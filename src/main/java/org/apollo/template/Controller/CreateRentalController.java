package org.apollo.template.Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.apollo.template.Domain.Autocamper;
import org.apollo.template.Service.Alert.AlertType;
import org.apollo.template.Service.Debugger.DebugMessage;
import org.apollo.template.Service.RentalUtil;
import org.apollo.template.Service.StartedRental;
import org.apollo.template.View.BorderPaneRegion;
import org.apollo.template.View.ViewList;
import org.apollo.template.Service.Alert.Alert;
import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;



public class CreateRentalController implements Initializable{

    @FXML
    private DatePicker dpStartDate, dpEndDate;
    @FXML
    private ComboBox cbType;
    @FXML
    private ListView<Autocamper> lvFreeAutoCampers;

    private Date selectedStartDate;
    private Date selectedEndDate;
    private String selectedAutoCamperType;
    private Autocamper selectedAutocamper;
    private List<Autocamper> availableAutocampersPeriod;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        comboBoxSetVal();
    }



    /**
     * Method for initiating a search for available auto campers. It validates the presence
     * of start and end dates and either displays an alert if one or both are missing,
     * or proceeds to find and list available auto campers.
     */
    public void onBtnSearch(){

        lvFreeAutoCampers.getItems().clear();

        getSelectedDates();
        getSelectedAutoCType();

        if (selectedStartDate == null || selectedEndDate == null || selectedAutoCamperType == null){
            final Alert ALERT_INFO = new Alert(MainController.getInstance(), 5, AlertType.INFO, "Please select a start date, end date and type");
            ALERT_INFO.start();
        }
        else {
            findAvailableAutoCampers();
            listAvailableAutoCampers();
        }
    }

    private void getSelectedAutoCType() {
        selectedAutoCamperType = cbType.getSelectionModel().getSelectedItem().toString();
    }


    /**
     * Method for handling the cancellation process by navigating back to the home view.
     */
    public void onButtonCancel(){
        MainController.getInstance().changeView(ViewList.HOME, BorderPaneRegion.CENTER);
    }


    /**
     * Method for confirming selection and proceeding to the rental creation view.
     * This method sets the rental period and auto camper, and checks if all necessary
     * information (start date, end date, chassis number) is provided. If any information
     * is missing, an informational alert is displayed. Otherwise, it transitions to the
     * rental creation view.
     */
    public void onButtonConfirm(){
        setPeriod();
        getSelectedAutoC();
        setAutoCamper();

        if (selectedStartDate != null && selectedEndDate != null && selectedAutocamper != null) {
            MainController.getInstance().changeView(ViewList.CREATERENTALCUSTOM, BorderPaneRegion.CENTER);
        }
        else {
            final Alert ALERT_INFO = new Alert(MainController.getInstance(), 5, AlertType.INFO, "Some information is missing.\nPlease check your search criteria");
            ALERT_INFO.start();
        }
    }


    /**
     * Method for finding available auto campers within a specified date range.
     * It retrieves the start and end dates from the date pickers, then uses
     * these dates to query available auto campers through the RentalUtil class.
     */
    private void findAvailableAutoCampers() {
        RentalUtil rentalUtil = new RentalUtil();

        if (selectedAutoCamperType.equals("Show all")){
            availableAutocampersPeriod = rentalUtil.availableAutocampers(selectedStartDate, selectedEndDate);
        }
        else {
            availableAutocampersPeriod = rentalUtil.availableAutocampers(selectedStartDate, selectedEndDate, selectedAutoCamperType);
        }
    }


    /**
     * Method for
     */
    private void listAvailableAutoCampers() {

        if (availableAutocampersPeriod == null){
            final Alert ALERT_INFO = new Alert(MainController.getInstance(), 5, AlertType.INFO, "No available auto camper.\nTry to select another type or date period");
            ALERT_INFO.start();
        }
        else {

            for (Autocamper autocamper : availableAutocampersPeriod) {
                lvFreeAutoCampers.getItems().add(autocamper);
            }
        }
    }


    /**
     * Method for
     */
    private void setPeriod() {
        if (selectedStartDate == null || selectedEndDate == null){
            final Alert ALERT_INFO = new Alert(MainController.getInstance(), 5, AlertType.INFO, "Please select a start date and end date");
            ALERT_INFO.start();
        }
        else {
            StartedRental.setStartOate(selectedStartDate);
            StartedRental.setEndOate(selectedEndDate);
        }
    }


    /**
     * Method for
     */
    private void setAutoCamper() {

        if (selectedAutocamper == null){
            final Alert ALERT_INFO = new Alert(MainController.getInstance(), 5, AlertType.INFO, "Please select a auto camper");
            ALERT_INFO.start();
        }
        else {
            StartedRental.setSelectedAutocamper(selectedAutocamper);
        }
    }


    private void getSelectedDates() {
        selectedStartDate = Date.valueOf(dpStartDate.getValue());
        selectedEndDate = Date.valueOf(dpEndDate.getValue());
    }

    private void getSelectedAutoC() {
        selectedAutocamper = lvFreeAutoCampers.getSelectionModel().getSelectedItem();
    }


    /**
     * Method for
     */
    private void comboBoxSetVal(){

        cbType.getItems().addAll("Show all", "Luxury", "Standard", "Basic");
        cbType.setValue("Show all");
    }




    // Singleton part

    private static CreateRentalController INSTANCE;

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
