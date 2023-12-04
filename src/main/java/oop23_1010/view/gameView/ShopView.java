package oop23_1010.view.gameView;

import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONArray;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.util.Pair;
import oop23_1010.controllers.ThemeController;
import oop23_1010.utils.JsonUtils;
import oop23_1010.utils.ShopThemeItem;
import oop23_1010.view.View;
import oop23_1010.view.ViewSwitcher;
import oop23_1010.view.ViewType;

public class ShopView extends View {

    @FXML
    private AnchorPane mainPane;

    @FXML
    private Button buttonBackToHome, buttonConfirm, buttonBack;

    @FXML
    private Pane titlePane, purchasePane;

    @FXML
    private Label titleLabel, questionLabel, labelAlert;

    @FXML
    private VBox verticalBox;

    private ArrayList<ShopThemeItem> shopList = new ArrayList<ShopThemeItem>();

    private static final Integer PADDING_SPACING_VBOX = 10;

    @Override
    public void init() {

        this.mainPane.setPrefSize(View.WINDOW_WIDTH, View.WINDOW_HEIGHT);

        this.loadSkins();

        this.mainPane.setStyle("-fx-background: " + ThemeController.getSelectedSkin().getColor_background());

        this.titlePane.setPrefSize(this.mainPane.getPrefWidth(), this.mainPane.getPrefHeight() / 9);

        this.titleLabel.setPrefSize(this.mainPane.getPrefWidth(), this.mainPane.getPrefHeight() / 9);

        this.verticalBox.setPadding(new Insets(PADDING_SPACING_VBOX));
        this.verticalBox.setSpacing(PADDING_SPACING_VBOX);
        this.verticalBox.setPrefSize(this.mainPane.getPrefWidth() - 100, this.mainPane.getPrefWidth() / 3.2);
        this.verticalBox.relocate((this.mainPane.getPrefWidth() - this.verticalBox.getPrefWidth()) / 2,
                (this.mainPane.getPrefHeight() - this.verticalBox.getPrefHeight()) / 2);

        this.createPurchasePane();

    }

    public void loadSkins() {
        this.verticalBox.getChildren().clear();

        try {
            JSONArray a = JsonUtils.loadDataArray(JsonUtils.SKINS, JsonUtils.GAME_DATA_FILE);

            for (int i = 0; i < a.length(); i++) {
                ShopThemeItem temp = new ShopThemeItem(a.getJSONObject(i).getString("name"),
                        (Boolean) a.getJSONObject(i).get("purchased"),
                        this.mainPane.getPrefWidth());
                if (temp.getPurchased()) {
                    if (!temp.getSkin().name().equals(ThemeController.getSelectedSkin().name())) {
                        this.setListenerIfShopThemeItemPurchased(true, temp);
                        temp.getCostLabel().setText("PURCHASED BUT NOT SELECTED");
                    } else {
                        temp.getCostLabel().setText("PURCHASED AND SELECTED");
                    }
                } else {
                    temp.getCostLabel().setText(temp.getSkin().getCost().toString());
                    this.setListenerIfShopThemeItemPurchased(false, temp);
                }
                this.verticalBox.getChildren().add(temp);
                shopList.add(temp);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void switchToHomeView() {
        ViewSwitcher.getInstance().switchView(getStage(), ViewType.HOME);
    }

    public void createPurchasePane() {
        this.purchasePane.setStyle("-fx-border-width: 2; -fx-border-color: black; -fx-background-color: "
                + ThemeController.getSelectedSkin().getColor_background());
        this.purchasePane.relocate((this.mainPane.getPrefWidth() - this.purchasePane.getPrefWidth()) / 2,
                (this.mainPane.getPrefHeight() - this.purchasePane.getPrefHeight()) / 2);
    }

    public void setListenerIfShopThemeItemPurchased(Boolean isPurchased, ShopThemeItem shopThemeItem) {
        if (isPurchased) {
            shopThemeItem.setOnMouseClicked(e -> {
                this.questionLabel.setText("Do you want to set this skin?");

                this.buttonConfirm.setText("Set");
                this.buttonBack.setText("Back");

                this.buttonBack.setOnMouseClicked(b -> {
                    this.purchasePane.setVisible(false);
                });

                buttonConfirm.setOnMouseClicked(b -> {
                    ThemeController.setSelectedSkin(shopThemeItem.getSkin());
                    ThemeController.saveSelectedSkin();
                    this.purchasePane.setVisible(false);
                    ViewSwitcher.getInstance().switchView(getStage(), ViewType.SHOP);
                });
                this.purchasePane.setVisible(true);
            });
        } else {
            shopThemeItem.setOnMouseClicked(e -> {
                this.questionLabel
                        .setText("Do you want to buy this item for " + shopThemeItem.getSkin().getCost() + " coins?");
                this.questionLabel.setPrefSize(this.purchasePane.getPrefWidth(), 80);

                this.labelAlert.setStyle("-fx-text-fill: red");

                this.buttonConfirm.setText("Buy");
                this.buttonBack.setText("Back");

                this.purchasePane.setVisible(true);

                this.buttonBack.setOnMouseClicked(b -> {
                    this.labelAlert.setVisible(false);
                    this.purchasePane.setVisible(false);
                });

                this.buttonConfirm.setOnMouseClicked(b -> {
                    Integer coinAmount;
                    try {
                        coinAmount = (Integer) JsonUtils.loadData(JsonUtils.COINS, JsonUtils.GAME_DATA_FILE);
                        if (coinAmount >= shopThemeItem.getSkin().getCost()) {
                            coinAmount = coinAmount - shopThemeItem.getSkin().getCost();
                            shopThemeItem.getSkin().setPurchased(true);
                            JsonUtils.addElement(new Pair<String, Object>(JsonUtils.COINS, coinAmount),
                                    JsonUtils.GAME_DATA_FILE);
                            ThemeController.saveSkins();
                            this.loadSkins();
                            this.purchasePane.setVisible(false);
                        } else {
                            this.labelAlert.setVisible(true);
                        }
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }

                });
            });
        }
    }
}
