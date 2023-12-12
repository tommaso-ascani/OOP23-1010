package oop23_1010.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import oop23_1010.types.BlockType;

public class RandomUtilsTest {

    @Test
    public void testGetRandomPuzzle() {
        BlockType returnType = null;

        returnType = RandomUtils.getRandomPuzzle();

        Assertions.assertNotNull(returnType);
    }
}
