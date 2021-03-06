import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Connectioninfo {

    String url;
    String driver;
    String user;
    String password;

    public Connectioninfo() throws NullPointerException {

        InputStream inStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("application.properties");
        Properties props = new Properties();

        try {

            props.load(inStream);

            this.url = props.getProperty("url");
            this.driver = props.getProperty("driver");
            this.user = props.getProperty("user");
            this.password= props.getProperty("mdp");

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

