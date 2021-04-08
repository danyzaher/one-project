public class ServerConfig {
    private int port;
    private int nboneco;

    public int getPort() {
        return port;
    }

    public int getNboneco() {
        return nboneco;
    }

    @Override
    public String toString() {
        return "SocketConfig{" +
                "port=" + port +
                ", nboneco=" + nboneco +
                '}';
    }
}
