package com.belhard.hotel.frame;

import com.belhard.hotel.util.Message;
import com.belhard.hotel.util.NewScene;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LoginFrame implements Initializable {
    private static ResultSet rsInfo;
    @FXML
    private TextField enterLogin;
    @FXML
    private PasswordField enterPassword;
    @FXML
    private Button buttonEnter;
    @FXML
    private Button buttonRegistration;

    public static ResultSet getRsInfo() {
        return rsInfo;
    }

    private void setRsInfo(ResultSet rsInf) {
        rsInfo = rsInf;
    }

    private void actionButtonEnter(TextField Login, PasswordField Password, Button button) {
        setRsInfo(Main.getDb().query("SELECT * FROM users WHERE login = '" + Login.getText() + "'"));
        try {
            if (rsInfo.next()) {
                if (Login.getText().equals(rsInfo.getString(2))) {
                    if (Password.getText().equals(rsInfo.getString(3))) {
                        if (rsInfo.getString(5).equals("OK")) {
                            if (rsInfo.getString(4).equals("admin")) {
                                new NewScene(button, "AdminFrameNew", true);
                            } else if (rsInfo.getString(4).equals("user")) {
                                new NewScene(button, "UserFrameNew", false);
                            } else {
                                System.out.println("ошибка в роли");
                                Message.msError("Error.","Mistake.", "Mistake in ROLE.");
                            }
                        } else {
                            System.out.println("пользователь заблокирован");
                            Message.msInfo("Information.","Locked.", Login.getText() + " is locked.", 1500);
                        }

                    } else {
                        System.out.println("неверны пароль");
                        Message.msInfo("Mistake","Mistake in login or password.", "Check your LOGIN and PASSWORD.", 1500);
                    }
                } else {
                    System.out.println("неверный логин");
                    Message.msInfo("Mistake", "Mistake in login or password.", "Check your LOGIN and PASSWORD.", 1500);
                }
            } else {
                System.out.println("не введен логин");
                Message.msInfo("Mistake", "Mistake in login.", "Enter your LOGIN.", 1500);
            }
        } catch (SQLException e) {
            System.out.println("Error in rsInfo.");
        }
    }

    @FXML
    // This method is called by the FXMLLoader when initialization is complete
    @Override public void initialize(URL location, ResourceBundle resources) {
        buttonEnter.setOnAction((event -> actionButtonEnter(enterLogin, enterPassword, buttonEnter)));
        buttonRegistration.setOnAction((event -> new NewScene(buttonRegistration, "RegFrame", false)));
        enterLogin.setOnAction(event -> actionButtonEnter(enterLogin, enterPassword, buttonEnter));
        enterPassword.setOnAction(event -> actionButtonEnter(enterLogin, enterPassword, buttonEnter));
    }
}
