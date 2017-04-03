package com.belhard.hotel.frame;

import com.belhard.hotel.dao.DaoUsers_info;
import com.belhard.hotel.entity.Users_info;
import com.belhard.hotel.util.Message;
import com.belhard.hotel.util.NewScene;
import com.belhard.hotel.dao.DaoUsers;
import com.belhard.hotel.entity.Users;
import com.belhard.hotel.util.Verification;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class RegFrame implements Initializable {

    @FXML
    private TextField enterLogin;

    @FXML
    private PasswordField enterPass01;

    @FXML
    private PasswordField enterPass02;

    @FXML
    private Button buttonBack;

    @FXML
    private Button buttonOK;
    @FXML
    public void initialize(URL location, ResourceBundle resources) {
        buttonBack.setOnAction((event ->
                new NewScene(buttonBack, "LoginFrame", false)));
        buttonOK.setOnAction((event ->
                handleOk(enterLogin, enterPass01, enterPass02, buttonOK)));
        enterLogin.setOnAction(event ->
                handleOk(enterLogin, enterPass01, enterPass02, buttonOK));
        enterPass01.setOnAction(event ->
                handleOk(enterLogin, enterPass01, enterPass02, buttonOK));
        enterPass02.setOnAction(event ->
                handleOk(enterLogin, enterPass01, enterPass02, buttonOK));
    }

    private void handleOk(TextField Login, PasswordField Pass01, PasswordField Pass02,
                                       Button button) {
        if (isInputValid()){
        ResultSet rsInfo = Main.getDb().query("SELECT * FROM users ");
        try {
            rsInfo.last();
            Users user = new Users(String.valueOf(Integer.parseInt(rsInfo.getString(1)) + 1), enterLogin.getText(), enterPass01.getText(), "user", "OK");
            DaoUsers du = new DaoUsers(Main.getDb());
            du.insert(user);
            Users_info users_info = new Users_info(String.valueOf(Integer.parseInt(rsInfo.getString(1)) + 1),
                    String.valueOf(Integer.parseInt(rsInfo.getString(1)) + 1),
                    null,
                    null,
                    null,
                    null,
                    null,
                    null);
            DaoUsers_info dui = new DaoUsers_info(Main.getDb());
            dui.insert(users_info);
            Message.msInfo("Excellent","Congratulations!", "You are registered in program.", 2500);
            new NewScene(buttonOK, "UserFrameNew", false);
        } catch (SQLException e) {
            e.printStackTrace();
        }}
    }

    private boolean isInputValid() {
        String errorMessage = "";
        if (enterLogin.getText() == null ||
                enterLogin.getText().length() == 0 ||
                !Verification.Login(enterLogin.getText(), enterLogin.getText())) {
            errorMessage += "Mistake in Login! Login exists! \n";
        }
        if (enterPass01.getText() == null ||
                enterPass01.getText().length() == 0 ||
                !Verification.Password(enterPass01.getText())) {
            errorMessage += "Mistake in Password! Password must be between 5 and 50 characters! \n";
        }
        if (!enterPass01.getText().equals(enterPass02.getText())){
            errorMessage += "Mistake in Password! Passwords do not match.! \n";
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
