package com.level.dao.interfaces;

import java.util.Map;

public interface EntityDao {

    void add(Object object);

    void update(Object object);

    void delete(Object object);

    Object getEntityByID(long id);

    Map<Long, Object> listAll();
}
