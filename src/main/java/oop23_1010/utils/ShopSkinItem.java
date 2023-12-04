package oop23_1010.utils;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import oop23_1010.types.SkinType;

public class ShopSkinItem extends VBox {

    private SkinType skin;

    private Integer pos;

    private Boolean purchased;

    private Pane descriptionPane;

    private Label descriptionLabel;

    private HBox viewPane;

    private Pane costPane;

    private Label costLabel;

    public ShopSkinItem(String skin, Integer pos, Boolean purchased, Double windowWidth) {

        for (SkinType skinType : SkinType.values()) {
            if (skinType.name().equals(skin)) {
                this.skin = skinType;
            }
        }
        this.setPrefHeight(200);
        this.setFillWidth(true);

        this.descriptionPane = new Pane();
        this.costPane = new Pane();

        this.viewPane = new HBox();
        this.viewPane.setPrefSize(windowWidth - 200, 120);
        this.viewPane.setSpacing(5);
        this.viewPane.setStyle("-fx-padding: 15");

        this.costLabel = new Label();
        this.costLabel.setFont(new Font(20));
        this.costLabel.setPrefSize(windowWidth - 200, 40);
        this.costLabel.setAlignment(Pos.CENTER_LEFT);
        this.costLabel.setStyle("-fx-padding: 4,4,4,4");

        this.descriptionLabel = new Label(this.skin.getDescription());
        this.descriptionLabel.setPrefSize(windowWidth - 200, 40);
        this.descriptionLabel.setFont(new Font(25));
        this.descriptionLabel.setAlignment(Pos.BASELINE_CENTER);
        this.descriptionLabel.setStyle("-fx-padding: 5");

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
        this.pos = pos;
        this.purchased = purchased;

        this.setStyle(
                "-fx-border-width: 2; -fx-border-color: yellow");

        this.descriptionPane.getChildren().add(descriptionLabel);
        this.costPane.getChildren().add(costLabel);
        this.getChildren().addAll(this.descriptionPane, this.viewPane, this.costPane);

    }

    public Boolean getPurchased() {
        return purchased;
    }

    public void setPurchased(Boolean purchased) {
        this.purchased = purchased;
    }

    public Integer getPos() {
        return pos;
    }

    public void setPos(Integer pos) {
        this.pos = pos;
    }

    public Label getCostLabel() {
        return costLabel;
    }

    public void setCostLabel(Label costLabel) {
        this.costLabel = costLabel;
    }

    public SkinType getSkin() {
        return skin;
    }

    public void setSkin(SkinType skin) {
        this.skin = skin;
    }
}
