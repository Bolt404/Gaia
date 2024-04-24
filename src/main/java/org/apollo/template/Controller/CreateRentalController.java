package org.apollo.template.Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.apollo.template.Domain.Autocamper;
import org.apollo.template.Domain.Rental;
import org.apollo.template.Service.Alert.AlertType;
import org.apollo.template.Service.Debugger.DebugMessage;
import org.apollo.template.Service.RentalUtil;
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


    private Rental rental = MainController.getInstance().rental;
    private List<Autocamper> availableAutocampersPeriod;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        comboBoxSetVal();
    }



    private void setPeriod() {
        if (dpStartDate.getValue() == null || dpEndDate.getValue() == null){
            final Alert ALERT_INFO = new Alert(MainController.getInstance(), 5, AlertType.INFO, "Please select a start date and end date");
            ALERT_INFO.start();
        }
        else {
            rental.setStartDate(Date.valueOf(dpStartDate.getValue()));
            rental.setEndDate(Date.valueOf(dpEndDate.getValue()));
        }
    }


    private void setAutoCamper() {
        if (lvFreeAutoCampers.getSelectionModel().getSelectedItem() == null){
            final Alert ALERT_INFO = new Alert(MainController.getInstance(), 5, AlertType.INFO, "Please select a auto camper");
            ALERT_INFO.start();
        }
        else {
            rental.setChassisNo(lvFreeAutoCampers.getSelectionModel().getSelectedItem().getChassisNo());
        }
    }



    private static CreateRentalController INSTANCE;


    public void onBtnSearch(){
        System.out.println("im in!");
        if (dpStartDate.getValue() == null || dpEndDate.getValue() == null){
            final Alert ALERT_INFO = new Alert(MainController.getInstance(), 5, AlertType.INFO, "Please select a start date and end date");
            ALERT_INFO.start();
        }
        else {
            System.out.println("1");
            findAvailableAutoCampers();
            System.out.println("2");
            listAvailableAutoCampers();
            System.out.println("3");
        }
    }

    private void findAvailableAutoCampers() {
        RentalUtil rentalUtil = new RentalUtil();

        Date startDate = Date.valueOf(dpStartDate.getValue());
        Date endDate = Date.valueOf(dpEndDate.getValue());
        System.out.println(startDate);
        System.out.println(endDate);

        availableAutocampersPeriod = rentalUtil.availableAutocampers(startDate, endDate);
    }


    private void listAvailableAutoCampers() {

        if (availableAutocampersPeriod == null){
            final Alert ALERT_INFO = new Alert(MainController.getInstance(), 5, AlertType.INFO, "No available auto camper.\nTry to select another type or date period");
            ALERT_INFO.start();
        }
        else {

            for (Autocamper autocamper : availableAutocampersPeriod) {

                // if "Show all" is selected all free auto campers in the given period is listed
                if (cbType.getSelectionModel().getSelectedItem() == "Show all") {
                    lvFreeAutoCampers.getItems().add(autocamper);
                }

                // if special type is selected all free auto campers of the selected type in the given period is listed
                if (autocamper.getType() == cbType.getSelectionModel().getSelectedItem()) {
                    lvFreeAutoCampers.getItems().add(autocamper);
                }
            }
        }
    }


    public void onButtonConfirm(){
        setPeriod();
        //setAutoCamper();

        if (rental.getStartDate() != null && rental.getEndDate() != null /*&& rental.getChassisNo() != null*/) {


            MainController.getInstance().changeView(ViewList.CREATERENTALCUSTOM, BorderPaneRegion.CENTER);
        }
        else {
            final Alert ALERT_INFO = new Alert(MainController.getInstance(), 5, AlertType.INFO, "Some information is missing.\nPlease check your search criteria");
            ALERT_INFO.start();
        }
    }


    public void onButtonCancel(){
        MainController.getInstance().changeView(ViewList.HOME, BorderPaneRegion.CENTER);
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
