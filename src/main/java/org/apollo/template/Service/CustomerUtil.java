package org.apollo.template.Service;

import org.apollo.template.Controller.EditCustomerController;
import org.apollo.template.Database.JDBC;
import org.apollo.template.Domain.Customer;
import org.apollo.template.Service.Debugger.DebugMessage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerUtil {

    private static Connection con = JDBC.get().getConnection();

    public static Customer getCustomerInformationFromEmail(String email){

        try {
            PreparedStatement ps = con.prepareCall("EXECUTE customerDataFromEmail ?");

            ps.setString(1, email);
            System.out.println(ps);
            ResultSet rs = ps.executeQuery();

            if(rs.next()){
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


}
