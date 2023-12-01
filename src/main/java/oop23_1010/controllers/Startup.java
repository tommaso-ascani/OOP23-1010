package oop23_1010.controllers;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import oop23_1010.types.SkinType;
import oop23_1010.utils.QuitUtils;
import oop23_1010.view.ViewSwitcher;
import oop23_1010.view.ViewType;

public class Startup extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        // TO-DO Change img path to general img class loader
        stage.getIcons().add(new Image(ClassLoader.getSystemResourceAsStream("img/icon.jpg")));
        stage.setTitle("1010!");

        stage.setOnCloseRequest(event -> {
            QuitUtils.quit();
        });

        ThemeController.setSelectedSkin(SkinType.DARK);

        ViewSwitcher.getInstance().switchView(stage, ViewType.HOME);
    }
}
