package tenten.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import tenten.types.BlockType;

/**
 * Class that test the java class RandomUtils.
 */
public class RandomUtilsTest {

    /**
     * Method that test the correct behaviour of the function getRandomPuzzle.
     */
    @Test
    public void testGetRandomPuzzle() {
        BlockType returnType = null;

        returnType = RandomUtils.getRandomPuzzle();

        Assertions.assertNotNull(returnType);
    }
}
