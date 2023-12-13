package tenten.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import tenten.types.BlockType;

/**
 * Class that test the java class RandomUtils.
 */
class RandomUtilsTest {

    /**
     * Method that test the correct behaviour of the function getRandomPuzzle.
     */
    @Test
    void testGetRandomPuzzle() {
        final BlockType returnType = RandomUtils.getRandomPuzzle();
        Assertions.assertNotNull(returnType);
    }
}
