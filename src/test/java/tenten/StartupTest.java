package tenten;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javafx.scene.image.Image;

public class StartupTest {
    @Test
    public void testStart() {
        Assertions.assertNotNull(new Image(ClassLoader.getSystemResourceAsStream("img/icon.jpg")));
    }
}
