/**
 * Sample Skeleton for 'UserFrame.fxml' Controller Class
 */

package com.belhard.hotel.frame;

import com.belhard.hotel.util.Message;
import com.belhard.hotel.util.NewScene;
import com.belhard.hotel.dao.DaoOrders;
import com.belhard.hotel.dao.DaoRooms;
import com.belhard.hotel.entity.Orders;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserFrame {

    @FXML
    private TextField NOcontBeds;

    @FXML
    private ComboBox<String> NOtype;

    @FXML
    private DatePicker NOcheck_in, NOcheck_out;

    @FXML
    private Button NObuttonLog_out, NObuttonOK, NObuttonCancel;

    @FXML
    private TableView<Orders> tableOrderUser;

    @FXML
    private TableColumn<Orders, String> idColumn;

    @FXML
    private TableColumn<Orders, String> idUserColumn;

    @FXML
    private TableColumn<Orders, String> numberColumn;

    @FXML
    private TableColumn<Orders, String> bedsColumn;

    @FXML
    private TableColumn<Orders, String> typeColumn;

    @FXML
    private TableColumn<Orders, String> check_inColumn;

    @FXML
    private TableColumn<Orders, String> check_outColumn;

    @FXML
    private TableColumn<Orders, String> priceColumn;

    @FXML
    private TableColumn<Orders, String> statusColumn;

    @FXML
    private Button CObuttonLog_out;

    @FXML
    private Button CObuttonOK;

    @FXML
    private Button CObuttonCancel;
    private ObservableList<String> listRooms = FXCollections.observableArrayList();
    private ObservableList<Orders> listOrder = FXCollections.observableArrayList();

    public UserFrame() {
        DaoRooms infoRooms = new DaoRooms(Main.getDb());
        ResultSet rs = infoRooms.getAll();
        try {
            while (rs.next()) {
                if (!listRooms.contains(rs.getString(3))) {
                    listRooms.add(rs.getString(3));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DaoOrders infoOrders = new DaoOrders(Main.getDb());
        ResultSet rsOrders = infoOrders.getAlluser();
        try {
            while (rsOrders.next()) {
                listOrder.add(new Orders(
                        rsOrders.getString(1),
                        //Integer.parseInt(rsOrders.getString(2)),
                        //Integer.parseInt(rsOrders.getString(3)),
                        rsOrders.getString(4),
                        rsOrders.getString(5),
                        rsOrders.getString(6),
                        rsOrders.getString(7),
                        //Integer.parseInt(rsOrders.getString(8)),
                        rsOrders.getString(9)
                ));
                //System.out.println(Integer.parseInt(rsOrders.getString(1)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        for (Orders aListOrder : listOrder) {
            System.out.println(aListOrder);
        }
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    public void initialize() {
        // Handle Button event.
        NObuttonLog_out.setOnAction(event -> {
            System.out.println("Вернулись в LoginFrame");
            new NewScene(NObuttonLog_out, "LoginFrame", false);
        });
        // Handle Button event.
        CObuttonLog_out.setOnAction(event -> {
            System.out.println("Вернулись в LoginFrame");
            new NewScene(CObuttonLog_out, "LoginFrame", false);
        });
        // Handle TextField event.
        NOcontBeds.textProperty().addListener((observable, oldValue, newValue) -> {
            // Observer (Наблюдатель) Реализация данного паттерна используется для наблюдения за состоянием объектов в системе.
            // Если состояние объектов изменяется в процессе их жизненного цикла, то Наблюдатель оповещает другие части системы об этих событиях.
            Pattern pt = Pattern.compile("\\d*"); // Numeric character
            Matcher mcNEW = pt.matcher(newValue);
            if (newValue.length() > 0) {
                if (!mcNEW.matches() || Integer.parseInt(newValue) > 10 || Integer.parseInt(newValue) == 0) {
                    NOcontBeds.setText(oldValue);
                }
            }
        });
        // Init ComboBox items.
        NOtype.setItems(listRooms);

        NObuttonCancel.setOnAction(event -> {
            NOcontBeds.setText("");
            NOtype.getSelectionModel().clearSelection();
            NOcheck_in.getEditor().clear();
            NOcheck_out.getEditor().clear();
        });
        NObuttonOK.setOnAction((ActionEvent event) -> {
            boolean en = true;

            if (NOcontBeds.getText().length() == 0) {
                en = false;
                Message.msInfo("Message","Mistake in beds.", "Enter counts of beds.", 1500);
            }
            if (NOtype.getSelectionModel().isEmpty()) {
                en = false;
                Message.msInfo("Message", "Mistake in type of room.","Enter type of room.", 1500);
            }
            if (NOcheck_in.getEditor().getLength() == 0) {
                en = false;
                Message.msInfo("Message", "Mistake in check in.","Enter date of check in.", 1500);
            }
            if (NOcheck_out.getEditor().getLength() == 0) {
                en = false;
                Message.msInfo("Message", "Mistake in check out.","Enter date of check out.", 1500);
            }
            if (NOcheck_in.getEditor().getLength() > 0 && NOcheck_out.getEditor().getLength() > 0) {
                System.out.println(NOcheck_in.getEditor().getText());
                System.out.println(NOcheck_out.getEditor().getText());
                DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
                try {
                    Date check_in_date = df.parse(NOcheck_in.getEditor().getText());
                    Date check_out_date = df.parse(NOcheck_out.getEditor().getText());
                    System.out.println(check_in_date);
                    System.out.println(check_in_date.getTime());
                    if (check_out_date.getTime() - check_in_date.getTime() < 0) {
                        en = false;
                        System.out.println(check_out_date.getTime() - check_in_date.getTime());
                        Message.msInfo("Message","Mistake in date.", "You are make mistake in date of check in and check out.", 1500);
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            if (en) {
                Orders order = null;
                try {
                    order = new Orders(
                            LoginFrame.getRsInfo().getString(1),
                            NOcontBeds.getText(),
                            NOtype.getSelectionModel().getSelectedItem(),
                            NOcheck_in.getEditor().getText(),
                            NOcheck_out.getEditor().getText());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                DaoOrders daoOrder = new DaoOrders(Main.getDb());
                daoOrder.insert_user(order);
                Message.msInfo("Success!","Thank you!", "Your order is accepted. \n" +
                        "In the near future our employee will process it. \n" +
                        "Information about the order can be viewed in CHECK ORDER.");
                // Your order is accepted. In the near future our employee will process it. Information about the order can be viewed in
            }
        });

        NOtype.setItems(listRooms);

        idColumn.setCellValueFactory(new PropertyValueFactory<>("idColumn"));
        idUserColumn.setCellValueFactory(new PropertyValueFactory<>("idUserColumn"));
        numberColumn.setCellValueFactory(new PropertyValueFactory<>("numberColumn"));
        bedsColumn.setCellValueFactory(new PropertyValueFactory<>("bedsColumn"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("typeColumn"));
        check_inColumn.setCellValueFactory(new PropertyValueFactory<>("check_inColumn"));
        check_outColumn.setCellValueFactory(new PropertyValueFactory<>("check_outColumn"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("priceColumn"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("statusColumn"));

        tableOrderUser.setItems(listOrder);
        //tableOrderUser.getColumns().addAll();

        System.out.println(LoginFrame.getNameLogin()); // имя (login) пользователя запустившего фрэйм
    }
}
