package oop23_1010.controllers;

import javafx.application.Application;
import javafx.stage.Stage;
import oop23_1010.view.ViewSwitcher;
import oop23_1010.view.ViewType;

public class Startup extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        ViewSwitcher.getInstance().switchView(stage, ViewType.HOME);
    }
}