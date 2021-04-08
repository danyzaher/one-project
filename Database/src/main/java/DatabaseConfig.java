
public class DatabaseConfig {
    private String driver;
    private String url;
    private String user;
    private String password;

    public String getDriver() {
        return driver;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public String toString() {
        return "DatabaseConfig{" +
                "driver='" + driver + '\'' +
                ", url='" + url + '\'' +
                ", user='" + user + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
