package org.apollo.template.persistence.Dao;

import org.apollo.template.Database.JDBC;
import org.apollo.template.Domain.Rental.Rental;
import org.apollo.template.Service.Debugger.DebugMessage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class DaoImplRental implements DAO<Rental, Integer>{
    Connection con = JDBC.get().getConnection();

    @Override
    public void add(Rental rental) {

        try{
            PreparedStatement ps = con.prepareStatement("INSERT INTO tbl_rental (fld_startDate, fld_endDate, fld_chassisNo, fld_insuranceID) VALUES (?, ?, ?, ?)");

            ps.setDate(1, rental.getStartDate());
            ps.setDate(2, rental.getEndDate());
            ps.setString(3, rental.getChassisNo());
            ps.setString(4, rental.getInsuranceID());

            ps.executeUpdate();

            DebugMessage.info(this,"ADD: Successfully added new rental.");


        } catch (SQLException e) {
            DebugMessage.error(this, "ADD: Failed to add rental " + e.getMessage());
            throw new RuntimeException();
        }

    }

    @Override
    public void addAll(List<Rental> list) {

    }

    @Override
    public void delete(Rental rental) {

    }

    @Override
    public void deleteAll(List<Rental> list) {

    }

    @Override
    public void update(Rental rental) {

    }

    @Override
    public void updateAll(List<Rental> t) {

    }

    @Override
    public Rental read(Integer integer) {
        return null;
    }

    @Override
    public List<Rental> readAll() {
        return List.of();
    }
}
