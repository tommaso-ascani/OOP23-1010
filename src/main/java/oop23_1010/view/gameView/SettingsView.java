package oop23_1010.view.gameView;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import oop23_1010.view.ViewImpl;
import oop23_1010.view.ViewSwitcher;
import oop23_1010.view.ViewType;

public class SettingsView extends ViewImpl {

    @FXML
    private Button buttonIndietro;

    @Override
    public void init() {

    }

    public void switchToHomeView() {
        ViewSwitcher.getInstance().switchView(getStage(), ViewType.HOME);
    }
}