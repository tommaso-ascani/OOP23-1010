package oop23_1010;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import oop23_1010.types.ViewType;
import oop23_1010.view.ViewSwitcher;

public class Startup extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        stage.getIcons().add(new Image(ClassLoader.getSystemResourceAsStream("img/icon.jpg")));
        stage.setTitle("1010!");

        ViewSwitcher.getInstance().switchView(stage, ViewType.HOME);
    }
}
