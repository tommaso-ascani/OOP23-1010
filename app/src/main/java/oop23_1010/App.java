package oop23_1010;

import javafx.application.Application;
import javafx.fxml.*;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        System.out.println(ClassLoader.getSystemResource("scene.fxml"));
        FXMLLoader loader = new FXMLLoader(ClassLoader.getSystemResource("scene.fxml"));
        Parent root = loader.load();
        stage.setTitle("1010");
        stage.setScene(new Scene(root, 300, 300));
        stage.show();
    }
}