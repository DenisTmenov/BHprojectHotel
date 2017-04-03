package com.belhard.hotel.util;

import com.belhard.hotel.frame.Main;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Verification {
    public static boolean Password(String password) {
        return password.length() <= 50 && password.length() >= 5;
    }
    public static boolean Login(String idUser, String login) {
        ResultSet rsInfo = Main.getDb().query("SELECT * FROM users WHERE login = '" + login + "'");
        try {
            if (rsInfo.next()) {
                return rsInfo.getString(1).equals(idUser) && login.length() <= 50 && login.length() > 0;
            }
        } catch (SQLException e) {
            return login.length() <= 50 && login.length() > 0;
        }
        return login.length() <= 50 && login.length() > 0;
    }
    public static boolean Role (String role){
        switch (role){
            case "admin" : return true;
            case "user" : return true;
            default: return false;
        }

    }
    public static boolean DelStatus(String del_status) {
        switch (del_status){
            case "OK" : return true;
            case "del" : return true;
            default: return false;
        }

    }
    public static boolean IdOrder(String idOrder){
        ResultSet rsInfo = Main.getDb().query("SELECT * FROM orders WHERE id_order = '" + idOrder + "'");
        try {
            if (rsInfo.next()) {
                return true;
            }
        } catch (SQLException e) {
            return false;
        }
        return false;
    }
    public static boolean NumberInRoom(String Number){
        ResultSet rsInfo = Main.getDb().query("SELECT * FROM rooms WHERE number = '" + Number + "'");
        try {
            if (rsInfo.next()) {
                return true;
            }
        } catch (SQLException e) {
            return false;
        }
        return false;
    }
    public static boolean IsItNumber(String number){
        if (Integer.valueOf(number) > 0) return true;
        else return false;
    }
    /*public static boolean IdUser(String idUser){
        ResultSet rsInfo = Main.getDb().query("SELECT * FROM users WHERE id_user = '" + idUser + "'");
        try {
            if (rsInfo.next()) {
                return true;
            }
        } catch (SQLException e) {
            return false;
        }
        return false;
    }*/

}
