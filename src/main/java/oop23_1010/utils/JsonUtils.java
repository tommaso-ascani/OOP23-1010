package oop23_1010.utils;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.json.JSONArray;
import org.json.JSONObject;

public class JsonUtils {

    private static final String DATA_PATH = "src/main/resources/config/";

    public static void loadData(){

    }

    public static void saveData(final String filePath) throws IOException{
        // TO-DO non crea la cartella config se non esiste
        Files.createDirectories(Paths.get(DATA_PATH));
        try (FileWriter file = new FileWriter(DATA_PATH + filePath + ".json")) {
            JSONObject json1 = new JSONObject();
            JSONObject json2 = new JSONObject();

            json1.put("prova1", "ciao1");
            json1.put("prova1", "ciao1");

            json2.put("salto", json2);

            JSONArray array = new JSONArray();
            array.put(json2);

            // file.write(array.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
