package oop23_1010.controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Startup extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(ClassLoader.getSystemResource("layouts/StartView.fxml"));
        Parent root = loader.load();
        stage.setTitle("1010!");
        stage.getIcons().add(new Image(ClassLoader.getSystemResourceAsStream("img/icon.jpg")));
        stage.setScene(new Scene(root, 600, 600));
        stage.show();
    }
}
