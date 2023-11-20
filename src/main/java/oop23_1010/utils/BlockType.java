package oop23_1010.utils;

public enum BlockType {

    // Width x Height
    
    BLOCK_1x1("1x1"),
    BLOCK_1x2("1x2"),
    BLOCK_1x3("1x3"),
    BLOCK_1x4("1x4"),
    BLOCK_1x5("1x5"),
    BLOCK_2x1("2x1"),
    BLOCK_2x2("2x2"),
    BLOCK_3x1("3x1"),
    BLOCK_3x3("3x3"),
    BLOCK_4x1("4x1"),
    BLOCK_5x1("5x1"),

    BLOCK_L_BOTTOM_LEFT_2x2("L_BOTTOM_LEFT_2x2"),
    BLOCK_L_TOP_LEFT_2x2("L_TOP_LEFT_2x2"),
    BLOCK_L_TOP_RIGHT_2x2("L_TOP_RIGHT_2x2"),
    BLOCK_L_BOTTOM_RIGHT_2x2("L_BOTTOM_RIGHT_2x2"),

    BLOCK_L_BOTTOM_LEFT_3X3("L_BOTTOM_LEFT_3X3"),
    BLOCK_L_TOP_LEFT_3X3("L_TOP_LEFT_3X3"),
    BLOCK_L_TOP_RIGHT_3X3("L_TOP_RIGHT_3X3"),
    BLOCK_L_BOTTOM_RIGHT_3X3("L_BOTTOM_RIGHT_3X3");

    private String block;

    BlockType(final String string){
        this.block = string;
    }

    public String getPath() {
        return this.block;
    }
}
