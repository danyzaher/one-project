package Socket;

import com.fasterxml.jackson.annotation.JsonGetter;

public class SocketConfig {
    private int port;
    private String ip;

    public int getPort() {
        return port;
    }

    public String getIp() {
        return ip;
    }

    @Override
    public String toString() {
        return "Socket.SocketConfig{" +
                "port=" + port +
                ", ip=" + ip +
                '}';
    }
}
