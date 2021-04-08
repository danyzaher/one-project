import com.fasterxml.jackson.annotation.JsonGetter;

public class SocketConfig {
    private int port;
    private String host;

    public int getPort() {
        return port;
    }
    @JsonGetter("ip")
    public String getHost() {
        return host;
    }

    @Override
    public String toString() {
        return "SocketConfig{" +
                "port=" + port +
                ", host=" + host +
                '}';
    }
}
