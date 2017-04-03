package com.belhard.hotel.util;

import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.sql.SQLException;

/**
 * Created by Darth Vader on 16.03.2017.
 */
public class Message {
    public static void msInfo(String Titel, String HeaderText, String ContentText, int milliseconds) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(Titel);
        alert.setHeaderText(HeaderText);
        alert.setContentText(ContentText);
        alert.show();
        closeAlert(alert, milliseconds);
    }

    public static void msInfo(String Titel, String HeaderText, String ContentText) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(Titel);
        alert.setHeaderText(HeaderText);
        alert.setContentText(ContentText);
        alert.showAndWait();
    }

    public static void msError(String titel, String HeaderText, String ContentText) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titel);
        alert.setHeaderText(HeaderText);
        alert.setContentText(ContentText);
        alert.showAndWait();
    }

    private static void closeAlert(Alert alert, int milliseconds) {
        if (alert.isShowing()) {
            try {
                Thread.sleep(milliseconds);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                alert.close();
            }
        }
    }

    public static void checkLoginPassword(TextField enterLogin, PasswordField enterPassword) {
        if (enterLogin.getText().equals("")) {
            msError("Error", "Mistake in login.", "Enter login.");
        } else if (enterPassword.getText().equals("")) {
            msError("Error", "Mistake in password.", "Enter password.");
        } else if (!enterLogin.getText().equals("") && !enterPassword.getText().equals("")) {
            msError("Error", "Mistake in login or password.", "Check login and password.");
        }
    }

    public static void checkUrlNamedbLoginPassword(TextField enterURL, TextField enterNameDB, TextField enterLogin, PasswordField enterPassword) {
        if (enterURL.getText().equals("")) {
            msError("Error", "Mistake in URL.", "Check you URL.");
        } else if (enterNameDB.getText().equals("")) {
            msError("Error", "Mistake in nameDB.", "Check nameDB.");
        } else if (enterLogin.getText().equals("")) {
            msError("Error", "Mistake in login.", "Check login.");
        } else if (enterPassword.getText().equals("")) {
            msError("Error", "Mistake in password.", "Check password.");
        } else if (!enterLogin.getText().equals("") && !enterPassword.getText().equals("")) {
            msError("Error", "Mistake in URL, nameDB, login or password." ,"Check URL, nameDB, login and password.");
        }
    }

    public static void checkSQLState(SQLException e, TextField enterURL, TextField enterNameDB, TextField enterLogin, PasswordField enterPassword) {
        if (e.getSQLState().equals("08S01")) {
            Message.msError("Error", "Mistake in server MySQL.", "Turn on the server MySQL.");
        }
        if (e.getSQLState().equals("08001")) {
            Message.msError("Error", "Mistake in URL.","Check URL.");
        }
        if (e.getSQLState().equals("42000") || e.getSQLState().equals("28000")) {
            Message.checkUrlNamedbLoginPassword(enterURL, enterNameDB, enterLogin, enterPassword);
        }
        if (e.getSQLState().equals("HY000")) {
            Message.msError("Error", "Mistake in nameDB.", "Database with this name \"" + enterNameDB.getText() + "\" is created.");
        }
    }

    public static void serverError() {
        Alert warningWindow = new Alert(Alert.AlertType.WARNING);
        warningWindow.setTitle("Предупреждение");
        warningWindow.setHeaderText("Нет связи с сервером");
        warningWindow.setContentText("Проверьте наличие соединения и драйвера");
        warningWindow.showAndWait();
    }
}
