

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;




public class Connectioninfo {
    String url;
    String driver;
    String user;
    String password;

    public Connectioninfo() throws NullPointerException {

        try {
            Reader reader = Files.newBufferedReader(Paths.get(System.getenv("EPISEN_DATABASE_CONF")));
            ObjectMapper om = new ObjectMapper(new YAMLFactory());
            DatabaseConfig dc = om.readValue(reader, DatabaseConfig.class);
            this.url = dc.getUrl();
            this.driver = dc.getDriver();
            this.user = dc.getUser();
            this.password= dc.getPassword();
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

