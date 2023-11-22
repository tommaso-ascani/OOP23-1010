package oop23_1010.utils;

import javafx.scene.layout.AnchorPane;

public class GridBlock extends AnchorPane {

    private Integer x;
    private Integer y;

    private Boolean isFilled;

    // Constuctor

    public GridBlock(Integer x, Integer y, Boolean isFilled){
        this.x = x;
        this.y = y;
        this.isFilled = isFilled;
    }

    // Setter

    public void setFill(Boolean state) {
        this.isFilled = state;
    }

    // Getter

    public Integer getGridX() {
        return this.x;
    }

    public Integer getGridY() {
        return this.y;
    }

    public Boolean getFill() {
        return this.isFilled;
    }
}
