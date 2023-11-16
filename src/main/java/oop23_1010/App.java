package oop23_1010;

import java.io.IOException;

import javafx.application.Application;
import oop23_1010.controllers.Startup;
import oop23_1010.utils.JsonUtils;

public class App {
    public static void main(String[] args) {
        Application.launch(Startup.class, args);
        // try {
        // JsonUtils.saveData("match");
        // } catch (IOException e) {
        // e.printStackTrace();
        // }
    }
}
