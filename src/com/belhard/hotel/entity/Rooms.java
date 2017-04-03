package com.belhard.hotel.entity;

import javafx.beans.property.SimpleStringProperty;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 * Created by Darth Vader on 18.03.2017.
 */
public class Rooms {
    private SimpleStringProperty id_room;
    private SimpleStringProperty number;
    private SimpleStringProperty type;
    private SimpleStringProperty count_beds;
    private SimpleStringProperty price;
    private SimpleStringProperty status;

    public Rooms() {
    }

    public Rooms(String number, String type, String count_beds, String price) {
        this.number = new SimpleStringProperty(number);
        this.type = new SimpleStringProperty(type);
        this.count_beds = new SimpleStringProperty(count_beds);
        this.price = new SimpleStringProperty(price);
    }

    public Rooms(String id_room, String number, String type, String count_beds, String price, String status) {
        this.id_room = new SimpleStringProperty( id_room);
        this.number = new SimpleStringProperty( number);
        this.type = new SimpleStringProperty(type);
        this.count_beds = new SimpleStringProperty(count_beds);
        this.price = new SimpleStringProperty(price);
        this.status = new SimpleStringProperty(status);
    }
    public Rooms(String id_room) {
        this.id_room = new SimpleStringProperty(id_room);
    }

    public Rooms(String number, String count_beds, String type) {
        this.number = new SimpleStringProperty( number);
        this.type = new SimpleStringProperty(type);
        this.count_beds = new SimpleStringProperty(count_beds);
    }

    @Override
    public String toString() {
        return "Rooms{" +
                "id_room=" + id_room +
                ", number=" + number +
                ", type=" + type +
                ", count_beds=" + count_beds +
                ", price=" + price +
                ", status=" + status +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Rooms rooms = (Rooms) o;

        if (id_room != null ? !id_room.equals(rooms.id_room) : rooms.id_room != null) return false;
        if (number != null ? !number.equals(rooms.number) : rooms.number != null) return false;
        if (type != null ? !type.equals(rooms.type) : rooms.type != null) return false;
        if (count_beds != null ? !count_beds.equals(rooms.count_beds) : rooms.count_beds != null) return false;
        if (price != null ? !price.equals(rooms.price) : rooms.price != null) return false;
        return status != null ? status.equals(rooms.status) : rooms.status == null;
    }

    @Override
    public int hashCode() {
        int result = id_room != null ? id_room.hashCode() : 0;
        result = 31 * result + (number != null ? number.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (count_beds != null ? count_beds.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }



    public static LinkedList<Rooms> getList(ResultSet rooms) {
        LinkedList<Rooms> fullRoomsList = new LinkedList<Rooms>();
        try {
            while (rooms.next()) {
                Rooms info = new Rooms(
                        rooms.getString("id_room"),
                        rooms.getString("number"),
                        rooms.getString("type"),
                        rooms.getString("count_beds"),
                        rooms.getString("price"),
                        rooms.getString("status"));
                fullRoomsList.add(info);
            }
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return fullRoomsList;
    }

    public String getId_room() {
        return id_room.get();
    }

    public SimpleStringProperty id_roomProperty() {
        return id_room;
    }

    public void setId_room(String id_room) {
        this.id_room.set(id_room);
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

    public String getType() {
        return type.get();
    }

    public SimpleStringProperty typeProperty() {
        return type;
    }

    public void setType(String type) {
        this.type.set(type);
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
}
