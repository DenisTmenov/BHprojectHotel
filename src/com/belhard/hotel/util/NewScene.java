package com.belhard.hotel.util;

import com.belhard.hotel.frame.NewOrderAdminFrame;
import com.belhard.hotel.frame.NewRoomAdminFrame;
import com.belhard.hotel.frame.NewUserAdminFrame;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class NewScene {
    public NewScene(Button button, String sceneName, boolean Resizable) {
        Stage stage = (Stage) button.getScene().getWindow();
        stage.close();
        String sceneLoader = "/com/belhard/hotel/controllers/" + sceneName + ".fxml";
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(sceneLoader));
            Parent page = loader.load();
            Stage mainStage = new Stage();
            mainStage.setTitle("Project HOTEL. " + sceneName + ".");
            mainStage.getIcons().add(new Image("com/belhard/hotel/resources/images/1489711413_City.png"));
            mainStage.setResizable(Resizable);
            Scene scene = new Scene(page);
            mainStage.setScene(scene);
            mainStage.show();
        } catch (IOException e) {
            System.out.println("Problem in LOADER " + sceneName);
            e.printStackTrace();
        }
    }

    public NewScene(Button button, String sceneName, boolean Resizable, boolean close) {
        Stage stage = (Stage) button.getScene().getWindow();
        if (close) {
            stage.close();
        }
        String sceneLoader = "/com/belhard/hotel/controllers/" + sceneName + ".fxml";
        try {
            // Загружаем fxml-файл и создаём новую сцену
            // для всплывающего диалогового окна.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(sceneLoader));
            Parent page = loader.load();
            // Создаём диалоговое окно Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Project HOTEL. " + sceneName + ".");
            dialogStage.getIcons().add(new Image("com/belhard/hotel/resources/images/1489711413_City.png"));
            dialogStage.setResizable(Resizable);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Передаём адресата в контроллер.
            if (sceneName.equals("NewOrderAdminFrame")){
            NewOrderAdminFrame controller = loader.getController();
            controller.setDialogStage(dialogStage);}
            if (sceneName.equals("NewUserAdminFrame")) {
                NewUserAdminFrame controller = loader.getController();
                controller.setDialogStage(dialogStage);
            }
            if (sceneName.equals("NewRoomAdminFrame")) {
                NewRoomAdminFrame controller = loader.getController();
                controller.setDialogStage(dialogStage);
            }
            // Отображаем диалоговое окно и ждём, пока пользователь его не закроет
            dialogStage.showAndWait();

        } catch (IOException e) {
            System.out.println("Problem in LOADER " + sceneName);
            e.printStackTrace();
        }
    }
}
