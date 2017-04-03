package com.belhard.hotel.frame;

import com.belhard.hotel.dao.DaoOrders;
import com.belhard.hotel.dao.DaoRooms;
import com.belhard.hotel.dao.DaoUsers;
import com.belhard.hotel.dao.DaoUsers_info;
import com.belhard.hotel.entity.Orders;
import com.belhard.hotel.entity.Rooms;
import com.belhard.hotel.entity.Users;
import com.belhard.hotel.entity.Users_info;
import com.belhard.hotel.util.Message;
import com.belhard.hotel.util.NewScene;
import com.belhard.hotel.util.Verification;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.TextFieldTableCell;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 */
public class AdminFrameNew implements Initializable {
    /**
     * Initializes the controller class.
     */


    @FXML
    private TableColumn<Orders, String> ordersIdOrderColumn, ordersIdUserColumn,
            ordersNumber, ordersCountBedsColumn, ordersPriceColumn, ordersTypeColumn,
            ordersStatusColumn, ordersCheckInColumn, ordersCheckOutColumn;

    @FXML
    private TableColumn<Users_info, String> usersInfoIdUserInfoColumn, usersInfoIdUserColumn,
            usersInfoNameColumn, usersInfoSureNameColumn, usersInfoNationalityColumn,
            usersInfoPhoneColumn, usersInfoEmailColumn, usersInfoNumPassportColumn;

    @FXML
    private TableColumn<Rooms, String> roomsIdRoomColumn, roomsNumberColumn,
            roomsTypeColumn, roomsCountBedsColumn, roomsPriceColumn, roomsStatusColumn;

    @FXML
    private TableColumn<Users, String> usersIdUserColumn, usersLoginColumn,
            usersPasswordColumn, usersRoleColumn, usersDelStatusColumn;

    @FXML
    private TableColumn<Users, String> usInfoIDUSERcolumn, usInfoLogincolumn, usInfoPASScolumn,
            usInfoROLEcolumn, usInfoDELSTATUScolumn;

    @FXML
    private Label usInfoNAMElable, usInfoSURENAMElable, usInfoNATIONALlable,
            usInfoPHONElable, usInfoEMAILlable, usInfoNUMPASSPORTlable;

    @FXML
    private Tab ordersTab, usersTab, usersInfoTab, roomsTab, usInfoTab;

    @FXML
    private TableView<Orders> ordersTable;
    @FXML
    private TableView<Users> usersTable;
    @FXML
    private TableView<Users_info> usersInfoTable;
    @FXML
    private TableView<Rooms> roomsTable;
    @FXML
    private TableView<Users> usInfoTable;
    @FXML
    private Button ordersLOGOUTbutton, usersLOGOUTbutton, usersInfoLOGOUTbutton, roomsLOGOUTbutton,
            ordersNEWbutton, usersNEWbutton, usersInfoNEWbutton, roomsNEWbutton,
            ordersUPDATEbutton, usersUPDATEbutton, usersInfoUPDATEbutton, roomsUPDATEbutton,
            ordersDELETEbutton, usersDELETEbutton, usersInfoDELETEbutton, roomsDELETEbutton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            showDataBase();
        } catch (ClassNotFoundException e) {
            System.out.println("ClassNotFoundException");
        } catch (SQLException e) {
            System.out.println("SQLException");
        }
        showUserInfo(null);


// !!!! button UPDATE !!!! start
        // необходимо указывать каждую строку которую хотим  изменить
        ordersUPDATEbutton.setOnAction(event -> {
            try {
                DaoOrders dao = new DaoOrders(Main.getDb());
                dao.update(new Orders(
                        ordersTable.getSelectionModel().getSelectedItem().getId_orders(),
                        ordersTable.getSelectionModel().getSelectedItem().getId_user(),
                        ordersTable.getSelectionModel().getSelectedItem().getNumber(),
                        ordersTable.getSelectionModel().getSelectedItem().getCount_beds(),
                        ordersTable.getSelectionModel().getSelectedItem().getType(),
                        ordersTable.getSelectionModel().getSelectedItem().getCheck_in(),
                        ordersTable.getSelectionModel().getSelectedItem().getCheck_out(),
                        ordersTable.getSelectionModel().getSelectedItem().getPrice(),
                        ordersTable.getSelectionModel().getSelectedItem().getStatus()));

                DaoRooms daoRooms = new DaoRooms(Main.getDb());
                daoRooms.updateStatus(new Rooms(
                        ordersTable.getSelectionModel().getSelectedItem().getNumber(),
                        ordersTable.getSelectionModel().getSelectedItem().getCount_beds(),
                        ordersTable.getSelectionModel().getSelectedItem().getType()));
                showDataBase();
            } catch (ClassNotFoundException e) {
                System.out.println("Нет связи с сервером");
            } catch (SQLException e) {
                System.out.println("Неправильно введены данные");
                System.out.println(ordersTable.getSelectionModel().getSelectedItem().getId_orders() + " " +
                        ordersTable.getSelectionModel().getSelectedItem().getId_user() + " " +
                        ordersTable.getSelectionModel().getSelectedItem().getNumber() + " " +
                        ordersTable.getSelectionModel().getSelectedItem().getCount_beds() + " " +
                        ordersTable.getSelectionModel().getSelectedItem().getType() + " " +
                        ordersTable.getSelectionModel().getSelectedItem().getCheck_in() + " " +
                        ordersTable.getSelectionModel().getSelectedItem().getCheck_out() + " " +
                        ordersTable.getSelectionModel().getSelectedItem().getPrice() + " " +
                        ordersTable.getSelectionModel().getSelectedItem().getStatus());
            } catch (NullPointerException e) {
                Message.msInfo("No select", "No select row.", "Please select a row.", 1500);
            }
        });
        usersUPDATEbutton.setOnAction(event -> {
            String errorMessange = "";
            try {
                if (!Verification.Login(usersTable.getSelectionModel().getSelectedItem().getId_user(), usersTable.getSelectionModel().getSelectedItem().getLogin())) {
                    errorMessange += "логин существует или он слишком большой \n";
                }
                if (!Verification.Password(usersTable.getSelectionModel().getSelectedItem().getPassword())) {
                    errorMessange += "слишком большой пароль \n";
                }
                if (!Verification.Role(usersTable.getSelectionModel().getSelectedItem().getRole())) {
                    errorMessange += "не правильная роль \n";
                }
                if (!Verification.DelStatus(usersTable.getSelectionModel().getSelectedItem().getDel_status())) {
                    errorMessange += "не правильная дел_статус \n";
                }

                if (!errorMessange.equals("")) {
                    throw new SQLException();
                }
                DaoUsers dao = new DaoUsers(Main.getDb());
                dao.update(new Users(
                        usersTable.getSelectionModel().getSelectedItem().getId_user(),
                        usersTable.getSelectionModel().getSelectedItem().getLogin(),
                        usersTable.getSelectionModel().getSelectedItem().getPassword(),
                        usersTable.getSelectionModel().getSelectedItem().getRole(),
                        usersTable.getSelectionModel().getSelectedItem().getDel_status()));

                showDataBase();
            } catch (ClassNotFoundException e) {
                System.out.println("Нет связи с сервером");
            } catch (SQLException e) {
                System.out.println("Неправильно введены данные");
                Message.msError("Error", "Incorrect data entered.",
                        "Check each column.\n" +
                                "Login: unique and less than 50 characters\n" +
                                "Password: 5 to 50 characters\n" +
                                "Role: admin or user\n" +
                                "Del status: OK or del\n" + errorMessange);
            } catch (NullPointerException e) {
                Message.msInfo("No select", "No select row.", "Please select a row.", 1500);
            }
        });
        usersInfoUPDATEbutton.setOnAction(event -> {
            try {
                DaoUsers_info dao = new DaoUsers_info(Main.getDb());
                dao.update(new Users_info(
                        usersInfoTable.getSelectionModel().getSelectedItem().getId_user_info(),
                        usersInfoTable.getSelectionModel().getSelectedItem().getId_user(),
                        usersInfoTable.getSelectionModel().getSelectedItem().getName(),
                        usersInfoTable.getSelectionModel().getSelectedItem().getSure_name(),
                        usersInfoTable.getSelectionModel().getSelectedItem().getNationality(),
                        usersInfoTable.getSelectionModel().getSelectedItem().getPhone(),
                        usersInfoTable.getSelectionModel().getSelectedItem().getE_mail(),
                        usersInfoTable.getSelectionModel().getSelectedItem().getNum_passport()));
                showDataBase();
            } catch (ClassNotFoundException e) {
                System.out.println("Нет связи с сервером");
            } catch (SQLException e) {
                System.out.println("Неправильно введены данные");
                System.out.println(
                        usersInfoTable.getSelectionModel().getSelectedItem().getId_user_info() + " " +
                                usersInfoTable.getSelectionModel().getSelectedItem().getId_user() + " " +
                                usersInfoTable.getSelectionModel().getSelectedItem().getName() + " " +
                                usersInfoTable.getSelectionModel().getSelectedItem().getSure_name() + " " +
                                usersInfoTable.getSelectionModel().getSelectedItem().getNationality() + " " +
                                usersInfoTable.getSelectionModel().getSelectedItem().getPhone() + " " +
                                usersInfoTable.getSelectionModel().getSelectedItem().getE_mail() + " " +
                                usersInfoTable.getSelectionModel().getSelectedItem().getNum_passport());
            } catch (NullPointerException e) {
                Message.msInfo("No select", "No select row.", "Please select a row.", 1500);
            }
        });
        roomsUPDATEbutton.setOnAction(event -> {
            try {
                DaoRooms dao = new DaoRooms(Main.getDb());
                dao.update(new Rooms(
                        roomsTable.getSelectionModel().getSelectedItem().getId_room(),
                        roomsTable.getSelectionModel().getSelectedItem().getNumber(),
                        roomsTable.getSelectionModel().getSelectedItem().getType(),
                        roomsTable.getSelectionModel().getSelectedItem().getCount_beds(),
                        roomsTable.getSelectionModel().getSelectedItem().getPrice(),
                        roomsTable.getSelectionModel().getSelectedItem().getStatus()));
                showDataBase();
            } catch (ClassNotFoundException e) {
                System.out.println("Нет связи с сервером");
            } catch (SQLException e) {
                System.out.println("Неправильно введены данные");
                System.out.println(
                        roomsTable.getSelectionModel().getSelectedItem().getId_room() + " " +
                                roomsTable.getSelectionModel().getSelectedItem().getNumber() + " " +
                                roomsTable.getSelectionModel().getSelectedItem().getType() + " " +
                                roomsTable.getSelectionModel().getSelectedItem().getCount_beds() + " " +
                                roomsTable.getSelectionModel().getSelectedItem().getPrice() + " " +
                                roomsTable.getSelectionModel().getSelectedItem().getStatus());
            } catch (NullPointerException e) {
                Message.msInfo("No select", "No select row.", "Please select a row.", 1500);
            }
        });
// !!!! button UPDATE !!!! finish
// !!!! button DELETE !!!! start
        ordersDELETEbutton.setOnAction(event -> {
            try {
                DaoOrders dao = new DaoOrders(Main.getDb());
                dao.delete(new Orders(ordersTable.getSelectionModel().getSelectedItem().getId_orders()));
                showDataBase();
            } catch (ClassNotFoundException e) {
                System.out.println("Нет связи с сервером");
            } catch (SQLException e) {
                System.out.println("Неправильно введены данные");
            } catch (NullPointerException e) {
                Message.msInfo("No select", "No select row.", "Please select a row.", 1500);
            }
        });
        usersDELETEbutton.setOnAction(event -> {
            try {
                if (usersTable.getSelectionModel().getSelectedItem().getLogin().equals("admin")) {
                    Message.msInfo("But not the administrator.", "It's the administrator. ", "You can not delete it.", 1500);
                } else {
                    try {
                        DaoUsers dao = new DaoUsers(Main.getDb());
                        dao.delete(new Users(usersTable.getSelectionModel().getSelectedItem().getId_user()));
                        showDataBase();
                    } catch (ClassNotFoundException e) {
                        System.out.println("Нет связи с сервером");
                    } catch (SQLException e) {
                        System.out.println("Неправильно введены данные");
                    }
                }
            } catch (NullPointerException e) {
                Message.msInfo("No select", "No select row.", "Please select a row.", 1500);
            }
        });
        roomsDELETEbutton.setOnAction(event -> {
            try {
                DaoRooms dao = new DaoRooms(Main.getDb());
                dao.delete(new Rooms(roomsTable.getSelectionModel().getSelectedItem().getId_room()));
                showDataBase();
            } catch (ClassNotFoundException e) {
                System.out.println("Нет связи с сервером");
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (NullPointerException e) {
                Message.msInfo("No select", "No select row.", "Please select a row.", 1500);
            }
        });
// !!!! button DELETE !!!! finish
// !!!! button LOGOUT !!!! start
        ordersLOGOUTbutton.setOnAction(event -> {
            System.out.println("Вернулись в LoginFrame");
            new NewScene(ordersLOGOUTbutton, "LoginFrame", false);
        });
        usersLOGOUTbutton.setOnAction(event -> {
            System.out.println("Вернулись в LoginFrame");
            new NewScene(usersLOGOUTbutton, "LoginFrame", false);
        });
        usersInfoLOGOUTbutton.setOnAction(event -> {
            System.out.println("Вернулись в LoginFrame");
            new NewScene(usersInfoLOGOUTbutton, "LoginFrame", false);
        });
        roomsLOGOUTbutton.setOnAction(event -> {
            System.out.println("Вернулись в LoginFrame");
            new NewScene(roomsLOGOUTbutton, "LoginFrame", false);
        });
// !!!! button LOGOUT !!!! finish
// !!!! button NEW !!!! start
        ordersNEWbutton.setOnAction(event -> {
            new NewScene(ordersNEWbutton, "NewOrderAdminFrame", false, false);
            try {
                showDataBase();
            } catch (ClassNotFoundException e) {
                System.out.println("Нет связи с сервером");
            } catch (SQLException e) {
                System.out.println("Ошибка SQLException");
            }
        });
        usersNEWbutton.setOnAction(event -> {
            new NewScene(usersNEWbutton, "NewUserAdminFrame", false, false);
            try {
                showDataBase();
            } catch (ClassNotFoundException e) {
                System.out.println("Нет связи с сервером");
            } catch (SQLException e) {
                System.out.println("Ошибка SQLException");
            }
        });
        roomsNEWbutton.setOnAction(event -> {
            new NewScene(roomsNEWbutton, "NewRoomAdminFrame", false, false);
            try {
                showDataBase();
            } catch (ClassNotFoundException e) {
                System.out.println("Нет связи с сервером");
            } catch (SQLException e) {
                System.out.println("Ошибка SQLException");
            }
        });
// !!!! button NEW !!!! finish

        usInfoTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showUserInfo(newValue));
    }

    private void showDataBase() throws ClassNotFoundException, SQLException {
        //!!!! ORDERS !!!!
        LinkedList<Orders> ordersRow = Orders.getList(Main.getDb().query("SELECT * FROM orders"));
        ObservableList<Orders> ordersOblist = FXCollections.observableArrayList();
        ObservableList<String> ordersIdUserCOMBO = FXCollections.observableArrayList();
        ObservableList<String> ordersNumberCOMBO = FXCollections.observableArrayList();
        ObservableList<String> ordersTypeCOMBO = FXCollections.observableArrayList();
        ObservableList<String> ordersStatusCOMBO = FXCollections.observableArrayList();
        ordersStatusCOMBO.addAll("paid", "busy", "new");
        ordersOblist.addAll(ordersRow);
        //!!!! USERS !!!!
        LinkedList<Users> usersRow = Users.getList(Main.getDb().query("SELECT * FROM users"));
        ObservableList<Users> usersOblist = FXCollections.observableArrayList();
        ObservableList<String> usersRoleCOMBO = FXCollections.observableArrayList();
        ObservableList<String> usersDelStatusCOMBO = FXCollections.observableArrayList();
        usersRoleCOMBO.addAll("admin", "user");
        usersDelStatusCOMBO.addAll("OK", "del");
        for (Users anUsersRow : usersRow) {
            usersOblist.add(anUsersRow);
            ordersIdUserCOMBO.add(anUsersRow.getId_user());
        }
        //!!!! USERS INFO !!!!
        LinkedList<Users_info> usersInfoRow = Users_info.getList(Main.getDb().query("SELECT * FROM users_info"));
        ObservableList<Users_info> usersInfoOblist = FXCollections.observableArrayList();
        usersInfoOblist.addAll(usersInfoRow);
        //!!!! ROOMS !!!!
        LinkedList<Rooms> roomsRow = Rooms.getList(Main.getDb().query("SELECT * FROM rooms"));
        ObservableList<Rooms> roomsOblist = FXCollections.observableArrayList();
        ObservableList<String> roomsStatusCOMBO = FXCollections.observableArrayList();
        roomsStatusCOMBO.addAll("free", "busy");
        for (Rooms anroomsRow : roomsRow) {
            roomsOblist.add(anroomsRow);
            ordersNumberCOMBO.add(anroomsRow.getNumber());
            if (!ordersTypeCOMBO.contains(anroomsRow.getType())) {
                ordersTypeCOMBO.add(anroomsRow.getType());
            }
        }

        //ordersIdOrderColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        ordersIdUserColumn.setCellFactory(ComboBoxTableCell.forTableColumn(ordersIdUserCOMBO));
        ordersNumber.setCellFactory(ComboBoxTableCell.forTableColumn(ordersNumberCOMBO));
        ordersCountBedsColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        ordersTypeColumn.setCellFactory(ComboBoxTableCell.forTableColumn(ordersTypeCOMBO));
        ordersCheckInColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        ordersCheckOutColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        ordersPriceColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        ordersStatusColumn.setCellFactory(ComboBoxTableCell.forTableColumn(ordersStatusCOMBO));

        ordersIdOrderColumn.setCellValueFactory(cellData -> cellData.getValue().id_ordersProperty());
        ordersIdUserColumn.setCellValueFactory(cellData -> cellData.getValue().id_userProperty());
        ordersNumber.setCellValueFactory(cellData -> cellData.getValue().numberProperty());
        ordersCountBedsColumn.setCellValueFactory(cellData -> cellData.getValue().count_bedsProperty());
        ordersTypeColumn.setCellValueFactory(cellData -> cellData.getValue().typeProperty());
        ordersCheckInColumn.setCellValueFactory(cellData -> cellData.getValue().check_inProperty());
        ordersCheckOutColumn.setCellValueFactory(cellData -> cellData.getValue().check_outProperty());
        ordersPriceColumn.setCellValueFactory(cellData -> cellData.getValue().priceProperty());
        ordersStatusColumn.setCellValueFactory(cellData -> cellData.getValue().statusProperty());
        //!!!!!!!!!!!!!!!!!
        //usersInfoIdUserInfoColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        //usersInfoIdUserColumn.setCellFactory(ComboBoxTableCell.forTableColumn(ordersIdUserCOMBO));
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
        //roomsIdRoomColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        roomsNumberColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        roomsTypeColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        roomsCountBedsColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        roomsPriceColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        roomsStatusColumn.setCellFactory(ComboBoxTableCell.forTableColumn(roomsStatusCOMBO));

        roomsIdRoomColumn.setCellValueFactory(cellData -> cellData.getValue().id_roomProperty());
        roomsNumberColumn.setCellValueFactory(cellData -> cellData.getValue().numberProperty());
        roomsTypeColumn.setCellValueFactory(cellData -> cellData.getValue().typeProperty());
        roomsCountBedsColumn.setCellValueFactory(cellData -> cellData.getValue().count_bedsProperty());
        roomsPriceColumn.setCellValueFactory(cellData -> cellData.getValue().priceProperty());
        roomsStatusColumn.setCellValueFactory(cellData -> cellData.getValue().statusProperty());
        //!!!!!!!!!!!!!!!!!
        //usersIdUserColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        usersLoginColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        usersPasswordColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        usersRoleColumn.setCellFactory(ComboBoxTableCell.forTableColumn(usersRoleCOMBO));
        usersDelStatusColumn.setCellFactory(ComboBoxTableCell.forTableColumn(usersDelStatusCOMBO));

        usersIdUserColumn.setCellValueFactory(cellData -> cellData.getValue().id_userProperty());
        usersLoginColumn.setCellValueFactory(cellData -> cellData.getValue().loginProperty());
        usersPasswordColumn.setCellValueFactory(cellData -> cellData.getValue().passwordProperty());
        usersRoleColumn.setCellValueFactory(cellData -> cellData.getValue().roleProperty());
        usersDelStatusColumn.setCellValueFactory(cellData -> cellData.getValue().del_statusProperty());
        //!!!!!!!!!!!!!!!!!

        usInfoIDUSERcolumn.setCellValueFactory(cellData -> cellData.getValue().id_userProperty());
        usInfoLogincolumn.setCellValueFactory(cellData -> cellData.getValue().loginProperty());
        usInfoPASScolumn.setCellValueFactory(cellData -> cellData.getValue().passwordProperty());
        usInfoROLEcolumn.setCellValueFactory(cellData -> cellData.getValue().roleProperty());
        usInfoDELSTATUScolumn.setCellValueFactory(cellData -> cellData.getValue().del_statusProperty());

        ordersTable.setItems(ordersOblist);
        usersTable.setItems(usersOblist);
        usersInfoTable.setItems(usersInfoOblist);
        roomsTable.setItems(roomsOblist);
        usInfoTable.setItems(usersOblist);

    }

    private void showUserInfo(Users users) {
        if (users != null) {
            ResultSet rsInfo = Main.getDb().query("SELECT * FROM users_info WHERE id_user = '" + users.getId_user() + "'");
            try {
                if (rsInfo.next()) {
                    /// Заполняем метки информацией.
                    usInfoNAMElable.setText(rsInfo.getString(3));
                    usInfoSURENAMElable.setText(rsInfo.getString(4));
                    usInfoNATIONALlable.setText(rsInfo.getString(5));
                    usInfoPHONElable.setText(rsInfo.getString(6));
                    usInfoEMAILlable.setText(rsInfo.getString(7));
                    usInfoNUMPASSPORTlable.setText(rsInfo.getString(8));
                }
            } catch (SQLException e) {
                System.out.println("Error in rsInfo.");
            }
        } else {
            usInfoNAMElable.setText("");
            usInfoSURENAMElable.setText("");
            usInfoNATIONALlable.setText("");
            usInfoPHONElable.setText("");
            usInfoEMAILlable.setText("");
            usInfoNUMPASSPORTlable.setText("");
        }
    }
}

