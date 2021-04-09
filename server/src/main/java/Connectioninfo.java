import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

public class Connectioninfo {
    final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    String url;
    String driver;
    String user;
    String password;

    public Connectioninfo() throws NullPointerException {

            try {
                Reader reader = Files.newBufferedReader(Paths.get(System.getenv("EPISEN_DATABASE_CONF")));
                ObjectMapper om = new ObjectMapper(new YAMLFactory());
                DatabaseConfig sc = om.readValue(reader, DatabaseConfig.class);

                this.url = sc.getUrl();
                this.driver = sc.getDriver();
                this.user = sc.getUser();
                this.password= sc.getPassword();
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

