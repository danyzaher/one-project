import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

public class testConnectionInfo {
    public static void main(String[] args) {
        try {
            Reader reader = Files.newBufferedReader(Paths.get(System.getenv("EPISEN_DATABASE_CONF")));
            System.out.println(System.getenv("EPISEN_DATABASE_CONF"));
            ObjectMapper om = new ObjectMapper(new YAMLFactory());
            DatabaseConfig sc = om.readValue(reader, DatabaseConfig.class);

            System.out.println(sc.getUrl());
            reader.close();
        } catch (IOException e) {

        }
    }
}
