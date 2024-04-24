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


    public List<Autocamper> availableAutocampers(Date startDate, Date endDate){

        List<Autocamper> autocampers = new ArrayList<>();
        int cnt = 1;
        try{
            PreparedStatement ps = con.prepareStatement("USE DB_Gaia;" +
                    " SELECT a.fld_chassisNo" +
                    ",a.fld_registrationNo" +
                    " ,a.fld_kmCount" +
                    " ,a.fld_noOfRental" +
                    ",a.fld_mainSeasonPrice" +
                            " ,a.fld_lowSeasonPrice" +
                    " ,a.fld_weight" +
                    " ,a.fld_length" +
                            " ,a.fld_width" +
                    " ,a.fld_height" +
                    ",a.fld_purchaseDate" +
                    " ,a.fld_noOfBeds" +
                    ",a.fld_noOfToilets" +
                    ",a.fld_noOfSeatbelts" +
                    ",a.fld_brand" +
                    ",a.fld_comment" +
      ",a.fld_type FROM tbl_autocamper a WHERE NOT EXISTS (SELECT 1 FROM tbl_rental r WHERE r.fld_chassisNo = a.fld_chassisNo AND ((r.fld_startDate <= ? AND r.fld_endDate >= ?)))");

            ps.setDate(1, startDate);
            ps.setDate(2, endDate);

            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                autocampers.add(new Autocamper(

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
                ));

            }

        } catch (SQLException e){
            DebugMessage.error(this,"availableAutocampers: Failed to read");
        }

        return autocampers;
    }
}
