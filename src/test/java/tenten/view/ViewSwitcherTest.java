package tenten.view;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Class that test the java class ViewSwitcher.
 */
class ViewSwitcherTest {

    /**
     * Method that verify the correct behavoiur of the function getInstance.
     */
    @Test
    void testGetInstance() {
        Assertions.assertNotNull(ViewSwitcher.getInstance());
    }
}
