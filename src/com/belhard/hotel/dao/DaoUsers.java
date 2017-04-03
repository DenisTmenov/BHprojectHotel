package com.belhard.hotel.dao;

import com.belhard.hotel.db.DB;
import com.belhard.hotel.entity.Users;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DaoUsers  implements DaoInterface<Users>  {
    private DB db;

    public DaoUsers(DB db) {
        this.db = db;
    }

    @Override
    public void insert(Users ob)   {
        try {
            PreparedStatement ps = db.getCn().prepareStatement("INSERT INTO " + ob.getClass().getSimpleName()
                    + " VALUES (?, ?, ?, ?, ?)");
            ps.setString(1, ob.getId_user());
            ps.setString(2, ob.getLogin());
            ps.setString(3, ob.getPassword());
            ps.setString(4, ob.getRole());
            ps.setString(5, ob.getDel_status());

            ps.execute();
        } catch (SQLException e) {
            System.out.println("Error in insert USER.");
            e.printStackTrace();
        }

    }
    /*public void insertUser(Users ob)   {
        try {
            PreparedStatement ps = db.getCn().prepareStatement("INSERT INTO " + ob.getClass().getSimpleName()
                    + " VALUES (?,?,?,?)");
            ps.setString(1, ob.getLogin());
            ps.setString(2, ob.getPassword());
            ps.setString(3, ob.getRole());
            ps.setString(4, ob.getDel_status());

            ps.execute();
        } catch (SQLException e) {
            System.out.println("Error in insert USER.");
            e.printStackTrace();
        }

    }*/
    @Override
    public void update(Users ob) throws SQLException {

            PreparedStatement ps = db.getCn().prepareStatement("UPDATE " + ob.getClass().getSimpleName()
                    + " SET login=?, password=?, role=?, del_status=? WHERE id_user=" + ob.getId_user());
            ps.setString(1, ob.getLogin());
            ps.setString(2, ob.getPassword());
            ps.setString(3, ob.getRole());
            ps.setString(4, ob.getDel_status());

            ps.execute();

    }

    @Override
    public void delete(Users ob) {
        try {
            PreparedStatement ps = db.getCn().prepareStatement("UPDATE " + ob.getClass().getSimpleName()
                    + " SET del_status='del' WHERE id_user=" + ob.getId_user());
            ps.execute();
        } catch (SQLException e) {
            System.out.println("Error in delete USER.");
            e.printStackTrace();
        }
    }

    public ResultSet getAll() {
        ResultSet rs = null;
        try {
            Statement st = db.getCn().createStatement();
            rs = st.executeQuery("SELECT * FROM users");
        } catch (SQLException e) {
            System.out.println("Error in getALL USER.");
            e.printStackTrace();
        }
        return rs;
    }
}
