package org.apollo.template.persistence.Dao;

import org.apollo.template.Database.JDBC;
import org.apollo.template.Domain.Autocamper;
import org.apollo.template.Service.Debugger.DebugMessage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DaoImplAutoCamper implements DAO<Autocamper, String> {
    Connection con = JDBC.get().getConnection();

    @Override
    public void add(Autocamper autoCamper) {
        try{
            PreparedStatement ps = con.prepareStatement("INSERT INTO tbl_autocamper (fld_chassisNo, fld_registrationNo, fld_kmCount," +
                    "fld_noOfRental, fld_mainSeasonPrice, fld_lowSeasonPrice, fld_weight, fld_length, fld_width, fld_height, " +
                    "fld_purchaseDate, fld_noOfBeds, fld_noOfToilets, fld_noOfSeatbelts, fld_brand, fld_comment, fld_type) VALUES " +
                    "( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )");

            ps.setString(1, autoCamper.getChassisNo());
            ps.setString(2, autoCamper.getRegistrationNo());
            ps.setInt(3,autoCamper.getKmCount());
            ps.setInt(4,autoCamper.getNoOfRental());
            ps.setFloat(5,autoCamper.getMainSeasonPrice());
            ps.setFloat(6,autoCamper.getLowSeasonPrice());
            ps.setInt(7,autoCamper.getWeight());
            ps.setInt(8,autoCamper.getLength());
            ps.setInt(9,autoCamper.getWidth());
            ps.setInt(10,autoCamper.getHeight());
            ps.setDate(11,autoCamper.getSQLPurchaseDate());
            ps.setInt(12,autoCamper.getNoOfBeds());
            ps.setInt(13,autoCamper.getNoOfToilets());
            ps.setInt(14,autoCamper.getNoOfSeatbelts());
            ps.setString(15,autoCamper.getBrand());
            ps.setString(16,autoCamper.getComment());
            ps.setString(17,autoCamper.getType());

            ps.executeUpdate();

            DebugMessage.info(this,"ADD: Successfully added new AutoCamper.");


        } catch (SQLException e) {
            DebugMessage.error(this, "ADD: Failed to add AutoCamper " + e.getMessage());
            throw new RuntimeException();
        }
    }

    @Override
    public void addAll(List<Autocamper> listAutoCampers) {
        for (Autocamper autoCamper : listAutoCampers) {
            this.add(autoCamper);
        }

    }

    @Override
    public void delete(Autocamper autoCamper) {
        try {
            PreparedStatement ps = con.prepareStatement("DELETE FROM tbl_autocamper WHERE fld_chassisNo = ?");
            ps.setString(1, autoCamper.getChassisNo());

            int rowsAffected = ps.executeUpdate();
            DebugMessage.info(this, "DELETE: Rows Deleted " + rowsAffected);
        } catch (SQLException e) {
            DebugMessage.error(this, "DELETE: Failed to delete " + autoCamper.getChassisNo());
        }
    }

    @Override
    public void update(Autocamper autoCamper) {
        try{
            PreparedStatement ps = con.prepareStatement("UPDATE tbl_autocamper SET " +
                    "fld_registrationNo = ?," +
                    "fld_kmCount = ?," +
                    "fld_noOfRental = ?," +
                    "fld_mainSeaonPrice = ?," +
                    "fld_lowSeasonPrice = ?," +
                    "fld_weight = ?," +
                    "fld_length = ?," +
                    "fld_width = ?," +
                    "fld_height = ?," +
                    "fld_purchaseDate = ?," +
                    "fld_noOfBeds = ?," +
                    "fld_noOfSeatbelts = ?," +
                    "fld_brand = ?," +
                    "fld_comment = ?" +
                    " WHERE fld_chassisNo = ?");
            ps.setString(1, autoCamper.getRegistrationNo());
            ps.setInt(2, autoCamper.getKmCount());
            ps.setInt(3, autoCamper.getNoOfRental());
            ps.setFloat(4, autoCamper.getMainSeasonPrice());
            ps.setFloat(5, autoCamper.getLowSeasonPrice());
            ps.setInt(6, autoCamper.getWeight());
            ps.setInt(7, autoCamper.getLength());
            ps.setInt(8, autoCamper.getWidth());
            ps.setInt(9,autoCamper.getHeight());
            ps.setDate(10,autoCamper.getSQLPurchaseDate());
            ps.setInt(11, autoCamper.getNoOfBeds());
            ps.setInt(12, autoCamper.getNoOfSeatbelts());
            ps.setString(13, autoCamper.getBrand());
            ps.setString(14, autoCamper.getComment());
            ps.setString(15, autoCamper.getChassisNo());

            int rowsAffected = ps.executeUpdate();
            DebugMessage.info(this,"UPDATE: Rows Updated " + rowsAffected);
        } catch (SQLException e){
            DebugMessage.error(this,"UPDATE: Failed to update " + autoCamper.getChassisNo());
        }
    }


    @Override
    public void deleteAll(List<Autocamper> listAutoCampers) {

    }

    @Override
    public void updateAll(List<Autocamper> listAutoCampers) {
        for (Autocamper autoCamper : listAutoCampers) {
            this.update((Autocamper) autoCamper);
        }
    }

    @Override
    public Autocamper read(String chassicNo) {
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM tbl_autocamper WHERE fld_chassisNo = ?");
            ps.setString(1, chassicNo);
            ResultSet rs = ps.executeQuery();

            // Check if there are any results
            if (rs.next()) {
                return new Autocamper(
                        rs.getString("fld_chassisNo"),
                        rs.getString("fld_registrationNo"),
                        rs.getString("fld_brand"),
                        rs.getString("fld_comment"),
                        rs.getString("fld_type"),
                        rs.getInt("fld_kmCount"),
                        rs.getInt("fld_noOfRental"),
                        rs.getInt("fld_weight"),
                        rs.getInt("fld_length"),
                        rs.getInt("fld_width"),
                        rs.getInt("fld_height"),
                        rs.getInt("fld_noOfBeds"),
                        rs.getInt("fld_noOfToilets"),
                        rs.getInt("fld_noOfSeatBelts"),
                        rs.getFloat("fld_mainSeasonPrice"),
                        rs.getFloat("fld_lowSeasonPrice"),
                        rs.getDate("fld_purchaseDate")
                );
            } else {
                DebugMessage.error(this, "READ: No Autocamper found with chassicNo " + chassicNo);
            }
        } catch (SQLException e) {
            DebugMessage.error(this, "READ: Failed to READ Autocamper with chassicNo " + chassicNo + ". Error: " + e.getMessage());
        }
        return null;
    }


    @Override
    public List<Autocamper> readAll() {
        List<Autocamper> autoCamperList = new ArrayList<>();
        try{
            PreparedStatement ps = con.prepareStatement("SELECT * FROM tbl_autocamper");
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                autoCamperList.add(this.read(rs.getString(1)));
            }
        } catch (SQLException e){
            DebugMessage.error(this,"READALL: Failed to READ all");
        }
        return autoCamperList;
    }
}
