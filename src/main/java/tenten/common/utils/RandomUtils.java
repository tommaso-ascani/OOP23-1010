package tenten.common.utils;

import tenten.model.types.BlockType;
import java.util.Random;

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

        int blockIndex = new Random().nextInt(BlockType.values().length);

        for (final BlockType block : BlockType.values()) {
            if (index == blockIndex) {
                return block;
            }
            index++;
        }
        return null;
    }

    public static double getRandomRotate(){
        double[] degrees = {0.0, 90.0, 180.0, 270.0};
        int random = new Random().nextInt(degrees.length);
        return degrees[random];
    }
}
