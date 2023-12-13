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
class JsonUtilsTest {

    private static final String TEST = "test";
    private static final String ARRAY = "array";
    private final Pair<String, Object> pair = new Pair<>(TEST, "value");
    private final JSONArray blocksArray = new JSONArray();

    @BeforeEach
    void setup() throws IOException {
        blocksArray.put("test1");
        blocksArray.put("test2");
        blocksArray.put("test3");
        blocksArray.put("test4");

        JsonUtils.addElement(new Pair<String, Object>(ARRAY, blocksArray), TEST);

        JsonUtils.addElement(pair, TEST);
    }

    @Test
    void testAddElement() throws IOException {
        Assertions.assertEquals(pair.getValue(), JsonUtils.loadData(TEST, TEST));
    }

    @Test
    void testIfDataExist() throws IOException {
        Assertions.assertTrue(JsonUtils.ifDataExist(pair.getKey(), TEST));
    }

    @Test
    void testJsonExist() throws IOException {
        Assertions.assertTrue(JsonUtils.jsonExist(TEST));
    }

    @Test
    void testLoadData() throws IOException {
        Assertions.assertEquals(JsonUtils.loadData(TEST, TEST), pair.getValue());
    }

    @Test
    void testLoadDataArray() throws IOException {
        for (int x = 0; x < JsonUtils.loadDataArray(ARRAY, TEST).length(); x++) {
            Assertions.assertEquals(JsonUtils.loadDataArray(ARRAY, TEST).get(x), blocksArray.get(x));
        }
    }

    @Test
    void testRemoveElement() throws IOException {
        final Integer beforeLength = JsonUtils.loadDatas(TEST).length();
        JsonUtils.removeElement(ARRAY, TEST);
        final Integer afterLength = JsonUtils.loadDatas(TEST).length();

        Assertions.assertTrue(beforeLength > afterLength && beforeLength > 0);
    }

    @Test
    void testLoadDatas() throws IOException {
        Assertions.assertEquals(JsonUtils.loadDatas(TEST).get(TEST), pair.getValue());
        for (int x = 0; x < JsonUtils.loadDataArray(ARRAY, TEST).length(); x++) {
            Assertions.assertEquals(JsonUtils.loadDataArray(ARRAY, TEST).get(x), blocksArray.get(x));
        }
    }

    @AfterEach
    @Test
    void testFlushJson() throws IOException {
        final Path path = Paths.get(JsonUtils.DATA_PATH + "test.json");
        JsonUtils.flushJson(TEST);
        Assertions.assertFalse(Files.exists(path));
    }
}
