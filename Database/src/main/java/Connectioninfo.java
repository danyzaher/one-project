import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Connectioninfo {

    String url;
    String driver;
    String user;
    String password;

    public Connectioninfo() {

        InputStream inStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("application.properties");
        Properties props = new Properties();

        try {

            props.load(inStream);

            this.url = props.getProperty("jdbc:postgresql://localhost:5432/testone");
            this.driver = props.getProperty("org.postgresql.Driver");
            this.user = props.getProperty("dany");
            this.password= props.getProperty("123");

        } catch (IOException e) {

            e.getMessage();

        } finally {

            try {

                inStream.close();

            } catch (IOException e) {
                e.getMessage();
            }
        }

    }

    public String getUrl() {
        return url; }

    public String getDriver() {
        return driver; }

    public String getUser() {
        return user; }

    public String getPassword() {
        return password; }
}

