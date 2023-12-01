package oop23_1010.view.gameView;

import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONArray;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import oop23_1010.controllers.ThemeController;
import oop23_1010.utils.JsonUtils;
import oop23_1010.utils.ShopSkinItem;
import oop23_1010.view.ViewImpl;
import oop23_1010.view.ViewSwitcher;
import oop23_1010.view.ViewType;

public class ShopView extends ViewImpl {

    @FXML
    private AnchorPane mainPane;

    @FXML
    private Button buttonBack;

    @FXML
    private Button buttonPurchase;

    @FXML
    private Pane titlePane;

    @FXML
    private Label titleLabel;

    @FXML
    private VBox verticalBox;

    private ArrayList<ShopSkinItem> shopList = new ArrayList<ShopSkinItem>();

    @Override
    public void init() {
        this.loadSkins();

        this.mainPane.setStyle("-fx-background: " + ThemeController.getSelectedSkin().getColor_background());

        this.titlePane.setPrefSize(ViewSwitcher.getWindowWidth(), ViewSwitcher.getWindowHeight() / 9);

        this.titleLabel.setText("SHOP");
        this.titleLabel.setFont(new Font(30));
        this.titleLabel.setPrefSize(ViewSwitcher.getWindowWidth(), ViewSwitcher.getWindowHeight() / 9);
        this.titleLabel.setAlignment(Pos.BASELINE_CENTER);

        this.verticalBox.setPadding(new Insets(10));
        this.verticalBox.setSpacing(10);
        this.verticalBox.setPrefSize(ViewSwitcher.getWindowWidth() - 100, 500);
        this.verticalBox.relocate((ViewSwitcher.getWindowWidth() - this.verticalBox.getPrefWidth()) / 2,
                (ViewSwitcher.getWindowHeight() - this.verticalBox.getPrefHeight()) / 2);

    }

    public void loadSkins() {
        this.verticalBox.getChildren().clear();
        JSONArray a = null;

        try {
            a = JsonUtils.loadDataArray(JsonUtils.SKINS, JsonUtils.GAME_DATA_FILE);

            for (int i = 0; i < a.length(); i++) {
                ShopSkinItem temp = new ShopSkinItem(a.getJSONObject(i).getString("name"), i + 1,
                        (Boolean) a.getJSONObject(i).get("purchased"));
                if (temp.getPurchased()) {
                    temp.getCostLabel().setText("PURCHASED");
                } else {
                    temp.getCostLabel().setText(temp.getSkin().getCost().toString());
                    temp.setOnMouseClicked(e -> {
                        System.out.println("comprato");
                        temp.getSkin().setPurchased(true);
                        ThemeController.saveSkins();
                        this.loadSkins();
                    });
                }

                this.verticalBox.getChildren().add(temp);
                shopList.add(temp);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.verticalBox.setPadding(new Insets(10));
        this.verticalBox.setSpacing(10);
        this.verticalBox.setPrefSize(ViewSwitcher.getWindowWidth() - 100, 500);
        this.verticalBox.relocate((ViewSwitcher.getWindowWidth() - this.verticalBox.getPrefWidth()) / 2,
                (ViewSwitcher.getWindowHeight() - this.verticalBox.getPrefHeight()) / 2);

    }

    public void switchToHomeView() {
        ViewSwitcher.getInstance().switchView(getStage(), ViewType.HOME);
    }
}
