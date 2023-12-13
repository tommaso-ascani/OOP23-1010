package tenten.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.json.JSONArray;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import javafx.util.Pair;

/**
 * Class that test the main functions of tha java class JsonUtils.
 */
public final class JsonUtilsTest {

    private Pair<String, Object> pair = new Pair<String, Object>("test", "value");

    private JSONArray blocksArray = new JSONArray();

    @BeforeEach
    void setup() throws IOException {
        blocksArray.put("test1");
        blocksArray.put("test2");
        blocksArray.put("test3");
        blocksArray.put("test4");

        JsonUtils.addElement(new Pair<String, Object>("array", blocksArray), "test");

        JsonUtils.addElement(pair, "test");
    }

    @Test
    void testAddElement() throws IOException {
        Assertions.assertEquals(pair.getValue(), JsonUtils.loadData("test", "test"));
    }

    @Test
    void testIfDataExist() throws IOException {
        Assertions.assertTrue(JsonUtils.ifDataExist(pair.getKey(), "test"));
    }

    @Test
    void testJsonExist() throws IOException {
        Assertions.assertTrue(JsonUtils.jsonExist("test"));
    }

    @Test
    void testLoadData() throws IOException {
        Assertions.assertEquals(JsonUtils.loadData("test", "test"), pair.getValue());
    }

    @Test
    void testLoadDataArray() throws IOException {
        for (int x = 0; x < JsonUtils.loadDataArray("array", "test").length(); x++) {
            Assertions.assertEquals(JsonUtils.loadDataArray("array", "test").get(x), blocksArray.get(x));
        }
    }

    @Test
    void testRemoveElement() throws IOException {
        Integer beforeLength = JsonUtils.loadDatas("test").length();
        JsonUtils.removeElement("array", "test");
        Integer afterLength = JsonUtils.loadDatas("test").length();

        Assertions.assertTrue(beforeLength > afterLength && beforeLength > 0);
    }

    @Test
    void testLoadDatas() throws IOException {
        Assertions.assertEquals(JsonUtils.loadDatas("test").get("test"), pair.getValue());
        for (int x = 0; x < JsonUtils.loadDataArray("array", "test").length(); x++) {
            Assertions.assertEquals(JsonUtils.loadDataArray("array", "test").get(x), blocksArray.get(x));
        }
    }

    @AfterEach
    @Test
    void testFlushJson() throws IOException {
        Path path = Paths.get(JsonUtils.DATA_PATH + "test.json");
        JsonUtils.flushJson("test");
        Assertions.assertFalse(Files.exists(path));
    }
}
