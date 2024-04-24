package org.apollo.template.persistence.Dao;

import org.apollo.template.Database.JDBC;
import org.apollo.template.Domain.Rental;

import java.sql.Connection;
import java.util.List;

public class DaoImplRental implements DAO<Rental, Integer>{
    Connection con = JDBC.get().getConnection();

    @Override
    public void add(Rental rental) {

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
