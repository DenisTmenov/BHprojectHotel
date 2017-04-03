package com.belhard.hotel.entity;

import javafx.beans.property.SimpleStringProperty;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 * Created by Darth Vader on 18.03.2017.
 */
public class Users_info {
    private SimpleStringProperty id_user_info;
    private SimpleStringProperty id_user;
    private SimpleStringProperty name;
    private SimpleStringProperty sure_name;
    private SimpleStringProperty nationality;
    private SimpleStringProperty phone;
    private SimpleStringProperty e_mail;
    private SimpleStringProperty num_passport;

    public Users_info(String id_user_info, String name, String sure_name, String nationality, String phone, String e_mail, String num_passport) {
        this.id_user_info = new SimpleStringProperty(id_user_info);
        this.name = new SimpleStringProperty(name);
        this.sure_name = new SimpleStringProperty(sure_name);
        this.nationality = new SimpleStringProperty(nationality);
        this.phone = new SimpleStringProperty(phone);
        this.e_mail = new SimpleStringProperty(e_mail);
        this.num_passport = new SimpleStringProperty(num_passport);
    }
    public Users_info(String id_user_info, String id_user, String name, String sure_name, String nationality, String phone, String e_mail, String num_passport) {
        this.id_user_info = new SimpleStringProperty(id_user_info);
        this.id_user = new SimpleStringProperty(id_user);
        this.name = new SimpleStringProperty(name);
        this.sure_name = new SimpleStringProperty(sure_name);
        this.nationality = new SimpleStringProperty(nationality);
        this.phone = new SimpleStringProperty(phone);
        this.e_mail = new SimpleStringProperty(e_mail);
        this.num_passport = new SimpleStringProperty(num_passport);
    }

    public Users_info() {

    }

    public String getId_user_info() {
        return id_user_info.get();
    }

    public SimpleStringProperty id_user_infoProperty() {
        return id_user_info;
    }

    public void setId_user_info(String id_user_info) {
        this.id_user_info.set(id_user_info);
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

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getSure_name() {
        return sure_name.get();
    }

    public SimpleStringProperty sure_nameProperty() {
        return sure_name;
    }

    public void setSure_name(String sure_name) {
        this.sure_name.set(sure_name);
    }

    public String getNationality() {
        return nationality.get();
    }

    public SimpleStringProperty nationalityProperty() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality.set(nationality);
    }

    public String getPhone() {
        return phone.get();
    }

    public SimpleStringProperty phoneProperty() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone.set(phone);
    }

    public String getE_mail() {
        return e_mail.get();
    }

    public SimpleStringProperty e_mailProperty() {
        return e_mail;
    }

    public void setE_mail(String e_mail) {
        this.e_mail.set(e_mail);
    }

    public String getNum_passport() {
        return num_passport.get();
    }

    public SimpleStringProperty num_passportProperty() {
        return num_passport;
    }

    public void setNum_passport(String num_passport) {
        this.num_passport.set(num_passport);
    }

    @Override
    public String toString() {
        return "Users_info{" +
                "id_user_info=" + id_user_info +
                ", id_user=" + id_user +
                ", name=" + name +
                ", sure_name=" + sure_name +
                ", nationality=" + nationality +
                ", phone=" + phone +
                ", e_mail=" + e_mail +
                ", num_passport=" + num_passport +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Users_info that = (Users_info) o;

        if (id_user_info != null ? !id_user_info.equals(that.id_user_info) : that.id_user_info != null) return false;
        if (id_user != null ? !id_user.equals(that.id_user) : that.id_user != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (sure_name != null ? !sure_name.equals(that.sure_name) : that.sure_name != null) return false;
        if (nationality != null ? !nationality.equals(that.nationality) : that.nationality != null) return false;
        if (phone != null ? !phone.equals(that.phone) : that.phone != null) return false;
        if (e_mail != null ? !e_mail.equals(that.e_mail) : that.e_mail != null) return false;
        return num_passport != null ? num_passport.equals(that.num_passport) : that.num_passport == null;
    }

    @Override
    public int hashCode() {
        int result = id_user_info != null ? id_user_info.hashCode() : 0;
        result = 31 * result + (id_user != null ? id_user.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (sure_name != null ? sure_name.hashCode() : 0);
        result = 31 * result + (nationality != null ? nationality.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (e_mail != null ? e_mail.hashCode() : 0);
        result = 31 * result + (num_passport != null ? num_passport.hashCode() : 0);
        return result;
    }

    public static LinkedList<Users_info> getList(ResultSet usersInfo) {
        LinkedList<Users_info> fullUsersInfoList = new LinkedList<Users_info>();
        try {
            while (usersInfo.next()) {
                Users_info info = new Users_info(
                        usersInfo.getString("id_user_info"),
                        usersInfo.getString("id_user"),
                        usersInfo.getString("name"),
                        usersInfo.getString("sure_name"),
                        usersInfo.getString("nationality"),
                        usersInfo.getString("phone"),
                        usersInfo.getString("e_mail"),
                        usersInfo.getString("num_passport"));
                fullUsersInfoList.add(info);
            }
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return fullUsersInfoList;
    }
}
