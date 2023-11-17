package oop23_1010;

import oop23_1010.utils.JsonUtils;
import java.io.IOException;

public class App {
    public static void main(String[] args) throws IOException{
        // Application.launch(Startup.class, args);
        System.out.println(JsonUtils.loadData(JsonUtils.MATCH_ON_GOING));
    }
}
