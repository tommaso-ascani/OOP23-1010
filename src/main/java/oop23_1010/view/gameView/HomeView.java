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
import oop23_1010.controllers.ThemeController;
import oop23_1010.utils.JsonUtils;
import oop23_1010.view.ViewImpl;
import oop23_1010.view.ViewSwitcher;
import oop23_1010.view.ViewType;

public class HomeView extends ViewImpl {

    private static int gridSize;

    @FXML
    private ImageView imageSettings,
            imageTitle,
            imageShop,
            imageQuit,
            imageResume,
            imagePlay;

    @FXML
    private Slider sliderGridWidth;

    @FXML
    private AnchorPane mainPane;

    @FXML
    private Label bestScore,
            sliderLabel,
            coinsLabel;

    @Override
    public void init() {

        // Coins

        try {
            if(!JsonUtils.ifDataExist(JsonUtils.COINS, JsonUtils.GAME_DATA_FILE)) {
                JsonUtils.addElement(new Pair<String, Object>(JsonUtils.COINS, 0), JsonUtils.GAME_DATA_FILE);
            }
            this.coinsLabel.setText("Coins: " + String.valueOf((Integer) JsonUtils.loadData(JsonUtils.COINS, JsonUtils.GAME_DATA_FILE)));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Object relocate

        this.imageSettings.relocate(15, 15);
        this.imageQuit.relocate(ViewSwitcher.getWindowWidth() - this.imageQuit.getFitWidth() - 15, 15);
        this.imageTitle.relocate((ViewSwitcher.getWindowWidth() / 2) - (this.imageTitle.getFitWidth() / 2), 220);
        this.imagePlay.relocate((ViewSwitcher.getWindowWidth() / 2) + 75, 350);
        this.imageResume.relocate((ViewSwitcher.getWindowWidth() / 2) - this.imageResume.getFitWidth() - 75, 350);
        this.sliderLabel.relocate((ViewSwitcher.getWindowWidth() / 2) - this.sliderLabel.getPrefWidth(), 450);
        this.sliderGridWidth.relocate((ViewSwitcher.getWindowWidth() / 2), 450);
        this.imageShop.relocate((ViewSwitcher.getWindowWidth() / 2) - (this.imageShop.getFitWidth() / 2), 750);
        this.coinsLabel.relocate((ViewSwitcher.getWindowWidth() / 2) - (this.coinsLabel.getPrefWidth() / 2), 820);

        // Style
        
        this.mainPane.setStyle("-fx-background: " + ThemeController.getSelectedSkin().getColor_background());

        this.sliderGridWidth.setValue(10);
        this.sliderGridWidth.setMin(5);
        this.sliderGridWidth.setMax(20);
        this.sliderGridWidth.setShowTickMarks(false);
        this.sliderGridWidth.setShowTickLabels(true);
        this.sliderGridWidth.setMajorTickUnit(5);
        this.sliderGridWidth.setMinorTickCount(0);
        this.sliderGridWidth.setSnapToTicks(true);

        this.coinsLabel.setAlignment(Pos.CENTER);

        try {
            if (JsonUtils.jsonExist(JsonUtils.BEST_SCORE_FILE)) {
                JSONObject best_score = JsonUtils.loadDatas(JsonUtils.BEST_SCORE_FILE);

                Integer padding = 0;

                for (int i = 0; i < 4; i++) {
                    if (JsonUtils.ifDataExist(String.valueOf((i + 1) * 5), JsonUtils.BEST_SCORE_FILE)) {
                        this.bestScore = new Label();
                        this.bestScore.setPrefSize(200, 10);
                        this.bestScore.relocate(
                                (ViewSwitcher.getWindowWidth() / 2) - (this.bestScore.getPrefWidth() / 2),
                                (padding * 25) + 5);
                        this.bestScore.setAlignment(Pos.CENTER);
                        this.bestScore.setText("Best Score on grid " + ((i + 1) * 5) + " ---> "
                                + String.valueOf(best_score.get(String.valueOf((i + 1) * 5))));
                        this.mainPane.getChildren().add(this.bestScore);
                        padding++;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
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
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            if (!JsonUtils.jsonExist(JsonUtils.GAME_DATA_FILE)
                    || !JsonUtils.ifDataExist(JsonUtils.SKINS, JsonUtils.GAME_DATA_FILE)) {
                ThemeController.saveSkins();
            }
        } catch (IOException e) {
            e.printStackTrace();
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
     * This method is used to switch view to the geme view and setting the grid size
     * if is a new game
     */
    public void switchToPlayView() {
        HomeView.gridSize = (int) this.sliderGridWidth.getValue();
        ViewSwitcher.getInstance().switchView(getStage(), ViewType.GAME);
    }

    /*
     * This method is used to create and positionize all nodes in the dialog pane
     * for resume a game
     */
    public void createDialogResume() {
        Pane dialogPaneResume = new Pane();
        Button dialogResumeYes = new Button("Resume the game");
        Button dialogResumeDelete = new Button("Delete the game");
        Button dialogResumeBack = new Button("Back");
        Label dialogResumeLabel1 = new Label("What do you want to do?");

        dialogResumeLabel1.setAlignment(Pos.BASELINE_CENTER);
        dialogPaneResume.setStyle(
                "-fx-background-color: " + ThemeController.getSelectedSkin().getColor_background() +"; -fx-border-width: 2; -fx-border-color: black");

        dialogPaneResume.setPrefSize(300, 200);
        dialogResumeYes.setPrefSize(120, 30);
        dialogResumeDelete.setPrefSize(120, 30);
        dialogResumeBack.setPrefSize(90, 30);
        dialogResumeLabel1.setPrefSize(300, 30);

        dialogPaneResume.relocate((this.mainPane.getPrefWidth() - dialogPaneResume.getPrefWidth()) / 2,
                (this.mainPane.getPrefHeight() - dialogPaneResume.getPrefHeight()) / 2);

        dialogResumeYes.relocate(
                (dialogPaneResume.getPrefWidth() - dialogResumeYes.getPrefWidth() - dialogResumeDelete.getPrefWidth())
                        / 3,
                (dialogPaneResume.getPrefHeight() - dialogResumeYes.getPrefHeight()) / 1.5);

        dialogResumeDelete.relocate((dialogPaneResume.getPrefWidth() - dialogResumeDelete.getPrefWidth()
                - ((dialogPaneResume.getPrefWidth() - dialogResumeYes.getPrefWidth()
                        - dialogResumeDelete.getPrefWidth() - 40)) / 2),
                (dialogPaneResume.getPrefHeight() - dialogResumeYes.getPrefHeight()) / 1.5);

        dialogResumeBack.relocate((dialogPaneResume.getPrefWidth() - dialogResumeBack.getPrefWidth()) / 2,
                (dialogPaneResume.getPrefHeight() - dialogResumeBack.getPrefHeight()) / 1.1);

        dialogResumeLabel1.relocate((dialogPaneResume.getPrefWidth() - dialogResumeLabel1.getPrefWidth()) / 2,
                (dialogPaneResume.getPrefHeight() - dialogResumeBack.getPrefHeight()) / 5);

        dialogPaneResume.getChildren().addAll(dialogResumeBack, dialogResumeDelete, dialogResumeLabel1,
                dialogResumeYes);

        this.mainPane.getChildren().add(dialogPaneResume);

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
            } catch (IOException e1) {
                e1.printStackTrace();
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