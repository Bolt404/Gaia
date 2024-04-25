package org.apollo.template.Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import org.apollo.template.Domain.Rental.Customer;
import org.apollo.template.Service.CustomerUtil;
import org.apollo.template.Service.Debugger.DebugMessage;
import org.apollo.template.View.BorderPaneRegion;
import org.apollo.template.View.ViewList;
import org.apollo.template.persistence.Dao.DaoImplCustomer;

import java.net.URL;
import java.util.ResourceBundle;

public class EditCustomerController implements Initializable {

    private Customer customer;
    @FXML
    TextField txCustomerEmail, txCustomerFirstName, txCustomerLastName, txCustomerStreet, txCustomerCountry,
            txCustomerZipcode, txCustomerCity, txCustomerPhoneNo, txCustomerDriverLicense;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

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
                }
            }
        });

    }

    private static EditCustomerController INSTANCE;


    @FXML
    public void onButtonEditBack(){
        MainController.getInstance().changeView(ViewList.HOME, BorderPaneRegion.CENTER);
    }

    @FXML
    public void onButtonEditCancel(){
        MainController.getInstance().changeView(ViewList.HOME, BorderPaneRegion.CENTER);
    }

    @FXML
    public void onButtonEditUpdate(){

        if(customer != null){

            try {

                customer.setCustomerFirstName(txCustomerFirstName.getText());
                customer.setCustomerLastName(txCustomerLastName.getText());
                customer.setCustomerPhoneNumber(txCustomerPhoneNo.getText());
                customer.setCustomerEmail(txCustomerEmail.getText());
                customer.setCustomerDrivingLicenceNo(txCustomerDriverLicense.getText());
                customer.setCustomerAddress(txCustomerStreet.getText());
                customer.setCustomerCountry(txCustomerCountry.getText());

                DaoImplCustomer dao = new DaoImplCustomer();

                dao.update(customer);

            } catch (NumberFormatException e) {
                DebugMessage.error(new EditCustomerController(), "onButtonEditUpdate; ZipCity was invalid");
            }
        }

        MainController.getInstance().changeView(ViewList.HOME, BorderPaneRegion.CENTER);
    }

    public static EditCustomerController getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new EditCustomerController();
        }
        return INSTANCE;
    }

}
