package org.apollo.template.persistence.Dao;

import org.apollo.template.Database.JDBC;
import org.apollo.template.Domain.Rental.Customer;
import org.apollo.template.Service.Debugger.DebugMessage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DaoImplCustomer implements DAO<Customer, String>{

    Connection con = JDBC.get().getConnection();

    @Override
    public void add(Customer customer) {

        try{
            PreparedStatement ps = con.prepareStatement("INSERT INTO tbl_customer(?, ?, ?, ?, ?, ?, ?, ?, ?)");
            ps.setString(1, customer.getCustomerID());
            ps.setString(2, customer.getCustomerFirstName());
            ps.setString(3, customer.getCustomerLastName());
            ps.setString(4, customer.getCustomerPhoneNumber());
            ps.setString(5, customer.getCustomerEmail());
            ps.setString(6, customer.getCustomerDrivingLicenceNo());
            ps.setString(7, customer.getCustomerAddress());
            ps.setInt(8, customer.getZipCity());
            ps.setInt(9, customer.getNoOfRentals());

            DebugMessage.info(this, "ADD: Successfully added new Customer.");
            ps.execute();

        }catch (SQLException e){
            DebugMessage.error(this, "ADD: Failed to add Customer");
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void addAll(List<Customer> listCustomers) {

        for (Customer customer : listCustomers) {
            this.add(customer);
        }

    }

    @Override
    public void delete(Customer customer) {

        try{
            PreparedStatement ps = con.prepareStatement("DELETE FROM tbl_customer WHERE fld_customerID = ?");
            ps.setString(1, customer.getCustomerID());

            int rowAffected = ps.executeUpdate();
            DebugMessage.info(this, "DELETE: Rows affected: " + rowAffected);

        }catch (SQLException e){
            DebugMessage.error(this, "DELETE; Failed to delete Customer! " + customer.getCustomerID());
        }

    }

    @Override
    public void deleteAll(List<Customer> listCustomer) {
    }

    @Override
    public void update(Customer customer) {
        try{
            PreparedStatement ps = con.prepareStatement("UPDATE tbl_customer SET " +
                    "fld_customerFirstName = ?," +
                    "fld_customerLastName = ?," +
                    "fld_customerPhoneNo = ?," +
                    "fld_customerEmail = ?," +
                    "fld_customerDrivingLicenceNo = ?," +
                    "fld_customerStreetAdress = ?," +
                    "fld_zipCityID = ?," +
                    "fld_noOfRental = ? " +
                    "WHERE fld_customerID = ?");

            ps.setString(1, customer.getCustomerFirstName());
            ps.setString(2, customer.getCustomerLastName());
            ps.setString(3, customer.getCustomerPhoneNumber());
            ps.setString(4, customer.getCustomerEmail());
            ps.setString(5, customer.getCustomerDrivingLicenceNo());
            ps.setString(6, customer.getCustomerAddress());
            ps.setInt(7, customer.getZipCity());
            ps.setInt(8, customer.getNoOfRentals());
            ps.setString(9, customer.getCustomerID());

            int rowsAffected = ps.executeUpdate();
            DebugMessage.info(this, "UPDATE; Rows Updated " + rowsAffected);
        }catch (SQLException e){
            System.out.println(e.getMessage());
            DebugMessage.error(this, "UPDATE; Failed to update " + customer.getCustomerID());
        }
    }

    @Override
    public void updateAll(List<Customer> listCustomer) {

        for (Customer customer : listCustomer) {
            this.update(customer);
        }

    }

    @Override
    public Customer read(String customerID) {

        try{
            PreparedStatement ps = con.prepareStatement("SELECT * FROM tbl_customer WHERE fld_customerID = ?");
            ps.setString(1, customerID);
            ResultSet rs = ps.executeQuery();
            rs.next();
            DebugMessage.info(this, "READ; Customer found");
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
            DebugMessage.error(this, "READ; Failed to read Customer " + customerID);
        }

        return null;
    }

    @Override
    public List<Customer> readAll() {

        ArrayList<Customer> customerArrayList = new ArrayList<>();
        try{
            PreparedStatement ps = con.prepareStatement("SELECT * FROM  tbl_customer");
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                customerArrayList.add(this.read(rs.getString(1)));
            }
        }catch (SQLException e){
            DebugMessage.error(this, "READALL; Failed to READ all");
        }

        return customerArrayList;
    }
}
