package tenten.items;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import tenten.model.items.GameGrid;
import tenten.model.items.GridBlock;

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
     * Method that test the function getElement.
     */
    @Test
    void testGetElement() {
        Assertions.assertEquals(2, gg.getElement(2, 2).getGridX());
        Assertions.assertEquals(2, gg.getElement(2, 2).getGridY());
    }
}
