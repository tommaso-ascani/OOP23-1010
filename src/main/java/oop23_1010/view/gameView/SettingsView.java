package oop23_1010.view.gameView;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Pair;
import oop23_1010.sound.GameSoundSystem;
import oop23_1010.utils.JsonUtils;
import oop23_1010.view.ViewImpl;
import oop23_1010.view.ViewSwitcher;
import oop23_1010.view.ViewType;

public class SettingsView extends ViewImpl {

    @FXML
    private AnchorPane mainPane;

    @FXML
    private Button buttonBack;

    @FXML
    private Button buttonApply;

    @FXML
    private Slider sliderVolume;

    @FXML
    private ImageView imageVolume;

    @Override
    public void init() {

        if (GameSoundSystem.getInstance().getVolume() != 0) {
            this.imageVolume.setImage(new Image("/img/YesAudioButton.png"));
        } else {
            this.imageVolume.setImage(new Image("/img/NoAudioButton.png"));
        }

        this.sliderVolume.setValue(GameSoundSystem.getInstance().getVolume());
        this.sliderVolume.setMin(0);
        this.sliderVolume.setMax(100);
        this.sliderVolume.setShowTickMarks(true);
        this.sliderVolume.setShowTickLabels(true);
        this.sliderVolume.setMajorTickUnit(10);
        this.sliderVolume.setMinorTickCount(1);
        this.sliderVolume.setSnapToTicks(true);

        // Set the listener on the button apply to save the chosen volume in the
        // settings.json file

        this.buttonApply.setOnMouseClicked(e -> {
            try {
                JsonUtils.addElement(new Pair<String, Object>(JsonUtils.VOLUME, this.sliderVolume.getValue()),
                        JsonUtils.SETTINGS_FILE);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });

        // Set the listener on the slider to change the visualized image

        this.sliderVolume.setOnMouseReleased(e -> {
            if (this.sliderVolume.getValue() == 0.0) {
                this.imageVolume.setImage(new Image("/img/NoAudioButton.png"));
            } else {
                this.imageVolume.setImage(new Image("/img/YesAudioButton.png"));
            }
        });
    }

    public void switchToHomeView() {
        ViewSwitcher.getInstance().switchView(getStage(), ViewType.HOME);
    }

    /*
     * This method is used to switch automatically from 0 to 100 volume by clicking
     * on the ImageView
     */
    public void changeVolumeImage() {
        Double temp = this.sliderVolume.getValue();
        if (temp.doubleValue() == 0.0) {
            this.imageVolume.setImage(new Image("/img/YesAudioButton.png"));
            this.sliderVolume.setValue(100);
        } else {
            this.imageVolume.setImage(new Image("/img/NoAudioButton.png"));
            this.sliderVolume.setValue(0);
        }
    }
}
