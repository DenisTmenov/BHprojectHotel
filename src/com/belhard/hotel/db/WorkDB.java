package com.belhard.hotel.db;

import com.belhard.hotel.dao.DaoOrders;
import com.belhard.hotel.dao.DaoRooms;
import com.belhard.hotel.dao.DaoUsers;
import com.belhard.hotel.dao.DaoUsers_info;
import com.belhard.hotel.entity.Orders;
import com.belhard.hotel.entity.Rooms;
import com.belhard.hotel.entity.Users;
import com.belhard.hotel.entity.Users_info;

import java.sql.SQLException;

/**
 * Created by Darth Vader on 12.03.2017.
 */
public class WorkDB {
    public static void createDB(String url, String nameDB, String login, String password) throws SQLException, ClassNotFoundException {
        DB db = new DB(url, "", login, password);
        db.update("CREATE DATABASE " + nameDB);
        db.update("USE " + nameDB);
        db.update("CREATE TABLE users ("
                + "id_user INT AUTO_INCREMENT,"
                + "login VARCHAR(50) NOT NULL UNIQUE,"
                + "password VARCHAR(50) NOT NULL,"
                + "role ENUM('user', 'admin') DEFAULT 'user',"
                + "del_status ENUM('OK', 'del') DEFAULT 'OK',"
                + "PRIMARY KEY(id_user))");

        db.update("CREATE TABLE users_info ("
                + "id_user_info INT AUTO_INCREMENT,"
                + "id_user INT NOT NULL UNIQUE,"
                + "name VARCHAR(50) NOT NULL,"
                + "sure_name VARCHAR(50) NOT NULL,"
                + "nationality VARCHAR(50) NOT NULL,"
                + "phone VARCHAR(50) NOT NULL UNIQUE,"
                + "e_mail VARCHAR(50) NOT NULL UNIQUE,"
                + "num_passport VARCHAR(50) NOT NULL UNIQUE,"
                + "FOREIGN KEY(id_user) REFERENCES users (id_user),"
                + "PRIMARY KEY(id_user_info))");

        db.update("CREATE TABLE rooms ("
                + "id_room INT AUTO_INCREMENT,"
                + "number INT NOT NULL UNIQUE,"
                + "type VARCHAR(50) NOT NULL,"
                + "count_beds INT NOT NULL,"
                + "price INT NOT NULL,"
                + "status ENUM('free', 'busy') DEFAULT 'free', "
                + "PRIMARY KEY(id_room))");

        db.update("CREATE TABLE orders ("
                + "id_order INT AUTO_INCREMENT,"
                + "id_user INT,"
                + "number INT,"
                + "count_beds INT ," //NOT NULL
                + "type VARCHAR(50) ,"
                + "check_in VARCHAR(10),"
                + "check_out VARCHAR(10),"
                + "price INT ,"
                + "status ENUM('paid', 'busy', 'new', 'del') DEFAULT 'new' ,"
                + "FOREIGN KEY(id_user) REFERENCES users (id_user),"
               // + "FOREIGN KEY(number) REFERENCES rooms (number),"
                + "PRIMARY KEY(id_order))");


        DaoUsers du = new DaoUsers(db);
        DaoUsers_info dui = new DaoUsers_info(db);
        DaoOrders dor = new DaoOrders(db);
        DaoRooms dr = new DaoRooms(db);
        //!!!!!!!!!!!!!!!!!!!!!!!!!!!
        du.insert(new Users("1", "admin", "123456","admin", "OK"));
        du.insert(new Users("2", "manager", "manager", "admin", "OK"));
        du.insert(new Users("3","user", "123456", "user", "OK"));
        du.update(new Users("2", "manager", "123456", "admin", "del"));
        //!!!!!!!!!!!!!!!!!!!!!!!!!!!
        dui.insert(new Users_info("1", "1","Shelden", "Kupper", "USA", "80294478896", "sheldon.k@gmail.com", "MM32569812"));
        dui.insert(new Users_info("2", "2", "Rajesh", "Kutrapaly", "IND","80339958745", "rajesh.k@gmail.com", "MM12453641"));
        dui.insert(new Users_info("3", "3", "Ivan", "Ivanov", "RUS", "80448865412", "ivan.i@gmail.com", "RS14525786"));
        dui.update(new Users_info("2","2", "qwe", "qwe","qwe","qwe","qwe","qwe"));
        //!!!!!!!!!!!!!!!!!!!!!!!!!!!
        dr.insert(new Rooms("1", "101", "Economy", "1", "50", "free"));
        dr.insert(new Rooms("2","102", "Economy", "1","50", "free"));
        dr.insert(new Rooms("3","103", "Family Room", "4","150", "free"));
        dr.insert(new Rooms("4","104", "Family Room", "4","150", "free"));
        dr.insert(new Rooms("5","201", "Economy", "2","90", "free"));
        dr.insert(new Rooms("6","202", "Economy", "2","90", "free"));
        dr.insert(new Rooms("7","203", "Standart", "2","160", "free"));
        dr.insert(new Rooms("8","204", "Standart", "2","160", "free"));
        dr.insert(new Rooms("9","205", "Luxe", "2","200", "free"));
        dr.insert(new Rooms("10","206", "Luxe", "2","200", "free"));
        dr.insert(new Rooms("11","301", "Luxe", "2","200", "free"));
        dr.insert(new Rooms("12","302", "Luxe", "2","200", "free"));
        dr.insert(new Rooms("13","303", "Luxe", "2","200", "free"));
        dr.insert(new Rooms("14","304", "Luxe", "2","200", "free"));
        dr.insert(new Rooms("15","305", "Luxe", "2","200", "free"));
        dr.insert(new Rooms("16","306", "Luxe", "2","200", "free"));
        dr.update(new Rooms("17","102", "Economy +", "1", "60", "free"));
        dr.update(new Rooms("18","204", "Standart +", "2", "180", "free"));
        //!!!!!!!!!!!!!!!!!!!!!!!!!!!
        dor.insert(new Orders("1", "1", "101", "1", "Economy", "20.04.2017", "22.04.2017", "200", "busy"));
        dor.insert_user(new Orders("3", "2", "Standart", "01.01.2017", "10.01.2017"));
        dor.insert_user(new Orders("3", "2", "Standart", "01.02.2017", "10.02.2017"));
        dor.insert_user(new Orders("3", "2", "Standart", "01.03.2017", "10.03.2017"));
        //!!!!!!!!!!!!!!!!!!!!!!!!!!!
        DB.showTable(db.query("SELECT * FROM users"));
        System.out.println();
        DB.showTable(db.query("SELECT * FROM users_info"));
        System.out.println();
        DB.showTable(db.query("SELECT * FROM rooms"));
        System.out.println();
        DB.showTable(db.query("SELECT * FROM orders"));
        System.out.println("\nБаза данных создана.");
    }

    public static void deleteDB(String url, String nameDB, String login, String password) throws SQLException, ClassNotFoundException {
        DB db = new DB(url, nameDB, login, password);
        db.update("DROP DATABASE " + nameDB);
        System.out.println("База данных удалена.");
    }
}
