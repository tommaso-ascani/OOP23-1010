package oop23_1010.view.gameView;

import javafx.fxml.FXML;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import oop23_1010.view.ViewImpl;
import oop23_1010.view.ViewSwitcher;
import oop23_1010.view.ViewType;

public class HomeView extends ViewImpl {

    private static int gridSize;

    @FXML
    private ImageView imageSettings;

    @FXML
    private ImageView imageShop;

    @FXML
    private ImageView imageQuit;

    @FXML
    private Slider sliderGridWidth;

    @Override
    public void init() {
        this.sliderGridWidth.setValue(10);
        this.sliderGridWidth.setMin(5);
        this.sliderGridWidth.setMax(20);
        this.sliderGridWidth.setShowTickMarks(false);
        this.sliderGridWidth.setShowTickLabels(true);
        this.sliderGridWidth.setMajorTickUnit(5);
        this.sliderGridWidth.setMinorTickCount(0);
        this.sliderGridWidth.setSnapToTicks(true);
    }

    public void switchToSettingsView() {
        ViewSwitcher.getInstance().switchView(getStage(), ViewType.SETTINGS);
    }

    public void switchToShopView() {
        ViewSwitcher.getInstance().switchView(getStage(), ViewType.SHOP);
    }

    public void switchToPlayView() {
        HomeView.gridSize = (int) this.sliderGridWidth.getValue();
        ViewSwitcher.getInstance().switchView(getStage(), ViewType.GAME);
    }

    public void closeGame() {

    }

    public static int getGridSize() {
        return gridSize;
    }
}