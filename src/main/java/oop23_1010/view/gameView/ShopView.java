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
    private Button buttonBack;

    @FXML
    private Button buttonPurchase;

    @FXML
    private Pane titlePane, purchasePane;

    @FXML
    private Label titleLabel;

    @FXML
    private VBox verticalBox;

    private ArrayList<ShopThemeItem> shopList = new ArrayList<ShopThemeItem>();

    @Override
    public void init() {

        this.mainPane.setPrefSize(View.WINDOW_WIDTH, View.WINDOW_HEIGHT);

        this.loadSkins();

        this.mainPane.setStyle("-fx-background: " + ThemeController.getSelectedSkin().getColor_background());

        this.titlePane.setPrefSize(this.mainPane.getPrefWidth(), this.mainPane.getPrefHeight() / 9);

        this.titleLabel.setText("SHOP");
        this.titleLabel.setFont(new Font(30));
        this.titleLabel.setPrefSize(this.mainPane.getPrefWidth(), this.mainPane.getPrefHeight() / 9);
        this.titleLabel.setAlignment(Pos.BASELINE_CENTER);

        this.verticalBox.setPadding(new Insets(10));
        this.verticalBox.setSpacing(10);
        this.verticalBox.setPrefSize(this.mainPane.getPrefWidth() - 100, 500);
        this.verticalBox.relocate((this.mainPane.getPrefWidth() - this.verticalBox.getPrefWidth()) / 2,
                (this.mainPane.getPrefHeight() - this.verticalBox.getPrefHeight()) / 2);

        this.createPurchasePane();
    }

    public void loadSkins() {
        this.verticalBox.getChildren().clear();
        JSONArray a = null;

        try {
            a = JsonUtils.loadDataArray(JsonUtils.SKINS, JsonUtils.GAME_DATA_FILE);

            for (int i = 0; i < a.length(); i++) {
                ShopThemeItem temp = new ShopThemeItem(a.getJSONObject(i).getString("name"),
                        i + 1,
                        (Boolean) a.getJSONObject(i).get("purchased"),
                        this.mainPane.getPrefWidth());
                if (temp.getPurchased()) {

                    String selectedSkin = ThemeController.getSelectedSkin().name();
                    if (!temp.getSkin().name().equals(selectedSkin)) {
                        temp.setOnMouseClicked(e -> {
                            Label labelSetSKin = new Label(
                                    "Do you want to set this skin?");
                            labelSetSKin.setPrefSize(this.purchasePane.getPrefWidth(), 80);
                            labelSetSKin.setAlignment(Pos.BASELINE_CENTER);
                            labelSetSKin.setFont(new Font(20));

                            Button buttonSetSkin = new Button();
                            Button buttonBack = new Button();
                            buttonSetSkin.setText("Set");
                            buttonBack.setText("Back");
                            buttonSetSkin.setPrefSize(80, 30);
                            buttonBack.setPrefSize(80, 30);
                            buttonSetSkin.relocate(
                                    (this.purchasePane.getPrefWidth() - buttonBack.getPrefWidth()
                                            - buttonSetSkin.getPrefWidth()) / 3,
                                    this.purchasePane.getPrefHeight() / 1.5);
                            buttonBack.relocate(
                                    this.purchasePane.getPrefWidth() - buttonBack.getPrefWidth()
                                            - ((this.purchasePane.getPrefWidth() - buttonBack.getPrefWidth()
                                                    - buttonSetSkin.getPrefWidth()) / 3),
                                    this.purchasePane.getPrefHeight() / 1.5);

                            buttonBack.setOnMouseClicked(b -> {
                                this.purchasePane.setVisible(false);
                            });

                            buttonSetSkin.setOnMouseClicked(b -> {
                                ThemeController.setSelectedSkin(temp.getSkin());
                                ThemeController.saveSelectedSkin();
                                this.purchasePane.setVisible(false);
                                this.purchasePane.getChildren().clear();
                                ViewSwitcher.getInstance().switchView(getStage(), ViewType.SHOP);
                            });
                            this.purchasePane.getChildren().addAll(labelSetSKin, buttonBack, buttonSetSkin);
                            this.purchasePane.setVisible(true);
                        });
                        temp.getCostLabel().setText("PURCHASED BUT NOT SELECTED");
                    } else {
                        temp.getCostLabel().setText("PURCHASED AND SELECTED");
                    }
                } else {
                    temp.getCostLabel().setText(temp.getSkin().getCost().toString());
                    temp.setOnMouseClicked(e -> {
                        Label labelBuy = new Label(
                                "Do you want to buy this item for " + temp.getSkin().getCost() + " coins?");
                        labelBuy.setPrefSize(this.purchasePane.getPrefWidth(), 80);
                        labelBuy.setAlignment(Pos.BASELINE_CENTER);
                        labelBuy.setFont(new Font(20));

                        Label labelAlert = new Label("YOU CANNOT AFFORD THIS ITEM!");
                        labelAlert.setPrefSize(this.purchasePane.getPrefWidth(), 80);
                        labelAlert.setAlignment(Pos.BASELINE_CENTER);
                        labelAlert.setFont(new Font(20));
                        labelAlert.setStyle("-fx-text-fill: red");
                        labelAlert.relocate(0, this.purchasePane.getPrefHeight() / 3);
                        labelAlert.setVisible(false);

                        Button buttonPurchase = new Button();
                        Button buttonBack = new Button();
                        buttonPurchase.setText("Buy");
                        buttonBack.setText("Back");
                        buttonPurchase.setPrefSize(80, 30);
                        buttonBack.setPrefSize(80, 30);
                        buttonPurchase.relocate(
                                (this.purchasePane.getPrefWidth() - buttonBack.getPrefWidth()
                                        - buttonPurchase.getPrefWidth()) / 3,
                                this.purchasePane.getPrefHeight() / 1.5);
                        buttonBack.relocate(
                                this.purchasePane.getPrefWidth() - buttonBack.getPrefWidth()
                                        - ((this.purchasePane.getPrefWidth() - buttonBack.getPrefWidth()
                                                - buttonPurchase.getPrefWidth()) / 3),
                                this.purchasePane.getPrefHeight() / 1.5);
                        this.purchasePane.getChildren().addAll(labelBuy, labelAlert, buttonBack, buttonPurchase);
                        this.purchasePane.setVisible(true);

                        buttonBack.setOnMouseClicked(b -> {
                            labelAlert.setVisible(false);
                            this.purchasePane.setVisible(false);
                        });

                        buttonPurchase.setOnMouseClicked(b -> {
                            Integer coinAmount;
                            try {
                                coinAmount = (Integer) JsonUtils.loadData(JsonUtils.COINS, JsonUtils.GAME_DATA_FILE);
                                if (coinAmount >= temp.getSkin().getCost()) {
                                    coinAmount = coinAmount - temp.getSkin().getCost();
                                    temp.getSkin().setPurchased(true);
                                    JsonUtils.addElement(new Pair<String, Object>(JsonUtils.COINS, coinAmount),
                                            JsonUtils.GAME_DATA_FILE);
                                    ThemeController.saveSkins();
                                    this.loadSkins();
                                    this.purchasePane.setVisible(false);
                                } else {
                                    labelAlert.setVisible(true);
                                }
                                labelBuy.setVisible(false);
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }

                        });
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
        this.verticalBox.setPrefSize(this.mainPane.getPrefWidth() - 100, 500);
        this.verticalBox.relocate((this.mainPane.getPrefWidth() - this.verticalBox.getPrefWidth()) / 2,
                (this.mainPane.getPrefHeight() - this.verticalBox.getPrefHeight()) / 2);

    }

    public void switchToHomeView() {
        ViewSwitcher.getInstance().switchView(getStage(), ViewType.HOME);
    }

    public void createPurchasePane() {
        this.purchasePane.setVisible(false);
        this.purchasePane.setPrefSize(this.mainPane.getPrefWidth() / 4, this.mainPane.getPrefHeight() / 4);
        this.purchasePane.setStyle("-fx-border-width: 2; -fx-border-color: black; -fx-background-color: "
                + ThemeController.getSelectedSkin().getColor_background());
        this.purchasePane.relocate((this.mainPane.getPrefWidth() - this.purchasePane.getPrefWidth()) / 2,
                (this.mainPane.getPrefHeight() - this.purchasePane.getPrefHeight()) / 2);
    }
}
