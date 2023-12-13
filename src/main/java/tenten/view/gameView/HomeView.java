package tenten.view.gameView;

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
import tenten.language.GameLanguageSystem;
import tenten.types.ThemeType;
import tenten.types.ViewType;
import tenten.utils.JsonUtils;
import tenten.utils.ThemeUtils;
import tenten.view.View;
import tenten.view.ViewSwitcher;

/**
 * Class that implements all methods to use the home view.
 */
public final class HomeView extends View {

    /**
     * Grid size selected by user.
     * Default is 10.
     */
    private static int gridSize;

    /**
     * Space between pause and quit button.
     */
    private static final Integer PAUSE_AND_QUIT_BUTTON_SPACE = 15;

    private static final Integer PLAY_AND_RESUME_BUTTON_SPACE = 75;

    private static final Double PLAY_AND_RESUME_BUTTON_DIVISOR_CONSTANT = 2.5;

    private static final Integer SHOP_BUTTON_DIVISOR_CONSTANT = 6;

    private static final Integer COINS_LABEL_DIVISOR_CONSTANT = 12;

    private static final Integer BEST_SCORE_LABEL_PREF_WIDTH = 200;

    private static final Double OPACITY_FOR_DISABLED_CONTENT = 0.4;

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

        // Load saved Theme and save themes
        try {
            if (!JsonUtils.ifDataExist(JsonUtils.THEMES, JsonUtils.GAME_DATA_FILE)) {
                ThemeUtils.saveThemes();
            }
            if (!JsonUtils.ifDataExist(JsonUtils.SELECTED_THEME, JsonUtils.GAME_DATA_FILE)) {
                ThemeUtils.setSelectedTheme(ThemeType.LIGHT);
                ThemeUtils.saveSelectedTheme();
                ViewSwitcher.getInstance().switchView(getStage(), ViewType.HOME);
            }
            ThemeUtils.loadSelectedTheme();
            ThemeUtils.loadSelectedTheme();
        } catch (IOException exc) {
            System.err.println("Home View - Error on theme loading!");
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
        this.imagePlay.relocate((this.mainPane.getPrefWidth() / 2) + HomeView.PLAY_AND_RESUME_BUTTON_SPACE,
                this.mainPane.getPrefHeight() / HomeView.PLAY_AND_RESUME_BUTTON_DIVISOR_CONSTANT);
        this.imageResume.relocate(
                (this.mainPane.getPrefWidth() / 2) - this.imageResume.getFitWidth()
                        - HomeView.PLAY_AND_RESUME_BUTTON_SPACE,
                this.mainPane.getPrefHeight() / HomeView.PLAY_AND_RESUME_BUTTON_DIVISOR_CONSTANT);
        this.sliderLabel.relocate((this.mainPane.getPrefWidth() / 2) - this.sliderLabel.getPrefWidth(),
                this.mainPane.getPrefHeight() / 2);
        this.sliderGridWidth.relocate((this.mainPane.getPrefWidth() / 2), this.mainPane.getPrefHeight() / 2);
        this.imageShop.relocate((this.mainPane.getPrefWidth() / 2) - (this.imageShop.getFitWidth() / 2),
                this.mainPane.getPrefHeight()
                        - (this.mainPane.getPrefHeight() / HomeView.SHOP_BUTTON_DIVISOR_CONSTANT));
        this.coinsLabel.relocate((this.mainPane.getPrefWidth() / 2) - (this.coinsLabel.getPrefWidth() / 2),
                this.mainPane.getPrefHeight()
                        - (this.mainPane.getPrefHeight() / HomeView.COINS_LABEL_DIVISOR_CONSTANT));

        // Style

        this.mainPane.setStyle("-fx-background: " + ThemeUtils.getSelectedTheme().getColorBackground());

        try {
            if (JsonUtils.jsonExist(JsonUtils.BEST_SCORE_FILE)) {
                JSONObject bestScore = JsonUtils.loadDatas(JsonUtils.BEST_SCORE_FILE);

                Integer padding = 0;

                for (int i = 0; i < 4; i++) {
                    if (JsonUtils.ifDataExist(String.valueOf((i + 1) * 5), JsonUtils.BEST_SCORE_FILE)) {
                        this.bestScore = new Label();
                        this.bestScore.setPrefSize(HomeView.BEST_SCORE_LABEL_PREF_WIDTH, 10);
                        this.bestScore.relocate(
                                (this.mainPane.getPrefWidth() / 2) - (this.bestScore.getPrefWidth() / 2),
                                (padding * 25) + 5);
                        this.bestScore.setAlignment(Pos.CENTER);
                        this.bestScore.setText("Best Score on grid " + ((i + 1) * 5) + " ---> "
                                + String.valueOf(bestScore.get(String.valueOf((i + 1) * 5))));
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
                this.imagePlay.setOpacity(HomeView.OPACITY_FOR_DISABLED_CONTENT);
                this.sliderGridWidth.setDisable(true);
                this.sliderGridWidth.setOpacity(HomeView.OPACITY_FOR_DISABLED_CONTENT);
            } else {
                this.imageResume.setDisable(true);
                this.imageResume.setOpacity(HomeView.OPACITY_FOR_DISABLED_CONTENT);
            }
        } catch (IOException exc) {
            System.err.println("Home View - Error on saved match loading!");
        }

    }

    /**
     * This method switch to setting view.
     */
    public void switchToSettingsView() {
        ViewSwitcher.getInstance().switchView(getStage(), ViewType.SETTINGS);
    }

    /**
     * This method switch to shop view.
     */
    public void switchToShopView() {
        ViewSwitcher.getInstance().switchView(getStage(), ViewType.SHOP);
    }

    /**
     * This method switch to game view and set the grid size if is a new game.
     */
    public void switchToPlayView() {
        gridSize = (int) this.sliderGridWidth.getValue();
        ViewSwitcher.getInstance().switchView(getStage(), ViewType.GAME);
    }

    /**
     * Method to quit the game.
     */
    public void quitGame() {
        System.exit(0);
    }

    /**
     * Method to create and locate all nodes in the dialog pane
     * for resume a game.
     */
    public void createDialogResume() {

        dialogPaneResume.setStyle(
                "-fx-background-color: " + ThemeUtils.getSelectedTheme().getColorBackground()
                        + "; -fx-border-width: 2; -fx-border-color: black");

        dialogPaneResume.relocate((this.mainPane.getPrefWidth() - dialogPaneResume.getPrefWidth()) / 2,
                (this.mainPane.getPrefHeight() - dialogPaneResume.getPrefHeight()) / 2);

        dialogPaneResume.setVisible(true);

        this.setListenersResumePane(dialogResumeBack, dialogResumeDelete, dialogResumeYes, dialogPaneResume);
    }

    /**
     * Method to set the listeners on all buttons in the dialog
     * resume pane.
     * 
     * @param btnBack    the back button.
     * @param btnDelete  the delete button.
     * @param btnResume  the resume button.
     * @param paneResume the dialog resume pane.
     */
    private void setListenersResumePane(final Button btnBack,
            final Button btnDelete,
            final Button btnResume,
            final Pane paneResume) {
        btnBack.setOnMouseClicked(e -> {
            paneResume.setVisible(false);
        });

        btnDelete.setOnMouseClicked(e -> {
            try {
                JsonUtils.flushJson(JsonUtils.MATCH_FILE);
                paneResume.setVisible(false);
                this.imageResume.setDisable(true);
                this.imageResume.setOpacity(HomeView.OPACITY_FOR_DISABLED_CONTENT);
                this.imagePlay.setDisable(false);
                this.imagePlay.setOpacity(1);
                this.sliderGridWidth.setDisable(false);
                this.sliderGridWidth.setOpacity(1);
            } catch (IOException exc) {
                System.err.println("Home View - Error on saved match deleting!");
            }
        });

        btnResume.setOnMouseClicked(e -> {
            try {
                gridSize = (Integer) JsonUtils.loadData(JsonUtils.GRID_SIZE, JsonUtils.MATCH_FILE);
            } catch (IOException e1) {
                System.err.println("Error in loading grid size!");
            }
            ViewSwitcher.getInstance().switchView(getStage(), ViewType.GAME);
        });
    }

    /**
     * Method to get the grid size.
     * 
     * @return grid size.
     */
    public static int getGridSize() {
        return gridSize;
    }
}
