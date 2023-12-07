package oop23_1010.view.gameView;

import java.io.IOException;

import org.json.JSONObject;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.util.Pair;
import oop23_1010.language.GameLanguageSystem;
import oop23_1010.types.ThemeType;
import oop23_1010.types.ViewType;
import oop23_1010.utils.JsonUtils;
import oop23_1010.utils.ThemeUtils;
import oop23_1010.view.View;
import oop23_1010.view.ViewSwitcher;

public class HomeView extends View {

    private static int gridSize;

    private static final Integer PAUSE_AND_QUIT_BUTTON_SPACE = 15;

    @FXML
    private ImageView imageSettings,
            imageTitle,
            imageShop,
            imageQuit,
            imageResume,
            imagePlay;

    @FXML
    private Button dialogResumeYes,
            dialogResumeDelete,
            dialogResumeBack;

    @FXML
    private Pane dialogPaneResume;

    @FXML
    private Slider sliderGridWidth;

    @FXML
    private AnchorPane mainPane;

    @FXML
    private Label bestScore,
            sliderLabel,
            coinsLabel,
            dialogResumeLabel1;

    @Override
    public void init() {

        this.sliderLabel.setText(GameLanguageSystem.getInstance().getLanguageType().getGridSize());

        this.coinsLabel.setText(GameLanguageSystem.getInstance().getLanguageType().getCoins());

        this.dialogResumeYes.setText(GameLanguageSystem.getInstance().getLanguageType().getResumeTheGame());
        this.dialogResumeDelete.setText(GameLanguageSystem.getInstance().getLanguageType().getDeleteTheGame());
        this.dialogResumeBack.setText(GameLanguageSystem.getInstance().getLanguageType().getBack());
        this.dialogResumeLabel1.setText(GameLanguageSystem.getInstance().getLanguageType().getSavedGameLabel());

        this.mainPane.setPrefSize(View.WINDOW_WIDTH, View.WINDOW_HEIGHT);

        // Load saved Theme and save skins
        try {
            if (!JsonUtils.ifDataExist(JsonUtils.SKINS, JsonUtils.GAME_DATA_FILE)) {
                ThemeUtils.saveThemes();
            }
            if (!JsonUtils.ifDataExist(JsonUtils.SELECTED_SKIN, JsonUtils.GAME_DATA_FILE)) {
                ThemeUtils.setSelectedSkin(ThemeType.LIGHT);
                ThemeUtils.saveSelectedSkin();
                ViewSwitcher.getInstance().switchView(getStage(), ViewType.HOME);
            }
            ThemeUtils.loadSelectedSkin();
            ThemeUtils.loadSelectedSkin();
        } catch (IOException exc) {
            System.err.println("Home View - Error on skin loading!");
        }

        // Coins

        try {
            if (!JsonUtils.ifDataExist(JsonUtils.COINS, JsonUtils.GAME_DATA_FILE)) {
                JsonUtils.addElement(new Pair<String, Object>(JsonUtils.COINS, 0), JsonUtils.GAME_DATA_FILE);
            }
            this.coinsLabel.setText(GameLanguageSystem.getInstance().getLanguageType().getCoins() + ": "
                    + String.valueOf((Integer) JsonUtils.loadData(JsonUtils.COINS, JsonUtils.GAME_DATA_FILE)));
        } catch (IOException exc) {
            System.err.println("Home View - Error on coins loading!");
        }

        // Object relocate

        this.imageSettings.relocate(PAUSE_AND_QUIT_BUTTON_SPACE, PAUSE_AND_QUIT_BUTTON_SPACE);
        this.imageQuit.relocate(
                this.mainPane.getPrefWidth() - this.imageQuit.getFitWidth() - PAUSE_AND_QUIT_BUTTON_SPACE,
                PAUSE_AND_QUIT_BUTTON_SPACE);
        this.imageTitle.relocate((this.mainPane.getPrefWidth() / 2) - (this.imageTitle.getFitWidth() / 2),
                this.mainPane.getPrefHeight() / 4);
        this.imagePlay.relocate((this.mainPane.getPrefWidth() / 2) + 75, this.mainPane.getPrefHeight() / 2.5);
        this.imageResume.relocate((this.mainPane.getPrefWidth() / 2) - this.imageResume.getFitWidth() - 75,
                this.mainPane.getPrefHeight() / 2.5);
        this.sliderLabel.relocate((this.mainPane.getPrefWidth() / 2) - this.sliderLabel.getPrefWidth(),
                this.mainPane.getPrefHeight() / 2);
        this.sliderGridWidth.relocate((this.mainPane.getPrefWidth() / 2), this.mainPane.getPrefHeight() / 2);
        this.imageShop.relocate((this.mainPane.getPrefWidth() / 2) - (this.imageShop.getFitWidth() / 2),
                this.mainPane.getPrefHeight() - (this.mainPane.getPrefHeight() / 6));
        this.coinsLabel.relocate((this.mainPane.getPrefWidth() / 2) - (this.coinsLabel.getPrefWidth() / 2),
                this.mainPane.getPrefHeight() - (this.mainPane.getPrefHeight() / 12));

        // Style

        this.mainPane.setStyle("-fx-background: " + ThemeUtils.getSelectedSkin().getColor_background());

        try {
            if (JsonUtils.jsonExist(JsonUtils.BEST_SCORE_FILE)) {
                JSONObject best_score = JsonUtils.loadDatas(JsonUtils.BEST_SCORE_FILE);

                Integer padding = 0;

                for (int i = 0; i < 4; i++) {
                    if (JsonUtils.ifDataExist(String.valueOf((i + 1) * 5), JsonUtils.BEST_SCORE_FILE)) {
                        this.bestScore = new Label();
                        this.bestScore.setPrefSize(200, 10);
                        this.bestScore.relocate(
                                (this.mainPane.getPrefWidth() / 2) - (this.bestScore.getPrefWidth() / 2),
                                (padding * 25) + 5);
                        this.bestScore.setAlignment(Pos.CENTER);
                        this.bestScore.setText("Best Score on grid " + ((i + 1) * 5) + " ---> "
                                + String.valueOf(best_score.get(String.valueOf((i + 1) * 5))));
                        this.mainPane.getChildren().add(this.bestScore);
                        padding++;
                    }
                }
            }
        } catch (IOException exc) {
            System.err.println("Home View - Error on best score loading!");
        }

        // In this try/catch we control if there is a json file with saved data and if
        // true, make the play button and the slider disable, if false make de resume
        // button disable
        try {
            if (JsonUtils.jsonExist(JsonUtils.MATCH_FILE)) {
                this.imagePlay.setDisable(true);
                this.imagePlay.setOpacity(0.4);
                this.sliderGridWidth.setDisable(true);
                this.sliderGridWidth.setOpacity(0.4);
            } else {
                this.imageResume.setDisable(true);
                this.imageResume.setOpacity(0.4);
            }
        } catch (IOException exc) {
            System.err.println("Home View - Error on saved match loading!");
        }

    }

    /*
     * This method is used to switch view to the setting view
     */
    public void switchToSettingsView() {
        ViewSwitcher.getInstance().switchView(getStage(), ViewType.SETTINGS);
    }

    /*
     * This method is used to switch view to the shop view
     */
    public void switchToShopView() {
        ViewSwitcher.getInstance().switchView(getStage(), ViewType.SHOP);
    }

    /*
     * This method is used to switch view to the game view and setting the grid size
     * if is a new game
     */
    public void switchToPlayView() {
        HomeView.gridSize = (int) this.sliderGridWidth.getValue();
        ViewSwitcher.getInstance().switchView(getStage(), ViewType.GAME);
    }

    /*
     * This method is used to quit the geme
     */
    public void quitGame() {
        System.exit(0);
    }

    /*
     * This method is used to create and positionize all nodes in the dialog pane
     * for resume a game
     */
    public void createDialogResume() {

        dialogPaneResume.setStyle(
                "-fx-background-color: " + ThemeUtils.getSelectedSkin().getColor_background()
                        + "; -fx-border-width: 2; -fx-border-color: black");

        dialogPaneResume.relocate((this.mainPane.getPrefWidth() - dialogPaneResume.getPrefWidth()) / 2,
                (this.mainPane.getPrefHeight() - dialogPaneResume.getPrefHeight()) / 2);

        dialogPaneResume.setVisible(true);

        this.setListenersResumePane(dialogResumeBack, dialogResumeDelete, dialogResumeYes, dialogPaneResume);
    }

    /**
     * This method is used to set the listeners to all the buttons in the dialog
     * resume pane
     * 
     * @param btnBack    the back button
     * @param btnDelete  the delete button
     * @param btnResume  the resume button
     * @param paneResume the dialog resume pane
     */
    private void setListenersResumePane(Button btnBack, Button btnDelete, Button btnResume, Pane paneResume) {
        btnBack.setOnMouseClicked(e -> {
            paneResume.setVisible(false);
        });

        btnDelete.setOnMouseClicked(e -> {
            try {
                JsonUtils.flushJson(JsonUtils.MATCH_FILE);
                paneResume.setVisible(false);
                this.imageResume.setDisable(true);
                this.imageResume.setOpacity(0.4);
                this.imagePlay.setDisable(false);
                this.imagePlay.setOpacity(1);
                this.sliderGridWidth.setDisable(false);
                this.sliderGridWidth.setOpacity(1);
            } catch (IOException exc) {
                System.err.println("Home View - Error on saved match deleting!");
            }
        });

        btnResume.setOnMouseClicked(e -> {
            this.switchToPlayView();
        });
    }

    public static int getGridSize() {
        return gridSize;
    }
}