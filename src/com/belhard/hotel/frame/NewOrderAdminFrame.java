package com.belhard.hotel.frame;

import com.belhard.hotel.dao.DaoOrders;
import com.belhard.hotel.dao.DaoRooms;
import com.belhard.hotel.dao.DaoUsers;
import com.belhard.hotel.entity.Orders;
import com.belhard.hotel.util.Message;
import com.belhard.hotel.util.Verification;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;

/**
 * Created by Darth Vader on 30.03.2017.
 */
public class NewOrderAdminFrame implements Initializable {
    protected String price = null;
    protected int dayCount = 1;
    private Stage dialogStage;
    private boolean okClicked = false;
    @FXML
    Button NOAFokBUTTON, NOAFcancelBUTTON;
    @FXML
    DatePicker NOAFcheckInDATE, NOAFcheckOutDATE;

    @FXML
    private TextField
            NOAFidOrderTEXTFIELD, NOAFcountBedsTEXTFIELD,
            NOAFtypeTEXTFIELD, NOAFpriceTEXTFIELD;
    @FXML
    private ComboBox<String> NOAFidUserCOMBObox, NOAFnumberCOMBOBOX, NOAFstatusCOMBOBOX;
    private ObservableList<String> idUserList = FXCollections.observableArrayList();
    private ObservableList<String> numberList = FXCollections.observableArrayList();
    private ObservableList<String> statusList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        statusList.addAll("paid", "busy", "new", "del");
        NOAFokBUTTON.setOnAction(event -> handleOk());
        NOAFcancelBUTTON.setOnAction(event -> handleCancel());
        DaoUsers infoUser = new DaoUsers(Main.getDb());
        ResultSet rsUser = infoUser.getAll();
        try {
            while (rsUser.next()) {
                if (!idUserList.contains(rsUser.getString(1))) {
                    idUserList.add(rsUser.getString(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DaoRooms infoRoom = new DaoRooms(Main.getDb());
        ResultSet rsRoom = infoRoom.getAll();
        try {
            while (rsRoom.next()) {
                if (!numberList.contains(rsRoom.getString(2))) {
                    numberList.add(rsRoom.getString(2));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DaoOrders infoOrder = new DaoOrders(Main.getDb());
        ResultSet rsOrder = infoOrder.getAll();
        try {
            rsOrder.last();
            NOAFidOrderTEXTFIELD.setText(String.valueOf(Integer.valueOf(rsOrder.getString(1)) + 1));
        } catch (SQLException e) {
            System.out.println("ошибка при взятии номера последнего заказа");
        }


        NOAFidUserCOMBObox.setItems(idUserList);
        NOAFnumberCOMBOBOX.setItems(numberList);
        NOAFstatusCOMBOBOX.setItems(statusList);

        NOAFnumberCOMBOBOX.setOnAction(event -> {
            if (NOAFnumberCOMBOBOX.isShowing()) {
                String type = null, countBeds = null;
                try {
                    rsRoom.first();
                    while (rsRoom.next()) {
                        if (NOAFnumberCOMBOBOX.getSelectionModel().getSelectedItem().equals(rsRoom.getString(2))) {
                            type = rsRoom.getString(3);
                            countBeds = rsRoom.getString(4);
                            price = rsRoom.getString(5);
                            break;
                        }
                    }
                    NOAFtypeTEXTFIELD.setText(type);
                    NOAFcountBedsTEXTFIELD.setText(countBeds);
                    NOAFpriceTEXTFIELD.setText(price);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    @FXML
    private void handleOk() {
        if (isInputValid()) {
            DaoOrders dao = new DaoOrders(Main.getDb());
            Orders order = new Orders(
                    NOAFidOrderTEXTFIELD.getText(),
                    NOAFidUserCOMBObox.getSelectionModel().getSelectedItem(),
                    NOAFnumberCOMBOBOX.getSelectionModel().getSelectedItem(),
                    NOAFcountBedsTEXTFIELD.getText(),
                    NOAFtypeTEXTFIELD.getText(),
                    NOAFcheckInDATE.getEditor().getText(),
                    NOAFcheckOutDATE.getEditor().getText(),
                    NOAFpriceTEXTFIELD.getText(),
                    NOAFstatusCOMBOBOX.getSelectionModel().getSelectedItem());
            dao.insert(order);

            okClicked = true;
            dialogStage.close();
        }
    }

    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    private boolean isInputValid() {
        String errorMessage = "";
        boolean dateENTER = true;
        //Date check_in = null, check_out = null;
        int checkInDAY = 0, checkOutDAY = 0;

        DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        if (NOAFidOrderTEXTFIELD.getText() == null ||
                NOAFidOrderTEXTFIELD.getText().length() == 0 ||
                Verification.IdOrder(NOAFidOrderTEXTFIELD.getText())) {
            errorMessage += "Mistake in IdOrder! \n";
        }
        if (NOAFidUserCOMBObox.getSelectionModel().getSelectedItem() == null ||
                NOAFidUserCOMBObox.getSelectionModel().getSelectedItem().length() == 0) {
            errorMessage += "IdUser not selected! \n";
        }
        if (NOAFnumberCOMBOBOX.getSelectionModel().getSelectedItem() == null ||
                NOAFnumberCOMBOBOX.getSelectionModel().getSelectedItem().length() == 0) {
            errorMessage += "Number not selected! \n";
        }
        if (NOAFcheckInDATE.getEditor().getText() == null ||
                NOAFcheckInDATE.getEditor().getText().length() == 0) {
            errorMessage += "Check in not selected! \n";
            dateENTER = false;
        } /*else {
            try {
                check_in = df.parse(NOAFcheckInDATE.getEditor().getText());

            } catch (ParseException e) {
                System.out.println("проблема в Check In");
            }
        }*/
        if (NOAFcheckOutDATE.getEditor().getText() == null ||
                NOAFcheckOutDATE.getEditor().getText().length() == 0) {
            errorMessage += "Check out not selected! \n";
            dateENTER = false;
        } /*else {
            try {
                check_out = df.parse(NOAFcheckOutDATE.getEditor().getText());

            } catch (ParseException e) {
                System.out.println("проблема в Check Out");
            }
        }*/
        if (NOAFstatusCOMBOBOX.getSelectionModel().getSelectedItem() == null ||
                NOAFstatusCOMBOBOX.getSelectionModel().getSelectedItem().length() == 0) {
            errorMessage += "Status not selected! \n";
        }

        //assert check_out != null;
        //assert check_in != null;

        /*Assert — это специальная конструкция, позволяющая проверять предположения
         о значениях произвольных данных в произвольном месте программы.
         Эта конструкция может автоматически сигнализировать при обнаружении некорректных данных,
         что обычно приводит к аварийному завершению программы с указанием места обнаружения некорректных данных.*/
        if (dateENTER) {
            try {
                if (df.parse(NOAFcheckOutDATE.getEditor().getText()).getTime() - df.parse(NOAFcheckInDATE.getEditor().getText()).getTime() <= 0) {
                    errorMessage += "Mistake in DATE! Check in should be less check out! \n";
                } else {
                    checkInDAY = (int) (df.parse(NOAFcheckInDATE.getEditor().getText()).getTime() / 1000 / 60 / 60 / 24);
                    checkOutDAY = (int) (df.parse(NOAFcheckOutDATE.getEditor().getText()).getTime() / 1000 / 60 / 60 / 24);
                    dayCount = checkOutDAY - checkInDAY;
                    NOAFpriceTEXTFIELD.setText(String.valueOf(Integer.parseInt(price) * dayCount));
                }

            } catch (NullPointerException e) {
                Message.msInfo("NullPointerException",
                        "The date of check in and check out is not selected.!",
                        "Please, selected date of  check in and check out!");
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        System.out.println(checkOutDAY - checkInDAY);

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Показываем сообщение об ошибке.
            Message.msError("Invalid Fields", "Please correct invalid fields", errorMessage);
            return false;
        }
    }


}

