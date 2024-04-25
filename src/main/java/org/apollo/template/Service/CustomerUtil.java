package org.apollo.template.Service;

import org.apollo.template.Database.JDBC;
import org.apollo.template.Domain.Rental.Customer;
import org.apollo.template.Service.Debugger.DebugMessage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerUtil {

    private static Connection con = JDBC.get().getConnection();
    private static String zipCode = "";

    /**
     * Method for getting all data from a customer, with a given email
     * @param email
     * @return Returns a Customer object
     */
    public static Customer getCustomerInformationFromEmail(String email){

        try {
            PreparedStatement ps = con.prepareCall("EXECUTE customerDataFromEmail ?");

            ps.setString(1, email);

            ResultSet rs = ps.executeQuery();

            if(rs.next()){
                zipCode = rs.getString(12);
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

            } else return null;


        }catch (SQLException e){
            DebugMessage.error(new CustomerUtil(), "GetCustomerInformationFromEmail: Failed!");
            return null;
        }
    }

    /**
     * Method for getting zipcode from a given customer
     * @return Returns the zipcode as a string
     */
    public static String getZipCode(){
        return zipCode;
    }


}
