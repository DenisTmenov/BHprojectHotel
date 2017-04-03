package com.belhard.hotel.entity;

import javafx.beans.property.SimpleStringProperty;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 * Created by Darth Vader on 18.03.2017.
 */
public class Orders {
    private SimpleStringProperty id_orders;
    private SimpleStringProperty id_user;
    private SimpleStringProperty number;
    private SimpleStringProperty count_beds;
    private SimpleStringProperty type;
    private SimpleStringProperty check_in;
    private SimpleStringProperty check_out;
    private SimpleStringProperty price;
    private SimpleStringProperty status;

    public Orders(String id_orders, String id_user, String number, String count_beds, String type, String check_in, String check_out, String price, String status) {
        this.id_orders = new SimpleStringProperty(id_orders);
        this.id_user = new SimpleStringProperty(id_user);
        this.number = new SimpleStringProperty(number);
        this.count_beds = new SimpleStringProperty(count_beds);
        this.type = new SimpleStringProperty(type);
        this.check_in = new SimpleStringProperty(check_in);
        this.check_out = new SimpleStringProperty(check_out);
        this.price = new SimpleStringProperty(price);
        this.status = new SimpleStringProperty(status);
    }

    public Orders(String id_user, String count_beds, String type, String check_in, String check_out) {
        this.id_user = new SimpleStringProperty(id_user);
        this.count_beds = new SimpleStringProperty(count_beds);
        this.type = new SimpleStringProperty(type);
        this.check_in = new SimpleStringProperty(check_in);
        this.check_out = new SimpleStringProperty(check_out);
    }

    public Orders(String id_orders, String count_beds, String type, String check_in, String check_out, String status) {
        this.id_orders = new SimpleStringProperty(id_orders);
        this.count_beds = new SimpleStringProperty(count_beds);
        this.type = new SimpleStringProperty(type);
        this.check_in = new SimpleStringProperty(check_in);
        this.check_out = new SimpleStringProperty(check_out);
        this.status = new SimpleStringProperty(status);
    }

    public Orders(String id_orders, String id_user, String number, String count_beds, String type, String check_in, String check_out, String status) {
        this.id_orders = new SimpleStringProperty(id_orders);
        this.id_user = new SimpleStringProperty(id_user);
        this.number = new SimpleStringProperty(number);
        this.count_beds = new SimpleStringProperty(count_beds);
        this.type = new SimpleStringProperty(type);
        this.check_in = new SimpleStringProperty(check_in);
        this.check_out = new SimpleStringProperty(check_out);
        this.status = new SimpleStringProperty(status);
    }

    public Orders() {

    }
    public Orders(String id_order) {
        this.id_orders = new SimpleStringProperty(id_order);
    }

    public static LinkedList<Orders> getList(ResultSet orders) {
        LinkedList<Orders> fullOrdersList = new LinkedList<Orders>();
        try {
            while (orders.next()) {
                Orders order = new Orders(
                        orders.getString("Id_order"),
                        orders.getString("Id_user"),
                        orders.getString("Number"),
                        orders.getString("Count_beds"),
                        orders.getString("Type"),
                        orders.getString("Check_in"),
                        orders.getString("Check_out"),
                        orders.getString("Price"),
                        orders.getString("Status"));
                fullOrdersList.add(order);
            }
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return fullOrdersList;

    }

    public String getId_orders() {
        return id_orders.get();
    }

    public SimpleStringProperty id_ordersProperty() {
        return id_orders;
    }

    public void setId_orders(String id_orders) {
        this.id_orders.set(id_orders);
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

    public String getNumber() {
        return number.get();
    }

    public SimpleStringProperty numberProperty() {
        return number;
    }

    public void setNumber(String number) {
        this.number.set(number);
    }

    public String getCount_beds() {
        return count_beds.get();
    }

    public SimpleStringProperty count_bedsProperty() {
        return count_beds;
    }

    public void setCount_beds(String count_beds) {
        this.count_beds.set(count_beds);
    }

    public String getType() {
        return type.get();
    }

    public SimpleStringProperty typeProperty() {
        return type;
    }

    public void setType(String type) {
        this.type.set(type);
    }

    public String getCheck_in() {
        return check_in.get();
    }

    public SimpleStringProperty check_inProperty() {
        return check_in;
    }

    public void setCheck_in(String check_in) {
        this.check_in.set(check_in);
    }

    public String getCheck_out() {
        return check_out.get();
    }

    public SimpleStringProperty check_outProperty() {
        return check_out;
    }

    public void setCheck_out(String check_out) {
        this.check_out.set(check_out);
    }

    public String getPrice() {
        return price.get();
    }

    public SimpleStringProperty priceProperty() {
        return price;
    }

    public void setPrice(String price) {
        this.price.set(price);
    }

    public String getStatus() {
        return status.get();
    }

    public SimpleStringProperty statusProperty() {
        return status;
    }

    public void setStatus(String status) {
        this.status.set(status);
    }

    @Override
    public String toString() {
        return "Orders{" +
                "id_orders=" + id_orders +
                ", id_user=" + id_user +
                ", number=" + number +
                ", count_beds=" + count_beds +
                ", type=" + type +
                ", check_in=" + check_in +
                ", check_out=" + check_out +
                ", price=" + price +
                ", status=" + status +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Orders orders = (Orders) o;

        if (id_orders != null ? !id_orders.equals(orders.id_orders) : orders.id_orders != null) return false;
        if (id_user != null ? !id_user.equals(orders.id_user) : orders.id_user != null) return false;
        if (number != null ? !number.equals(orders.number) : orders.number != null) return false;
        if (count_beds != null ? !count_beds.equals(orders.count_beds) : orders.count_beds != null) return false;
        if (type != null ? !type.equals(orders.type) : orders.type != null) return false;
        if (check_in != null ? !check_in.equals(orders.check_in) : orders.check_in != null) return false;
        if (check_out != null ? !check_out.equals(orders.check_out) : orders.check_out != null) return false;
        if (price != null ? !price.equals(orders.price) : orders.price != null) return false;
        return status != null ? status.equals(orders.status) : orders.status == null;
    }

    @Override
    public int hashCode() {
        int result = id_orders != null ? id_orders.hashCode() : 0;
        result = 31 * result + (id_user != null ? id_user.hashCode() : 0);
        result = 31 * result + (number != null ? number.hashCode() : 0);
        result = 31 * result + (count_beds != null ? count_beds.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (check_in != null ? check_in.hashCode() : 0);
        result = 31 * result + (check_out != null ? check_out.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }
}
