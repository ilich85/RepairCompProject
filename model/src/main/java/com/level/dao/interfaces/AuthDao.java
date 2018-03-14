package com.level.dao.interfaces;

public interface AuthDao extends EntityDao {
    Object getAuthByName(String name);
}