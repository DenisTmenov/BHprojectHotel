package com.belhard.hotel.dao;

import com.belhard.hotel.db.DB;
import com.belhard.hotel.entity.Orders;
import com.belhard.hotel.frame.LoginFrame;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Darth Vader on 18.03.2017.
 */
public class DaoOrders implements DaoInterface<Orders> {
    private DB db;

    public DaoOrders(DB db) {
        this.db = db;
    }
    @Override
    public void insert(Orders ob) {
        try {
            PreparedStatement ps = db.getCn().prepareStatement("INSERT INTO " + ob.getClass().getSimpleName()
                    + " VALUES (?,?,?,?,?,?,?,?,?)");
            ps.setString(1, ob.getId_orders());
            ps.setString(2, ob.getId_user());
            ps.setString(3, ob.getNumber());
            ps.setString(4, ob.getCount_beds());
            ps.setString(5, ob.getType());
            ps.setString(6, ob.getCheck_in());
            ps.setString(7, ob.getCheck_out());
            ps.setString(8, ob.getPrice());
            ps.setString(9, ob.getStatus());

            ps.execute();
        } catch (SQLException e) {
            System.out.println("Error in insert ORDER.");
            e.printStackTrace();
        }
    }
    @Override
    public void update(Orders ob) throws SQLException {
        PreparedStatement ps = db.getCn().prepareStatement("UPDATE " + ob.getClass().getSimpleName()
                + " SET id_user = ?, number=?, count_beds=?, type=?, check_in=?, check_out=?, price=?, status=? WHERE id_order=" + ob.getId_orders());
        ps.setString(1, ob.getId_user());
        ps.setString(2, ob.getNumber());
        ps.setString(3, ob.getCount_beds());
        ps.setString(4, ob.getType());
        ps.setString(5, ob.getCheck_in());
        ps.setString(6, ob.getCheck_out());
        ps.setString(7, ob.getPrice());
        ps.setString(8, ob.getStatus());

        ps.execute();
    }
    @Override
    public void delete(Orders ob) {
        try {
            PreparedStatement ps = db.getCn().prepareStatement("UPDATE " + ob.getClass().getSimpleName()
                    + " SET status='del' WHERE id_order=" + ob.getId_orders());
            ps.execute();
        } catch (SQLException e) {
            System.out.println("Error in delete ORDER.");
            e.printStackTrace();
        }
    }
    public ResultSet getAlluser() {
        ResultSet rs = null;
        try {
            Statement st = db.getCn().createStatement();
            rs = st.executeQuery("SELECT * FROM orders WHERE id_user = " + LoginFrame.getRsInfo().getString(1));
            System.out.println("id_user = " + LoginFrame.getRsInfo().getString(1));
        } catch (SQLException e) {
            System.out.println("Error in getALL ORDERS.");
            e.printStackTrace();
        }
        return rs;
    }
    public ResultSet getAll() {
        ResultSet rs = null;
        try {
            Statement st = db.getCn().createStatement();
            rs = st.executeQuery("SELECT * FROM orders");
        } catch (SQLException e) {
            System.out.println("Error in getALL USER.");
            e.printStackTrace();
        }
        return rs;
    }
    public void insert_user(Orders ob) {
        try {
            PreparedStatement ps = db.getCn().prepareStatement("INSERT INTO " + ob.getClass().getSimpleName()
                    + " (id_user, count_beds, type, check_in, check_out) VALUES (?,?,?,?,?)");
            ps.setString(1, ob.getId_user());
            ps.setString(2, ob.getCount_beds());
            ps.setString(3, ob.getType());
            ps.setString(4, ob.getCheck_in());
            ps.setString(5, ob.getCheck_out());

            ps.execute();
        } catch (SQLException e) {
            System.out.println("Error in insert ORDER.");
            e.printStackTrace();
        }
    }
}
