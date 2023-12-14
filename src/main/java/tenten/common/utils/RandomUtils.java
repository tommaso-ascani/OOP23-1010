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
        Integer index = 0;

        final Double temp = Math.floor(Math.random() * (BlockType.values().length - 1));
        final Integer rand = temp.intValue();

        for (final BlockType block : BlockType.values()) {
            if (index.equals(rand)) {
                return block;
            }
            index++;
        }
        return null;
    }
}
