package com.belhard.hotel.dao;

import java.sql.SQLException;

/**
 * Created by Darth Vader on 18.03.2017.
 */
public interface DaoInterface<T> {
    public abstract void insert(T ob) throws SQLException;
    public abstract void update(T ob) throws SQLException;
    public abstract void delete(T ob) throws SQLException;
}
