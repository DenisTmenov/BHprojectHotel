package com.belhard.hotel.frame;

import com.belhard.hotel.util.NewScene;
import com.belhard.hotel.entity.Orders;
import com.belhard.hotel.entity.Rooms;
import com.belhard.hotel.entity.Users;
import com.belhard.hotel.entity.Users_info;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.TextFieldTableCell;

import java.net.URL;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 */
public class AdminFrame implements Initializable {
    /**
     * Initializes the controller class.
     */


    @FXML private TableColumn<Orders, String> ordersIdOrderColumn, ordersIdUserColumn,
            ordersNumber, ordersCountBeds, ordersPriceColumn, ordersTypeColumn,
            ordersStatusColumn, ordersCheckInColumn, ordersCheckOutColumn;

    @FXML private TableColumn<Users_info, String> usersInfoIdUserInfoColumn, usersInfoIdUserColumn,
            usersInfoNameColumn, usersInfoSureNameColumn, usersInfoNationalityColumn,
            usersInfoPhoneColumn, usersInfoEmailColumn, usersInfoNumPassportColumn;

    @FXML private TableColumn<Rooms, String> roomsIdRoomColumn, roomsNumberColumn,
            roomsTypeColumn, roomsCountBedsColumn, roomsPriceColumn, roomsStatusColumn;
    @FXML private TableColumn<Users, String> usersIdUserColumn, usersLoginColumn,
            usersPasswordColumn, usersRoleColumn, usersDelStatusColumn;

    @FXML private Tab ordersTab, usersTab, usersInfoTab, roomsTab;

    @FXML private TableView<Orders> ordersTable;
    @FXML private TableView<Users> usersTable;
    @FXML private TableView<Users_info> usersInfoTable;
    @FXML private TableView<Rooms> roomsTable;

    @FXML Button buttonLogOut, ButtonUpdate;
    @Override public void initialize(URL location, ResourceBundle resources) {
        try {
            showDataBase();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        buttonLogOut.setOnAction(event -> {
            System.out.println("Вернулись в LoginFrame");
            new NewScene(buttonLogOut, "LoginFrame", false);
        });

    }

    private void showDataBase() throws ClassNotFoundException, SQLException {

        ordersIdOrderColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        ordersIdUserColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        ordersNumber.setCellFactory(TextFieldTableCell.forTableColumn());
        ordersCountBeds.setCellFactory(TextFieldTableCell.forTableColumn());
        ordersTypeColumn.setCellFactory(ComboBoxTableCell.forTableColumn());
        ordersCheckInColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        ordersCheckOutColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        ordersPriceColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        ordersStatusColumn.setCellFactory(ComboBoxTableCell.forTableColumn());

        /*ordersStatusColumn.setOnEditCommit(t -> {
            String newName = t.getNewValue();

            ObservableList<Orders> items = t.getTableView().getItems();
            TablePosition<Orders, String> tablePosition = t.getTablePosition();

            Orders fieldRow = items.get(tablePosition.getRow());
            fieldRow.setName(newName);
        });


        LinkedList<Orders> ordersStatusRow = Orders.getList(Main.getDb().query("SELECT type FROM orders"));
        ObservableList<Orders> ordersStatusOblist = FXCollections.observableArrayList();
        for (Orders anOrdersStatusRow : ordersStatusRow) {
            ordersStatusOblist.add(anOrdersStatusRow);
        }
        ordersStatusColumn.setItems(roomsOblist);*/


        ordersIdOrderColumn.setCellValueFactory(cellData -> cellData.getValue().id_ordersProperty());
        ordersIdUserColumn.setCellValueFactory(cellData -> cellData.getValue().id_userProperty());
        ordersNumber.setCellValueFactory(cellData -> cellData.getValue().numberProperty());
        ordersCountBeds.setCellValueFactory(cellData -> cellData.getValue().count_bedsProperty());
        ordersTypeColumn.setCellValueFactory(cellData -> cellData.getValue().typeProperty());
        ordersCheckInColumn.setCellValueFactory(cellData -> cellData.getValue().check_inProperty());
        ordersCheckOutColumn.setCellValueFactory(cellData -> cellData.getValue().check_outProperty());
        ordersPriceColumn.setCellValueFactory(cellData -> cellData.getValue().priceProperty());
        ordersStatusColumn.setCellValueFactory(cellData -> cellData.getValue().statusProperty());
        //!!!!!!!!!!!!!!!!!
        usersInfoIdUserInfoColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        usersInfoIdUserColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        usersInfoNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        usersInfoSureNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        usersInfoNationalityColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        usersInfoPhoneColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        usersInfoEmailColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        usersInfoNumPassportColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        usersInfoIdUserInfoColumn.setCellValueFactory(cellData -> cellData.getValue().id_user_infoProperty());
        usersInfoIdUserColumn.setCellValueFactory(cellData -> cellData.getValue().id_userProperty());
        usersInfoNameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        usersInfoSureNameColumn.setCellValueFactory(cellData -> cellData.getValue().sure_nameProperty());
        usersInfoNationalityColumn.setCellValueFactory(cellData -> cellData.getValue().nationalityProperty());
        usersInfoPhoneColumn.setCellValueFactory(cellData -> cellData.getValue().phoneProperty());
        usersInfoEmailColumn.setCellValueFactory(cellData -> cellData.getValue().e_mailProperty());
        usersInfoNumPassportColumn.setCellValueFactory(cellData -> cellData.getValue().num_passportProperty());
        //!!!!!!!!!!!!!!!!!
        roomsIdRoomColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        roomsNumberColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        roomsTypeColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        roomsCountBedsColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        roomsPriceColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        roomsStatusColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        roomsIdRoomColumn.setCellValueFactory(cellData -> cellData.getValue().id_roomProperty());
        roomsNumberColumn.setCellValueFactory(cellData -> cellData.getValue().numberProperty());
        roomsTypeColumn.setCellValueFactory(cellData -> cellData.getValue().typeProperty());
        roomsCountBedsColumn.setCellValueFactory(cellData -> cellData.getValue().count_bedsProperty());
        roomsPriceColumn.setCellValueFactory(cellData -> cellData.getValue().priceProperty());
        roomsStatusColumn.setCellValueFactory(cellData -> cellData.getValue().statusProperty());

        //!!!!!!!!!!!!!!!!!
        usersIdUserColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        usersLoginColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        usersPasswordColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        usersRoleColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        usersDelStatusColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        usersIdUserColumn.setCellValueFactory(cellData -> cellData.getValue().id_userProperty());
        usersLoginColumn.setCellValueFactory(cellData -> cellData.getValue().loginProperty());
        usersPasswordColumn.setCellValueFactory(cellData -> cellData.getValue().passwordProperty());
        usersRoleColumn.setCellValueFactory(cellData -> cellData.getValue().roleProperty());
        usersDelStatusColumn.setCellValueFactory(cellData -> cellData.getValue().del_statusProperty());


        LinkedList<Orders> ordersRow = Orders.getList(Main.getDb().query("SELECT * FROM orders"));
        ObservableList<Orders> ordersOblist = FXCollections.observableArrayList();
        for (Orders anOrdersRow : ordersRow) {
            ordersOblist.add(anOrdersRow);
        }

        LinkedList<Users> usersRow = Users.getList(Main.getDb().query("SELECT * FROM users"));
        ObservableList<Users> usersOblist = FXCollections.observableArrayList();
        for (Users anUsersRow : usersRow) {
            usersOblist.add(anUsersRow);
        }
        LinkedList<Users_info> usersInfoRow = Users_info.getList(Main.getDb().query("SELECT * FROM users_info"));
        ObservableList<Users_info> usersInfoOblist = FXCollections.observableArrayList();
        for (Users_info anUsersInfoRow : usersInfoRow) {
            usersInfoOblist.add(anUsersInfoRow);
        }
        LinkedList<Rooms> roomsRow = Rooms.getList(Main.getDb().query("SELECT * FROM rooms"));
        ObservableList<Rooms> roomsOblist = FXCollections.observableArrayList();
        for (Rooms anroomsRow : roomsRow) {
            roomsOblist.add(anroomsRow);
        }
       /* // вывод на консоль
        for (Orders anOrdersOblist : ordersOblist) {
            System.out.println(anOrdersOblist);
        }*/

        ordersTable.setItems(ordersOblist);
        usersTable.setItems(usersOblist);
        usersInfoTable.setItems(usersInfoOblist);
        roomsTable.setItems(roomsOblist);


    }

    /**
     * Called to initialize a controller after its root element has been
     * completely processed.
     *
     * @param location  The location used to resolve relative paths for the root object, or
     *                  <tt>null</tt> if the location is not known.
     * @param resources The resources used to localize the root object, or <tt>null</tt> if
     */

}

