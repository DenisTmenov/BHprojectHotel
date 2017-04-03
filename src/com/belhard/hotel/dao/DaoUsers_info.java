package com.belhard.hotel.dao;

import com.belhard.hotel.db.DB;
import com.belhard.hotel.entity.Users_info;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by Darth Vader on 18.03.2017.
 */
public class DaoUsers_info implements DaoInterface<Users_info> {
    private DB db;

    public DaoUsers_info(DB db) {
        this.db = db;
    }

    @Override
    public void insert(Users_info ob) {
        try {
            PreparedStatement ps = db.getCn().prepareStatement("INSERT INTO " + ob.getClass().getSimpleName()
                    + " VALUES (?,?,?,?,?,?,?,?)");
            ps.setString(1, ob.getId_user_info());
            ps.setString(2, ob.getId_user());
            ps.setString(3, ob.getName());
            ps.setString(4, ob.getSure_name());
            ps.setString(5, ob.getNationality());
            ps.setString(6, ob.getPhone());
            ps.setString(7, ob.getE_mail());
            ps.setString(8, ob.getNum_passport());

            ps.execute();
        } catch (SQLException e) {
            System.out.println("Error in insert USER_INFO.");
            e.printStackTrace();
        }
    }

    @Override
    public void update(Users_info ob) throws SQLException {
            PreparedStatement ps = db.getCn().prepareStatement("UPDATE " + ob.getClass().getSimpleName()
                    + " SET id_user=?, name=?, sure_name=?, nationality=?, phone=?, e_mail=?, num_passport=? " +
                    " WHERE id_user_info=" + ob.getId_user_info());
            ps.setString(1, ob.getId_user());
            ps.setString(2, ob.getName());
            ps.setString(3, ob.getSure_name());
            ps.setString(4, ob.getNationality());
            ps.setString(5, ob.getPhone());
            ps.setString(6, ob.getE_mail());
            ps.setString(7, ob.getNum_passport());

            ps.execute();
    }

    @Override
    public void delete(Users_info ob) {
        System.out.println("User_info del status changed.");
    }
}
