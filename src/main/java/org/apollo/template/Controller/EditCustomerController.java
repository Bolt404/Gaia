package org.apollo.template.Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import org.apollo.template.Database.JDBC;
import org.apollo.template.Domain.Customer;
import org.apollo.template.Service.Debugger.DebugMessage;
import org.apollo.template.View.BorderPaneRegion;
import org.apollo.template.View.ViewList;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class EditCustomerController implements Initializable {

    private static Connection con = JDBC.get().getConnection();
    @FXML
    private AnchorPane baseAnchorPane;
    @FXML
    TextField txCustomerEmail, txCustomerFirstName, txCustomerLastName, txCustomerStreet, txCustomerCountry,
            txCustomerZipcode, txCustomerCity, txCustomerPhoneNo, txCustomerDriverLicense;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

//        if(!txCustomerEmail.focusedProperty().getValue() && txCustomerEmail.getText() != null){
//            getCustomerInformationFromEmail(txCustomerEmail.getText());
//        }

        txCustomerEmail.focusedProperty().addListener((obs, oldVal, newVal) ->{
            if(!newVal){

                Customer customer = getCustomerInformationFromEmail(txCustomerEmail.getText());
                if(customer != null){
                    txCustomerFirstName.setText(customer.getCustomerFirstName());
                    txCustomerLastName.setText(customer.getCustomerLastName());
                    txCustomerStreet.setText(customer.getCustomerAddress());
                    txCustomerCountry.setText(customer.getCustomerCountry());
                    txCustomerZipcode.setText(customer.getZipCity() + "");
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

    }


    private static Customer getCustomerInformationFromEmail(String email){

        try {
            PreparedStatement ps = con.prepareCall("SELECT * FROM tbl_customer WHERE fld_customerEmail = ?");

            ps.setString(1, email);
            System.out.println(ps);
            ResultSet rs = ps.executeQuery();

            String firstName = rs.getString(1);
            if (firstName == null) {
                return null; // No customer found for the given email
            }

            return new Customer(
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5),
                    rs.getString(6),
                    rs.getString(7),
                    rs.getString(8),
                    rs.getInt(9),
                    rs.getInt(10)
            );

        }catch (SQLException e){
            DebugMessage.error(new EditCustomerController(), "GetCustomerInformationFromEmail: Failed!");
        }

        return null;
    }

    public static EditCustomerController getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new EditCustomerController();
        }
        return INSTANCE;
    }

}
