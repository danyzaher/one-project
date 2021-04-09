public class DatabaseConfig {
    private String user;
    private String password;
    private String driver;
    private String url;

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    public String getDriver() {
        return driver;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public String toString() {
        return "DatabaseConfig{" +
                "user='" + user + '\'' +
                ", password='" + password + '\'' +
                ", driver='" + driver + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}