package com.belhard.hotel.entity;

import javafx.beans.property.SimpleStringProperty;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 * Created by Darth Vader on 18.03.2017.
 */
public class Users {
    private SimpleStringProperty id_user;
    private SimpleStringProperty login;
    private SimpleStringProperty password;
    private SimpleStringProperty role;
    private SimpleStringProperty del_status;

    public Users() {

    }

    public Users(String login, String password) {
        this.login = new SimpleStringProperty(login);
        this.password = new SimpleStringProperty(password);
    }

    public Users(String id_user, String login, String password) {
        this.id_user = new SimpleStringProperty(id_user);
        this.login = new SimpleStringProperty(login);
        this.password = new SimpleStringProperty(password);
    }

    public Users(String id_user) {
        this.id_user = new SimpleStringProperty(id_user);    }

    public Users(String id_user, String login, String password, String role, String del_status) {
        this.id_user = new SimpleStringProperty(id_user);
        this.login = new SimpleStringProperty(login);
        this.password = new SimpleStringProperty(password);
        this.role = new SimpleStringProperty(role);
        this.del_status = new SimpleStringProperty(del_status);
    }

    public Users(String login, String password, String role, String del_status) {
        this.login = new SimpleStringProperty(login);
        this.password = new SimpleStringProperty(password);
        this.role = new SimpleStringProperty(role);
        this.del_status = new SimpleStringProperty(del_status);
    }

    @Override
    public String toString() {
        return "Users{" +
                "id_user=" + id_user +
                ", login=" + login +
                ", password=" + password +
                ", role=" + role +
                ", del_status=" + del_status +
                '}';
    }

    public String getId_user() {
        return id_user.get();
    }

    public SimpleStringProperty id_userProperty() {
        return id_user;
    }

    public void setId_user(String id_user) {
        this.id_user.set(id_user);
    }

    public String getLogin() {
        return login.get();
    }

    public SimpleStringProperty loginProperty() {
        return login;
    }

    public void setLogin(String login) {
        this.login.set(login);
    }

    public String getPassword() {
        return password.get();
    }

    public SimpleStringProperty passwordProperty() {
        return password;
    }

    public void setPassword(String password) {
        this.password.set(password);
    }

    public String getRole() {
        return role.get();
    }

    public SimpleStringProperty roleProperty() {
        return role;
    }

    public void setRole(String role) {
        this.role.set(role);
    }

    public String getDel_status() {
        return del_status.get();
    }

    public SimpleStringProperty del_statusProperty() {
        return del_status;
    }

    public void setDel_status(String del_status) {
        this.del_status.set(del_status);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Users users = (Users) o;

        if (id_user != null ? !id_user.equals(users.id_user) : users.id_user != null) return false;
        if (login != null ? !login.equals(users.login) : users.login != null) return false;
        if (password != null ? !password.equals(users.password) : users.password != null) return false;
        if (role != null ? !role.equals(users.role) : users.role != null) return false;
        return del_status != null ? del_status.equals(users.del_status) : users.del_status == null;
    }

    @Override
    public int hashCode() {
        int result = id_user != null ? id_user.hashCode() : 0;
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (role != null ? role.hashCode() : 0);
        result = 31 * result + (del_status != null ? del_status.hashCode() : 0);
        return result;
    }

    public static LinkedList<Users> getList(ResultSet users) {
        LinkedList<Users> fullUsersList = new LinkedList<Users>();
        try {
            while (users.next()) {
                Users user = new Users(
                        users.getString("Id_user"),
                        users.getString("Login"),
                        users.getString("Password"),
                        users.getString("Role"),
                        users.getString("Del_status"));
                fullUsersList.add(user);
            }
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return fullUsersList;
    }
}
