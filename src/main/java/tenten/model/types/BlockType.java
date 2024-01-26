package tenten.model.types;

/**
 * class used to hold all types of blocks.
 */
public enum BlockType {

    /**
     * Block 1x1.
     */
    BLOCK_1_1(1,1),
    /**
     * Block 1x2.
     */
    BLOCK_1_2(1,2),
    /**
     * Block 1x3.
     */
    BLOCK_1_3(1,3),
    /**
     * Block 1x4.
     */
    BLOCK_1_4(1,4),
    /**
     * Block 1x5.
     */
    BLOCK_1_5(1,5),
    /**
     * Block 2x1.
     */
    BLOCK_2_1(2,1),
    /**
     * Block 2x2.
     */
    BLOCK_2_2(2,2),
    /**
     * Block 3x1.
     */
    BLOCK_3_1(3,1),
    /**
     * Block 3x3.
     */
    BLOCK_3_3(3,3),
    /**
     * Block 4x1.
     */
    BLOCK_4_1(4,1),
    /**
     * Block 5x1.
     */
    BLOCK_5_1(5,1);

    private int width;
    private int height;

    BlockType(final int width, final int height){
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}