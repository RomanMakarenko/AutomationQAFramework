package romm.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class JSONParser {
    public List<Map<String, String>> parseJsonFile(String fileName) {
        List<Map<String, String>> dataList = new ArrayList<>();
        try (FileReader fileReader = new FileReader(fileName)) {
            Gson gson = new Gson();
            Type type = new TypeToken<List<Map<String, String>>>() {}.getType();
            dataList = gson.fromJson(fileReader, type);
        } catch (IOException e) {
            System.out.println(e);
        }
        return dataList;
    }
}
