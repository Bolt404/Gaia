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
            PreparedStatement ps = con.prepareStatement("INSERT INTO tbl_autocamper ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            ps.setString(1, autoCamper.getChassisNo());
            ps.setString(2, autoCamper.getRegistrationNo());
            ps.setInt(3,autoCamper.getKmCount());
            ps.setInt(4,0);
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

            DebugMessage.info(this,"ADD: Successfully added new AutoCamper.");
            ps.executeUpdate();

        } catch (SQLException e) {
            DebugMessage.error(this, "ADD: Failed to add AutoCamper");
        }
    }

    @Override
    public void addAll(List<Autocamper> listAutoCampers) {
        for (Autocamper autoCamper : listAutoCampers) {
            this.add((Autocamper) autoCamper);
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
        try{
           PreparedStatement ps = con.prepareStatement("SELECT * FROM tbl_autocamper WHERE fld_chassisNo = ?");
           ps.setString(1, chassicNo);
            ResultSet rs = ps.executeQuery();
            rs.next();
            DebugMessage.info(this,"READ: Autocamper found.");
            return new Autocamper(
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(15),
                    rs.getString(16),
                    rs.getString(17),
                    rs.getInt(3),
                    rs.getInt(4),
                    rs.getInt(7),
                    rs.getInt(8),
                    rs.getInt(9),
                    rs.getInt(12),
                    rs.getInt(13),
                    rs.getInt(14),
                    rs.getInt(15),
                    rs.getFloat(5),
                    rs.getFloat(6)
            );
        } catch (SQLException e){
            DebugMessage.error(this,"READ: Failed to READ Autocamper with " + chassicNo);
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