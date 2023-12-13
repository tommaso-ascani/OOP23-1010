package tenten.view.gameview;

import java.io.IOException;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.util.Pair;
import tenten.language.GameLanguageSystem;
import tenten.sound.GameSoundSystem;
import tenten.types.LanguageType;
import tenten.types.ViewType;
import tenten.utils.DataUtils;
import tenten.utils.JsonUtils;
import tenten.utils.ThemeUtils;
import tenten.view.View;
import tenten.view.ViewSwitcher;

/**
 * Class that implements all methods to use the settings view.
 */
public final class SettingsView extends View {

    private static final Logger LOG = Logger.getLogger(DataUtils.class.getName());

    /**
     * List of languages.
     */
    private final ObservableList<String> languageList = FXCollections.observableArrayList();

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
    private Label settingsLabel,
            volumeLabel,
            languageLabel;

    @FXML
    private ChoiceBox<String> languageChoiceBox;

    @Override
    public void init() {

        for (final LanguageType langType : LanguageType.values()) {
            this.languageList.add(langType.name());
        }

        this.languageChoiceBox.setItems(this.languageList);
        this.languageChoiceBox.setValue(GameLanguageSystem.getInstance().getLanguage());

        this.settingsLabel.setText(GameLanguageSystem.getInstance().getLanguageType().getSettings());
        this.volumeLabel.setText(GameLanguageSystem.getInstance().getLanguageType().getVolume());
        this.languageLabel.setText(GameLanguageSystem.getInstance().getLanguageType().getLanguage());
        this.buttonApply.setText(GameLanguageSystem.getInstance().getLanguageType().getApply());
        this.buttonBack.setText(GameLanguageSystem.getInstance().getLanguageType().getBack());

        this.mainPane.setPrefSize(View.WINDOW_WIDTH, View.WINDOW_HEIGHT);
        this.mainPane.setStyle("-fx-background: " + ThemeUtils.getSelectedTheme().getColorBackground());

        this.settingsPane.relocate(View.WINDOW_WIDTH / 2 - this.settingsPane.getPrefWidth() / 2,
                View.WINDOW_HEIGHT / 2 - this.settingsPane.getPrefHeight() / 2);

        if (GameSoundSystem.getInstance().getVolume() != 0) {
            this.imageVolume.setImage(new Image("/img/YesAudioButton.png"));
        } else {
            this.imageVolume.setImage(new Image("/img/NoAudioButton.png"));
        }

        this.sliderVolume.setValue(GameSoundSystem.getInstance().getVolume());

        // Set the listener on the button apply to save the chosen volume in the
        // settings.json file

        this.buttonApply.setOnMouseClicked(e -> {
            try {
                JsonUtils.addElement(new Pair<String, Object>(JsonUtils.VOLUME, this.sliderVolume.getValue()),
                        JsonUtils.GAME_DATA_FILE);

                JsonUtils.addElement(new Pair<String, Object>(JsonUtils.LANGUAGE, this.languageChoiceBox.getValue()),
                        JsonUtils.GAME_DATA_FILE);
            } catch (IOException exc) {
                LOG.fine("Settings View - Error on volume/language apply!");
            }
            ViewSwitcher.getInstance().switchView(getStage(), ViewType.SETTINGS);
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

    /**
     * Switch to home view.
     */
    public void switchToHomeView() {
        ViewSwitcher.getInstance().switchView(getStage(), ViewType.HOME);
    }

    /**
     * This method is used to switch from 0 to 100 volume by clicking
     * on the ImageView.
     */
    public void changeVolumeImage() {
        final Double temp = this.sliderVolume.getValue();
        if (temp.doubleValue() == 0.0) {
            this.imageVolume.setImage(new Image("/img/YesAudioButton.png"));
            this.sliderVolume.setValue(100);
        } else {
            this.imageVolume.setImage(new Image("/img/NoAudioButton.png"));
            this.sliderVolume.setValue(0);
        }
    }
}
