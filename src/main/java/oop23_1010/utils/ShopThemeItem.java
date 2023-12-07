package oop23_1010.utils;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import oop23_1010.types.ThemeType;
import oop23_1010.view.View;

public class ShopThemeItem extends VBox {

    private ThemeType skin;

    private Boolean purchased;

    private Label descriptionLabel;

    private HBox viewPane;

    private Label costLabel;

    private static final Integer HEIGHT = 200;

    private static final Integer VIEW_PANE_HEIGHT = 120;

    public ShopThemeItem(String skin, Boolean purchased, Double windowWidth) {

        for (ThemeType skinType : ThemeType.values()) {
            if (skinType.name().equals(skin)) {
                this.skin = skinType;
            }
        }
        this.setPrefHeight(ShopThemeItem.HEIGHT);
        this.setFillWidth(true);

        this.viewPane = new HBox();
        this.viewPane.setPrefSize(View.WINDOW_WIDTH - ShopThemeItem.HEIGHT, ShopThemeItem.VIEW_PANE_HEIGHT);
        this.viewPane.setSpacing(5);
        this.viewPane.setAlignment(Pos.CENTER);
        this.viewPane.setStyle("-fx-padding: 15");

        this.costLabel = new Label();
        this.costLabel.setFont(new Font(20));
        this.costLabel.setPrefSize(View.WINDOW_WIDTH, ShopThemeItem.VIEW_PANE_HEIGHT / 3);
        this.costLabel.setAlignment(Pos.CENTER);
        this.costLabel.setStyle("-fx-padding: 4,4,4,4");

        this.descriptionLabel = new Label(this.skin.getName());
        this.descriptionLabel.setPrefSize(View.WINDOW_WIDTH, ShopThemeItem.VIEW_PANE_HEIGHT / 3);
        this.descriptionLabel.setFont(new Font(25));
        this.descriptionLabel.setAlignment(Pos.CENTER);

        for (int i = 1; i <= 9; i++) {
            Pane pane = new Pane();
            switch (i) {
                case 1:
                    pane.setStyle(
                            "-fx-background-color: " + this.skin.getColor_background() + "; -fx-border-color: black");
                    break;
                case 2:
                    pane.setStyle("-fx-background-color: " + this.skin.getColor_grid() + "; -fx-border-color: black");
                    break;
                case 3:
                    pane.setStyle("-fx-background-color: " + this.skin.getColor_1x1() + "; -fx-border-color: black");
                    break;
                case 4:
                    pane.setStyle("-fx-background-color: " + this.skin.getColor_2x2() + "; -fx-border-color: black");
                    break;
                case 5:
                    pane.setStyle("-fx-background-color: " + this.skin.getColor_3x3() + "; -fx-border-color: black");
                    break;
                case 6:
                    pane.setStyle(
                            "-fx-background-color: " + this.skin.getColor_2x1_1x2() + "; -fx-border-color: black");
                    break;
                case 7:
                    pane.setStyle(
                            "-fx-background-color: " + this.skin.getColor_3x1_1x3() + "; -fx-border-color: black");
                    break;
                case 8:
                    pane.setStyle(
                            "-fx-background-color: " + this.skin.getColor_4x1_1x4() + "; -fx-border-color: black");
                    break;
                case 9:
                    pane.setStyle(
                            "-fx-background-color: " + this.skin.getColor_5x1_1x5() + "; -fx-border-color: black");
                    break;
                default:
                    break;
            }
            pane.setPrefWidth(this.viewPane.getPrefWidth() / 9);
            this.viewPane.getChildren().add(pane);
        }
        this.purchased = purchased;

        this.setStyle("-fx-border-width: 1; -fx-border-color: black; -fx-border-radius: 10 10 10 10;");

        this.getChildren().addAll(this.descriptionLabel, this.viewPane, this.costLabel);

    }

    public Boolean getPurchased() {
        return purchased;
    }

    public void setPurchased(Boolean purchased) {
        this.purchased = purchased;
    }

    public Label getCostLabel() {
        return costLabel;
    }

    public void setCostLabel(Label costLabel) {
        this.costLabel = costLabel;
    }

    public ThemeType getSkin() {
        return skin;
    }

    public void setSkin(ThemeType skin) {
        this.skin = skin;
    }
}
