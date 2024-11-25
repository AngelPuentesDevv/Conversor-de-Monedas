package util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;

public class ArchiveGenerator {

    public static void saveJson(String json) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        FileWriter escritura = new FileWriter("monedas_convertidas.json");
        escritura.write(gson.toJson(json));
        escritura.close();
    }
}
