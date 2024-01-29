package tenten.common.utils;

import tenten.model.types.BlockType;

/**
 * Class with random utility methods.
 */
public final class RandomUtils {

    /**
     * Deafult constructor.
     */
    private RandomUtils() {
    }

    /**
     * Method randomize a new item to be generated.
     * 
     * @return Block type.
     */
    public static BlockType getRandomPuzzle() {
        int index = 0;
        double temp = 0.0;

        while (temp == 0.0){
            temp = Math.floor(Math.random() * (BlockType.values().length));
        }

        for (final BlockType block : BlockType.values()) {
            if (index == (int)temp) {
                return block;
            }
            index++;
        }
        return null;
    }
}
