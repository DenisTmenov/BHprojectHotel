package com.belhard.hotel.frame;

import com.belhard.hotel.dao.DaoOrders;
import com.belhard.hotel.dao.DaoRooms;
import com.belhard.hotel.entity.Orders;
import com.belhard.hotel.entity.Rooms;
import com.belhard.hotel.util.Message;
import com.belhard.hotel.util.Verification;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;

/**
 * Created by Darth Vader on 30.03.2017.
 */
public class NewRoomAdminFrame implements Initializable {
    private Stage dialogStage;
    private boolean okClicked = false;
    @FXML
    Button NRAFokButton, NRAFcancelButton;
    @FXML
    private TextField
            NRAFidRoomTextField, NRAFnumberTextField, NRAFtypeTextField,
            NRAFicountBedsTextField, NRAFpriceTextField, NRAFstatusTextField;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        NRAFokButton.setOnAction(event -> handleOk());
        NRAFcancelButton.setOnAction(event -> handleCancel());
        DaoRooms infoRooms = new DaoRooms(Main.getDb());
        ResultSet rsRooms = infoRooms.getAll();
        try {
            rsRooms.last();
            NRAFidRoomTextField.setText(String.valueOf(Integer.valueOf(rsRooms.getString(1)) + 1 ));
        } catch (SQLException e) {
            System.out.println("ошибка при взятии id номера последней комнаты.");
        }

    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    @FXML
    private void handleOk() {
        if (isInputValid()) {
            DaoRooms dao = new DaoRooms(Main.getDb());
            Rooms rooms = new Rooms(
                    NRAFidRoomTextField.getText(),
                    NRAFnumberTextField.getText(),
                    NRAFtypeTextField.getText(),
                    NRAFicountBedsTextField.getText(),
                    NRAFpriceTextField.getText(),
                    NRAFstatusTextField.getText());
            dao.insert(rooms);

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
        if (NRAFnumberTextField.getText() == null ||
                NRAFnumberTextField.getText().length() == 0 ||
                Verification.NumberInRoom(NRAFnumberTextField.getText())) {
            errorMessage += "Mistake in Number! \n";
        }
        if (NRAFtypeTextField.getText() == null ||
                NRAFtypeTextField.getText().length() == 0 ) {
            errorMessage += "Mistake in Type! \n";
        }
        if (NRAFicountBedsTextField.getText() == null ||
                NRAFicountBedsTextField.getText().length() == 0 ||
                !Verification.IsItNumber(NRAFicountBedsTextField.getText()) ||
                Integer.valueOf(NRAFicountBedsTextField.getText()) <= 0 ||
                Integer.valueOf(NRAFicountBedsTextField.getText()) > 4) {
            errorMessage += "Mistake in Count beds! ( 1 - 4 ) \n";
        }
        if (NRAFpriceTextField.getText() == null ||
                NRAFpriceTextField.getText().length() == 0 ||
                !Verification.IsItNumber(NRAFpriceTextField.getText()) ||
                Integer.valueOf(NRAFpriceTextField.getText()) <= 0) {
            errorMessage += "Mistake in Number! \n";
        }
        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Показываем сообщение об ошибке.
            Message.msError("Invalid Fields", "Please correct invalid fields", errorMessage);
            return false;
        }
    }
}