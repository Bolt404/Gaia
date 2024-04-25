package org.apollo.template.Service;

import org.apollo.template.Database.JDBC;
import org.apollo.template.Domain.Autocamper;
import org.apollo.template.Service.Debugger.DebugMessage;
import org.apollo.template.persistence.Dao.DaoImplAutoCamper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class RentalUtil {

    Connection con = JDBC.get().getConnection();

    DaoImplAutoCamper dao = new DaoImplAutoCamper();


    public List<Autocamper> availableAutocampers(Date startDate, Date endDate, String type){



        List<Autocamper> autocampers = new ArrayList<>();
        int cnt = 1;
        try{

            PreparedStatement ps = con.prepareStatement("SELECT a.fld_chassisNo FROM tbl_autocamper a WHERE a.fld_type = ? AND NOT EXISTS (SELECT 1 FROM tbl_rental r WHERE r.fld_chassisNo = a.fld_chassisNo AND ((r.fld_startDate <= ? AND r.fld_endDate >= ?)))");

            ps.setString(1, type);
            ps.setDate(2, startDate);
            ps.setDate(3, endDate);

            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                autocampers.add(dao.read(rs.getString(cnt)));

            }

        } catch (SQLException e){
            DebugMessage.error(this,"availableAutocampers: Failed to read");
        }

        return autocampers;
    }





    public List<Autocamper> availableAutocampers(Date startDate, Date endDate){

        List<Autocamper> autocampers = new ArrayList<>();
        int cnt = 1;
        try{
            PreparedStatement ps = con.prepareStatement("SELECT a.fld_chassisNo FROM tbl_autocamper a WHERE NOT EXISTS (SELECT 1                      FROM tbl_rental r WHERE r.fld_chassisNo = a.fld_chassisNo AND ((r.fld_startDate <= ? AND r.fld_endDate >= ?)))");

            ps.setDate(1, startDate);
            ps.setDate(2, endDate);

            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                autocampers.add(dao.read(rs.getString(cnt)));

            }

        } catch (SQLException e){
            DebugMessage.error(this,"availableAutocampers: Failed to read");
        }

        return autocampers;
    }
}
