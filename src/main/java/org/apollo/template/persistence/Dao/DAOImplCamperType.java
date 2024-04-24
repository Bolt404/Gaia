package org.apollo.template.persistence.Dao;

import org.apollo.template.Database.JDBC;
import org.apollo.template.Domain.CamperType;
import org.apollo.template.Service.Debugger.DebugMessage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAOImplCamperType implements DAO<CamperType, String>{

    private Connection conn = JDBC.get().getConnection();

    @Override
    public void add(CamperType camperType) {
        try {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO tbl_autocamperType (fld_type, fld_typeDescription) VALUES (`?, ?)");
            ps.setString(1, camperType.getType());
            ps.setString(2, camperType.getTypeDescription());

            ps.executeUpdate();

            DebugMessage.info(this, "Created a new camperType: " + camperType.getType() + " : " + camperType.getTypeDescription());

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void addAll(List<CamperType> list) {

        for (CamperType camperType : list){
            add(camperType);
        }

    }

    @Override
    public void delete(CamperType camperType) {
        try {
            PreparedStatement ps = conn.prepareStatement("DELETE FROM tbl_autocamperType WHERE fld_type = ? AND fld_typeDescription = ?");

            ps.setString(1, camperType.getType());
            ps.setString(2, camperType.getTypeDescription());

            DebugMessage.info(this, "deleted camperType: " + camperType.getType() + " : " + camperType.getTypeDescription());


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void deleteAll(List<CamperType> list) {

        for (CamperType camperType : list){
            delete(camperType);
        }

    }

    @Override
    public void update(CamperType camperType) {

    }

    @Override
    public void updateAll(List<CamperType> t) {

    }

    @Override
    public CamperType read(String s) {

        PreparedStatement ps = null;
        try {
            ps = conn.prepareCall("SELECT * FROM tbl_autocamperType WHERE fld_type = ?");
            ps.setString(1, s);

            ResultSet rs = ps.executeQuery();

            return new CamperType(rs.getString(1), rs.getString(1));


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<CamperType> readAll() {

        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM tbl_autocamperType");

            ResultSet rs = ps.executeQuery();

            List<CamperType> camperTypeList = new ArrayList<>();

            while (rs.next()){
                camperTypeList.add(new CamperType(rs.getString(1), rs.getString(2)));
            }

            return camperTypeList;



        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


}
