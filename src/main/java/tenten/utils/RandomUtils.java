package tenten.utils;

import tenten.types.BlockType;

/**
 * Class with random utility methods.
 */
public final class RandomUtils {

    /**
     * Deafult constructor.
     */
    private RandomUtils() { }

    /**
     * Method randomize a new item to be generated.
     * 
     * @return Block type.
     */
    public static BlockType getRandomPuzzle() {
        Integer index = 0;

        Double temp = Math.floor(Math.random() * (BlockType.values().length - 1));
        Integer rand = temp.intValue();

        for (BlockType block : BlockType.values()) {
            if (index == rand) {
                return block;
            }
            index++;
        }
        return null;
    }
}
