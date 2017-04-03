package com.belhard.hotel.dao;

import com.belhard.hotel.db.DB;
import com.belhard.hotel.entity.Rooms;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DaoRooms implements DaoInterface<Rooms> {
    private DB db;

    public DaoRooms(DB db) {
        this.db = db;
    }

    @Override
    public void insert(Rooms ob) {
        try {
            PreparedStatement ps = db.getCn().prepareStatement("INSERT INTO " + ob.getClass().getSimpleName()
                    + " VALUES (?,?,?,?,?,?)");
            ps.setString(1, ob.getId_room());
            ps.setString(2, ob.getNumber());
            ps.setString(3, ob.getType());
            ps.setString(4, ob.getCount_beds());
            ps.setString(5, ob.getPrice());
            ps.setString(6, ob.getStatus());

            ps.execute();
        } catch (SQLException e) {
            System.out.println("Error in insert ROOM");
            e.printStackTrace();
        }
    }

    @Override
    public void update(Rooms ob) {
        try {
            PreparedStatement ps = db.getCn().prepareStatement("UPDATE " + ob.getClass().getSimpleName()
                    + " SET number=?, type=?, count_beds=?, price=?, status=? WHERE id_room=" + ob.getId_room());
            ps.setString(1, ob.getNumber());
            ps.setString(2, ob.getType());
            ps.setString(3, ob.getCount_beds());
            ps.setString(4, ob.getPrice());
            ps.setString(5, ob.getStatus());

            ps.execute();
        } catch (SQLException e) {
            System.out.println("Error in update ROOM.");
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Rooms ob) throws SQLException {
        PreparedStatement ps = db.getCn().prepareStatement("DELETE FROM " + ob.getClass().getSimpleName()
                + " WHERE id_room = " + ob.getId_room());
        ps.execute();
    }

    public ResultSet getAll() {
        ResultSet rs = null;
        try {
            Statement st = db.getCn().createStatement();
            rs = st.executeQuery("SELECT * FROM rooms");
        } catch (SQLException e) {
            System.out.println("Error in getALL ROOM.");
            e.printStackTrace();
        }
        return rs;
    }

    public void updateStatus(Rooms ob) {
        try {
            PreparedStatement ps = db.getCn().prepareStatement("UPDATE " + ob.getClass().getSimpleName()
                    + " SET status = " + "'busy'" + " WHERE number=" + ob.getNumber());

            ps.execute();
        } catch (SQLException e) {
            System.out.println("Error in updateStatus ROOM.");
            e.printStackTrace();
        }
    }
}
