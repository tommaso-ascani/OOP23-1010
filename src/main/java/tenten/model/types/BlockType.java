package tenten.model.types;

import tenten.common.utils.ThemeUtils;

/**
 * class used to hold all types of blocks.
 */
public enum BlockType {

    /**
     * Block 1x1.
     */
    BLOCK_1_1(1,1,false, ThemeUtils.getSelectedTheme().getColor("color1x1")),
    /**
     * Block 2x2.
     */
    BLOCK_2_2(2,2,false, ThemeUtils.getSelectedTheme().getColor("color2x2")),
    /**
     * Block 3x3.
     */
    BLOCK_3_3(3,3,false, ThemeUtils.getSelectedTheme().getColor("color3x3")),
    /**
     * Block 2x1.
     */
    BLOCK_2_1(2,1,false, ThemeUtils.getSelectedTheme().getColor("color2x1")),
    /**
     * Block 3x1.
     */
    BLOCK_3_1(3,1,false, ThemeUtils.getSelectedTheme().getColor("color3x1")),
    /**
     * Block 4x1.
     */
    BLOCK_4_1(4,1,false, ThemeUtils.getSelectedTheme().getColor("color4x1")),
    /**
     * Block 5x1.
     */
    BLOCK_5_1(5,1,false, ThemeUtils.getSelectedTheme().getColor("color5x1")),
    /**
     * Block L 2x2.
     */
    BLOCK_L_2_2(2,2,true, ThemeUtils.getSelectedTheme().getColor("color_L2x2")),
    /**
     * Block L 3x3.
     */
    BLOCK_L_3_3(3,3,true, ThemeUtils.getSelectedTheme().getColor("color_L3x3")),
    /**
     * Block L 3x3.
     */
    BLOCK_L_3_2(3,2,true, ThemeUtils.getSelectedTheme().getColor("color_L2x2")),
    /**
     * Block L 3x3.
     */
    BLOCK_L_2_3(2,3,true, ThemeUtils.getSelectedTheme().getColor("color_L2x2"));

    private int width;
    private int height;
    private boolean l_type;
    private String color;

    BlockType(final int width, final int height, final boolean l_type, final String color){
        this.width = width;
        this.height = height;
        this.l_type = l_type;
        this.color = color;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public boolean getL_type() {
        return l_type;
    }

    public String getColor() {
        return color;
    }
}