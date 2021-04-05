import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

public class testSerialization {


    public static void main(String[] args) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String url;
        String driver;
        String user;
        String password;
        try {
            Reader reader = Files.newBufferedReader(Paths.get("Database\\src\\main\\resources\\databaseConnection.json"));
            HashMap<?, ?> map = gson.fromJson(reader, HashMap.class);

            url = (String) map.get("url");
            driver = (String) map.get("driver");
            user = (String) map.get("user");
            password= (String) map.get("mdp");
            reader.close();
            System.out.println(url + " - " + driver  +" - " + password +" - " + user);
        } catch (IOException e) {

            e.getMessage();

        }
    }
}
