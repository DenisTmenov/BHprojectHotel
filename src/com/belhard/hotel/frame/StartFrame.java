package com.belhard.hotel.frame;

import com.belhard.hotel.util.Message;
import com.belhard.hotel.util.NewScene;
import com.belhard.hotel.db.DB;
import com.belhard.hotel.db.WorkDB;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.sql.SQLException;

public class StartFrame {
    @FXML
    private TextField enterURL, enterLogin, enterNameDB;
    @FXML
    private PasswordField enterPassword;
    @FXML
    private Button buttonCreate, buttonDelete, buttonConnect;
    /**
     * The constructor (is called before the initialize()-method).
     */
    public StartFrame() {
    }

    private static void actionButtonConnect(TextField URL, TextField NameDB, TextField Login, PasswordField Password,
                                            Button button) {
        try {
            DB db = new DB(URL.getText(), NameDB.getText(), Login.getText(), Password.getText());
            db.update("USE "+ NameDB.getText());
            Main.setDb(db);
            DB.showTable(db.query("SELECT * FROM users"));
            System.out.println();
            DB.showTable(db.query("SELECT * FROM users_info"));
            System.out.println();
            DB.showTable(db.query("SELECT * FROM rooms"));
            System.out.println();
            DB.showTable(db.query("SELECT * FROM orders"));
            System.out.println("\nПодключение к базе данных прошло успешно.");
            new NewScene(button, "LoginFrame", false);
        } catch (ClassNotFoundException e) {
            System.out.println("Error in Connect");
            Message.serverError();
        }catch (SQLException e) {
            System.out.println("Error in Connect");
            Message.checkSQLState(e, URL, NameDB, Login, Password);}
    }

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    public void initialize() {
        // Handle Button event.

        buttonCreate.setOnAction((event) -> {
            System.out.println("Enter button Create \n");
            try {
                WorkDB.createDB(enterURL.getText(), enterNameDB.getText(), enterLogin.getText(), enterPassword.getText());
                Message.msInfo("Information.","Success!", "Database was created successful!", 1500);
            } catch (ClassNotFoundException e) {
                System.out.println("Error in Create");
                Message.serverError();
            } catch (SQLException e) {
                System.out.println("Error in Create");
                Message.checkSQLState(e, enterURL, enterNameDB, enterLogin, enterPassword);
            }
        });
        buttonDelete.setOnAction((event) -> {
            try {
                WorkDB.deleteDB(enterURL.getText(), enterNameDB.getText(), enterLogin.getText(), enterPassword.getText());
                Message.msInfo("Information.","Success!", "Database was deleted successful!", 1500);
            } catch (ClassNotFoundException e) {
                System.out.println("Error in Delete");
                Message.serverError();
            } catch (SQLException e) {
                System.out.println("Error in Delete");
                Message.checkSQLState(e, enterURL, enterNameDB, enterLogin, enterPassword);
            }
        });
        buttonConnect.setOnAction((event) ->
                actionButtonConnect(enterURL, enterNameDB, enterLogin, enterPassword, buttonConnect));

        enterURL.setOnAction((event -> {
            System.out.println("Action URL");
            actionButtonConnect(enterURL, enterNameDB, enterLogin, enterPassword, buttonConnect);
        }));
        enterLogin.setOnAction(event -> {
            System.out.println("Action Login");
            actionButtonConnect(enterURL, enterNameDB, enterLogin, enterPassword, buttonConnect);
        });
        enterNameDB.setOnAction(event -> {
            System.out.println("Enter NameDB");
            actionButtonConnect(enterURL, enterNameDB, enterLogin, enterPassword, buttonConnect);
        });
        enterPassword.setOnAction(event -> {
            System.out.println("Enter Password");
            actionButtonConnect(enterURL, enterNameDB, enterLogin, enterPassword, buttonConnect);
        });
    }

}

