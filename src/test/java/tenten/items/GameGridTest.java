package tenten.items;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 * Class used to test the main method of the GameGrid class.
 */
class GameGridTest {

    private static GameGrid<GridBlock> gg;

    private static final Integer TEST_GRID_SIZE = 5;

    /**
     * Method that instantiate a GameGrid of GridBlocks.
     */
    @BeforeAll
    static void setup() {
        gg = new GameGrid<>(GameGridTest.TEST_GRID_SIZE);
        for (int rowIndex = 0; rowIndex < gg.getGridSize(); rowIndex++) {
            for (int columnIndex = 0; columnIndex < gg.getGridSize(); columnIndex++) {
                final GridBlock aPane = new GridBlock(columnIndex, rowIndex, null,
                        null);
                gg.add(aPane);
            }
        }
    }

    /**
     * Method that test the function getUpBlock.
     */
    @Test
    void testGetUpBlock() {
        final GridBlock gb1 = new GridBlock(2, 2, null, null);
        final GridBlock gb2 = new GridBlock(2, 1, null, null);

        Assertions.assertEquals(gb2.getGridX(), gg.getUpBlock(gb1, 1).getGridX());
        Assertions.assertEquals(gb2.getGridY(), gg.getUpBlock(gb1, 1).getGridY());
    }

    /**
     * Method that test the function getDownBlock.
     */
    @Test
    void testGetDownBlock() {
        final GridBlock gb1 = new GridBlock(2, 2, null, null);
        final GridBlock gb2 = new GridBlock(2, 1, null, null);

        Assertions.assertEquals(gb1.getGridX(), gg.getDownBlock(gb2, 1).getGridX());
        Assertions.assertEquals(gb1.getGridY(), gg.getDownBlock(gb2, 1).getGridY());
    }

    /**
     * Method that test the function getLeftBlock.
     */
    @Test
    void testGetLeftBlock() {
        final GridBlock gb1 = new GridBlock(2, 2, null, null);
        final GridBlock gb2 = new GridBlock(1, 2, null, null);

        Assertions.assertEquals(gb2.getGridX(), gg.getLeftBlock(gb1, 1).getGridX());
        Assertions.assertEquals(gb2.getGridY(), gg.getLeftBlock(gb1, 1).getGridY());
    }

    /**
     * Method that test the function getRightBlock.
     */
    @Test
    void testGetRightBlock() {
        final GridBlock gb1 = new GridBlock(2, 2, null, null);
        final GridBlock gb2 = new GridBlock(3, 2, null, null);

        Assertions.assertEquals(gb2.getGridX(), gg.getRightBlock(gb1, 1).getGridX());
        Assertions.assertEquals(gb2.getGridY(), gg.getRightBlock(gb1, 1).getGridY());
    }

    /**
     * Method that test the function getElement.
     */
    @Test
    void testGetElement() {
        Assertions.assertEquals(2, gg.getElement(2, 2).getGridX());
        Assertions.assertEquals(2, gg.getElement(2, 2).getGridY());
    }
}