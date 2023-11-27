package oop23_1010.view.gameView;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import oop23_1010.utils.JsonUtils;
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
    private ImageView imageResume;

    @FXML
    private ImageView imagePlay;

    @FXML
    private Slider sliderGridWidth;

    @FXML
    private AnchorPane mainPane;

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

        // In this try catch we control if there is a json file with saved data and if
        // true, make the play button and the slider disable, if false make de resume
        // button disable
        try {
            if (JsonUtils.jsonMatchExist()) {
                this.imagePlay.setDisable(true);
                this.imagePlay.setOpacity(0.4);
                this.sliderGridWidth.setDisable(true);
                this.sliderGridWidth.setOpacity(0.4);
            } else {
                this.imageResume.setDisable(true);
                this.imageResume.setOpacity(0.4);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    public static int getGridSize() {
        return gridSize;
    }
}