package oop23_1010.view.gameView;

import java.io.IOException;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableListBase;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.util.Pair;
import oop23_1010.language.GameLanguageSystem;
import oop23_1010.language.LanguageType;
import oop23_1010.sound.GameSoundSystem;
import oop23_1010.utils.JsonUtils;
import oop23_1010.utils.ThemeUtils;
import oop23_1010.view.View;
import oop23_1010.view.ViewSwitcher;
import oop23_1010.view.ViewType;

public class SettingsView extends View {

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

    @FXML
    private Pane settingsPane;

    @FXML
    private ChoiceBox<String> languageChoiceBox;

    private ObservableList<String> languageList = FXCollections.observableArrayList();

    @Override
    public void init() {

        for (LanguageType langType : LanguageType.values()) {
            this.languageList.add(langType.name());
        }

        this.languageChoiceBox.setItems(this.languageList);

        this.mainPane.setPrefSize(View.WINDOW_WIDTH, View.WINDOW_HEIGHT);
        this.mainPane.setStyle("-fx-background: " + ThemeUtils.getSelectedSkin().getColor_background());

        this.settingsPane.relocate((View.WINDOW_WIDTH / 2) - (this.settingsPane.getPrefWidth() / 2),
                (View.WINDOW_HEIGHT / 2) - (this.settingsPane.getPrefHeight() / 2));

        if (GameSoundSystem.getInstance().getVolume() != 0) {
            this.imageVolume.setImage(new Image("/img/YesAudioButton.png"));
        } else {
            this.imageVolume.setImage(new Image("/img/NoAudioButton.png"));
        }

        this.sliderVolume.setValue(GameSoundSystem.getInstance().getVolume());

        this.languageChoiceBox.setValue(GameLanguageSystem.getLanguage());

        // Set the listener on the button apply to save the chosen volume in the
        // settings.json file

        this.buttonApply.setOnMouseClicked(e -> {
            try {
                JsonUtils.addElement(new Pair<String, Object>(JsonUtils.VOLUME, this.sliderVolume.getValue()),
                        JsonUtils.GAME_DATA_FILE);

                JsonUtils.addElement(new Pair<String, Object>(JsonUtils.LANGUAGE, this.languageChoiceBox.getValue()),
                        JsonUtils.GAME_DATA_FILE);
            } catch (IOException exc) {
                System.err.println("Settings View - Error on volume/language apply!");
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
