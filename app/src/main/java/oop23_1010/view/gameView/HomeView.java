package oop23_1010.view.gameView;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import oop23_1010.view.ViewImpl;
import oop23_1010.view.ViewSwitcher;
import oop23_1010.view.ViewType;

public class HomeView extends ViewImpl {

    @FXML
    private ImageView imageSettings;

    @FXML
    private ImageView imageShop;

    @FXML
    private ImageView imageQuit;

    @Override
    public void init() {

    }

    public void switchToSettingsView() {
        ViewSwitcher.getInstance().switchView(getStage(), ViewType.SETTINGS);
    }

    public void switchToShopView() {
        ViewSwitcher.getInstance().switchView(getStage(), ViewType.SHOP);
    }

    public void closeGame() {

    }
}