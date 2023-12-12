package tenten.Items;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class GameGridTest {

    private static GameGrid<GridBlock> gg;

    @BeforeAll
    public static void setup() {
        gg = new GameGrid<>(5);
        for (int RowIndex = 0; RowIndex < gg.getGridSize(); RowIndex++) {
            for (int ColumnIndex = 0; ColumnIndex < gg.getGridSize(); ColumnIndex++) {
                GridBlock aPane = new GridBlock(ColumnIndex, RowIndex, null,
                        null);
                gg.add(aPane);
            }
        }
    }

    @Test
    public void testGetUpBlock() {
        GridBlock gb1 = new GridBlock(2, 2, null, null);
        GridBlock gb2 = new GridBlock(2, 1, null, null);

        Assertions.assertEquals(gb2.getGridX(), gg.getUpBlock(gb1, 1).getGridX());
        Assertions.assertEquals(gb2.getGridY(), gg.getUpBlock(gb1, 1).getGridY());
    }

    @Test
    public void testGetDownBlock() {
        GridBlock gb1 = new GridBlock(2, 2, null, null);
        GridBlock gb2 = new GridBlock(2, 1, null, null);

        Assertions.assertEquals(gb1.getGridX(), gg.getDownBlock(gb2, 1).getGridX());
        Assertions.assertEquals(gb1.getGridY(), gg.getDownBlock(gb2, 1).getGridY());
    }

    @Test
    public void testGetLeftBlock() {
        GridBlock gb1 = new GridBlock(2, 2, null, null);
        GridBlock gb2 = new GridBlock(1, 2, null, null);

        Assertions.assertEquals(gb2.getGridX(), gg.getLeftBlock(gb1, 1).getGridX());
        Assertions.assertEquals(gb2.getGridY(), gg.getLeftBlock(gb1, 1).getGridY());
    }

    @Test
    public void testGetRightBlock() {
        GridBlock gb1 = new GridBlock(2, 2, null, null);
        GridBlock gb2 = new GridBlock(3, 2, null, null);

        Assertions.assertEquals(gb2.getGridX(), gg.getRightBlock(gb1, 1).getGridX());
        Assertions.assertEquals(gb2.getGridY(), gg.getRightBlock(gb1, 1).getGridY());
    }

    @Test
    public void testGetElement() {

        Assertions.assertEquals(2, gg.getElement(2, 2).getGridX());
        Assertions.assertEquals(2, gg.getElement(2, 2).getGridY());
    }
}