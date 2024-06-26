package org.apollo.template.persistence.Dao;

import java.util.List;

public interface DAOAble<T, I>{

    void add(T t);
    void addAll(List<T> list);

    void delete(T t);
    void deleteAll(List<T> list);

    void update(T t);
    void updateAll(List<T> t);

    T read(I i);
    List<T> readAll();
    
}
