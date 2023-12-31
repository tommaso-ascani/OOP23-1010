package tenten;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javafx.scene.image.Image;

/**
 * Class that used to test the java class Startup.
 */
class StartupTest {

    /**
     * Method that test if start is setting correctly the icon image.
     */
    @Test
    void testStart() {
        Assertions.assertNotNull(new Image(ClassLoader.getSystemResourceAsStream("img/icon.jpg")));
    }
}
