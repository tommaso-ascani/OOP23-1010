package tenten.items;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import tenten.types.ThemeType;
import tenten.view.View;

/**
 * Class that extend VBox, used to represent a theme item in the shop.
 */
public final class ShopTheme extends VBox {

    private ThemeType theme;

    private Boolean purchased;

    private final Label costLabel;

    private static final Integer HEIGHT = 200;

    private static final Integer VIEW_PANE_HEIGHT = 120;

    private static final Integer SPACING_HBOX = 5;

    private static final Integer FONT_SIZE_LABEL = 20;

    private static final Integer NUMBER_OF_BLOCK_TYPE_COLOR = 9;

    private static final Integer WIDTH_PANE_COLORS = 155;

    private static final String STATIC_STYLE_ATTRIBUTES = "-fx-border-color: black; -fx-background-color: ";

    private static final Integer PANE_NUMBER_1 = 1;
    private static final Integer PANE_NUMBER_2 = 2;
    private static final Integer PANE_NUMBER_3 = 3;
    private static final Integer PANE_NUMBER_4 = 4;
    private static final Integer PANE_NUMBER_5 = 5;
    private static final Integer PANE_NUMBER_6 = 6;
    private static final Integer PANE_NUMBER_7 = 7;
    private static final Integer PANE_NUMBER_8 = 8;
    private static final Integer PANE_NUMBER_9 = 9;

    /**
     * Initialize new ShopThemeItem object.
     * 
     * @param theme name
     * @param purchased condition
     */
    public ShopTheme(final String theme, final Boolean purchased) {

        for (final ThemeType themeType : ThemeType.values()) {
            if (themeType.name().equals(theme)) {
                this.theme = themeType;
            }
        }
        this.setPrefHeight(ShopTheme.HEIGHT);
        this.setFillWidth(true);

        final HBox viewPane = new HBox();
        viewPane.setPrefSize(View.WINDOW_WIDTH - ShopTheme.HEIGHT, ShopTheme.VIEW_PANE_HEIGHT);
        viewPane.setSpacing(ShopTheme.SPACING_HBOX);
        viewPane.setAlignment(Pos.CENTER);
        viewPane.setStyle("-fx-padding: 15");

        this.costLabel = new Label();
        this.costLabel.setFont(new Font(ShopTheme.FONT_SIZE_LABEL));
        this.costLabel.setPrefSize(View.WINDOW_WIDTH, ShopTheme.VIEW_PANE_HEIGHT / 3.0);
        this.costLabel.setAlignment(Pos.CENTER);
        this.costLabel.setStyle("-fx-padding: 4,4,4,4");

        final Label descriptionLabel = new Label(this.theme.getName());
        descriptionLabel.setPrefSize(View.WINDOW_WIDTH, ShopTheme.VIEW_PANE_HEIGHT / 3.0);
        descriptionLabel.setFont(new Font(ShopTheme.FONT_SIZE_LABEL));
        descriptionLabel.setAlignment(Pos.CENTER);

        for (Integer i = 1; i <= ShopTheme.NUMBER_OF_BLOCK_TYPE_COLOR; i++) {
            final Pane pane = new Pane();
            if (i.equals(ShopTheme.PANE_NUMBER_1)) {
                pane.setStyle(
                        ShopTheme.STATIC_STYLE_ATTRIBUTES + this.theme.getColorBackground());
            } else if (i.equals(ShopTheme.PANE_NUMBER_2)) {
                pane.setStyle(ShopTheme.STATIC_STYLE_ATTRIBUTES + this.theme.getColorGrid());
            } else if (i.equals(ShopTheme.PANE_NUMBER_3)) {
                pane.setStyle(ShopTheme.STATIC_STYLE_ATTRIBUTES + this.theme.getColor1x1());
            } else if (i.equals(ShopTheme.PANE_NUMBER_4)) {
                pane.setStyle(ShopTheme.STATIC_STYLE_ATTRIBUTES + this.theme.getColor2x2());
            } else if (i.equals(ShopTheme.PANE_NUMBER_5)) {
                pane.setStyle(ShopTheme.STATIC_STYLE_ATTRIBUTES + this.theme.getColor3x3());
            } else if (i.equals(ShopTheme.PANE_NUMBER_6)) {
                pane.setStyle(
                        ShopTheme.STATIC_STYLE_ATTRIBUTES + this.theme.getColor2x1or1x2());
            } else if (i.equals(ShopTheme.PANE_NUMBER_7)) {
                pane.setStyle(
                        ShopTheme.STATIC_STYLE_ATTRIBUTES + this.theme.getColor3x1or1x3());
            } else if (i.equals(ShopTheme.PANE_NUMBER_8)) {
                pane.setStyle(
                        ShopTheme.STATIC_STYLE_ATTRIBUTES + this.theme.getColor4x1or1x4());
            } else if (i.equals(ShopTheme.PANE_NUMBER_9)) {
                pane.setStyle(
                        ShopTheme.STATIC_STYLE_ATTRIBUTES + this.theme.getColor5x1or1x5());
            }
            pane.setPrefWidth(ShopTheme.WIDTH_PANE_COLORS);
            viewPane.getChildren().add(pane);
        }
        this.purchased = purchased;

        this.setStyle("-fx-border-width: 1; -fx-border-color: black; -fx-border-radius: 10 10 10 10;");

        this.getChildren().addAll(descriptionLabel, viewPane, this.costLabel);

    }

    /**
     * Method to get if the item is purchased.
     * 
     * @return If purchased.
     */
    public Boolean getPurchased() {
        return purchased;
    }

    /**
     * Method to set if the is purchased.
     * 
     * @param purchased condition
     */
    public void setPurchased(final Boolean purchased) {
        this.purchased = purchased;
    }

    /**
     * Method to set the cost label text.
     * 
     * @param text of cost
     */
    public void setCostLabeltext(final String text) {
        this.costLabel.setText(text);
    }

    /**
     * Method to get the theme.
     * 
     * @return Theme.
     */
    public ThemeType getTheme() {
        return theme;
    }

    /**
     * Method to set the theme.
     * 
     * @param theme ThemeType
     */
    public void setTheme(final ThemeType theme) {
        this.theme = theme;
    }
}
