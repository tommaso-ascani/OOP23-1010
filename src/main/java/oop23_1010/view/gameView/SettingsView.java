package oop23_1010.view.gameView;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import oop23_1010.sound.GameSoundSystem;
import oop23_1010.sound.Volume;
import oop23_1010.view.ViewImpl;
import oop23_1010.view.ViewSwitcher;
import oop23_1010.view.ViewType;

public class SettingsView extends ViewImpl {

    @FXML
    private Button buttonIndietro;

    @FXML
    private Slider sliderVolume;

    @FXML
    private ImageView imageVolume;

    @Override
    public void init() {
        this.sliderVolume.setValue(100);
        this.sliderVolume.setMin(0);
        this.sliderVolume.setMax(100);
        this.sliderVolume.setShowTickMarks(true);
        this.sliderVolume.setShowTickLabels(true);
        this.sliderVolume.setMajorTickUnit(10);
        this.sliderVolume.setMinorTickCount(1);
        this.sliderVolume.setSnapToTicks(true);

        this.sliderVolume.setOnMouseReleased(e -> {
            GameSoundSystem.getInstance().setVolume(((Double) this.sliderVolume.getValue()) / 100.0);
        });
    }

    public void switchToHomeView() {
        ViewSwitcher.getInstance().switchView(getStage(), ViewType.HOME);
    }
}
