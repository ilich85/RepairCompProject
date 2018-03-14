package com.level.dao.interfaces;

import java.util.Set;

public interface EntityDao {

    void add(Object object);

    void update(Object object);

    void delete(Object object);

    Object getEntityByID(long id);

    Set getAllEntities();
}
