package com.belhard.hotel.frame;

import com.belhard.hotel.db.DB;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    // как проверить загружен ли SQL сервер
    private static DB db;
    public static DB getDb() {
        return db;
    }

    static void setDb(DB db) {
        Main.db = db;
    }

    @Override
    public void start(Stage mainStage) {
        try {
            mainStage.setTitle("Project HOTEL. Start Frame.");
            mainStage.getIcons().add(new Image("com/belhard/hotel/resources/images/1489711413_City.png"));
            Parent page = FXMLLoader.load(getClass().getResource("/com/belhard/hotel/controllers/StartFrame.fxml"));
            Scene scene = new Scene(page);
            mainStage.setScene(scene);
            mainStage.setResizable(false); // Изменяемый по размеру
            mainStage.show();
        } catch (IOException e) {
            System.out.println("Problem in LOADER DB");
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        launch(args);
    }

}
