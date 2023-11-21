package oop23_1010.utils;

import javafx.scene.shape.Path;

public class Block extends Path {

    private BlockType type;

    public Block(BlockType type) {
        super();
        this.type = type;
    }

    public BlockType getBlockType() {
        return this.type;
    }
}
