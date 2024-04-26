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
     * Method for initiating a search for available auto campers. This method validates the presence of start and end dates
     * and the type of auto camper. If any parameter is missing, an alert is displayed. If all parameters are present,
     * the method proceeds to find and list available auto campers.
     */
    public void onBtnSearch(){
        // checks if no start or end date is selected
        if (dpStartDate.getValue() == null || dpEndDate.getValue() == null){

            final Alert ALERT_INFO = new Alert(MainController.getInstance(), 5, AlertType.INFO, "Please select a start date and end date");
            ALERT_INFO.start();

          // checks if end date is before start date
        } else if (dpEndDate.getValue().isBefore(dpStartDate.getValue())) {

            final Alert ALERT_INFO = new Alert(MainController.getInstance(), 5, AlertType.INFO, "Please check start date and end date");
            ALERT_INFO.start();

          // if all search criteria is fulfilled
        } else {

            // resets the listView for new search
            lvFreeAutoCampers.getItems().clear();

            getSelectedDates();
            getSelectedAutoCType();

            if (selectedStartDate == null || selectedEndDate == null || selectedAutoCamperType == null) {
                final Alert ALERT_INFO = new Alert(MainController.getInstance(), 5, AlertType.INFO, "Please select a start date, end date and type");
                ALERT_INFO.start();
            } else {
                findAvailableAutoCampers();
                listAvailableAutoCampers();
            }
        }
    }


    /**
     * Method for handling the cancellation process by navigating back to the home view.
     */
    public void onButtonCancel(){
        MainController.getInstance().changeView(ViewList.ViewAutoCampers, BorderPaneRegion.CENTER);
    }


    /**
     * Method for confirming selections and proceeding to the rental creation view. This method sets the rental period
     * and selects the auto camper based on user inputs. It verifies if all necessary information, such as start date,
     * end date, and auto camper selection, is provided. If any information is missing, an informational alert is displayed.
     * Otherwise, it transitions to the rental creation view.
     */
    public void onButtonConfirm(){

            setPeriod();

            getSelectedAutoC();
            setAutoCamper();

            if (selectedStartDate != null && selectedEndDate != null && selectedAutocamper != null) {
                MainController.getInstance().changeView(ViewList.CREATERENTALCUSTOM, BorderPaneRegion.CENTER);
            } else {
                final Alert ALERT_INFO = new Alert(MainController.getInstance(), 5, AlertType.INFO, "Some information is missing.\nPlease check your search criteria");
                ALERT_INFO.start();
            }

    }


    /**
     * Method for finding available auto campers within a specified date range. Retrieves the start and end dates
     * from previously selected values and uses these dates to query for available auto campers. The search can
     * either show all types of auto campers or filter by a specific type depending on the user's selection.
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
     * Method for listing available auto campers in a ListView. If no auto campers are available for the specified period,
     * an informational alert is displayed prompting the user to select another type or date period. If auto campers are available,
     * each one is added to the ListView for display.
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
     * Method for setting the rental period on a static StartedRental class based on selected start and end dates. If either date is not selected,
     * an informational alert is displayed, prompting the user to choose both dates. If both dates are selected, the start and end dates
     * are set in the StartedRental class to define the rental period.
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
     * Method for setting the selected auto camper in the static StartedRental class. If no auto camper is selected,
     * an informational alert is displayed, prompting the user to make a selection. If an auto camper is selected,
     * it is set as the selected auto camper in StartedRental.
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


    /**
     * Method for retrieving and storing the selected start and end dates from date pickers as class variables.
     * This method captures the values from the date picker inputs and directly updates the class variables
     * selectedStartDate and selectedEndDate.
     */
    private void getSelectedDates() {
        selectedStartDate = Date.valueOf(dpStartDate.getValue());
        selectedEndDate = Date.valueOf(dpEndDate.getValue());
    }


    /**
     * Method for retrieving the selected auto camper type from a combo box and storing it in a class variable.
     * This method captures the currently selected item from the combo box and converts it to a string,
     * which is then assigned to the selectedAutoCamperType class variable.
     */
    private void getSelectedAutoCType() {
        selectedAutoCamperType = cbType.getSelectionModel().getSelectedItem().toString();
    }


    /**
     * Method for retrieving the selected auto camper from a list view and storing it as a class variable.
     * This method accesses the selected item from the list view's selection model and updates the class variable
     * selectedAutocamper with this value.
     */
    private void getSelectedAutoC() {
        selectedAutocamper = lvFreeAutoCampers.getSelectionModel().getSelectedItem();
    }


    /**
     * Method for initializing a combo box with predefined values and setting a default selection.
     * This method populates the combo box with various auto camper types and sets the initial selected
     * value to "Show all", ensuring a default state is ready for user interaction.
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
