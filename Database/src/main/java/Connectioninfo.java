import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Connectioninfo {
    final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    String url;
    String driver;
    String user;
    String password;

    public Connectioninfo() throws NullPointerException {

        try {
            Reader reader = Files.newBufferedReader(Paths.get("Database\\src\\main\\resources\\databaseConnection.json"));
            HashMap<String, String> map = gson.fromJson(reader, HashMap.class);

            this.url = map.get("url");
            this.driver = map.get("driver");
            this.user = map.get("user");
            this.password= map.get("mdp");
            reader.close();
        } catch (IOException e) {

            e.getMessage();

        }
    }

    public String getUrl() {
        return url;
    }

    public String getDriver() {
        return driver;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }
}

