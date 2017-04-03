package com.belhard.hotel.frame;

import com.belhard.hotel.dao.DaoOrders;
import com.belhard.hotel.dao.DaoUsers;
import com.belhard.hotel.dao.DaoUsers_info;
import com.belhard.hotel.entity.Orders;
import com.belhard.hotel.entity.Users;
import com.belhard.hotel.entity.Users_info;
import com.belhard.hotel.util.Message;
import com.belhard.hotel.util.Verification;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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
public class NewUserAdminFrame implements Initializable {
    private Stage dialogStage;
    private boolean okClicked = false;
    @FXML
    private TextField NUAFidUserTextField, NUAFloginTextField,
            NUAFpasswordTextField,NUAFstatusTextField;
    @FXML
    private ComboBox<String> NUAFroleComboBox;
    @FXML
    Button NUAFokButton, NUAFcancelButton;
    private ObservableList<String> roleList = FXCollections.observableArrayList();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        roleList.addAll("user", "admin");
        NUAFokButton.setOnAction(event -> handleOk());
        NUAFcancelButton.setOnAction(event -> handleCancel());
        DaoUsers infoUser = new DaoUsers(Main.getDb());
        ResultSet rsOrder = infoUser.getAll();
        try {
            rsOrder.last();
            NUAFidUserTextField.setText(String.valueOf(Integer.valueOf(rsOrder.getString(1)) + 1));
        } catch (SQLException e) {
            System.out.println("ошибка при взятии номера последнего заказа");
        }
        NUAFroleComboBox.setItems(roleList);
    }
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
    @FXML
    private void handleOk() {
        if (isInputValid()) {
            DaoUsers dao = new DaoUsers(Main.getDb());
            Users user = new Users(
                    NUAFidUserTextField.getText(),
                    NUAFloginTextField.getText(),
                    NUAFpasswordTextField.getText(),
                    NUAFroleComboBox.getSelectionModel().getSelectedItem(),
                    NUAFstatusTextField.getText());
            dao.insert(user);

            DaoUsers_info daoUsers_info = new DaoUsers_info(Main.getDb());
            Users_info info = new Users_info(
                    NUAFidUserTextField.getText(),
                    NUAFidUserTextField.getText(),
                    "new",
                    "new",
                    "new",
                    String.valueOf(Integer.parseInt(NUAFidUserTextField.getText()) + 1),
                    String.valueOf(Integer.parseInt(NUAFidUserTextField.getText()) + 1),
                    String.valueOf(Integer.parseInt(NUAFidUserTextField.getText()) + 1)
            );
            daoUsers_info.insert(info);
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
        if (NUAFloginTextField.getText() == null ||
                NUAFloginTextField.getText().length() == 0 ||
                !Verification.Login(NUAFidUserTextField.getText(), NUAFloginTextField.getText())) {
            errorMessage += "Mistake in Login! Login exists! \n";
        }
        if (NUAFpasswordTextField.getText() == null ||
                NUAFpasswordTextField.getText().length() == 0 ||
                !Verification.Password(NUAFpasswordTextField.getText())) {
            errorMessage += "Mistake in Password! Password must be between 5 and 50 characters! \n";
        }
        if (NUAFroleComboBox.getSelectionModel().getSelectedItem() == null ||
                NUAFroleComboBox.getSelectionModel().getSelectedItem().length() == 0) {
            errorMessage += "Role not selected! \n";
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