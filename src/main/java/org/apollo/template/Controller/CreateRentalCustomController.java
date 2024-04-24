package org.apollo.template.Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import org.apollo.template.Domain.Autocamper;
import org.apollo.template.Domain.Rental;
import org.apollo.template.View.BorderPaneRegion;
import org.apollo.template.View.ViewList;
import org.apollo.template.persistence.Dao.DAO;
import org.apollo.template.persistence.Dao.DaoImplAutoCamper;

import java.net.URL;
import java.util.ResourceBundle;

public class CreateRentalCustomController implements Initializable {


    @FXML
    private TextField txStartDate, txEndDate, txAutoCamper, txCustomerEmail, txCustomerFirstName, txCustomerLastName,
                      txCustomerStreet, txCustomerCountry, txCustomerZipcode, txCustomerCity, txCustomerPhoneNo, txCustomerDriverLicense;
    @FXML
    private ListView listViewCoDrivers;

    private Rental rental;
    private DAO<Autocamper, String> autocamperDao;



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
        Autocamper autocamper = autocamperDao.read(rental.getChassisNo());
        if (autocamper != null) {
            txAutoCamper.setText(autocamper.toString());
        } else {
            txAutoCamper.setText("Ingen autocamper fundet med chassisnummeret: " + rental.getChassisNo());
        }
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
