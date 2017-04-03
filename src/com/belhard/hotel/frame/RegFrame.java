package com.belhard.hotel.frame;

import com.belhard.hotel.util.Message;
import com.belhard.hotel.util.NewScene;
import com.belhard.hotel.dao.DaoUsers;
import com.belhard.hotel.db.DB;
import com.belhard.hotel.entity.Users;
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

    @FXML // fx:id="enterLogin"
    private TextField enterLogin; // Value injected by FXMLLoader

    @FXML // fx:id="enterPass01"
    private PasswordField enterPass01; // Value injected by FXMLLoader

    @FXML // fx:id="enterPass02"
    private PasswordField enterPass02; // Value injected by FXMLLoader

    @FXML // fx:id="buttonBack"
    private Button buttonBack; // Value injected by FXMLLoader

    @FXML // fx:id="buttonOK"
    private Button buttonOK; // Value injected by FXMLLoader

    private void actionButtonOK(TextField Login, PasswordField Pass01, PasswordField Pass02,
                                       Button button) {
        boolean boolPass01, boolPass02;
        if (Login.getText().length() > 0) {
            if (Login.getText().length() <= 50) {
                ArrayList<String> loginsFromDB = new ArrayList<>();
                ResultSet resultLoginFromDB = Main.getDb().query("SELECT login FROM users");
                try {
                    while (resultLoginFromDB.next()) {
                        loginsFromDB.add(resultLoginFromDB.getString(1));
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                if (loginsFromDB.contains(Login.getText())) {
                    Message.msInfo("Message", "Mistake in login.","This login is already registered. Enter a different login.", 2000);
                } else {
                    boolPass01 = Pass01.getText().length() >= 1;
                    boolPass02 = Pass02.getText().length() >= 1;
                    if (boolPass01 && boolPass02) {
                        if (Pass01.getText().length() <= 50 && Pass02.getText().length() <= 50) {
                            if (Pass01.getText().equals(Pass02.getText())) {
                                ResultSet rsInfo = Main.getDb().query("SELECT * FROM users ");
                                try {
                                    rsInfo.last();
                                Users user = new Users(rsInfo.getString(1), enterLogin.getText(), enterPass01.getText(), "user", "OK");
                                DaoUsers du = new DaoUsers(Main.getDb());
                                du.insert(user);
                                Message.msInfo("Excellent","Congratulations!", "You are registered in program.", 2500);
                                new NewScene(button, "UserFrame", false);
                                } catch (SQLException e) {
                                    e.printStackTrace();
                                }
                            } else {
                                System.out.println("Пароли не совпадают.");
                                Message.msError("Error", "Mistake in password.","Your passwords don't match.");
                            }
                        } else {
                            System.out.println("пароль слишком большой.");
                            Message.msInfo("Message", "Large password.","Enter smaller Password.", 1500);
                        }
                    } else {
                        System.out.println("Введите PASSWORD");
                        Message.msInfo("Message", "Mistake in password.","Enter your Password.", 1500);
                    }
                }
            } else {
                System.out.println("Слишком большой логин.");
                Message.msInfo("Message", "Large login.","Enter smaller Login.", 1500);
            }
        } else {
            Message.msInfo("Message","Mistake in login.", "Enter your Login", 1500);
        }
    }

    @FXML
    // This method is called by the FXMLLoader when initialization is complete
    public void initialize(URL location, ResourceBundle resources) {
        buttonBack.setOnAction((event ->
            new NewScene(buttonBack, "LoginFrame", false)));
        buttonOK.setOnAction((event ->
            actionButtonOK(enterLogin, enterPass01, enterPass02, buttonOK)));
        enterLogin.setOnAction(event ->
            actionButtonOK(enterLogin, enterPass01, enterPass02, buttonOK));
        enterPass01.setOnAction(event ->
            actionButtonOK(enterLogin, enterPass01, enterPass02, buttonOK));
        enterPass02.setOnAction(event ->
            actionButtonOK(enterLogin, enterPass01, enterPass02, buttonOK));
    }
}
