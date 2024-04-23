package org.apollo.template.persistence.Dao;

import org.apollo.template.Database.JDBC;
import org.apollo.template.Domain.AutoCamper;
import org.apollo.template.Service.Debugger.DebugMessage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DaoImplAutoCamper implements DAO<AutoCamper, String> {
    Connection con = JDBC.get().getConnection();

    @Override
    public void add(AutoCamper autoCamper) {
        try{
            PreparedStatement ps = con.prepareStatement("INSERT INTO tbl_autocamper ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            //TODO FINISH ME

        } catch (SQLException e) {
            DebugMessage.error(this, "ADD: Failed to add AutoCamper");
        }
    }

    @Override
    public void addAll(List<AutoCamper> listAutoCampers) {
        for (AutoCamper autoCamper : listAutoCampers) {
            this.add((AutoCamper) autoCamper);
        }

    }

    @Override
    public void delete(AutoCamper autoCamper) {

    }

    @Override
    public void update(AutoCamper autoCamper) {
        String stringId = autoCamper.get
        try{
            PreparedStatement ps = con.prepareStatement();
        } catch (SQLException e){
            DebugMessage.error(this,"UPDATE: Failed to update " + stringId);
        }
    }


    @Override
    public void deleteAll(List<AutoCamper> listAutoCampers) {

    }

    @Override
    public void updateAll(List<AutoCamper> listAutoCampers) {

    }

    @Override
    public AutoCamper read(String chassicNo) {
        try{
           PreparedStatement ps = con.prepareStatement("SELECT * FROM tbl_autocamper WHERE fld_chassisNo = ?");
           ps.setString(1, chassicNo);
            ResultSet rs = ps.executeQuery();
            rs.next();
            return new AutoCamper(
                //TODO FINISH ME
            );
        } catch (SQLException e){
            DebugMessage.error(this,"READ: Failed to READ Autocamper with " + chassicNo);
        }
        return null;
    }


    @Override
    public List<AutoCamper> readAll() {
        List<AutoCamper> autoCamperList = new ArrayList<>();
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
