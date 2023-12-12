package tenten.view;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ViewSwitcherTest {
    @Test
    public void testGetInstance() {
        Assertions.assertNotNull(ViewSwitcher.getInstance());
    }
}
