package com.belhard.hotel.db;

import java.sql.*;

public class DB {
    private Connection cn;
    private Statement st;
    private ResultSet rs;

    public DB(String url, String nameDB, String login, String password) throws SQLException, ClassNotFoundException {

        Class.forName("com.mysql.jdbc.Driver");

        cn = DriverManager.getConnection(url + nameDB + "?useSSL=false", login, password);
        st = cn.createStatement();
        update("SET NAMES 'utf8'");
        //System.out.println("Подключение к серверу установлено.");
    }

    public void update(String sql) throws SQLException {
        st.executeUpdate(sql);
    }


    public ResultSet query(String sql) {
        try {
            rs = st.executeQuery(sql);
        } catch (SQLException e) {
            System.out.print("Проблема в ResultSet");
        }
        return rs;
    }


    /*public static void showTable(ResultSet rs) {
        try {
            ResultSetMetaData rsmd = rs.getMetaData();
            for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                System.out.print(rsmd.getColumnName(i) + "\t");
            }
            while (rs.next()) {
                System.out.println();
                for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                    System.out.print(rs.getString(i) + "\t");
                }
            }
        } catch (SQLException e) {
            System.out.println("Oшибка в выводе данных.");
        }
    }*/

    public void close() {
        try {
            st.close();
            cn.close();
        } catch (SQLException e) {
            System.out.println("Oшибка в при закрытии соединения.");
        }
    }

    public Connection getCn() {
        return cn;
    }

}
