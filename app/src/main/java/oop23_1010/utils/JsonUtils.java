package oop23_1010.utils;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import org.json.JSONObject;

public class JsonUtils {

    private static final String DATA_PATH = "config/";

    public static void loadData(){
        
    }

    public static <E> void saveData(final List<E> data, final String filePath) throws IOException{
        try(FileWriter file = new FileWriter(DATA_PATH + filePath)) {
            JSONObject json = new JSONObject();
            
        }
    }
}
