package oop23_1010.utils;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import oop23_1010.types.SkinType;

public class ShopSkinItem extends VBox {

    private SkinType skin;

    private Integer pos;

    private Boolean purchased;

    private Pane descriptionPane;

    private Label descriptionLabel;

    private Pane costPane;

    private Label costLabel;

    public ShopSkinItem(SkinType skin, Integer pos) {

        this.skin = skin;

        this.descriptionPane = new Pane();
        this.costPane = new Pane();
        this.costLabel = new Label();
        this.descriptionLabel = new Label(this.skin.getDescription());

        this.pos = pos;
        if (skin == SkinType.LIGHT) {
            this.purchased = true;
        } else {
            this.purchased = false;
            this.descriptionLabel.setTextFill(Paint.valueOf("white"));
            this.costLabel.setTextFill(Paint.valueOf("white"));
        }

        this.setStyle(
                "-fx-background-color: " + this.skin.getColor() + "; -fx-border-width: 2; -fx-border-color: yellow");
        this.setPrefHeight(150);

        this.descriptionPane.getChildren().add(descriptionLabel);
        this.costPane.getChildren().add(costLabel);
        this.getChildren().addAll(this.descriptionPane, this.costPane);

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
